/*
 * Copyright (c) 2017. Mattia Campana, m.campana@iit.cnr.it, campana.mattia@gmail.com
 *
 * This file is part of Android Sensing Kit (ASK).
 *
 * Android Sensing Kit (ASK) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Android Sensing Kit (ASK) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Android Sensing Kit (ASK).  If not, see <http://www.gnu.org/licenses/>.
 */

package it.matbell.ask.controllers;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

import it.matbell.ask.controllers.network.GsonRequest;

public class WeatherController {

    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?";

    private static final int UNIT_KELVIN = 1;
    private static final int UNIT_METRIC = 2;
    private static final int UNIT_IMPERIAL = 3;

    public static void getWeatherByCoordinates(Context context, final WeatherListener listener,
                                               double latitude, double longitude, int unit,
                                               String appId){

        RequestQueue queue = Volley.newRequestQueue(context);

        String url = BASE_URL.concat("lat=").concat(String.valueOf(latitude))
                .concat("&lon=").concat(String.valueOf(longitude)).concat("&APPID=").concat(appId);

        switch (unit){
            //Kelvin is the default unit for openweathermap, there is no need to specify it
            case UNIT_KELVIN:
                break;

            case UNIT_METRIC:
                url = url.concat("&units=metric");
                break;

            case UNIT_IMPERIAL:
                url = url.concat("&units=imperial");
                break;
        }

        GsonRequest<OpenWeatherMapJsonResponse> request = new GsonRequest<>(url,
                OpenWeatherMapJsonResponse.class,
                null,
                new Response.Listener<OpenWeatherMapJsonResponse>() {
                    @Override
                    public void onResponse(OpenWeatherMapJsonResponse response) {
                        listener.onWeatherAvailable(response.getDataToPrint());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onWeatherFailed(error.getMessage());
                    }});

        queue.add(request);
    }

    public interface WeatherListener{
        void onWeatherAvailable(Object[] data);
        void onWeatherFailed(String reason);
    }

    private class OpenWeatherMapJsonResponse{

        List<WeatherField> weather;
        MainInfo main;
        WindField wind;
        CloudsField clouds;
        RainField rain;
        SnowField snow;
        SysField sys;

        public Object[] getDataToPrint(){

            return Arrays.asList(
                    weather != null ? String.valueOf(weather.get(0).id) : 0,
                    main != null ? String.valueOf(main.temp) : 0,
                    main != null ? String.valueOf(main.temp_min) : 0,
                    main != null ? String.valueOf(main.temp_max) : 0,
                    main != null ? String.valueOf(main.humidity) : 0,
                    main != null ? String.valueOf(main.pressure) : 0,
                    wind != null ? String.valueOf(wind.speed) : 0,
                    wind != null ? String.valueOf(wind.deg) : 0,
                    clouds != null ? String.valueOf(clouds.all) : 0,
                    rain != null ? String.valueOf(rain.last3hours) : 0,
                    snow != null ? String.valueOf(snow.last3hours) : 0,
                    sys != null ? String.valueOf(sys.sunrise) : 0,
                    sys != null ? String.valueOf(sys.sunset) : 0
            ).toArray();
        }

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
