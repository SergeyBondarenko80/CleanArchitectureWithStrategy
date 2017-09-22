package com.yellowgears.sergeibondarenko.startactivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;
import com.yellowgears.sergeibondarenko.R;
import com.yellowgears.sergeibondarenko.utils.ActivityUtils;
import com.yellowgears.sergeibondarenko.utils.SystemUtils;
import java.util.Timer;
import java.util.TimerTask;

public class StartActivity extends AppCompatActivity {

  private ICleanArchitectureAnimationCallback mICleanArchitectureAnimationCallback;

  private boolean isAnimationEnded;
  private boolean isTaskDefined;

  private Typeface customTypeface;
  private TextView tvCompanyName;
  private final static int ALPHA_ANIMATION_DURATION = 2000;


  @Override
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_splash );

    customTypeface = Typeface.createFromAsset( getAssets(), "avenir_next_bold.ttf" );

    tvCompanyName = ( TextView ) findViewById( R.id.tvCompanyName );
    tvCompanyName.setTypeface( customTypeface );

    if ( !SystemUtils.isOnline( this ) ) {
      SystemUtils
          .showToast( this, getResources().getString( R.string.trying_establish_connection ) );
    } else {
      startAppWrapper();
    }
  }


  @Override
  protected void onResume() {
    super.onResume();

    Timer mTimer = new Timer();
    TimerTask mMyTimerTask = new MyTimerTask();
    mTimer.schedule( mMyTimerTask, 1000 );
  }


  private void startAppWrapper() {
    startApp();
  }


  private void startApp() {
    mICleanArchitectureAnimationCallback = new ICleanArchitectureAnimationCallback() {
      @Override
      public void onAnimationCallBack() {
        ActivityUtils.openSignInActivityAndCloseStartActivity( StartActivity.this );
      }
    };

    if ( !isAnimationEnded ) {
      isTaskDefined = true;
    } else {
      mICleanArchitectureAnimationCallback.onAnimationCallBack();
    }
  }


  class MyTimerTask extends TimerTask {

    @Override
    public void run() {

      final AlphaAnimation alphaAnimation = new AlphaAnimation( 1.0f, 0.0f );
      alphaAnimation.setDuration( ALPHA_ANIMATION_DURATION );
      alphaAnimation.setRepeatCount( 0 );
      alphaAnimation.setRepeatMode( Animation.REVERSE );
      alphaAnimation.setAnimationListener( new Animation.AnimationListener() {
        @Override
        public void onAnimationStart( Animation animation ) {
        }

        @Override
        public void onAnimationEnd( Animation animation ) {
          findViewById( R.id.rlSplash ).setVisibility( View.GONE );

          if ( !isAnimationEnded && !isTaskDefined ) {

            isAnimationEnded = true;
          } else {
            mICleanArchitectureAnimationCallback.onAnimationCallBack();
          }
        }

        @Override
        public void onAnimationRepeat( Animation animation ) {

        }
      } );

      runOnUiThread( new Runnable() {
        @Override
        public void run() {
          findViewById( R.id.rlSplash ).startAnimation( alphaAnimation );
        }
      } );
    }
  }


  private interface ICleanArchitectureAnimationCallback {

    void onAnimationCallBack();
  }
}
