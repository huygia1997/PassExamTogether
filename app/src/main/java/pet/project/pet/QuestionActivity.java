package pet.project.pet;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
    private Dialog dialog;
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
        Button btn_EditQues = (Button) findViewById(R.id.btnQuestion_editQuestion);
        btn_EditQues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(QuestionActivity.this);
                dialog.setContentView(R.layout.dialog_update_question);
                TextView txt_edit_title = (TextView) dialog.findViewById(R.id.txt_edit_ques_title);
                txt_edit_title.setText("anh load data title vao day");
                TextView txt_edit_content = (TextView) dialog.findViewById(R.id.txt_edit_quest_content);
                txt_edit_content.setText("anh load data content vao day");


                dialog.show();

                Button btn_submit_edit_quest = (Button) dialog.findViewById(R.id.btn_submit_edit_quest);
                btn_submit_edit_quest.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // sau khi nhan submit tao group
                        Toast.makeText(QuestionActivity.this, "edit quest", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        final EditText edtAnswer = (EditText) findViewById(R.id.edtQuestion_Answer);
        edtAnswer.setScroller(new Scroller(this));
        edtAnswer.setMaxLines(1);
        edtAnswer.setVerticalScrollBarEnabled(true);
        edtAnswer.setMovementMethod(new ScrollingMovementMethod());


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
