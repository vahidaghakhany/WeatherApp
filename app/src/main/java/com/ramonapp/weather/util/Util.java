package com.ramonapp.weather.util;

import com.ramonapp.weather.data.model.Weather;
import com.ramonapp.weather.data.model.WeatherRsp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * Created by vahid on 12/10/2017.
 */

public final class Util {
    public static List<Weather> find3DaysWeather(WeatherRsp weatherRsp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(weatherRsp.getList().get(0).getDt() * 1000l);
        calendar.add(Calendar.DAY_OF_MONTH,1);
        long date1st = calendar.getTimeInMillis();
        calendar.add(Calendar.DAY_OF_MONTH,1);
        long date2nd = calendar.getTimeInMillis();
        calendar.add(Calendar.DAY_OF_MONTH,1);
        long date3rd = calendar.getTimeInMillis();

        List<Weather> weathers = new ArrayList<>();
        Iterator<Weather> iterator = weatherRsp.getList().iterator();
        while (iterator.hasNext()){
            Weather weather = iterator.next();
            long dt = weather.getDt() * 1000l;
            if ( dt == date1st || dt == date2nd || dt == date3rd){
                weathers.add(weather);
            }
        }
        return weathers;
    }

    public static String convertMillisToStringDate(long millis){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis * 1000l);
        return String.format(Locale.US,"%tB",calendar) + " " + calendar.get(Calendar.DAY_OF_MONTH);
    }
}
