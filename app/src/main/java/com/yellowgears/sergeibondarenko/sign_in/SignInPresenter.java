/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yellowgears.sergeibondarenko.sign_in;

import static com.google.common.base.Preconditions.checkNotNull;

import android.app.Activity;
import android.support.annotation.NonNull;
import com.yellowgears.sergeibondarenko.sign_in.datasource.remotedatasource.interfacedatasource.ISignInDataSource;
import com.yellowgears.sergeibondarenko.utils.SystemUtils;
import com.yellowgears.sergeibondarenko.utils.Validation;


public class SignInPresenter implements SignInContract.Presenter {

  private final SignInContract.View mSignInView;
  private final ISignInDataSource mISignInDataSource;


  public SignInPresenter( @NonNull ISignInDataSource ISignInDataSource,
      @NonNull SignInContract.View signInView ) {

    this.mSignInView = checkNotNull( signInView );
    this.mISignInDataSource = checkNotNull( ISignInDataSource );
    this.mSignInView.setPresenter( this );
  }


  @Override
  public void start() {
  }


  @Override
  public void signIn( String email, String password, final Activity activity ) {

    if ( !Validation.isValidEmail( activity, email ) ) {
      mSignInView.setEmailBackgroundError();
    } else {
      mSignInView.setEmailBackground();
    }

    if ( !Validation.isValidPassword( password, activity ) ) {
      mSignInView.setPasswordBackgroundError();
    } else {
      mSignInView.setPasswordBackground();
    }

    if ( Validation.isValidEmail( activity, email ) && Validation
        .isValidPassword( password, activity ) ) {

      SystemUtils.showToast( activity, "Press Sign In" );

      /*
       * When you have the URL in RetrofitHelper String SERVER_URL and
       * URL in public interface ISignIn
       * uncomment this code
       */

      /*SignInRequest signInRequest = new SignInRequest( email, password );
      String jsonObjectStringField = new Gson().toJson( signInRequest );

      mISignInDataSource.signIn( new SignInPerformRequestBase(), jsonObjectStringField,
          new BaseRestRequestCallback() {
            @Override
            public void onSuccess( Response< JsonObject > response ) {
              if ( response.isSuccessful() ) {
                try {

                  String body = response.body().toString();
                  String responseToken = new Gson()
                      .fromJson( new Gson().toJson( body ), SignInResponseToken.class ).data.token;
                  activity.getSharedPreferences( Constants.CLEAN_ARCHITECTURE_WITH_STRATEGY,
                      Context.MODE_PRIVATE ).edit().putString( Constants.TOKEN, responseToken )
                      .apply();

                  ActivityUtils.openMainActivity( activity );

                } catch ( Exception e ) {
                  e.printStackTrace();
                }

              } else {
                RetrofitHelper.processRetrofitErrorResponse( activity, response );
              }
            }

            @Override
            public void onError( Response< JsonObject > response, Throwable t ) {
              ErrorUtils.processRetrofitFailure( activity, t, response );
            }
          }, activity );*/
    }
  }
}
