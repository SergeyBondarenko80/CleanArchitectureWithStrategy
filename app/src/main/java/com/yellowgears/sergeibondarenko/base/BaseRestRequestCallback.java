package com.yellowgears.sergeibondarenko.base;

import com.google.gson.JsonObject;


public interface BaseRestRequestCallback {

  void onSuccess( retrofit2.Response< JsonObject > response );

  void onError( retrofit2.Response< JsonObject > response, Throwable t );
}