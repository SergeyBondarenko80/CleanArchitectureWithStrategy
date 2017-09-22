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

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.yellowgears.sergeibondarenko.R;
import com.yellowgears.sergeibondarenko.utils.ActivityUtils;


public class SignInFragment extends Fragment implements SignInContract.View {

  private SignInContract.Presenter mPresenter;

  private TextView tvEmailValid;
  private TextView tvPasswordValid;
  private EditText etEmail;
  private EditText etPassword;
  private Button btnSignIn;
  private Button btnSignUp;
  private Button btnForgotPassword;
  private Button btnSkip;


  public static SignInFragment newInstance() {
    return new SignInFragment();
  }


  public SignInFragment() {
  }


  @Override
  public void onResume() {
    super.onResume();
    mPresenter.start();
  }


  @Override
  public void setPresenter( SignInContract.Presenter presenter ) {
    mPresenter = checkNotNull( presenter );
  }


  @Override
  public void onActivityCreated( Bundle savedInstanceState ) {
    super.onActivityCreated( savedInstanceState );
  }


  @Nullable
  @Override
  public View onCreateView( LayoutInflater inflater, final ViewGroup container,
      Bundle savedInstanceState ) {

    View root = inflater.inflate( R.layout.fragment_sign_in, container, false );

    tvEmailValid = root.findViewById( R.id.tvEmailValid );
    tvPasswordValid = root.findViewById( R.id.tvPasswordValid );
    etEmail = root.findViewById( R.id.etEmailSignIn );
    etPassword = root.findViewById( R.id.etPasswordSignIn );
    btnSignIn = root.findViewById( R.id.btnSignIn );

    btnSignIn.setOnClickListener( new View.OnClickListener() {
      @Override
      public void onClick( View v ) {
        final String email = etEmail.getText().toString();
        final String password = etPassword.getText().toString();

        mPresenter.signIn( email, password, getActivity() );
      }
    } );

    btnForgotPassword = root.findViewById( R.id.btnForgotPassword );
    btnForgotPassword.setOnClickListener( new View.OnClickListener() {
      @Override
      public void onClick( View v ) {
        ActivityUtils.openForgetPassword( getActivity() );
      }
    } );

    btnSignUp = root.findViewById( R.id.btnSignUp );
    btnSignUp.setOnClickListener( new View.OnClickListener() {
      @Override
      public void onClick( View v ) {
        ActivityUtils.openSignUpActivity( getActivity() );
      }
    } );

    btnSkip = root.findViewById( R.id.btnSkip );
    btnSkip.setOnClickListener( new View.OnClickListener() {
      @Override
      public void onClick( View view ) {
        ActivityUtils.openMainActivity( getActivity() );
      }
    } );

    return root;
  }


  @Override
  public void setEmailBackgroundError() {

    tvEmailValid.setText( R.string.enter_valid_email_address );

    etEmail.setBackground(
        ContextCompat.getDrawable( getActivity(), R.drawable.edit_text_background_error ) );
  }


  @Override
  public void setEmailBackground() {

    tvEmailValid.setText( "" );

    etEmail.setBackground(
        ContextCompat.getDrawable( getActivity(), R.drawable.edit_text_background ) );
  }


  @Override
  public void setPasswordBackgroundError() {

    tvPasswordValid.setText( R.string.enter_valid_password );

    etPassword.setBackground(
        ContextCompat.getDrawable( getActivity(), R.drawable.edit_text_background_error ) );
  }


  @Override
  public void setPasswordBackground() {

    tvPasswordValid.setText( "" );

    etPassword.setBackground(
        ContextCompat.getDrawable( getActivity(), R.drawable.edit_text_background ) );
  }
}
