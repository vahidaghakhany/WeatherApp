package com.ramonapp.weather.ui.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ramonapp.weather.R;
import com.ramonapp.weather.data.model.WeatherRsp;
import com.ramonapp.weather.ui.WaitingDialog;
import com.ramonapp.weather.ui.searchCity.SearchCityActivity;
import com.ramonapp.weather.util.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView{

    @BindView(R.id.city_txt) TextView cityTxt;
    @BindView(R.id.now_icon_img) ImageView nowIconImg;
    @BindView(R.id.now_deg_txt) TextView nowDegTxt;
    @BindView(R.id.now_type_txt) TextView nowTypeTxt;
    @BindView(R.id.day_1st_icon_img) ImageView day1stIconImg;
    @BindView(R.id.day_1st_deg_txt) TextView day1stDegTxt;
    @BindView(R.id.day_2nd_date_txt) TextView day2ndDateTxt;
    @BindView(R.id.day_2nd_icon_img) ImageView day2ndIconImg;
    @BindView(R.id.day_2nd_deg_txt) TextView day2ndDegTxt;
    @BindView(R.id.day_3rd_date_txt) TextView day3rdDateTxt;
    @BindView(R.id.day_3rd_icon_img) ImageView day3rdIconImg;
    @BindView(R.id.day_3rd_deg_txt) TextView day3rdDegTxt;
    @BindView(R.id.humidity_txt) TextView humidityTxt;
    @BindView(R.id.wind_txt) TextView windTxt;

    private MainPresenter presenter;
    private WaitingDialog waitingDialog;
    private double lat;
    private double lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        waitingDialog = new WaitingDialog(this);
        presenter = new MainPresenterImpl(this);

        lat = Constant.TEHRAN_LAT;
        lng = Constant.TEHRAN_LNG;
        presenter.getWeatherData(lat, lng);
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
                .setCancelable(false)
                .setPositiveButton("retry", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        presenter.getWeatherData(lat, lng);
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showNowWeatherData(WeatherRsp weatherRsp) {
        cityTxt.setText(weatherRsp.getCity().getName()+ " - " + weatherRsp.getCity().getCountry());
        nowTypeTxt.setText(weatherRsp.getList().get(0).getWeather().get(0).getMain());
        nowDegTxt.setText(Math.round(weatherRsp.getList().get(0).getMain().getTemp())+"°");
        humidityTxt.setText(weatherRsp.getList().get(0).getMain().getHumidity()+"%");
        windTxt.setText(weatherRsp.getList().get(0).getWind().getSpeed()+" m/s");
        Glide.with(this)
                .load(Constant.URL_IMAGES + weatherRsp.getList().get(0).getWeather().get(0).getIcon() + ".png")
                .centerCrop()
                .crossFade()
                .into(nowIconImg)
        ;
    }

    @Override
    public void show1stDeg(String deg) {
        day1stDegTxt.setText(deg);
    }

    @Override
    public void show1stIcon(String url) {
        Glide.with(this)
                .load(url)
                .centerCrop()
                .crossFade()
                .into(day1stIconImg)
        ;
    }

    @Override
    public void show2ndDeg(String deg) {
        day2ndDegTxt.setText(deg);
    }

    @Override
    public void show2ndDate(String date) {
        day2ndDateTxt.setText(date);
    }

    @Override
    public void show2ndIcon(String url) {
        Glide.with(this)
                .load(url)
                .centerCrop()
                .crossFade()
                .into(day2ndIconImg)
        ;
    }

    @Override
    public void show3rdDeg(String deg) {
        day3rdDegTxt.setText(deg);
    }

    @Override
    public void show3rdDate(String date) {
        day3rdDateTxt.setText(date);
    }

    @Override
    public void show3rdIcon(String url) {
        Glide.with(this)
                .load(url)
                .centerCrop()
                .crossFade()
                .into(day3rdIconImg)
        ;
    }

    @OnClick(R.id.search_btn)
    public void onClickSearchBtn(){
        startActivityForResult(new Intent(this, SearchCityActivity.class),0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 1){
            lat = data.getDoubleExtra("lat",0d);
            lng = data.getDoubleExtra("lng",0d);
            presenter.getWeatherData(lat,lng);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.unSubscribe();
    }
}
