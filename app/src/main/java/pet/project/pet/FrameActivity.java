package pet.project.pet;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import pet.project.pet.fragment.GroupFragment;
import pet.project.pet.model.Group;
import pet.project.pet.model.ResObj;
import pet.project.pet.model.Subject;
import pet.project.pet.model.User;
import pet.project.pet.remote.ApiUtils;
import pet.project.pet.remote.GroupService;
import pet.project.pet.remote.SubjectService;
import pet.project.pet.remote.UserService;
import pet.project.pet.sharedPrefApp.SharedPrefApp;
import retrofit2.Call;

public class FrameActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private ViewPager pager;
    private TabLayout tabLayout;
    private Spinner spnr_Subject;
    private Dialog dialog;
    GroupService groupService;
    private List<Group> groups;
    private List<Subject> subjects;
    private SubjectService subjectService;
    private UserService userService;
    Spinner subCode;
    EditText groupName, groupPassword, groupConfirmPassword;
    private final String REQUIRED_FIELD = "Field is required!";
    private int currentUserRoleId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        groupService = ApiUtils.getGroupService();
        groups = getAllGroups();

        subjectService = ApiUtils.getSubjectService();
        subjects = getAllSubject();

        addControl(groups, subjects);

        userService = ApiUtils.getUserService();
        currentUserRoleId = getCurrentUserRoleId();

        /*mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItem()) {
                    case R.id.nav_logout:

                        SharedPrefApp sharedPref;
                        sharedPref = SharedPrefApp.getInstance();
                        sharedPref.logout(getApplicationContext());

                        Intent intent = new Intent(FrameActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                        return true;
                    case R.id.nav_profile:
                        Intent intent1 = new Intent(FrameActivity.this, AccountActivity.class);
                        startActivity(intent1);
                        return true;
                    default:
                        return false;
                }
            }
        });*/

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
        View view = getSupportActionBar().getCustomView();

        ImageButton btn_search = (ImageButton) view.findViewById(R.id.btn_search);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Forward Button is clicked", Toast.LENGTH_LONG).show();
            }
        });

        ImageButton btn_create_group = (ImageButton) view.findViewById(R.id.btn_add_group);
        btn_create_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(FrameActivity.this);
                dialog.setContentView(R.layout.dialog_creating_group);
                groupName = (EditText) dialog.findViewById(R.id.txt_group_name);
                groupPassword = (EditText) dialog.findViewById(R.id.txt_group_pass);
                groupConfirmPassword = (EditText) dialog.findViewById(R.id.txt_group_confirmpassword);
                spnr_Subject = (Spinner) dialog.findViewById(R.id.spnr_Subject);

                final List<Subject> dataSrc = new ArrayList<>();

                for (Subject x : subjects) {
                    dataSrc.add(x);
                }
                ArrayAdapter<Subject> dataAdapter = new ArrayAdapter<Subject>(FrameActivity.this, android.R.layout.simple_spinner_item, dataSrc);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spnr_Subject.setAdapter(dataAdapter);
                dialog.show();

                Button btn_submit_create_group = (Button) dialog.findViewById(R.id.btn_submit_create_group);

                btn_submit_create_group.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String nameNewGroup = groupName.getText().toString();

                        Subject subject = (Subject) spnr_Subject.getSelectedItem();
                        int subId = subject.getSubId();

                        String passwordNewGroup = groupPassword.getText().toString();
                        String confirmPasswordNewGroup = groupConfirmPassword.getText().toString();

                        SharedPrefApp sharedPrefApp = SharedPrefApp.getInstance();
                        int userId = sharedPrefApp.getCurrentUserId(getApplicationContext());

                        /*SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                        Calendar calendar = Calendar.getInstance();
                        Date date = Date.valueOf(sdf.format(calendar.getTime()));*/

                        if (valiedationCreateNewGroup(nameNewGroup, passwordNewGroup, confirmPasswordNewGroup)) {
                            createNewGroup(nameNewGroup, subId, passwordNewGroup, userId, 0, true);

                            finish();
                            startActivity(getIntent());
                            dialog.dismiss();

                        }
                    }
                });

            }
        });

    }

    private int getCurrentUserRoleId(){
        SharedPrefApp sharedPrefApp = SharedPrefApp.getInstance();
        int userId = sharedPrefApp.getCurrentUserId(getApplicationContext());
        int currentUserRoleId = sharedPrefApp.getCurrentUserRoleId(getApplicationContext());
        if(currentUserRoleId == 4){
            if(checkApprovedTuitorRequest(userId) == 3) {
                Toast.makeText(this, "Your Tuitor Requirement is accepted", Toast.LENGTH_SHORT).show();
            }
        }
        return currentUserRoleId;
    }

    private int checkApprovedTuitorRequest(int userId) {
        List<User> users = null;
        int userRoleId = -1;
        Call<List<User>> call = userService.getUserById(userId);
        try {
            users = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (User user: users) {
            userRoleId = user.getRoleId();
        }
        return userRoleId;
    }

    private boolean valiedationCreateNewGroup(String nameGroup, String pass, String confirmPass) {
        if (nameGroup.isEmpty() || nameGroup == "") {
            Toast.makeText(this, REQUIRED_FIELD, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!(pass.isEmpty() || pass == "")) {
            if (!confirmPass.equals(pass)) {
                Toast.makeText(this, "Confirm password is not matched!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    private void createNewGroup(String groupName, int subId, String password, int userId, int totalQuestions, boolean active) {
        Group newGroup = new Group(groupName, subId, password, userId, totalQuestions, active);
        Call<ResObj> call = groupService.createGroup(newGroup);
        try {
            call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addControl(List<Group> groups, List<Subject> subjects) {
        pager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        PagerAdapter adapter = new PagerAdapter(manager, groups, subjects);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);

    }

    private List<Group> getAllGroups() {

        List<Group> groupsList = null;
        Call<List<Group>> call = groupService.getAllGroups();
        try {
            groupsList = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return groupsList;
    }

    private List<Subject> getAllSubject() {
        List<Subject> subjectList = null;
        Call<List<Subject>> call = subjectService.getAllSubjects();
        try {
            subjectList = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return subjectList;
    }

 /*   @Override
    protected void onResume() {
        super.onResume();
        this.onCreate(null);
    }*/
}
