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
import java.util.List;

import pet.project.pet.model.ResObj;
import pet.project.pet.model.User;
import pet.project.pet.remote.ApiUtils;
import pet.project.pet.remote.UserService;
import pet.project.pet.sharedPrefApp.SharedPrefApp;
import retrofit2.Call;

public class AccountActivity extends AppCompatActivity {

    EditText edtEditUserPassword, edtEditUserConfrimPassword, edtEditUserDisplayName;
    CheckBox chkEditUserTuitor;
    Button btnEditUser;
    UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        setTitle("Edit User");
        edtEditUserPassword = findViewById(R.id.edtEditUserPassword);
        edtEditUserConfrimPassword = findViewById(R.id.edtEditUserConfrimPassword);
        edtEditUserDisplayName = findViewById(R.id.edtEditUserDisplayName);
        chkEditUserTuitor = findViewById(R.id.chkEditUserTuitor);
        btnEditUser = findViewById(R.id.btnEditUser);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        userService = ApiUtils.getUserService();

        SharedPrefApp sharedPrefApp = SharedPrefApp.getInstance();
        int userId = sharedPrefApp.getCurrentUserId(getApplicationContext());

        final User currentUser = getCurrentUser(userId);
        if (currentUser != null) {
            edtEditUserPassword.setText(currentUser.getPassword());
            if (currentUser.getDisplayName() != null) {
                edtEditUserDisplayName.setText(currentUser.getDisplayName());
            }
            if (currentUser.getRoleId() == 3) {
                chkEditUserTuitor.setVisibility(View.GONE);
            }
        }

        btnEditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass = edtEditUserPassword.getText().toString();
                String comfrim = edtEditUserConfrimPassword.getText().toString();
                String displayName = edtEditUserDisplayName.getText().toString();
                int roleId = currentUser.getRoleId();
                if (chkEditUserTuitor.isChecked()) {
                    roleId = 4;
                }
                currentUser.setPassword(pass);
                currentUser.setDisplayName(displayName);
                currentUser.setRoleId(roleId);
                if (validToEditUser(pass, comfrim)) {
                    updateUser(currentUser);
                    Toast.makeText(AccountActivity.this, "Update Successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AccountActivity.this, FrameActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    private boolean validToEditUser(String pass, String confirm) {
        if (pass.trim().isEmpty() || pass.trim() == "") {
            Toast.makeText(this, "Password is required!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!confirm.equals(pass)) {
            Toast.makeText(this, "Confirm Password is not matched!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void updateUser(User user) {
        Call<ResObj> call = userService.updateUser(user);
        try {
            call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private User getCurrentUser(int userId) {
        List<User> users = null;
        User currentUser = null;
        Call<List<User>> call = userService.getUserById(userId);
        try {
            users = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (User user : users) {
            currentUser = user;
        }
        return currentUser;
    }
}
