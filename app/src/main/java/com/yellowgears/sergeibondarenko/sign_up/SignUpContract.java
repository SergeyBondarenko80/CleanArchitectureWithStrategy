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


import android.content.Context;

import com.yellowgears.sergeibondarenko.base.BasePresenter;
import com.yellowgears.sergeibondarenko.base.BaseView;

/**
 * This is a contract between View and Presenter.
 */
public interface SignUpContract {

  interface View extends BaseView< Presenter > {

    void setEmailBackgroundError();

    void setEmailBackground();

    void setPasswordBackgroundError();

    void setPasswordBackground();

    void setConfirmPasswordBackgroundError();

    void setConfirmPasswordBackground();

    void setBackgroundErrorIfPasswordNotEqual();

    void setBackgroundIfPasswordEqual();

    void setNameBackgroundError();

    void setNameBackground();

    void setPhoneNumberBackgroundError();

    void setPhoneNumberBackground();

  }


  interface Presenter extends BasePresenter {

    void SignUp(
        String email,
        String password,
        String confirmPassword,
        String name,
        String phone,
        Context context
    );

  }
}
