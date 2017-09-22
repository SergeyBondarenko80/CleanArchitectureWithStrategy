package com.yellowgears.sergeibondarenko.sign_up.datasource.request;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.yellowgears.sergeibondarenko.base.BaseRestRequestCallback;
import com.yellowgears.sergeibondarenko.base.BaseRestRequestStrategy;
import com.yellowgears.sergeibondarenko.sign_up.datasource.restprotocol.SignUpRequest;
import com.yellowgears.sergeibondarenko.sign_up.datasource.request.interfacerequest.ISignUp;
import com.yellowgears.sergeibondarenko.utils.RetrofitHelper;

import retrofit2.Call;
import retrofit2.Callback;


public class SignUpPerformRequestBase implements BaseRestRequestStrategy {

  @Override
  public void performRequest(
      String jsonObjectStringField,
      final BaseRestRequestCallback baseRestRequestCallback,
      Context context ) {

    ISignUp serviceSignUp = RetrofitHelper.retrofit().create( ISignUp.class );

    Call< JsonObject > callSignUp = serviceSignUp
        .signUp( new Gson().fromJson( jsonObjectStringField, SignUpRequest.class ) );
    callSignUp.enqueue( new Callback< JsonObject >() {
      @Override
      public void onResponse( Call< JsonObject > call, retrofit2.Response< JsonObject > response ) {
        if ( response.isSuccessful() ) {
          baseRestRequestCallback.onSuccess( response );
        } else {
          baseRestRequestCallback.onError( response, null );
        }
      }

      @Override
      public void onFailure( Call< JsonObject > call, Throwable t ) {
        baseRestRequestCallback.onError( null, t );
      }
    } );
  }
}
