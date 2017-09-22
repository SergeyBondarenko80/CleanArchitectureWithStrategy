package com.yellowgears.sergeibondarenko.sign_up.datasource.request.interfacerequest;


import com.google.gson.JsonObject;
import com.yellowgears.sergeibondarenko.sign_up.datasource.restprotocol.SignUpRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ISignUp {

  /**
   * If you have a URL query, has this form
   * https://<name_of_the_site>/<settings>/sign_up
   * then
   * https://<name_of_the_site>/<settings>/ you assign to a variable SERVER_URL in RetrofitHelper,
   * and "sign_up" write here in @POST( "sign_up" )
   */

  //@FormUrlEncoded,
  @POST( "" )
  Call< JsonObject > signUp( @Body SignUpRequest signUpRequest );

}
