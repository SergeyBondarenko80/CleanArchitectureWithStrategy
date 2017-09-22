package com.yellowgears.sergeibondarenko.utils;


import static com.yellowgears.sergeibondarenko.utils.SystemUtils.showToast;

import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;
import com.yellowgears.sergeibondarenko.R;

public class Validation {

  private static final int MINIMUM_NUMBER_OF_CHARACTERS_IN_PASSWORD = 6;
  private static final int MINIMUM_PHONE_NUMBER = 7;
  private static final int MAXIMUM_PHONE_NUMBER = 16;


  public static boolean isValidEmail( Context ctx, CharSequence target ) {

    boolean isValid = !( TextUtils.isEmpty( target ) );

    if ( !isValid ) {
      showToast( ctx, R.string.enter_your_email );

      return false;
    }

    isValid = Patterns.EMAIL_ADDRESS.matcher( target ).matches();

    if ( !isValid ) {
      showToast( ctx, R.string.incorrect_email );

      return false;
    }

    return isValid;
  }


  public static boolean isValidPassword( String password, Context ctx ) {

    if ( password != null && password.length() > 5 ) {
      return true;
    }

    showToast( ctx, R.string.password_length );

    return false;
  }

  public static boolean isValidPasswordFields( String oldPassword, String newPassword,
      String repeatNewPassword, Context context ) {

    if ( oldPassword.length() == 0 ) {
      SystemUtils.showToast( context, R.string.enter_old_password );

      return false;
    }

    if ( newPassword.length() == 0 ) {
      SystemUtils.showToast( context, R.string.enter_new_password );

      return false;
    }

    if ( repeatNewPassword.length() == 0 ) {
      SystemUtils.showToast( context, R.string.repeat_new_password );

      return false;
    }

    if ( !newPassword.equals( repeatNewPassword ) ) {
      SystemUtils.showToast( context, R.string.passwords_do_not_match );

      return false;
    }

    if ( ( repeatNewPassword.length() >= 0 ) && ( repeatNewPassword.length()
        < MINIMUM_NUMBER_OF_CHARACTERS_IN_PASSWORD ) ) {
      SystemUtils.showToast( context, R.string.password_6_characters );

      return false;
    }

    if ( ( newPassword.length() >= 0 ) && ( newPassword.length()
        < MINIMUM_NUMBER_OF_CHARACTERS_IN_PASSWORD ) ) {
      SystemUtils.showToast( context, R.string.password_6_characters );

      return false;
    }

    return true;
  }


  public static boolean isPasswordsEqual( String password, String confirmPassword,
      Context context ) {

    if ( password.length() == 0 || confirmPassword.length() == 0 ) {
      SystemUtils.showToast( context, R.string.enter_your_password );

      return false;
    }

    if ( ( password.length() >= 0 ) && ( password.length()
        < MINIMUM_NUMBER_OF_CHARACTERS_IN_PASSWORD ) ) {
      SystemUtils.showToast( context, R.string.password_6_characters );

      return false;
    }

    if ( !password.equals( confirmPassword ) ) {
      SystemUtils.showToast( context, R.string.passwords_do_not_match );

      return false;
    }

    return true;
  }


  public static boolean isValidPhone( String phone, Context context ) {

    if ( phone.length() < MINIMUM_PHONE_NUMBER || phone.length() > MAXIMUM_PHONE_NUMBER ) {
      SystemUtils.showToast( context, R.string.enter_phone_number );

      return false;
    }

    return true;
  }

}
