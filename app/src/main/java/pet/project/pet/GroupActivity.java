package pet.project.pet;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pet.project.pet.adapter.QuestionListAdapter;
import pet.project.pet.model.Group;
import pet.project.pet.model.Question;
import pet.project.pet.model.ResObj;
import pet.project.pet.model.Subject;
import pet.project.pet.remote.ApiUtils;
import pet.project.pet.remote.GroupService;
import pet.project.pet.remote.QuestionService;
import pet.project.pet.remote.SubjectService;
import pet.project.pet.sharedPrefApp.SharedPrefApp;
import retrofit2.Call;

public class GroupActivity extends AppCompatActivity {
    QuestionService questionService;
    GroupService groupService;
    private List<Question> questions;
    private Dialog dialog;
    private ListView listView;
    private final String REQUIRED_FIELD = "Field is required!";
    TextView txt_edit_title;
    TextView txt_edit_content;
    TextView txt_edit_question;
    Spinner spnrSubjectInGroupActivity;
    private List<Subject> subjects;
    private SubjectService subjectService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        listView = (ListView) findViewById(R.id.listQuestion);
        setSupportActionBar(toolbar);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        questionService = ApiUtils.getQuestionService();
        groupService = ApiUtils.getGroupService();

        subjectService = ApiUtils.getSubjectService();
        subjects = getAllSubject();

        Intent intentFromGroupFragment = getIntent();
        final int groupId = intentFromGroupFragment.getIntExtra("GroupId", 0);
        final Group selectedGroup = (Group) intentFromGroupFragment.getSerializableExtra("SelectedGroup");

        questions = getListQuestions(groupId);
        final List<Group> currentGroup = getCurrentGroup(groupId);

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
                txt_edit_title = (TextView) dialog.findViewById(R.id.txt_edit_ques_title);
                txt_edit_content = (TextView) dialog.findViewById(R.id.txt_edit_quest_content);
                txt_edit_question = (TextView) dialog.findViewById(R.id.txt_edit_question);
                spnrSubjectInGroupActivity = (Spinner) dialog.findViewById(R.id.spnrSubjectInGroupActivity);

                final List<Subject> dataSrc = new ArrayList<>();

                for (Subject x : subjects) {
                    dataSrc.add(x);
                }
                ArrayAdapter<Subject> dataAdapter = new ArrayAdapter<Subject>(GroupActivity.this, android.R.layout.simple_spinner_item, dataSrc);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spnrSubjectInGroupActivity.setAdapter(dataAdapter);

                txt_edit_question.setText("Create Question");

                selectedGroup.getSubCode();
                if (selectedGroup.getSubCode() == null || selectedGroup.getSubCode() == "" || selectedGroup.getSubCode().isEmpty()) {
                    spnrSubjectInGroupActivity.setEnabled(true);
                } else {
                    for (int i = 0; i < spnrSubjectInGroupActivity.getAdapter().getCount(); i++) {
                        String subCode = spnrSubjectInGroupActivity.getAdapter().getItem(i).toString();
                        if (subCode != null) {
                            if (subCode.contains(selectedGroup.getSubCode())) {
                                spnrSubjectInGroupActivity.setSelection(i);
                            }
                        }

                    }
                    spnrSubjectInGroupActivity.setEnabled(false);
                }

                dialog.show();

                Button btn_submit_edit_quest = (Button) dialog.findViewById(R.id.btn_submit_edit_quest);
                btn_submit_edit_quest.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        SharedPrefApp sharedPrefApp = SharedPrefApp.getInstance();
                        int userId = sharedPrefApp.getCurrentUserId(getApplicationContext());

                        String quesTitle = txt_edit_title.getText().toString();
                        String quesContent = txt_edit_content.getText().toString();

                        //int subId = group

                        if (validationCreateQues(quesTitle, quesContent)) {
                            createQuestion(userId, groupId, 0, quesTitle, quesContent, 0, false, true);

                            Group thisGroup = new Group();
                            for (Group x : currentGroup) {
                                thisGroup = x;
                            }
                            int i = thisGroup.getTotalQuestions();
                            i += 1;
                            thisGroup.setTotalQuestions(i);

                            updateGroup(thisGroup);

                            Toast.makeText(GroupActivity.this, "Create Question successfully!", Toast.LENGTH_SHORT).show();

                            finish();
                            startActivity(getIntent());
                            dialog.dismiss();
                        }
                    }
                });
            }
        });

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

    private void updateGroup(Group group) {
        Call<ResObj> call = groupService.updateGroup(group);
        try {
            call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private boolean validationCreateQues(String title, String content) {
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

    private void createQuestion(int userId, int groupId, int subId, String title, String content, int totalAnswers, boolean approvedAnswer, boolean active) {
        Question newQuestion = new Question(userId, groupId, subId, title, content, totalAnswers, approvedAnswer, active);
        Call<ResObj> call = questionService.createQuestion(newQuestion);
        try {
            call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Question> getListQuestions(int groupId) {

        List<Question> questionList = null;
        Call<List<Question>> call = questionService.getQuestionsByGroup(groupId);
        try {
            questionList = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questionList;
    }

    private List<Group> getCurrentGroup(int groupId) {
        List<Group> currentGroup = null;
        Call<List<Group>> call = groupService.getGroupById(groupId);
        try {
            currentGroup = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return currentGroup;
    }
}