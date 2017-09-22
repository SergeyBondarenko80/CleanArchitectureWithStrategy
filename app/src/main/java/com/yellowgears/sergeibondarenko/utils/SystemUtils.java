package com.yellowgears.sergeibondarenko.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.yellowgears.sergeibondarenko.R;
import com.yellowgears.sergeibondarenko.sign_in.SignInActivity;


public class SystemUtils {

  private static String lastToastText;
  private static Toast toast;


  public static void showToast( Context context, String text ) {

    //Create custom view for toast.
    LayoutInflater inflater = ( LayoutInflater ) context
        .getSystemService( Context.LAYOUT_INFLATER_SERVICE );
    View toastView = inflater.inflate( R.layout.custom_toast, null );
    TextView tvToastText = toastView.findViewById( R.id.tvToastText );
    tvToastText.setText( text );

    if ( text == null || text.isEmpty() || text.equalsIgnoreCase( "" ) ) {
      text = "Is empty";
    }

    if ( toast == null ) {

      lastToastText = text;
      toast = Toast.makeText( context, text, Toast.LENGTH_SHORT );
      toast.setGravity( 16, 0, 0 );
      toast.setView( toastView );
      toast.show();

    } else if ( text == null && text.equals( lastToastText ) ) {

      toast.setView( toastView );
      toast.show();

    } else {

      lastToastText = text;
      toast = Toast.makeText( context, text, Toast.LENGTH_SHORT );
      toast.setGravity( 16, 0, 0 );
      toast.setView( toastView );
      toast.show();
    }
  }


  public static void showToast( Context context, int id ) {

    if ( context != null ) {
      String text = context.getResources().getString( id );

      //Create custom view for toast.
      LayoutInflater inflater = ( LayoutInflater ) context
          .getSystemService( Context.LAYOUT_INFLATER_SERVICE );
      View toastView = inflater.inflate( R.layout.custom_toast, null );
      TextView tvToastText = toastView.findViewById( R.id.tvToastText );
      tvToastText.setText( text );

      if ( text == null || text.isEmpty() || text.equalsIgnoreCase( "" ) ) {
        text = "Is empty";
      }

      if ( toast == null ) {

        lastToastText = text;
        toast = Toast.makeText( context, text, Toast.LENGTH_SHORT );
        toast.setGravity( 16, 0, 0 );
        toast.setView( toastView );
        toast.show();

      } else if ( text == null && text.equals( lastToastText ) ) {

        toast.setView( toastView );
        toast.show();

      } else {

        lastToastText = text;
        toast = Toast.makeText( context, text, 1 );
        toast.setGravity( 16, 0, 0 );
        toast.setView( toastView );
        toast.show();
      }
    }
  }


  public static boolean isOnline( Context context ) {
    ConnectivityManager cm = ( ConnectivityManager ) context.getSystemService( "connectivity" );
    return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting();
  }


  public static boolean isTokenExist( String token, Context context ) {

    if ( token == null || token.equals( "" ) ) {
      SystemUtils.showToast( context, R.string.please_sign_in );
      ActivityUtils.openSignInActivity( ( Activity ) context );

      return false;
    }

    return true;
  }


  public static boolean isNotLoggedIn( Context context ) {
    SharedPreferences prefs = context
        .getSharedPreferences( Constants.CLEAN_ARCHITECTURE_WITH_STRATEGY, Context.MODE_PRIVATE );
    final String token = prefs.getString( Constants.TOKEN, "" );

    if ( token.equals( "" ) ) {
      showToast( context, R.string.please_login );
      boolean noOpenMainActivity = true;
      Intent i = new Intent( context, SignInActivity.class );
      i.putExtra( Constants.DO_NOT_OPEN_MAIN_ACTIVITY, noOpenMainActivity );
      context.startActivity( i );
      return true;
    } else {

      return false;
    }
  }

}
