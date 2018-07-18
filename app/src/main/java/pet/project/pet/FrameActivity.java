package pet.project.pet;

import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pet.project.pet.model.Group;
import pet.project.pet.remote.ApiUtils;
import pet.project.pet.remote.GroupService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FrameActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private ViewPager pager;
    private TabLayout tabLayout;
    private Spinner spnr_Subject;
    Button btn_Create_Group;
    private Dialog dialog;
    GroupService groupService;
    private List<Group> groups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        groupService = ApiUtils.getGroupService();
        groups = getAllGroups();
        addControl();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_logout:
                        Toast.makeText(FrameActivity.this, "abc", Toast.LENGTH_SHORT).show();
                        SharedPreferences shared = getSharedPreferences("Account",Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = shared.edit();
                        editor.clear();
                        editor.commit();
                        Intent intent = new Intent(FrameActivity.this, LoginActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.nav_ava:
                        return true;
                    default:
                        return false;
                }
                // close drawer when item is tapped


            }
        });

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
        View view =getSupportActionBar().getCustomView();

        ImageButton btn_search= (ImageButton)view.findViewById(R.id.btn_search);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Forward Button is clicked", Toast.LENGTH_LONG).show();
            }
        });

        ImageButton btn_create_group = (ImageButton)view.findViewById(R.id.btn_add_group);
        btn_create_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(FrameActivity.this);
                dialog.setContentView(R.layout.dialog_creating_group);
                spnr_Subject = (Spinner)dialog.findViewById(R.id.spnr_Subject);
                List<String> dataSrc = new ArrayList<>();
                dataSrc.add("SWD ");
                dataSrc.add("PRM321");
                dataSrc.add("HCI");
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(FrameActivity.this, android.R.layout.simple_spinner_item, dataSrc);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spnr_Subject.setAdapter(dataAdapter);
                dialog.show();

                Button btn_submit_create_group = (Button) dialog.findViewById(R.id.btn_submit_create_group);
                btn_submit_create_group.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // sau khi nhan submit tao group
                    }
                });

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation_menu, menu);
        return true;
    }


    private void addControl() {
        pager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        PagerAdapter adapter = new PagerAdapter(manager);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(adapter);
    }


//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        MenuItem nav_logout = (MenuItem)findViewById(R.id.nav_logout);
//        nav_logout.setTitle("abc");
//        return super.onPrepareOptionsMenu(menu);
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);

    }

    private List<Group> getAllGroups(){

        Call<List<Group>> call = groupService.getAllGroups();
        call.enqueue(new Callback<List<Group>>() {
            @Override
            public void onResponse(Call<List<Group>> call, Response<List<Group>> response) {
                groups = response.body();
            }

            @Override
            public void onFailure(Call<List<Group>> call, Throwable t) {
                Toast.makeText(FrameActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return groups;
    }
}
