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

package com.yellowgears.sergeibondarenko.sign_up.datasource.repository.interfacesignupprotocolrepository;

import android.content.Context;
import android.support.annotation.NonNull;

import com.yellowgears.sergeibondarenko.base.BaseRestRequestCallback;
import com.yellowgears.sergeibondarenko.sign_up.datasource.restprotocol.SignUpRequest;


public interface SignUpProtocolRepository {

  void signUp(
      @NonNull SignUpRequest signUpRequest,
      @NonNull BaseRestRequestCallback baseRestRequestCallback,
      Context context
  );

}
