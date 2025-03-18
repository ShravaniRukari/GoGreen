package com.example.gogreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class VerifyOTPActivity extends AppCompatActivity {

    TextView tvMobileNo,tvResentOTP;

    EditText etInputCode1 , etInputCode2,etInputCode3,etInputCode4,etInputCode5,etInputCode6;
    AppCompatButton btnVerify;

                ProgressDialog progressDialog;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_verify_otpactivity);

            tvMobileNo= findViewById(R.id.tvVerifyOTPMobileno);
            tvResentOTP= findViewById(R.id.tvVerifyOTPResendOTP);
            etInputCode1=findViewById(R.id.etVerifyOTPInputCode1);
            etInputCode2=findViewById(R.id.etVerifyOTPInputCode2);
            etInputCode3=findViewById(R.id.etVerifyOTPInputCode3);
            etInputCode4=findViewById(R.id.etVerifyOTPInputCode4);
            etInputCode5=findViewById(R.id.etVerifyOTPInputCode5);
            etInputCode6=findViewById(R.id.etVerifyOTPInputCode6);
            btnVerify=findViewById(R.id.acbtnVerifyOTPVerify);

            setInputOTP();

    }

    private void setInputOTP() {
            etInputCode1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                    if (!s.toString().trim().isEmpty())
                    {
                        etInputCode2.requestFocus();
                    }

                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            etInputCode2.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if(!charSequence.toString().trim().isEmpty()){
                        etInputCode3.requestFocus();
                    }

                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            etInputCode3.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (!charSequence.toString().trim().isEmpty()){
                        etInputCode4.requestFocus();
                    }

                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

        etInputCode4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()){
                    etInputCode5.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etInputCode5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()){
                    etInputCode6.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}