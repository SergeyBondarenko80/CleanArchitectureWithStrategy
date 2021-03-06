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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.yellowgears.sergeibondarenko.R;
import com.yellowgears.sergeibondarenko.injection.Injection;
import com.yellowgears.sergeibondarenko.utils.ActivityUtils;


public class SignUpActivity extends AppCompatActivity {

  @Override
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.base_activity );

    // Toolbar Settings.
    TextView tvTitle = ( TextView ) findViewById( R.id.tvABTitle );
    tvTitle.setText( R.string.app_name );
    tvTitle.setTextSize( TypedValue.COMPLEX_UNIT_SP,
        getResources().getInteger( R.integer.action_bar_text_size ) );

    RelativeLayout rlArrow = ( RelativeLayout ) findViewById( R.id.rlArrow );
    rlArrow.setOnClickListener( new OnClickListener() {
      @Override
      public void onClick( View view ) {
        onBackPressed();
      }
    } );

    // Create fragment
    SignUpFragment signUpFragment = ( SignUpFragment ) getSupportFragmentManager()
        .findFragmentById( R.id.flRoot );

    if ( signUpFragment == null ) {
      signUpFragment = SignUpFragment.newInstance();

      Bundle bundle = new Bundle();
      signUpFragment.setArguments( bundle );

      ActivityUtils
          .addFragmentToActivity( getSupportFragmentManager(), signUpFragment, R.id.flRoot );
    }

    // Create presenter
    new SignUpPresenter( Injection.provideSignUpRepository( SignUpActivity.this ), signUpFragment );
  }


  @Override
  public boolean onSupportNavigateUp() {
    onBackPressed();
    return true;
  }


  @Override
  public void onBackPressed() {
    if ( getSupportFragmentManager().getBackStackEntryCount() == 1 ) {
      finish();
    } else if ( getSupportFragmentManager().getBackStackEntryCount() == 2 ) {
      finish();
    } else {
      super.onBackPressed();
    }
  }
}
