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

package com.yellowgears.sergeibondarenko.utils;

import static com.google.common.base.Preconditions.checkNotNull;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import com.yellowgears.sergeibondarenko.R;
import com.yellowgears.sergeibondarenko.main.MainActivity;
import com.yellowgears.sergeibondarenko.sign_in.SignInActivity;
import com.yellowgears.sergeibondarenko.sign_up.SignUpActivity;


public class ActivityUtils extends AppCompatActivity {


  public static void addFragmentToActivity( @NonNull FragmentManager fragmentManager,
      @NonNull Fragment fragment, int frameId ) {
    checkNotNull( fragmentManager );
    checkNotNull( fragment );
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    transaction.add( frameId, fragment );
    transaction.commit();
  }


  public static void openSignInActivity( Activity activity ) {
    Intent intentOpenSignInActivity = new Intent( activity, SignInActivity.class );
    activity.startActivity( intentOpenSignInActivity );
  }


  public static void openSignInActivityAndCloseStartActivity( Activity activity ) {
    Intent intentOpenSignInActivityAndCloseStartActivity = new Intent( activity, SignInActivity.class )
        .addFlags( Intent.FLAG_ACTIVITY_NO_ANIMATION );
    activity.finish();
    activity.startActivity( intentOpenSignInActivityAndCloseStartActivity );
  }


  public static void openSignUpActivity( Context context ) {
    Intent intentOpenSignUpActivity = new Intent( context, SignUpActivity.class );
    context.startActivity( intentOpenSignUpActivity );
  }


  public static void openMainActivity( Context context ) {
    Intent intentOpenMainActivity = new Intent( context, MainActivity.class );
    context.startActivity( intentOpenMainActivity );
  }


  public static void openForgetPassword( Context context ) {
    SystemUtils.showToast( context, context.getString( R.string.press_forgot_password ) );
  }

}
