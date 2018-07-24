package pet.project.pet;

import android.app.Dialog;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import pet.project.pet.adapter.AnswerListAdapter;
import pet.project.pet.model.Answer;
import pet.project.pet.model.Question;
import pet.project.pet.model.ResObj;
import pet.project.pet.remote.AnswerService;
import pet.project.pet.remote.ApiUtils;
import pet.project.pet.remote.QuestionService;
import pet.project.pet.sharedPrefApp.SharedPrefApp;
import retrofit2.Call;

public class QuestionActivity extends AppCompatActivity {
    AnswerService answerService;
    private Question question;
    private List<Answer> answers;
    private ListView listView_listAnswer;
    private Dialog dialog;
    TextView txtQuestion_QuestionTitle, txtQuestion_QuestionOwner, txtQuestion_QuestionContent;
    Button btnQuestion_editQuestion, btnQuestion_submitAnswer, btn_Ques_Tag;
    EditText edtQuestion_Answer;
    private final String REQUIRED_FIELD = "Field is required!";
    QuestionService questionService;
    private int quesId, userId;
    private String contentComment;
    private int currentUserId;
    private Button btn_Up, btn_Down;
    private TextView textView_numberUpvote;
    private Answer selectedAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        listView_listAnswer = (ListView) findViewById(R.id.listView_listAnswer);
        txtQuestion_QuestionTitle = (TextView) findViewById(R.id.txtQuestion_QuestionTitle);
        txtQuestion_QuestionOwner = (TextView) findViewById(R.id.txtQuestion_QuestionOwner);
        btnQuestion_editQuestion = (Button) findViewById(R.id.btnQuestion_editQuestion);
        btnQuestion_submitAnswer = (Button) findViewById(R.id.btnQuestion_submitAnswer);
        txtQuestion_QuestionContent = (TextView)findViewById(R.id.txtQuestion_QuestionContent);

        question = (Question) getIntent().getSerializableExtra("QuestionSelected");
        quesId = question.getQuesId();
        Intent intent = new Intent(this, GroupActivity.class);

        SharedPrefApp sharedPrefApp = SharedPrefApp.getInstance();
        currentUserId = sharedPrefApp.getCurrentUserId(getApplicationContext());

        if (question.getUserId() == currentUserId) {
            btnQuestion_editQuestion.setVisibility(View.VISIBLE);
        } else {
            btnQuestion_editQuestion.setVisibility(View.GONE);
        }

        questionService = ApiUtils.getQuestionService();

        btnQuestion_editQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(QuestionActivity.this);
                dialog.setContentView(R.layout.dialog_update_question);

                final TextView txt_edit_title = (TextView) dialog.findViewById(R.id.txt_edit_ques_title);
                final TextView txt_edit_content = (TextView) dialog.findViewById(R.id.txt_edit_quest_content);

                txt_edit_title.setText(txtQuestion_QuestionTitle.getText().toString());
                txt_edit_content.setText(txtQuestion_QuestionContent.getText().toString());

                dialog.show();

                Button btn_submit_edit_quest = (Button) dialog.findViewById(R.id.btn_submit_edit_quest);
                btn_submit_edit_quest.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String title = txt_edit_title.getText().toString();
                        String content = txt_edit_content.getText().toString();

                        if (validationEditQues(title, content)) {
                            question.setTitle(title);
                            question.setContent(content);
                            editQuestion(question);
                            Toast.makeText(QuestionActivity.this, "successfully", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(getIntent());
                        }

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

        btnQuestion_submitAnswer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                contentComment = edtAnswer.getText().toString();

                SharedPrefApp sharedPrefApp = SharedPrefApp.getInstance();
                userId = sharedPrefApp.getCurrentUserId(getApplicationContext());

                if (validationComment(contentComment)) {
                    addNewAnswer(userId, quesId, contentComment);

                    int totalAnswers = question.getTotalAnswers() + 1;
                    question.setTotalAnswers(totalAnswers);
                    editQuestion(question);

                    finish();
                    startActivity(getIntent());
                }
            }
        });

        int userId = sharedPrefApp.getCurrentUserId(getApplicationContext());
        int userRoleId = sharedPrefApp.getCurrentUserRoleId(getApplicationContext());

        answers = getAnswersByQuestion(quesId);
        listView_listAnswer.setAdapter(new AnswerListAdapter(answers, this, userId, userRoleId));

        txtQuestion_QuestionTitle.setText(question.getTitle());
        txtQuestion_QuestionContent.setText(question.getContent());
        if (question.getDisplayName() == null || question.getDisplayName() == "" || question.getDisplayName().isEmpty()) {
            txtQuestion_QuestionOwner.setText(question.getUsername());
        } else {
            txtQuestion_QuestionOwner.setText(question.getDisplayName());
        }

    }

    private boolean validationEditQues(String title, String content) {
        if (title == "" || title == null || title.isEmpty()) {
            Toast.makeText(this, REQUIRED_FIELD, Toast.LENGTH_SHORT).show();
            return false;
        }

        if (content == "" || content == null || content.isEmpty()) {
            Toast.makeText(this, REQUIRED_FIELD, Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private boolean validationComment(String content) {
        if (content == "" || content == null || content.isEmpty()) {
            Toast.makeText(this, REQUIRED_FIELD, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /*private void editQuestion(int quesId, String title, String content) {
        Question question = new Question(quesId, title, content, true);
        Call<ResObj> call = questionService.updateQuestion(question);
        try {
            call.execute().body();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    private void editQuestion(Question question) {
        Call<ResObj> call = questionService.updateQuestion(question);
        try {
            call.execute().body();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addNewAnswer(int userId, int quesId, String content) {
        Answer newAnswer = new Answer(userId, quesId, content, 0, true);
        Call<ResObj> call = answerService.createAnswer(newAnswer);
        try {
            call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Answer> getAnswersByQuestion(int quesId) {
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
