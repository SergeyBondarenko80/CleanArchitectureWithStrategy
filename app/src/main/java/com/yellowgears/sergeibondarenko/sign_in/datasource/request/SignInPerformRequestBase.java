package com.yellowgears.sergeibondarenko.sign_in.datasource.request;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.yellowgears.sergeibondarenko.base.BaseRestRequestCallback;
import com.yellowgears.sergeibondarenko.base.BaseRestRequestStrategy;
import com.yellowgears.sergeibondarenko.sign_in.datasource.request.interfacerequest.ISignIn;
import com.yellowgears.sergeibondarenko.sign_in.datasource.restprotocol.SignInRequest;
import com.yellowgears.sergeibondarenko.utils.RetrofitHelper;

import retrofit2.Call;
import retrofit2.Callback;


public class SignInPerformRequestBase implements BaseRestRequestStrategy {

  @Override
  public void performRequest( String jsonObjectStringField,
      final BaseRestRequestCallback baseRestRequestCallback, Context context ) {

    ISignIn serviceLogin = RetrofitHelper.retrofit().create( ISignIn.class );

    Call< JsonObject > callSignIn =
        serviceLogin.signIn( new Gson().fromJson( jsonObjectStringField, SignInRequest.class ) );

    callSignIn.enqueue( new Callback< JsonObject >() {
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
