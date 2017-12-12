package com.ramonapp.weather;

import com.ramonapp.weather.data.network.NetworkRepository;
import com.ramonapp.weather.ui.searchCity.SearchCityPresenter;
import com.ramonapp.weather.ui.searchCity.SearchCityPresenterImpl;
import com.ramonapp.weather.ui.searchCity.SearchCityView;

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

public class SearchCityPresenterTest {

    SearchCityView view;
    SearchCityPresenter presenter;
    NetworkRepository networkRepository;

    @Before
    public void setup() throws Exception{
        view = mock(SearchCityView.class);
        networkRepository = mock(NetworkRepository.class);
        presenter = new SearchCityPresenterImpl(view);

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
    public void testDoSearch(){
        String s = "Tehran";
        presenter.doSearch(s);

        InOrder inOrder = inOrder(view);
        inOrder.verify(view).showLoading();
        inOrder.verify(view).dismissLoading();

    }


}
