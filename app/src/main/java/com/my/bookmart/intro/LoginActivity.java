package com.my.bookmart.intro;

import androidx.appcompat.app.AppCompatActivity;

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

import com.my.bookmart.R;
import com.my.bookmart.activities.MainActivity;


public class LoginActivity extends AppCompatActivity {
    private EditText email,password;
    private Button loginBtn;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        TextView tvBack = findViewById(R.id.tv_back);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        loginBtn=findViewById(R.id.login_btn);

        tvBack.setOnClickListener(new View.OnClickListener() {
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
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEmailandPassword();
            }
        });
    }

    private void checkInput() {
        if (!TextUtils.isEmpty(email.getText())) {
            if (!TextUtils.isEmpty(password.getText())) {
                if (!TextUtils.isEmpty(password.getText()) && password.length() >= 8) {
                        loginBtn.setEnabled(true);
                    loginBtn.setTextColor(Color.parseColor("#ffffff"));
                } else {
                    loginBtn.setEnabled(false);
                    loginBtn.setTextColor(Color.parseColor("#50ffffff"));
                }
            } else {
                loginBtn.setEnabled(false);
                loginBtn.setTextColor(Color.parseColor("#50ffffff"));
            }
        } else {
            loginBtn.setEnabled(false);
            loginBtn.setTextColor(Color.parseColor("#50ffffff"));
        }
    }

    private void checkEmailandPassword() {
        if (email.getText().toString().matches(emailPattern)) {
            Intent mainIntent = new Intent(this, MainActivity.class);
            startActivity(mainIntent);

        } else {
            email.setError("Invalid email!");
        }
    }

}
