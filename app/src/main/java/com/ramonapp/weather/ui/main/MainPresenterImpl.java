package com.ramonapp.weather.ui.main;

import com.ramonapp.weather.data.model.Weather;
import com.ramonapp.weather.data.model.WeatherRsp;
import com.ramonapp.weather.data.network.ApiDataSource;
import com.ramonapp.weather.data.network.NetworkRepository;
import com.ramonapp.weather.util.Constant;
import com.ramonapp.weather.util.Util;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by vahid on 12/9/2017.
 */

public class MainPresenterImpl implements MainPresenter {

    private MainView mainView;
    private NetworkRepository networkRepository;
    private CompositeDisposable compositeDisposable;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
        networkRepository = new ApiDataSource();
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getWeatherData(double lat, double lng) {
        networkRepository.getWeather(lat, lng, Constant.WEATHER_UNITS, Constant.APP_ID)
        .subscribe(new SingleObserver<WeatherRsp>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                compositeDisposable.add(d);
                mainView.showLoading();
            }

            @Override
            public void onSuccess(@NonNull WeatherRsp weatherRsp) {
                mainView.dismissLoading();
                mainView.showNowWeatherData(weatherRsp);
                prepare3DaysValues(weatherRsp);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                mainView.dismissLoading();
                mainView.onError("Connection Error");
            }
        });
    }

    private void prepare3DaysValues(WeatherRsp weatherRsp){
        List<Weather> weathers = Util.find3DaysWeather(weatherRsp);
        if ( weathers.size() > 0 ) {
            mainView.show1stDeg(Math.round(weathers.get(0).getMain().getTemp())+"°");
            mainView.show1stIcon(Constant.URL_IMAGES + weathers.get(0).getWeather().get(0).getIcon() +".png");
        }
        if ( weathers.size() > 1 ) {
            mainView.show2ndDeg(Math.round(weathers.get(1).getMain().getTemp())+"°");
            mainView.show2ndDate(Util.convertMillisToStringDate(weathers.get(1).getDt()));
            mainView.show2ndIcon(Constant.URL_IMAGES + weathers.get(1).getWeather().get(0).getIcon() +".png");
        }
        if ( weathers.size() > 2 ) {
            mainView.show3rdDeg(Math.round(weathers.get(2).getMain().getTemp())+"°");
            mainView.show3rdDate(Util.convertMillisToStringDate(weathers.get(2).getDt()));
            mainView.show3rdIcon(Constant.URL_IMAGES + weathers.get(2).getWeather().get(0).getIcon() +".png");
        }

    }

    @Override
    public void onDestroy() {
        mainView = null;
        compositeDisposable.dispose();
    }

    @Override
    public void unSubscribe() {
        compositeDisposable.clear();
    }
}
