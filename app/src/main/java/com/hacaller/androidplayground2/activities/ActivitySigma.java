package com.hacaller.androidplayground2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


import com.hacaller.androidplayground2.MainActivity;
import com.hacaller.androidplayground2.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ActivitySigma extends AppCompatActivity {

    @BindView(R.id.email) TextInputEditText edtEmail;
    @BindView(R.id.password)   TextInputEditText edtPassword;
    @BindView(R.id.btn_next)   Button btnNext;
    int speedSound = 123456;
    Intent intent;

    private static final String SANDBOX_ID = "d6fad0bc9c12da9710feb847a34b5d78df976d58727128dd5174f7a9f7428a7c";
    private static final String SANDBOX_SECRET = "9f541e7c41c3647e8282a7484a5381bd36686b68bd7adca8056ba0aa4ca61828";

    private static final String REDIRECT_URI = "alistaircaller-androidplayground2://coinbase-oauth";
    //OAuth.beginAuthorization(this, SANDBOX_ID, "user", REDIRECT_URI, null);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_sigma);
        ButterKnife.bind(this);

        edtEmail.setText(String.valueOf(speedSound));


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

    }





}
