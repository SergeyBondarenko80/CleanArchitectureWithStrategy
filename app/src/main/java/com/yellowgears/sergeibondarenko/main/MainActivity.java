package com.yellowgears.sergeibondarenko.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.yellowgears.sergeibondarenko.R;
import com.yellowgears.sergeibondarenko.utils.SystemUtils;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_main );

    TextView tvTitle = ( TextView ) findViewById( R.id.tvABTitle );
    tvTitle.setText( R.string.main_activity );
    tvTitle.setTextSize( TypedValue.COMPLEX_UNIT_SP,
        getResources().getInteger( R.integer.action_bar_text_size ) );

    RelativeLayout rlArrow = ( RelativeLayout ) findViewById( R.id.rlArrow );
    rlArrow.setOnClickListener( new OnClickListener() {
      @Override
      public void onClick( View view ) {
        onBackPressed();
      }
    } );

    SystemUtils.showToast( this, "Open MainActivity" );
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
  }
}
