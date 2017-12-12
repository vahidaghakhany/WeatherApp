package com.ramonapp.weather.ui.searchCity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ramonapp.weather.R;
import com.ramonapp.weather.data.model.CityGeocode;
import com.ramonapp.weather.data.model.GeocodeRsp;
import com.ramonapp.weather.data.model.Location;
import com.ramonapp.weather.ui.WaitingDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchCityActivity extends AppCompatActivity implements SearchCityView {

    @BindView(R.id.search_vw)
    android.support.v7.widget.SearchView searchView;
    @BindView(R.id.recycler_vw) RecyclerView recyclerView;

    private WaitingDialog waitingDialog;
    private SearchCityPresenter presenter;
    private List<CityGeocode> cities;
    private CitiesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_city);
        ButterKnife.bind(this);
        waitingDialog = new WaitingDialog(this);
        presenter = new SearchCityPresenterImpl(this);
        initRecyclerView();
        searchView.onActionViewExpanded();


        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.doSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    private void initRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        cities = new ArrayList<>();
        adapter = new CitiesAdapter(this, cities);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClick(new CitiesAdapter.OnItemClickListener() {
            @Override
            public void onClick(Location location) {
                Intent intent = new Intent();
                intent.putExtra("lat",location.getLat());
                intent.putExtra("lng",location.getLng());
                setResult(1,intent);
                finish();
            }
        });
    }

    @Override
    public void showLoading() {
        waitingDialog.show();
    }

    @Override
    public void dismissLoading() {
        waitingDialog.dismiss();
    }

    @Override
    public void onError(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setMessage(message)
                .setCancelable(true)
                .setPositiveButton("OK",null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void showCityList(GeocodeRsp geocodeRsp) {
        cities.clear();
        cities.addAll(geocodeRsp.getResults());
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.unSubscribe();
    }
}
