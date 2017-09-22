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

package com.yellowgears.sergeibondarenko.sign_up.datasource.repository.remotedatasourse;

import android.content.Context;

import com.yellowgears.sergeibondarenko.base.BaseRestRequestCallback;
import com.yellowgears.sergeibondarenko.base.BaseRestRequestStrategy;
import com.yellowgears.sergeibondarenko.sign_up.datasource.repository.remotedatasourse.interfacedatasource.SignUpDataSource;


public class SignUpRemoteDataSource implements SignUpDataSource {

  private static SignUpRemoteDataSource INSTANCE;


  public static SignUpRemoteDataSource getInstance() {
    if ( INSTANCE == null ) {
      INSTANCE = new SignUpRemoteDataSource();
    }
    return INSTANCE;
  }


  // Prevent direct initialization.
  private SignUpRemoteDataSource() {
  }


  @Override
  public void getData(
      BaseRestRequestStrategy baseRestRequestStrategy,
      String jsonObjectStringField,
      BaseRestRequestCallback baseRestRequestCallback,
      Context context ) {

    baseRestRequestStrategy
        .performRequest( jsonObjectStringField, baseRestRequestCallback, context );
  }
}
