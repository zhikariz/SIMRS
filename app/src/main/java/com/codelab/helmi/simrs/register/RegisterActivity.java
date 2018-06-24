package com.codelab.helmi.simrs.register;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codelab.helmi.simrs.R;
import com.codelab.helmi.simrs.api.RestApi;
import com.codelab.helmi.simrs.api.RestServer;

import java.io.Console;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etNoRm, etPassword, etPassword2;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    private void initView() {
        etNoRm = (EditText) findViewById(R.id.et_no_rm);
        etPassword = (EditText) findViewById(R.id.et_password);
        etPassword2 = (EditText) findViewById(R.id.et_password2);

        btnRegister = (Button) findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(this);
    }

    private void addUser(){
        String no_rm = etNoRm.getText().toString();
        String password = etPassword.getText().toString();
        String password2 = etPassword2.getText().toString();

        RestApi api = RestServer.getClient().create(RestApi.class);
        Call<RegisterResponseModel> postData = api.registerUser(no_rm, password, password2);
        postData.enqueue(new Callback<RegisterResponseModel>() {
            @Override
            public void onResponse(Call<RegisterResponseModel> call, Response<RegisterResponseModel> response) {
                Toast.makeText(RegisterActivity.this, response.body().getResult().toString(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<RegisterResponseModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_register:
                addUser();
            break;
        }
    }
}
