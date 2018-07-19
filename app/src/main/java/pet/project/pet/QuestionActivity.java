package pet.project.pet;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pet.project.pet.model.Answer;
import pet.project.pet.model.Question;
import pet.project.pet.remote.AnswerService;
import pet.project.pet.remote.ApiUtils;
import pet.project.pet.remote.QuestionService;
import retrofit2.Call;

public class QuestionActivity extends AppCompatActivity {
    AnswerService answerService;
    private Question question;
    private List<Answer> answers;
    private ListView listView_listAnswer;
    TextView txtQuestion_QuestionTitle, txtQuestion_QuestionOwner;
    Button btnQuestion_editQuestion, btnQuestion_submitAnswer, btn_Ques_Tag;
    EditText edtQuestion_Answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        listView_listAnswer = (ListView)findViewById(R.id.listView_listAnswer);
        txtQuestion_QuestionTitle = (TextView) findViewById(R.id.txtQuestion_QuestionTitle);
        txtQuestion_QuestionOwner = (TextView) findViewById(R.id.txtQuestion_QuestionOwner);

        answerService = ApiUtils.getAnswerService();

        question = (Question) getIntent().getSerializableExtra("QuestionSelected") ;
        int quesId = question.getQuesId();

        answers = getAnswersByQuestion(quesId);
        listView_listAnswer.setAdapter(new AnswerListAdapter(answers, this));

        txtQuestion_QuestionTitle.setText(question.getTitle());
        txtQuestion_QuestionOwner.setText(question.getUserId() + "");
    }

    private List<Answer> getAnswersByQuestion(int quesId){
        List<Answer> answerList = null;
        Call<List<Answer>> call = answerService.getAnswersByQuestion(quesId);
        try {
            answerList = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answerList;
    }

    public void clickToEdit(View view) {
    }

    public void clickToComment(View view) {
    }

    public void clickToReset(View view) {
    }
}
