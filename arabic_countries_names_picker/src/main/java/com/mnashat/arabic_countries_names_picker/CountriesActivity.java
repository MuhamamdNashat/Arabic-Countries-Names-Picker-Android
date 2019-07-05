package com.mnashat.arabic_countries_names_picker;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.mnashat.arabic_countries_names_picker.RecyclerItemClickListener.OnItemClickListener;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CountriesActivity extends AppCompatActivity {

  private RecyclerView mRecyclerView;
  private EditText mSearchText;
  private Button mSearch;
  private List<CountryModel> mCountries;
  private CountriesAdapter mAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_countries);
    mRecyclerView = findViewById(R.id.rv_countries);
    mSearchText = findViewById(R.id.et_search_Text);

    mCountries = readFile(this);

    notifyAdapter(mCountries);

    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    mRecyclerView.setHasFixedSize(true);

    mRecyclerView.setAdapter(mAdapter);

    mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, mRecyclerView,
        new OnItemClickListener() {
          @Override
          public void onItemClick(View view, int position) {
            Intent intent = new Intent();
            intent.putExtra(CountriesProvider.COUNTRY,mCountries.get(position));
            setResult(RESULT_OK,intent);
            finish();
          }

          @Override
          public void onLongItemClick(View view, int position) {

          }
        }));
    mSearchText.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        setSearchText(getBaseContext());
      }

      @Override
      public void afterTextChanged(Editable s) {

      }
    });

  }

  private void setSearchText(Context context) {
    String text = mSearchText.getText().toString().trim();
    if (text.isEmpty()) {
      mCountries = readFile(context);
      notifyAdapter(mCountries);
    } else {
      List<CountryModel> countries = new ArrayList<>();
      for (CountryModel model : mCountries) {
        if (model.getName().contains(text)) {
          countries.add(model);
        }
      }
      mCountries = countries;
      notifyAdapter(mCountries);
    }

  }

  private void notifyAdapter(List<CountryModel> countries) {
    mAdapter = new CountriesAdapter(countries);
    mRecyclerView.setAdapter(mAdapter);
  }

  private List<CountryModel> readFile(Context context) {
    List<CountryModel> countries = null;
    try {
      InputStreamReader is = new InputStreamReader(context.getAssets().open("countries.json"));
      JsonReader reader = new JsonReader(is);
      Type type = new TypeToken<List<CountryModel>>() {
      }.getType();
      countries = new Gson().fromJson(reader, type);
      is.close();
      return countries;
    } catch (Exception ignored) {
    }
    return countries;
  }

}
