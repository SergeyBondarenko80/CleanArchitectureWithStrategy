package com.yellowgears.sergeibondarenko.base.protocol;

import android.app.Activity;
import com.yellowgears.sergeibondarenko.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ErrorResultBase {

  private int code;
  private Collection< String > message;
  private String status;


  @Override
  public String toString() {

    if ( this.message == null ) {
      return "No message in response";
    }

    StringBuffer stringBuffer = new StringBuffer();

    Iterator iterator = new ArrayList( this.message ).iterator();

    while ( iterator.hasNext() ) {
      stringBuffer.append( ( ( String ) iterator.next() ) );
      if ( iterator.hasNext() ) {
        stringBuffer.append( ", " );
      }
    }
    return stringBuffer.toString();
  }


  public String showMessageAndProcessError( Activity activity ) {

    if ( this.message == null ) {
      return activity.getResources().getString( R.string.error_response );
    }

    StringBuffer stringBuffer = new StringBuffer();

    Iterator iterator = new ArrayList( this.message ).iterator();

    while ( iterator.hasNext() ) {
      stringBuffer.append( ( ( String ) iterator.next() ) );
      if ( iterator.hasNext() ) {
        stringBuffer.append( ", " );
      }
    }
    return stringBuffer.toString();
  }

  private void processErrorCode( Activity activity ) {
    switch ( code ) {
      case 401:
        break;
      default:
        break;
    }
  }
}
