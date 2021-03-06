package pet.project.pet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import pet.project.pet.model.ResObj;
import pet.project.pet.model.User;
import pet.project.pet.remote.ApiUtils;
import pet.project.pet.remote.UserService;
import pet.project.pet.sharedPrefApp.SharedPrefApp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    TextView txtRes;
    EditText edtUsername, edtPassword;
    Button btnLogin;
    UserService userService;

    private boolean Logged = false;
    private int currentUserId = -1, currentUserRoleId = -1;
    private String LOGGED_ACCOUNT = "LOGGED_ACCOUNT";
    private String IS_LOGGED = "IS_LOGGED";
    private String USER_ID = "CURRENT_USER_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        userService = ApiUtils.getUserService();

        setTitle("Login");

        txtRes = (TextView) findViewById(R.id.txtRegistration);
        txtRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();

                //validation
                if (validationLogin(username, password)) {
                    doLogin(username, password);
                }
            }
        });

        /*SharedPreferences sharedPreferences = getSharedPreferences(LOGGED_ACCOUNT, Context.MODE_PRIVATE);
        boolean isLogged = sharedPreferences.getBoolean(IS_LOGGED, false);
        Logged = isLogged;
        if (Logged) {
            Intent intent = new Intent(this, FrameActivity.class);
            startActivity(intent);
            finish();
        }*/

        SharedPrefApp sharedPref;
        sharedPref = SharedPrefApp.getInstance();
        if (sharedPref.getISLogged_IN(getApplicationContext())) {
            Intent NextScreen = new Intent(getApplicationContext(),
                    FrameActivity.class);
            startActivity(NextScreen);
            finish();
        }
        /*else{
            Intent intent = new Intent(LoginActivity.this, FrameActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();

        }*/

    }

    private boolean validationLogin(String username, String password) {
        if (username == null || username.trim().length() == 0) {
            Toast.makeText(this, "Username is required!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password == null || password.trim().length() == 0) {
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void doLogin(final String username, final String password) {
        Call<ResObj> call = userService.getUser(username, password);
        call.enqueue(new Callback<ResObj>() {
            @Override
            public void onResponse(Call<ResObj> call, Response<ResObj> response) {
                if (response.isSuccessful()) {
                    ResObj resObj = response.body();
                    if (resObj.isMessage()) {
                        Logged = true;
                        currentUserId = resObj.getCurrentUserId();
                        currentUserRoleId = resObj.getCurrentUserRoleId();

                        SharedPrefApp sharedPref;
                        sharedPref = SharedPrefApp.getInstance();

                        sharedPref.saveISLogged_IN(getApplicationContext(), true, currentUserId, currentUserRoleId);

                        Toast.makeText(LoginActivity.this, "Login successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, FrameActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "The Username or Password is incorrect!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResObj> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
