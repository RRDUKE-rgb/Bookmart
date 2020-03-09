package com.my.bookmart.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.my.bookmart.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText email,fullname,password,confirmPassword;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    private Button joinBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        TextView tv_back=(TextView)findViewById(R.id.tv_back);
        email=findViewById(R.id.sign_up_email);
        fullname=findViewById(R.id.sign_up_fullname);
        password=findViewById(R.id.sign_up_password);
        confirmPassword=findViewById(R.id.sign_up_confirm_password);
        joinBtn=findViewById(R.id.sign_up_btn);

        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                checkInput();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        fullname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInput();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInput();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        confirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInput();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEmailandPassword();
            }
        });
    }

    private void checkInput() {
        if (!TextUtils.isEmpty(email.getText())) {
            if (!TextUtils.isEmpty(fullname.getText())) {
                if (!TextUtils.isEmpty(password.getText()) && password.length() >= 8) {
                    if (!TextUtils.isEmpty(confirmPassword.getText())) {
                        joinBtn.setEnabled(true);
                        joinBtn.setTextColor(Color.parseColor("#ffffff"));
                    } else {
                        joinBtn.setEnabled(false);
                        joinBtn.setTextColor(Color.parseColor("#50ffffff"));
                    }
                } else {
                    joinBtn.setEnabled(false);
                    joinBtn.setTextColor(Color.parseColor("#50ffffff"));
                }
            } else {
                joinBtn.setEnabled(false);
                joinBtn.setTextColor(Color.parseColor("#50ffffff"));
            }
        } else {
            joinBtn.setEnabled(false);
            joinBtn.setTextColor(Color.parseColor("#50ffffff"));
        }
    }

    private void checkEmailandPassword() {
        if (email.getText().toString().matches(emailPattern)) {
            if (password.getText().toString().equals(confirmPassword.getText().toString())) {

                mainIntent();

            } else {
                confirmPassword.setError("password doesn't matched !");
            }
        } else {
            email.setError("Invalid email!");
        }
    }

    private void mainIntent() {
            Intent mainIntent = new Intent(this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        }
}
