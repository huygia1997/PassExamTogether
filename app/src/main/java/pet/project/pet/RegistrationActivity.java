package pet.project.pet;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import javax.xml.transform.Result;

import pet.project.pet.model.ResObj;
import pet.project.pet.model.User;
import pet.project.pet.remote.ApiUtils;
import pet.project.pet.remote.UserService;
import pet.project.pet.sharedPrefApp.SharedPrefApp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {
    EditText edtResUsername, edtResPassword, edtResConfirmPass, edtResDisplayName;
    CheckBox chkTuitor;
    Button btnRegis;
    UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setTitle("Registration");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        edtResUsername = (EditText) findViewById(R.id.edtResUsername);
        edtResPassword = (EditText) findViewById(R.id.edtResPassword);
        edtResConfirmPass = (EditText) findViewById(R.id.edtResConfirmPass);
        edtResDisplayName = (EditText) findViewById(R.id.edtResDisplayName);
        chkTuitor = (CheckBox) findViewById(R.id.chkTuitor);
        btnRegis = (Button) findViewById(R.id.btnRegis);
        userService = ApiUtils.getUserService();

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtResUsername.getText().toString();
                String password = edtResPassword.getText().toString();
                String comfirmPass = edtResConfirmPass.getText().toString();
                String displayName = edtResDisplayName.getText().toString();
                int roleId = 2;
                if (chkTuitor.isChecked()) {
                    roleId = 4;
                }

                if (validationRegistration(username, password, comfirmPass)) {
                    doRegistration(username, password, displayName, roleId);
                } else {
                    return;
                }
            }
        });
    }

    private boolean validationRegistration(String username, String password, String comfirmPass) {
        if (username.trim() == null || username.trim().length() == 0) {
            Toast.makeText(this, "Username is required!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.trim() == null || password.trim().length() == 0) {
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!comfirmPass.equals(password)) {
            Toast.makeText(this, "Confirm Password is not matched!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (checkExistedUsername(username)) {
            Toast.makeText(this, "Account is existed!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private boolean checkExistedUsername(String username) {
        Call<ResObj> call = userService.checkExistedUsername(username);
        ResObj resObj = null;
        try {
            resObj = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resObj.isMessage();
    }

    private void doRegistration(String username, String password, String displayName, int roleId) {
        User newUser = new User(username, password, displayName, roleId);
        Call<ResObj> call = userService.createUser(newUser);
        call.enqueue(new Callback<ResObj>() {
            @Override
            public void onResponse(Call<ResObj> call, Response<ResObj> response) {
                if (response.isSuccessful()) {
                    ResObj resObj = response.body();
                    if (resObj.isMessage()) {
                        Toast.makeText(RegistrationActivity.this, "Register successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(RegistrationActivity.this, "Register fail!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegistrationActivity.this, "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResObj> call, Throwable t) {
                Toast.makeText(RegistrationActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
