package it.matbell.ask.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import it.matbell.ask.logs.FileLogger;

public class OpenWeatherData implements Loggable {

    private List<WeatherField> weather;
    private MainInfo main;
    private WindField wind;
    private CloudsField clouds;
    private RainField rain;
    private SnowField snow;
    private SysField sys;

    @Override
    public String getDataToLog() {

        return  (weather != null ? String.valueOf(weather.get(0).id) : 0) + FileLogger.SEP +
                (main != null ? String.valueOf(main.temp) : 0) + FileLogger.SEP +
                (main != null ? String.valueOf(main.temp_min) : 0) + FileLogger.SEP +
                (main != null ? String.valueOf(main.temp_max) : 0) + FileLogger.SEP +
                (main != null ? String.valueOf(main.humidity) : 0) + FileLogger.SEP +
                (main != null ? String.valueOf(main.pressure) : 0) + FileLogger.SEP +
                (wind != null ? String.valueOf(wind.speed) : 0) + FileLogger.SEP +
                (wind != null ? String.valueOf(wind.deg) : 0) + FileLogger.SEP +
                (clouds != null ? String.valueOf(clouds.all) : 0) + FileLogger.SEP +
                (rain != null ? String.valueOf(rain.last3hours) : 0) + FileLogger.SEP +
                (snow != null ? String.valueOf(snow.last3hours) : 0) + FileLogger.SEP +
                (sys != null ? String.valueOf(sys.sunrise) : 0) + FileLogger.SEP +
                (sys != null ? String.valueOf(sys.sunset) : 0);
    }

    private class WeatherField{
        // Weather condition codes: http://www.openweathermap.com/weather-conditions
        int id;
    }

    private class MainInfo{ float temp, temp_min, temp_max, humidity, pressure; }

    private class WindField{ float speed, deg; }

    private class CloudsField{ float all; }

    private class RainField{ @SerializedName("3h") float last3hours; }

    private class SnowField{ @SerializedName("3h") float last3hours; }

    private class SysField{ long sunrise, sunset; }
}
