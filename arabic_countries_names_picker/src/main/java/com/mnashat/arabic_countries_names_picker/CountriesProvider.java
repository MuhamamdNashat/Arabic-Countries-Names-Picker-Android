package com.mnashat.arabic_countries_names_picker;

import android.app.Activity;
import android.content.Intent;

public class CountriesProvider {
  public static final int COUNTRIES_CODE = 421;
  public static final String COUNTRY = "country";
  public static void getCountries(Activity activity)
  {
    Intent intent = new Intent(activity,CountriesActivity.class);
    activity.startActivityForResult(intent,COUNTRIES_CODE);
  }

}
