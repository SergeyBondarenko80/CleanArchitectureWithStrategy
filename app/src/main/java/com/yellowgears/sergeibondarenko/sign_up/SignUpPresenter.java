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

package com.yellowgears.sergeibondarenko.sign_up;

import static com.google.common.base.Preconditions.checkNotNull;

import android.content.Context;
import android.support.annotation.NonNull;
import com.yellowgears.sergeibondarenko.sign_up.datasource.repository.SignUpRepository;
import com.yellowgears.sergeibondarenko.utils.Validation;


public class SignUpPresenter implements SignUpContract.Presenter {

  private final SignUpContract.View mSignUpView;
  private final SignUpRepository signUpRepository;


  public SignUpPresenter( @NonNull SignUpRepository signUpRepository,
      @NonNull SignUpContract.View addTaskView ) {
    this.signUpRepository = signUpRepository;
    mSignUpView = checkNotNull( addTaskView, "addTaskView cannot be null!" );
    mSignUpView.setPresenter( this );
  }


  @Override
  public void start() {
  }


  @Override
  public void SignUp(
      String email,
      String password,
      String confirmPassword,
      String name,
      String phone,
      final Context context ) {

    if ( !Validation.isValidEmail( context, email ) ) {
      mSignUpView.setEmailBackgroundError();
    } else {
      mSignUpView.setEmailBackground();
    }

    if ( !Validation.isValidPassword( password, context ) ) {
      mSignUpView.setPasswordBackgroundError();
    } else {
      mSignUpView.setPasswordBackground();
    }

    if ( !Validation.isValidPassword( confirmPassword, context ) ) {
      mSignUpView.setConfirmPasswordBackgroundError();
    } else {
      mSignUpView.setConfirmPasswordBackground();
    }

    if ( !Validation.isPasswordsEqual( password, confirmPassword, context ) ) {
      mSignUpView.setBackgroundErrorIfPasswordNotEqual();
    } else {
      mSignUpView.setBackgroundIfPasswordEqual();
    }

    if ( name.equals( "" ) ) {
      mSignUpView.setNameBackgroundError();
    } else {
      mSignUpView.setNameBackground();
    }

    if ( !Validation.isValidPhone( phone, context ) ) {
      mSignUpView.setPhoneNumberBackgroundError();
    } else {
      mSignUpView.setPhoneNumberBackground();
    }

    if ( Validation.isValidEmail( context, email )
        && Validation.isValidPassword( password, context )
        && Validation.isValidPassword( confirmPassword, context )
        && Validation.isPasswordsEqual( password, confirmPassword, context )
        && Validation.isValidPhone( phone, context ) ) {

      /*
       * When you have the URL in RetrofitHelper String SERVER_URL and
       * URL in public interface ISignUp
       * uncomment this code
       */

      /*signUpRepository.signUp(
          new SignUpRequest( email, password, name, phone ),
          new BaseRestRequestCallback() {
            @Override
            public void onSuccess( Response< JsonObject > response ) {
              if ( response.isSuccessful() ) {
                try {

                  String body = response.body().toString();
                  String responseToken = new ArrayList< DataToken >( new Gson()
                      .fromJson( new Gson().toJson( body ), SignUpResponseToken.class ).data )
                      .get( 0 ).token;

                  context.getSharedPreferences( Constants.CLEAN_ARCHITECTURE_WITH_STRATEGY,
                      Context.MODE_PRIVATE ).edit().putString( Constants.TOKEN, responseToken )
                      .apply();

                  ActivityUtils.openMainActivity( context );

                } catch ( Exception e ) {
                  e.printStackTrace();
                }

              } else {
                Tools.processRetrofitErrorResponse( ( Activity ) context, response );
              }
            }

            @Override
            public void onError( Response< JsonObject > response, Throwable t ) {
              ErrorUtils.processRetrofitFailure( ( Activity ) context, t, response );
            }
          }, context );*/
    }
  }
}
