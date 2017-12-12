package com.ramonapp.weather.ui.searchCity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ramonapp.weather.R;
import com.ramonapp.weather.data.model.CityGeocode;
import com.ramonapp.weather.data.model.Location;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.MyViewHolder> {

    public interface OnItemClickListener{
        void onClick(Location location);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.city_name_txt)TextView cityTxt;

        public MyViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    private OnItemClickListener onItemClickListener;
    private List<CityGeocode> items;
    private Context context;

    public CitiesAdapter(Context context, List<CityGeocode> items) {
        this.items = items;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final CityGeocode item = items.get(position);

        holder.cityTxt.setText(item.getName());

        holder.cityTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onClick(item.getGeometry().getLocation());
            }
        });
    }

    public void setOnItemClick(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
