package com.hypa.hypaship.activitites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hypa.hypaship.R;


public class LoginActivity extends AppCompatActivity {

    boolean loggedIn=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent intent;

        if (loggedIn) {

            intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);

            finish();
        }
            else
            {
                setContentView(R.layout.activity_login);
            }
        }


}
