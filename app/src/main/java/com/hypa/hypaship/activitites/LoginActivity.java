package com.hypa.hypaship.activitites;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import com.hypa.hypaship.Constants;
import com.hypa.hypaship.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.username_txt_field)
    EditText userName;
    @BindView(R.id.depo_nr_txt_field)
    EditText depoField;
    @BindView(R.id.password_txt_field)
    EditText passwordField;
    @BindView(R.id.sign_in_button)
    Button signInButton;

    SharedPreferences sharedPreferences;

    boolean loogedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        loogedIn=sharedPreferences.getBoolean(Constants.LOGGED_IN,false);

        if(loogedIn) {
            goToMainActivity();
        }

        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        this.setListeners();
    }

    void setListeners() {

        signInButton.setAlpha(0.5f);
        signInButton.setEnabled(false);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String user_name = userName.getText().toString();
                String depo = depoField.getText().toString();
                String password = passwordField.getText().toString();

                if (!user_name.equals("") && !depo.equals("") && !password.equals("")) {
                    signInButton.setAlpha(1.0f);
                    signInButton.setEnabled(true);
                } else {
                    signInButton.setAlpha(0.5f);
                    signInButton.setEnabled(false);
                }

            }
        };

        userName.addTextChangedListener(textWatcher);
        depoField.addTextChangedListener(textWatcher);
        passwordField.addTextChangedListener(textWatcher);

    }


    @OnClick(R.id.sign_in_button)
    void onSignInButtonTaped() {

        String user_name = userName.getText().toString();
        String depo = depoField.getText().toString();
        String password = passwordField.getText().toString();

        if (user_name.length() < 3 || depo.length() < 3 || password.length() < 3) {
            // one of your field is incorrect, please check
            this.displayAlert(getResources().getString(R.string.incorrect_field));
        } else {

            saveCredentials(user_name,depo,password);

            goToMainActivity();

        }
    }

    private void goToMainActivity() {

        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        finish();

    }

    private void saveCredentials(String user_name, String depo, String password) {

       SharedPreferences.Editor editor= sharedPreferences.edit();

       editor.putBoolean(Constants.LOGGED_IN,true);
       editor.putString(Constants.LOGGED_USERNAME,user_name);
       editor.putString(Constants.LOGGED_DEPO,depo);

       editor.commit();

    }


    void displayAlert( String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
//        alertDialog.setTitle("Alert");
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }


}
