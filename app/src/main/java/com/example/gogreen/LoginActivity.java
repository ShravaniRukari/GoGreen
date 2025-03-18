package com.example.gogreen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity {
    ImageView ivLogo;
    TextView tvTitle,tvNewUser;
    EditText etUserName, etPassword;
    CheckBox chkShowHide;
    Button btnLogin;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ivLogo= findViewById(R.id.ivLoginLogo);
        tvTitle= findViewById(R.id.tvLoginTitle);
        tvNewUser= findViewById(R.id.tvLoginNewUser);
        etUserName= findViewById(R.id.etLoginUsername);
        etPassword= findViewById(R.id.etLoginPassword);
        chkShowHide= findViewById(R.id.cbLoginShowHidePassword);
        btnLogin= findViewById(R.id.btnLoginLogin);


        tvNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(i);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etUserName.getText().toString().isEmpty())
                {
                    etUserName.setError("Please Enter Your Name");
                } else if (!etUserName.getText().toString().contains("@")&& !etUserName.getText().toString().contains(".com") )
                {
                    etUserName.setError("Please Enter Name which contains @ and .com");
                } else if (etUserName.getText().toString().length()<8)
                {
                    etUserName.setError("Please Enter name Greater than 8 ");
                }
                 else if (etPassword.getText().toString().isEmpty())
                {
                    etPassword.setError("Please Enter Your Password");
                }
                else if (etPassword.getText().toString().length() < 8)
                {
                    etPassword.setError("Please Enter Password Greater than 8 ");
                }
                else
                {
                    progressDialog = new ProgressDialog(LoginActivity.this);
                    progressDialog.setTitle("PLease Wait");
                    progressDialog.setMessage("Login Under Process");
                    progressDialog.show();

                    userLogin();
                }

            }
        });
    }

    private void userLogin() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();


        params.put("Username",etUserName.getText().toString());
        params.put("password",etPassword.getText().toString());


        client.post("http://192.168.225.151:/NatureAPI/userregisterdetails.php",params,new JsonHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                try {
                    String status= response.getString("success");

                    if (status.equals("1"))
                    {

                       Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                       startActivity(intent);
                       finish();
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this,"Invalid Username or Password",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this,"Invalid Username or Password",Toast.LENGTH_SHORT).show();
            }

        }

        );



    }
}
