package com.mnashat.arabic_countries_names_picker_example;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.mnashat.arabic_countries_names_picker.CountriesProvider;
import com.mnashat.arabic_countries_names_picker.CountryModel;

public class MainActivity extends AppCompatActivity {
  private TextView tv;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Button button = findViewById(R.id.bt_get_countries);
     tv = findViewById(R.id.tv_name);

     button.setOnClickListener(new OnClickListener() {
       @Override
       public void onClick(View v) {
         CountriesProvider.getCountries(MainActivity.this);
       }
     });
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == CountriesProvider.COUNTRIES_CODE && resultCode == RESULT_OK)
    {
      CountryModel model = (CountryModel) data.getSerializableExtra(CountriesProvider.COUNTRY);
      tv.setText(model.getName());
    }
  }
}
