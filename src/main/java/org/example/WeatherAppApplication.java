package org.example;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.example.WeatherController;

public class WeatherAppApplication extends Application<FlipCommerceConfigurations> {
    public static void main(String[] args) throws Exception {
        new WeatherAppApplication().run(args);
    }

    @Override
    public void run(FlipCommerceConfigurations configuration, Environment environment) {
        final WeatherController controller = new WeatherController();
        environment.jersey().register(controller);
    }

    @Override
    public void initialize(Bootstrap<FlipCommerceConfigurations> bootstrap) {

    }
}
