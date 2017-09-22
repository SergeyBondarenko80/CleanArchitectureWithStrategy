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

package com.yellowgears.sergeibondarenko.sign_up.datasource.repository;

import static com.google.common.base.Preconditions.checkNotNull;

import android.content.Context;
import android.support.annotation.NonNull;
import com.google.gson.Gson;
import com.yellowgears.sergeibondarenko.base.BaseRestRequestCallback;
import com.yellowgears.sergeibondarenko.sign_up.datasource.repository.remotedatasourse.interfacedatasource.SignUpDataSource;
import com.yellowgears.sergeibondarenko.sign_up.datasource.restprotocol.SignUpRequest;
import com.yellowgears.sergeibondarenko.sign_up.datasource.repository.interfacesignupprotocolrepository.SignUpProtocolRepository;
import com.yellowgears.sergeibondarenko.sign_up.datasource.request.SignUpPerformRequestBase;


public class SignUpRepository implements SignUpProtocolRepository {

  private static SignUpRepository INSTANCE = null;
  private final SignUpDataSource signUpDataSource;


  /**
   * Returns the single instance of this class, creating it if necessary.
   *
   * @param signUpDataSource the backend data source
   * @return the {@link SignUpRepository} instance
   */

  public static SignUpRepository getInstance( SignUpDataSource signUpDataSource ) {
    if ( INSTANCE == null ) {
      INSTANCE = new SignUpRepository( signUpDataSource );
    }
    return INSTANCE;
  }


  // Prevent direct initialization.
  private SignUpRepository( @NonNull SignUpDataSource tasksRemoteDataSource ) {
    signUpDataSource = checkNotNull( tasksRemoteDataSource );
  }


  public static void destroyInstance() {
    INSTANCE = null;
  }


  @Override
  public void signUp(
      @NonNull SignUpRequest signUpRequest,
      @NonNull BaseRestRequestCallback baseRestRequestCallback,
      Context context ) {

    String jsonObjectStringField = new Gson().toJson( signUpRequest );

    signUpDataSource.getData(
        new SignUpPerformRequestBase(),
        jsonObjectStringField,
        baseRestRequestCallback,
        context
    );
  }
}
