package com.agendacontato.leandro.agendacontato;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class SplashActivity extends Activity implements Runnable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler SplashScreen = new Handler();
        SplashScreen.postDelayed(SplashActivity.this, 5000);
    }

    @Override
    public void run() {
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        finish();

    }



}
