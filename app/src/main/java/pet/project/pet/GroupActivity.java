package pet.project.pet;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import pet.project.pet.model.Group;

public class GroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        List<QuestionDTO> list = getListData();
        ListView listView = (ListView) findViewById(R.id.listQuestion);
        listView.setAdapter(new QuestionListAdapter(list, this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(GroupActivity.this, QuestionActivity.class);
                startActivity(intent);

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    private List<QuestionDTO> getListData() {
        List<QuestionDTO> list = new ArrayList<QuestionDTO>();
        QuestionDTO question1 = new QuestionDTO("Q01", "SWD", "Day la cau 01", "SWD", "2018", "phukhanh", 10, true, false);
        QuestionDTO question2 = new QuestionDTO("Q02", "HCI", "Day la cau 02", "SWD", "2017", "giahuy", 10, false, true);
        QuestionDTO question3 = new QuestionDTO("Q03", "PRM", "Day la cau 03", "SWD", "2018", "phukhanh", 10, true, false);
        list.add(question1);
        list.add(question2);
        list.add(question3);
        return list;
    }
}