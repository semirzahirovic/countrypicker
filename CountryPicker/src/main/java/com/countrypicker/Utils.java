package com.countrypicker;

import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by david on 27/01/16.
 */
public class Utils {
  public static GeoPoint getLocation(Context context) {
    try {
      LocationManager locationManager =
          (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
      Criteria criteria = new Criteria();
      criteria.setAccuracy(Criteria.ACCURACY_COARSE);
      String provider = locationManager.getBestProvider(criteria, true);
      //In order to make sure the device is getting the location, request updates.
      // this requests updates every hour or if the user moves 1 kilometer
      if (provider != null) {
        Location curLocation = locationManager.getLastKnownLocation(provider);
        return new GeoPoint((int) (curLocation.getLatitude() * 1e6),
            (int) (curLocation.getLongitude() * 1e6));
      } else {
        return null;
      }
    } catch (NullPointerException e) {
      Log.e("CountryPicker", "Error getting location: ", e);
      return null;
    }
  }

  public static String getCountryName(Context context, GeoPoint curLocal) {
    if (curLocal != null && context != null) {
      Geocoder geocoder = new Geocoder(context, Locale.getDefault());
      List<Address> addresses = null;
      try {
        addresses = geocoder.getFromLocation((double) curLocal.getLatitudeE6() / 1e6,
            (double) curLocal.getLongitudeE6() / 1e6, 1);
      } catch (IOException ignored) {
        return "";
      }
      return addresses.get(0).getCountryName();
    } else {
      return "";
    }
  }

  public static String getCountryName(Context context) {
    return getCountryName(context, getLocation(context));
  }
}
