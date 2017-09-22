package com.yellowgears.sergeibondarenko.sign_in.datasource.request.interfacerequest;


import com.google.gson.JsonObject;
import com.yellowgears.sergeibondarenko.sign_in.datasource.restprotocol.SignInRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ISignIn {

  /**
   * If you have a URL query, has this form
   * https://<name_of_the_site>/<settings>/sign_in
   * then
   * https://<name_of_the_site>/<settings>/ you assign to a variable SERVER_URL in RetrofitHelper,
   * and "sign_in" write here in @POST( "sign_in" )
   */

  //@FormUrlEncoded,
  @POST( "" )
  Call< JsonObject > signIn( @Body SignInRequest signInRequest );

}
