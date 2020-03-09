package com.my.bookmart.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.my.bookmart.R;

public class StartActivity extends AppCompatActivity {
    private Button joinUsBtn,loginAsGuestBtn;
    private TextView tv_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        joinUsBtn = findViewById(R.id.join_us_btn);
        loginAsGuestBtn = findViewById(R.id.login_as_a_guest_btn);
        tv_login=findViewById(R.id.tv_login);

        joinUsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent=new Intent(StartActivity.this,RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent=new Intent(StartActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });

        loginAsGuestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent=new Intent(StartActivity.this,MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });

    }

}
