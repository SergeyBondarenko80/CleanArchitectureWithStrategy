package com.yellowgears.sergeibondarenko.utils;

import android.app.Activity;
import android.util.Log;
import com.google.gson.JsonObject;
import com.yellowgears.sergeibondarenko.R;
import com.yellowgears.sergeibondarenko.base.protocol.ErrorResultBase;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitHelper {

  /**
   * If you have a portion of the URL which is used in all queries, you can save it in a
   * retrofit like base url. Example
   * https://<name_of_the_site>/<settings>/sign_in
   * https://<name_of_the_site>/<settings>/sign_up
   * https://<name_of_the_site>/<settings>/logout
   * you can save "https://<name_of_the_site>/<settings>/" like base URL - field {@link SERVER_URL}.
   * The rest of the URL is used in the query interface.
   * "sign_up" in interface
   * @see com.yellowgears.sergeibondarenko.sign_up.datasource.request.interfacerequest.ISignUp,
   * "sign_in" in interface
   * @see com.yellowgears.sergeibondarenko.sign_in.datasource.request.interfacerequest.ISignIn,
   *
   */

  private static final String SERVER_URL = "";
  private static final int CONNECT_TIMEOUT = 60;
  private static final int READ_TIMEOUT = 60;
  private static final int INTERNAL_SERVER_ERROR = 500;
  private static final String LOG_TAG = "RetrofitHelper";
  private static final String LOG_LONG_RESPONSE = "LongResponse";
  private static final int MAXIMUM_NUMBER_OF_SYMBOLS_IN_LOG = 4000;

  private static final OkHttpClient httpClient = new OkHttpClient.Builder()
      .readTimeout( READ_TIMEOUT, TimeUnit.SECONDS )
      .connectTimeout( CONNECT_TIMEOUT, TimeUnit.SECONDS ).retryOnConnectionFailure( true ).build();

  private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl( SERVER_URL )
      .addConverterFactory( GsonConverterFactory.create() );


  public static < S > S createService( Class< S > serviceClass ) {
    Retrofit retrofit = builder.client( httpClient ).build();
    return retrofit.create( serviceClass );
  }


  public static Retrofit retrofit() {
    OkHttpClient client = httpClient;
    return builder.client( client ).build();
  }


  public static void processRetrofitErrorResponse( Activity activity,
      retrofit2.Response< JsonObject > response ) {

    // parse the response body …
    ErrorResultBase erb = ErrorUtils.parseError( response );

    // … and use it to show error information
    // … or just log the issue like we’re doing
    if ( response.code() != INTERNAL_SERVER_ERROR ) {

      if ( activity != null ) {
        SystemUtils.showToast( activity, erb.showMessageAndProcessError( activity ) );
        Log.d( LOG_TAG, activity.getString( R.string.message_error ) + erb
            .showMessageAndProcessError( activity ) );
      }

    } else {

      if ( activity != null ) {
        SystemUtils.showToast( activity, R.string.error_response );
        Log.d( LOG_TAG, activity.getString( R.string.message_error ) + R.string.error_response );
      }
    }
  }


  public static void longResponse( String str ) {

    if ( str.length() > MAXIMUM_NUMBER_OF_SYMBOLS_IN_LOG ) {

      Log.v( LOG_LONG_RESPONSE, "str.length = " + str.length() );
      int chunkCount = str.length() / MAXIMUM_NUMBER_OF_SYMBOLS_IN_LOG;

      for ( int i = 0; i <= chunkCount; i++ ) {
        int max = MAXIMUM_NUMBER_OF_SYMBOLS_IN_LOG * ( i + 1 );

        if ( max >= str.length() ) {

          Log.v( LOG_LONG_RESPONSE, "chunk " + i + " of " + chunkCount + ":" + str
              .substring( MAXIMUM_NUMBER_OF_SYMBOLS_IN_LOG * i ) + "END RESPONSE" );

        } else {

          Log.v( LOG_LONG_RESPONSE, "chunk " + i + " of " + chunkCount + ":" + str
              .substring( MAXIMUM_NUMBER_OF_SYMBOLS_IN_LOG * i, max ) + "END RESPONSE" );
        }
      }

    } else {
      Log.v( LOG_LONG_RESPONSE, str.toString() + "END RESPONSE" );
    }
  }
}
