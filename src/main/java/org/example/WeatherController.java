package org.example;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Set;


@Path("/weather")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WeatherController {
    private Map<String, WeatherData> weatherDataStore = new HashMap<>();

    @POST
    @Path("/{city}")
    public Response createWeatherData(@PathParam("city") String city, WeatherData data) {
        if (data.getCity() == null || data.getTemperature() == null || data.getCondition() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("All fields (city, temperature, and condition) are required.").build();
        }
        weatherDataStore.put(city, data);
        return Response.ok(data).build();
//        return Response.status(Response.Status.CREATED).entity("Weather data created for " + city).build();
    }


    @GET
    @Path("/{city}")
    public Response getWeatherData(@PathParam("city") String city) {
        WeatherData data = weatherDataStore.get(city);
        if (data == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Weather data not found for " + city).build();
        }
        return Response.ok(data).build();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCityNames() {
        Set<String> cityNames = weatherDataStore.keySet();
        if (cityNames.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("No city names found").build();
        }
        CityNamesResponse response = new CityNamesResponse(cityNames);
        return Response.ok(response).build();
    }


    @PUT
    @Path("/{city}")
    public Response updateWeatherData(@PathParam("city") String city, WeatherData data) {

        if (!weatherDataStore.containsKey(city)) {
            return Response.status(Response.Status.NOT_FOUND).entity("Weather data not found for " + city).build();
        }

        WeatherData existingData = weatherDataStore.get(city);

        if (data.getCity() != null && !data.getCity().equals(existingData.getCity())) {
            return Response.status(Response.Status.BAD_REQUEST).entity("City cannot be changed").build();
        }

        if (data.getTemperature() != null) {
            existingData.setTemperature(data.getTemperature());
        }

        if (data.getCondition() != null) {
            existingData.setCondition(data.getCondition());
        }

        weatherDataStore.put(city, existingData);

        return Response.ok("Weather data updated for " + city).build();
    }



    @DELETE
    @Path("/{city}")
    public Response deleteWeatherData(@PathParam("city") String city) {
        if (weatherDataStore.remove(city) == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Weather data not found for " + city).build();
        }
        return Response.ok("Weather data deleted for " + city).build();
    }
}
