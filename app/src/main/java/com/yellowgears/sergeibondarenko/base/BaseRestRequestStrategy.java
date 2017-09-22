package com.yellowgears.sergeibondarenko.base;

import android.content.Context;


public interface BaseRestRequestStrategy {

  void performRequest(
      String jsonObjectStringField,
      BaseRestRequestCallback baseRestRequestCallback,
      Context context
  );
}