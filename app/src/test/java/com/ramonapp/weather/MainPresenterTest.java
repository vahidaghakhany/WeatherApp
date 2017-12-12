package com.ramonapp.weather;

import com.ramonapp.weather.data.network.NetworkRepository;
import com.ramonapp.weather.ui.main.MainPresenter;
import com.ramonapp.weather.ui.main.MainPresenterImpl;
import com.ramonapp.weather.ui.main.MainView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.util.concurrent.Callable;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

/**
 * Created by vahid on 12/11/2017.
 */

public class MainPresenterTest {

    MainView mainView;
    MainPresenter presenter;
    NetworkRepository networkRepository;

    @Before
    public void setup() throws Exception{
        mainView = mock(MainView.class);
        networkRepository = mock(NetworkRepository.class);
        presenter = new MainPresenterImpl(mainView);

        RxJavaPlugins.setIoSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(@NonNull Scheduler scheduler) throws Exception {
                return Schedulers.trampoline();
            }
        });

        RxAndroidPlugins.setInitMainThreadSchedulerHandler(new Function<Callable<Scheduler>, Scheduler>() {
            @Override
            public Scheduler apply(@NonNull Callable<Scheduler> schedulerCallable) throws Exception {
                return Schedulers.trampoline();
            }
        });
    }

    @Test
    public void testGetWeatherSuccess(){
        double lat = 34.11111;
        double lng = 51.23112;
        presenter.getWeatherData(lat, lng);

        InOrder inOrder = inOrder(mainView);

        inOrder.verify(mainView).showLoading();
        inOrder.verify(mainView).dismissLoading();

    }


}
