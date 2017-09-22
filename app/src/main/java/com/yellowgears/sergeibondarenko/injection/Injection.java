package com.yellowgears.sergeibondarenko.injection;

import android.content.Context;
import android.support.annotation.NonNull;
import com.yellowgears.sergeibondarenko.sign_in.datasource.remotedatasource.SignInRemoteDataSource;
import com.yellowgears.sergeibondarenko.sign_up.datasource.repository.SignUpRepository;
import com.yellowgears.sergeibondarenko.sign_up.datasource.repository.remotedatasourse.SignUpRemoteDataSource;

public class Injection {

  public static SignInRemoteDataSource provideSignInDataSource( @NonNull Context context ) {
    return SignInRemoteDataSource.getInstance();
  }


  public static SignUpRepository provideSignUpRepository( @NonNull Context context ) {
    return SignUpRepository.getInstance( SignUpRemoteDataSource.getInstance() );
  }

}
