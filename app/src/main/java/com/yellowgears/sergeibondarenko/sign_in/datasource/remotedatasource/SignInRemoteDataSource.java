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

package com.yellowgears.sergeibondarenko.sign_in.datasource.remotedatasource;

import android.content.Context;

import com.yellowgears.sergeibondarenko.base.BaseRestRequestCallback;
import com.yellowgears.sergeibondarenko.base.BaseRestRequestStrategy;
import com.yellowgears.sergeibondarenko.sign_in.datasource.remotedatasource.interfacedatasource.ISignInDataSource;


public class SignInRemoteDataSource implements ISignInDataSource {

  private static SignInRemoteDataSource INSTANCE;


  // Returns the single instance of this class.
  public static SignInRemoteDataSource getInstance() {
    if ( INSTANCE == null ) {
      INSTANCE = new SignInRemoteDataSource();
    }
    return INSTANCE;
  }


  // Prevent direct initialization.
  private SignInRemoteDataSource() {
  }


  @Override
  public void signIn( BaseRestRequestStrategy baseRestRequestStrategy, String jsonObjectStringField,
      BaseRestRequestCallback baseRestRequestCallback, Context context ) {

    baseRestRequestStrategy
        .performRequest( jsonObjectStringField, baseRestRequestCallback, context );
  }
}
