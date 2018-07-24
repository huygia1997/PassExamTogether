package pet.project.pet;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import pet.project.pet.model.User;
import pet.project.pet.remote.ApiUtils;
import pet.project.pet.remote.UserService;
import pet.project.pet.sharedPrefApp.SharedPrefApp;

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
        userService = ApiUtils.getUserService();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        SharedPrefApp sharedPrefApp = SharedPrefApp.getInstance();
        int userId = sharedPrefApp.getCurrentUserId(getApplicationContext());

        //User currentUser = userService.getUserById(userId);
    }

    //private User getCurrentUser(int userId)
}
