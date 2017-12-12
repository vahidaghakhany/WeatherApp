package com.ramonapp.weather;

import com.ramonapp.weather.data.model.Weather;
import com.ramonapp.weather.data.model.WeatherRsp;
import com.ramonapp.weather.util.Util;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vahid on 12/12/2017.
 */

public class UtilTest {


    @Test
    public void testConvertMillisToStringDate(){
        String s =  Util.convertMillisToStringDate(1513058002l);
        Assert.assertEquals("December 12",s);
    }

    @Test
    public void testFind3DaysWeather(){
        WeatherRsp weatherRsp = new WeatherRsp("",null,null);
        List<Weather> weathers = new ArrayList<Weather>();
        weathers.add(new Weather(1513058002l,null,null,null));
        weathers.add(new Weather(1513144402l,null,null,null));
        weathers.add(new Weather(1513230802l,null,null,null));
        weathers.add(new Weather(1513317202l,null,null,null));
        weatherRsp.setList(weathers);

        List<Weather> weatherList = Util.find3DaysWeather(weatherRsp);

        Assert.assertEquals(1513144402l,weatherList.get(0).getDt());
        Assert.assertEquals(1513230802l,weatherList.get(1).getDt());
        Assert.assertEquals(1513317202l,weatherList.get(2).getDt());
    }
}
