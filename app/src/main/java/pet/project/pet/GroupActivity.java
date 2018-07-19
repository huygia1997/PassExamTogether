package pet.project.pet;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pet.project.pet.model.Group;
import pet.project.pet.model.Question;
import pet.project.pet.remote.ApiUtils;
import pet.project.pet.remote.QuestionService;
import retrofit2.Call;

public class GroupActivity extends AppCompatActivity {
    QuestionService questionService;
    private List<Question> questions;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        listView = (ListView) findViewById(R.id.listQuestion);
        setSupportActionBar(toolbar);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);



        Intent intentFromGroupFragment = getIntent();
        int groupId = intentFromGroupFragment.getIntExtra("GroupId", 0);
//        Toast.makeText(this, groupId + "", Toast.LENGTH_SHORT).show();

        questions = getListQuestions(groupId);

        //Intent intent = getIntent();
        //List<Question> list = (List<Question>) intent.getSerializableExtra("dataPass");


        listView.setAdapter(new QuestionListAdapter(questions, this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(GroupActivity.this, QuestionActivity.class);
                pet.project.pet.model.Question questionSelected = (pet.project.pet.model.Question) listView.getAdapter().getItem(position);
                intent.putExtra("QuestionId", questionSelected.getGroupId());
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

    /*private List<Question> getListData() {
        List<Question> list = new ArrayList<Question>();
        Question question1 = new QuestionDTO("Q01", "SWD", "Day la cau 01", "SWD", "2018", "phukhanh", 10, true, false);
        QuestionDTO question2 = new QuestionDTO("Q02", "HCI", "Day la cau 02", "SWD", "2017", "giahuy", 10, false, true);
        QuestionDTO question3 = new QuestionDTO("Q03", "PRM", "Day la cau 03", "SWD", "2018", "phukhanh", 10, true, false);
        list.add(question1);
        list.add(question2);
        list.add(question3);
        return list;
    }*/

    private List<Question> getListQuestions(int groupId){
        questionService = ApiUtils.getQuestionService();
        List<Question> questionList = null;
        Call<List<Question>> call = questionService.getQuestionById(groupId);
        try {
            questionList = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questionList;
    }
}