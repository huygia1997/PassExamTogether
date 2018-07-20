package pet.project.pet;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
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
    private Dialog dialog;
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

        listView.setAdapter(new QuestionListAdapter(questions, this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pet.project.pet.model.Question questionSelected = (pet.project.pet.model.Question) listView.getAdapter().getItem(position);

                Intent intent = new Intent(GroupActivity.this, QuestionActivity.class);
                intent.putExtra("QuestionSelected", questionSelected);
                startActivity(intent);

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(GroupActivity.this);
                dialog.setContentView(R.layout.dialog_update_question);
                dialog.setTitle("Create Question");
                TextView txt_edit_title = (TextView) dialog.findViewById(R.id.txt_edit_ques_title);
                txt_edit_title.setText("anh load data title vao day");
                TextView txt_edit_content = (TextView) dialog.findViewById(R.id.txt_edit_quest_content);
                txt_edit_content.setText("anh load data content vao day");


                dialog.show();

                Button btn_submit_edit_quest = (Button) dialog.findViewById(R.id.btn_submit_edit_quest);
                btn_submit_edit_quest.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(GroupActivity.this, "tao ques", Toast.LENGTH_SHORT).show();
                        // sau khi nhan submit tao group
                    }
                });
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
        Call<List<Question>> call = questionService.getQuestionsByGroup(groupId);
        try {
            questionList = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questionList;
    }
}