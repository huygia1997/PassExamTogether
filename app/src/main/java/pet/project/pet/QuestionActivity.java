package pet.project.pet;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.ScrollView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pet.project.pet.model.Question;
import pet.project.pet.remote.ApiUtils;
import pet.project.pet.remote.QuestionService;
import retrofit2.Call;

public class QuestionActivity extends AppCompatActivity {
    QuestionService questionService;
    private List<Question> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);



        Intent intentFromGroupFragment = getIntent();
        int groupId = intentFromGroupFragment.getIntExtra("GroupId", 0);
//        Toast.makeText(this, groupId + "", Toast.LENGTH_SHORT).show();

        questions = getListQuestions(groupId);


    }

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

    public void clickToEdit(View view) {
    }

    public void clickToComment(View view) {
    }

    public void clickToReset(View view) {
    }
}
