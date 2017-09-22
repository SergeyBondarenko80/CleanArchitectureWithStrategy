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

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hbb20.CountryCodePicker;
import com.yellowgears.sergeibondarenko.R;
import com.yellowgears.sergeibondarenko.utils.ActivityUtils;
import com.yellowgears.sergeibondarenko.utils.SystemUtils;


public class SignUpFragment extends Fragment implements SignUpContract.View {

  private SignUpContract.Presenter mPresenter;

  private EditText etPassword;
  private EditText etEmail;
  private EditText etConfirmPassword;
  private EditText etName;
  private EditText etPhoneNumber;

  private TextView tvEmailValid;
  private TextView tvPasswordValid;
  private TextView tvConfirmPasswordValid;
  private TextView tvNameValid;
  private TextView tvPhoneValid;

  private LinearLayout llPhoneNumber;
  private LinearLayout llAlreadyHaveAccount;

  private CountryCodePicker countryCodePicker;

  private Button btnSignUp;


  public static SignUpFragment newInstance() {
    return new SignUpFragment();
  }


  public SignUpFragment() {
  }


  @Override
  public void onResume() {
    super.onResume();
    mPresenter.start();
  }


  @Override
  public void setPresenter( @NonNull SignUpContract.Presenter presenter ) {
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

    View root = inflater.inflate( R.layout.fragment_sign_up, container, false );

    etPassword = root.findViewById( R.id.etPasswordSignUp );
    etEmail = root.findViewById( R.id.etEmailSignUp );
    etConfirmPassword = root.findViewById( R.id.etConfirmPasswordSignUp );
    etName = root.findViewById( R.id.etNameSignUp );
    etPhoneNumber = root.findViewById( R.id.etPhoneNumberSignUp );

    tvEmailValid = root.findViewById( R.id.tvEmailValidSignUp );
    tvPasswordValid = root.findViewById( R.id.tvPasswordValidSignUp );
    tvConfirmPasswordValid = root.findViewById( R.id.tvConfirmPasswordValidSignUp );
    tvNameValid = root.findViewById( R.id.tvNameValidSignUp );
    tvPhoneValid = root.findViewById( R.id.tvPhoneValidSignUp );

    llPhoneNumber = root.findViewById( R.id.llPhoneNumberSignUp );

    llAlreadyHaveAccount = root.findViewById( R.id.llAlreadyHaveAccount );
    llAlreadyHaveAccount.setOnClickListener( new View.OnClickListener() {
      @Override
      public void onClick( View v ) {
        ActivityUtils.openSignInActivity( getActivity() );
      }
    } );

    countryCodePicker = root.findViewById( R.id.countryCodePicker );
    countryCodePicker.setDialogKeyboardAutoPopup( false );
    countryCodePicker.setOnCountryChangeListener( new CountryCodePicker.OnCountryChangeListener() {
      @Override
      public void onCountrySelected() {
        SystemUtils.showToast( getActivity(),
            getString( R.string.updated ) + " " + countryCodePicker.getSelectedCountryName() );
      }
    } );

    btnSignUp = root.findViewById( R.id.btnSignUp );
    btnSignUp.setOnClickListener( new View.OnClickListener() {
      @Override
      public void onClick( View v ) {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();
        String name = etName.getText().toString();
        String phoneNumber =
            "+" + countryCodePicker.getSelectedCountryCode() + etPhoneNumber.getText().toString();

        mPresenter.SignUp( email, password, confirmPassword, name, phoneNumber, getActivity() );
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

    tvPasswordValid.setText( R.string.enter_valid_email_address );

    etPassword.setBackground(
        ContextCompat.getDrawable( getActivity(), R.drawable.edit_text_background_error ) );
  }


  @Override
  public void setPasswordBackground() {

    tvPasswordValid.setText( "" );

    etPassword.setBackground(
        ContextCompat.getDrawable( getActivity(), R.drawable.edit_text_background ) );
  }


  @Override
  public void setConfirmPasswordBackgroundError() {

    tvConfirmPasswordValid.setText( R.string.enter_valid_email_address );

    etConfirmPassword.setBackground(
        ContextCompat.getDrawable( getActivity(), R.drawable.edit_text_background_error ) );
  }


  @Override
  public void setConfirmPasswordBackground() {

    tvConfirmPasswordValid.setText( "" );

    etConfirmPassword.setBackground(
        ContextCompat.getDrawable( getActivity(), R.drawable.edit_text_background ) );
  }


  @Override
  public void setBackgroundErrorIfPasswordNotEqual() {

    tvPasswordValid.setText( R.string.password_is_not_the_same );

    etPassword.setBackground(
        ContextCompat.getDrawable( getActivity(), R.drawable.edit_text_background_error ) );

    tvConfirmPasswordValid.setText( R.string.password_is_not_the_same );

    etConfirmPassword.setBackground(
        ContextCompat.getDrawable( getActivity(), R.drawable.edit_text_background_error ) );
  }


  @Override
  public void setBackgroundIfPasswordEqual() {

    tvPasswordValid.setText( "" );
    tvConfirmPasswordValid.setText( "" );

    etPassword.setBackground(
        ContextCompat.getDrawable( getActivity(), R.drawable.edit_text_background ) );

    etConfirmPassword.setBackground(
        ContextCompat.getDrawable( getActivity(), R.drawable.edit_text_background ) );
  }


  @Override
  public void setNameBackgroundError() {

    tvNameValid.setText( R.string.choose_yourself_name );

    etName.setBackground(
        ContextCompat.getDrawable( getActivity(), R.drawable.edit_text_background_error ) );
  }


  @Override
  public void setNameBackground() {

    tvNameValid.setText( "" );

    etName.setBackground(
        ContextCompat.getDrawable( getActivity(), R.drawable.edit_text_background ) );
  }


  @Override
  public void setPhoneNumberBackgroundError() {

    tvPhoneValid.setText( R.string.enter_phone_number );

    llPhoneNumber.setBackground(
        ContextCompat.getDrawable( getActivity(), R.drawable.edit_text_background_error ) );

    etPhoneNumber.setBackground( ContextCompat
        .getDrawable( getActivity(), R.drawable.edit_text_background_error_not_round ) );
  }


  @Override
  public void setPhoneNumberBackground() {

    tvPhoneValid.setText( "" );

    llPhoneNumber.setBackground(
        ContextCompat.getDrawable( getActivity(), R.drawable.edit_text_background ) );

    etPhoneNumber.setBackground(
        ContextCompat.getDrawable( getActivity(), R.drawable.edit_text_background_not_rounded ) );
  }
}
