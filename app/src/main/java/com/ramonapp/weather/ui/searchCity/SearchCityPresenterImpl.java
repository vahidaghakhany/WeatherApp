package com.ramonapp.weather.ui.searchCity;

import com.ramonapp.weather.data.model.GeocodeRsp;
import com.ramonapp.weather.data.network.ApiDataSource;
import com.ramonapp.weather.data.network.NetworkRepository;
import com.ramonapp.weather.util.Constant;

import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by vahid on 12/11/2017.
 */

public class SearchCityPresenterImpl implements SearchCityPresenter {

    private SearchCityView searchCityView;
    private NetworkRepository networkRepository;
    private CompositeDisposable compositeDisposable;


    public SearchCityPresenterImpl(SearchCityView searchCityView) {
        this.searchCityView = searchCityView;
        networkRepository = new ApiDataSource(Constant.URL_GOOGLE_API);
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void doSearch(String s) {
        networkRepository.searchCity(s)
                .subscribe(new SingleObserver<GeocodeRsp>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        searchCityView.showLoading();
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull GeocodeRsp geocodeRsp) {
                        searchCityView.dismissLoading();
                        if("ok".equalsIgnoreCase(geocodeRsp.getStatus())) {
                            searchCityView.showCityList(geocodeRsp);
                        }else{
                            searchCityView.onError("Not Found!");
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        searchCityView.dismissLoading();
                        searchCityView.onError(e.getMessage());
                    }
                });
    }

    @Override
    public void onDestroy() {
        searchCityView = null;
        compositeDisposable.dispose();
    }

    @Override
    public void unSubscribe() {
        compositeDisposable.clear();
    }
}
