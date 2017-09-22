package com.yellowgears.sergeibondarenko.utils;


import android.app.Activity;
import android.util.Log;

import com.google.gson.JsonObject;
import com.yellowgears.sergeibondarenko.R;

import com.yellowgears.sergeibondarenko.base.protocol.ErrorResultBase;
import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

public class ErrorUtils {

  private static final String LOG_ERROR_UTILS = "LOG_ERROR_UTILS";


  public static ErrorResultBase parseError( Response< JsonObject > response ) {

    Converter< ResponseBody, ErrorResultBase > converter = RetrofitHelper.retrofit()
        .responseBodyConverter( ErrorResultBase.class, new Annotation[ 0 ] );

    ErrorResultBase error;

    try {
      error = converter.convert( response.errorBody() );
    } catch ( IOException e ) {
      Log.d( LOG_ERROR_UTILS, e.getMessage() );
      e.printStackTrace();
      return new ErrorResultBase();
    }

    return error;
  }


  public static void processRetrofitFailure( Activity activity, Throwable throwable,
      Response< JsonObject > response ) {

    if ( throwable != null ) {
      Log.d( LOG_ERROR_UTILS, "onFailure" );
      Log.d( LOG_ERROR_UTILS, "Throwable setTourAvailableTimes: " + throwable.getMessage() );

      if ( activity != null ) {
        SystemUtils.showToast( activity, R.string.error_response );
      }

    } else {
      SystemUtils.showToast( activity, ErrorUtils.parseError( response ).toString() );
    }
  }
}
