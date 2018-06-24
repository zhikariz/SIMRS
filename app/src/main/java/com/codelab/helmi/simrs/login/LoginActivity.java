package com.codelab.helmi.simrs.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codelab.helmi.simrs.HomeActivity;
import com.codelab.helmi.simrs.R;
import com.codelab.helmi.simrs.api.RestApi;
import com.codelab.helmi.simrs.api.RestServer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    EditText etNoRm, etPassword;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        etNoRm = (EditText) findViewById(R.id.et_no_rm);
        etPassword = (EditText) findViewById(R.id.et_password);

        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
    }

    private void loginUser(){
        String no_rm = etNoRm.getText().toString();
        String password = etPassword.getText().toString();

        RestApi api = RestServer.getClient().create(RestApi.class);
        Call<LoginResponseModel> postData = api.loginUser(no_rm, password);
        postData.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                Toast.makeText(LoginActivity.this, response.body().getResult().toString(), Toast.LENGTH_SHORT).show();
                if (response.body().getResult().toString().equals("Login Berhasil")){
                    
                    Intent i = new Intent(LoginActivity.this, HomeActivity.class);

                    startActivity(i);

                } else {
                    Toast.makeText(LoginActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                loginUser();
                break;
        }
    }
}
