package com.example.gogreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class RegistrationActivity extends AppCompatActivity {

    EditText etName,etMobileNo,etEmailID,etUserName,etPassword;
    Button btnRegister;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
   ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        preferences = PreferenceManager.getDefaultSharedPreferences(RegistrationActivity.this);
        editor= preferences.edit();

        etName=findViewById(R.id.etRegisterName);
        etMobileNo=findViewById(R.id.etRegisterMobileno);
        etEmailID=findViewById(R.id.etRegisterEmailid);
        etUserName=findViewById(R.id.etRegisterUserName);
        etPassword=findViewById(R.id.etRegisterPassWord);
        btnRegister=findViewById(R.id.btnRegisterRegister);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etName.getText().toString().isEmpty()) {
                    etName.setError("please enter your name");
                } else if (etMobileNo.getText().toString().isEmpty()) {
                    etMobileNo.setError("please enter your Mobile no ");
                } else if (etMobileNo.getText().toString().length() < 10) {
                    etMobileNo.setError("Invalid Mobile no ");
                } else if (etEmailID.getText().toString().isEmpty()) {
                    etEmailID.setError("please enter your EmailID  ");
                } else if (!etEmailID.getText().toString().contains("@") ||
                        !etEmailID.getText().toString().contains(".com")) {
                    etEmailID.setError("please enter Valid EmailID  ");
                } else if (etUserName.getText().toString().isEmpty()) {
                    etUserName.setError("please enter your UserName  ");
                } else if (etPassword.getText().toString().isEmpty()) {
                    etPassword.setError("please enter your Password  ");
                } else if (etUserName.getText().toString().length() < 8) {
                    etUserName.setError("Invalid Username  ");
                } else if (!etUserName.getText().toString().matches(".*[A-Z].*")) {
                    etUserName.setError("Please Enter Atleast 1 Uppercase Letter");
                } else if (!etUserName.getText().toString().matches(".*[a-z].*")) {
                    etUserName.setError("Please Enter Atleast 1 Lowercase Letter");
                } else if (!etUserName.getText().toString().matches(".*[0-9].*")) {
                    etUserName.setError("Please Enter Atleast 1 number");
                } else if (!etUserName.getText().toString().matches(".*[@,#,$,%,&].*")) {
                    etUserName.setError("Please Enter Atleast 1 Special Symbol");
                } else if (etPassword.getText().toString().isEmpty()) {
                    etPassword.setError("please enter your Password");
                } else if (etPassword.getText().toString().length() < 8) {
                    etPassword.setError(" Password should be greater than 8 characters");
                } else {
                    progressDialog = new ProgressDialog(RegistrationActivity.this);
                    progressDialog.setTitle("Please Wait");
                    progressDialog.setMessage(" Registration is in Process");
                    progressDialog.setCanceledOnTouchOutside(true);
                    progressDialog.show();
                    userRegisterDetails();
                }
            }
        });
    }




            private void userRegisterDetails() {

// Client server communiction over the network
        AsyncHttpClient client= new AsyncHttpClient();// client and server communication
        RequestParams params = new RequestParams();//put the data
        params.put("name", etName.getText().toString());
         params.put("mobile_no", etMobileNo.getText().toString());
         params.put("emailid",etEmailID.getText().toString());
         params.put("user_name", etUserName.getText().toString());
         params.put("password", etPassword.getText().toString());


         client.post("http://192.168.32.151:80/NatureAPI/userregisterdetails.php",params,new
                 JsonHttpResponseHandler() {
                     @Override
                     public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                         super.onSuccess(statusCode, headers, response);
                         try {
                             String status = response.getString("success");
                             if (status.equals("1")) {
                                progressDialog.dismiss();
                                 Toast.makeText(RegistrationActivity.this, "Registration Successfully Done", Toast.LENGTH_SHORT).show();
                                 Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                                 startActivity(intent);
                             } else {
                                progressDialog.dismiss();
                                 Toast.makeText(RegistrationActivity.this, "Already Data Present", Toast.LENGTH_SHORT).show();
                             }
                         } catch (JSONException e) {
                             throw new RuntimeException(e);
                         }
                     }

                     @Override
                     public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                         super.onFailure(statusCode, headers, throwable, errorResponse);
                       progressDialog.dismiss();
                         Toast.makeText(RegistrationActivity.this,"Server Error",Toast.LENGTH_SHORT);
                     }
                 });

            }

        }


