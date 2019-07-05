package com.mnashat.arabic_countries_names_picker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.ViewHolder>{
  List<CountryModel> mList;

  public CountriesAdapter(
      List<CountryModel> list) {
    mList = list;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_layout,parent,false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.bindData(mList.get(position).getName(),mList.get(position).getCode(),mList.get(position).getDialCode());
  }

  @Override
  public int getItemCount() {
    return mList == null?0:mList.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    TextView mName;
    TextView mCode;
    TextView mNumber;
    ViewHolder(@NonNull View itemView) {
      super(itemView);
      mName = itemView.findViewById(R.id.tv_country_name);
      mCode = itemView.findViewById(R.id.tv_country_code);
      mNumber = itemView.findViewById(R.id.tv_country_number);
    }

    public void bindData(String name,String code,String number)
    {
      mName.setText(name);
      mCode.setText(code);
      mNumber.setText(number);
    }
  }
}
