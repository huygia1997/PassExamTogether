package pet.project.pet.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pet.project.pet.GroupActivity;
import pet.project.pet.QuestionActivity;
import pet.project.pet.R;
import pet.project.pet.adapter.GroupListAdapter;
import pet.project.pet.adapter.QuestionListAdapter;
import pet.project.pet.model.Group;
import pet.project.pet.model.GroupParticipant;
import pet.project.pet.model.History;
import pet.project.pet.model.Question;
import pet.project.pet.model.ResObj;
import pet.project.pet.remote.ApiUtils;
import pet.project.pet.remote.GroupParticipantService;
import pet.project.pet.remote.GroupService;
import pet.project.pet.remote.HistoryService;
import pet.project.pet.remote.QuestionService;
import pet.project.pet.sharedPrefApp.SharedPrefApp;
import retrofit2.Call;


public class RecentFragment extends Fragment {

    private RadioGroup rdG_filter;
    private RadioButton rd_group_choice, rd_question_choice;
    GroupService groupService;
    SharedPrefApp sharedPrefApp;
    private ListView lv_recent;
    int userId;
    HistoryService historyService;
    private Group groupSelected;
    GroupParticipantService groupParticipantService;
    private EditText txt_group_password;
    private Button btn_group_login;
    QuestionService questionService;

    public RecentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recent, container, false);


        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        rdG_filter = (RadioGroup) getActivity().findViewById(R.id.rdG_filter);
        rd_group_choice = (RadioButton) getActivity().findViewById(R.id.rd_group_choice);
        rd_question_choice = getActivity().findViewById(R.id.rd_question_choice);
        lv_recent = getActivity().findViewById(R.id.lv_recent);

        rd_group_choice.setChecked(true);

        sharedPrefApp = SharedPrefApp.getInstance();
        userId = sharedPrefApp.getCurrentUserId(getContext());

        if (rd_group_choice.isChecked()) {
            List<Group> recentGroupList = getRecentGroupList();
            GroupListAdapter adapter = new GroupListAdapter(recentGroupList, getActivity());
            lv_recent.setAdapter(adapter);
            lv_recent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    groupSelected = (pet.project.pet.model.Group) lv_recent.getAdapter().getItem(position);
                    //check selected group is public?
                    if (!(groupSelected.getPassword() == null || groupSelected.getPassword().isEmpty() || groupSelected.getPassword() == "")) {
                        //Create a dialog to input pass and compare with groupSelected.getPassword()

                        //check user login group yet
                        if (!checkParticipantInGroup(groupSelected.getGroupId(), groupSelected.getUserId())) {
                            final Dialog dialog = new Dialog(getContext());
                            dialog.setContentView(R.layout.dialog_group_password);
                            txt_group_password = (EditText) dialog.findViewById(R.id.txt_group_password);
                            btn_group_login = (Button) dialog.findViewById(R.id.btn_group_login);

                            dialog.show();

                            btn_group_login.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if (txt_group_password.getText().toString().compareTo(groupSelected.getPassword()) == 0) {
                                        Intent intent = new Intent(getActivity(), GroupActivity.class);
                                        intent.putExtra("GroupId", groupSelected.getGroupId());
                                        intent.putExtra("SelectedGroup", groupSelected);

                                        GroupParticipant participant = new GroupParticipant(groupSelected.getGroupId(), groupSelected.getUserId());
                                        addUserToGroupParticipant(participant);

                                        SharedPrefApp sharedPrefApp = SharedPrefApp.getInstance();
                                        int userId = sharedPrefApp.getCurrentUserId(getContext());
                                        History history = new History(userId, groupSelected.getGroupId(), 1);
                                        addToHistory(history);

                                        getActivity().startActivity(intent);
                                        dialog.dismiss();
                                    } else {
                                        Toast.makeText(getContext(), "Password is not correct!", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                }
                            });
                        } else {
                            SharedPrefApp sharedPrefApp = SharedPrefApp.getInstance();
                            int userId = sharedPrefApp.getCurrentUserId(getContext());
                            History history = new History(userId, groupSelected.getGroupId(), 1);
                            addToHistory(history);
                            Intent intent = new Intent(getActivity(), GroupActivity.class);
                            intent.putExtra("GroupId", groupSelected.getGroupId());
                            intent.putExtra("SelectedGroup", groupSelected);
                            getActivity().startActivity(intent);
                        }
                    } else {
                        SharedPrefApp sharedPrefApp = SharedPrefApp.getInstance();
                        int userId = sharedPrefApp.getCurrentUserId(getContext());
                        History history = new History(userId, groupSelected.getGroupId(), 1);
                        addToHistory(history);
                        Intent intent = new Intent(getActivity(), GroupActivity.class);
                        intent.putExtra("GroupId", groupSelected.getGroupId());
                        intent.putExtra("SelectedGroup", groupSelected);
                        getActivity().startActivity(intent);
                    }
                }
            });
        }

        rd_group_choice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Group> recentGroupList = getRecentGroupList();
                GroupListAdapter adapter = new GroupListAdapter(recentGroupList, getActivity());
                lv_recent.setAdapter(adapter);
                lv_recent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        groupSelected = (pet.project.pet.model.Group) lv_recent.getAdapter().getItem(position);
                        //check selected group is public?
                        if (!(groupSelected.getPassword() == null || groupSelected.getPassword().isEmpty() || groupSelected.getPassword() == "")) {
                            //Create a dialog to input pass and compare with groupSelected.getPassword()

                            //check user login group yet
                            if (!checkParticipantInGroup(groupSelected.getGroupId(), groupSelected.getUserId())) {
                                final Dialog dialog = new Dialog(getContext());
                                dialog.setContentView(R.layout.dialog_group_password);
                                txt_group_password = (EditText) dialog.findViewById(R.id.txt_group_password);
                                btn_group_login = (Button) dialog.findViewById(R.id.btn_group_login);

                                dialog.show();

                                btn_group_login.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if (txt_group_password.getText().toString().compareTo(groupSelected.getPassword()) == 0) {
                                            Intent intent = new Intent(getActivity(), GroupActivity.class);
                                            intent.putExtra("GroupId", groupSelected.getGroupId());
                                            intent.putExtra("SelectedGroup", groupSelected);

                                            GroupParticipant participant = new GroupParticipant(groupSelected.getGroupId(), groupSelected.getUserId());
                                            addUserToGroupParticipant(participant);

                                            SharedPrefApp sharedPrefApp = SharedPrefApp.getInstance();
                                            int userId = sharedPrefApp.getCurrentUserId(getContext());
                                            History history = new History(userId, groupSelected.getGroupId(), 1);
                                            addToHistory(history);

                                            getActivity().startActivity(intent);
                                            dialog.dismiss();
                                        } else {
                                            Toast.makeText(getContext(), "Password is not correct!", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                    }
                                });
                            } else {
                                SharedPrefApp sharedPrefApp = SharedPrefApp.getInstance();
                                int userId = sharedPrefApp.getCurrentUserId(getContext());
                                History history = new History(userId, groupSelected.getGroupId(), 1);
                                addToHistory(history);
                                Intent intent = new Intent(getActivity(), GroupActivity.class);
                                intent.putExtra("GroupId", groupSelected.getGroupId());
                                intent.putExtra("SelectedGroup", groupSelected);
                                getActivity().startActivity(intent);
                            }
                        } else {
                            SharedPrefApp sharedPrefApp = SharedPrefApp.getInstance();
                            int userId = sharedPrefApp.getCurrentUserId(getContext());
                            History history = new History(userId, groupSelected.getGroupId(), 1);
                            addToHistory(history);
                            Intent intent = new Intent(getActivity(), GroupActivity.class);
                            intent.putExtra("GroupId", groupSelected.getGroupId());
                            intent.putExtra("SelectedGroup", groupSelected);
                            getActivity().startActivity(intent);
                        }
                    }
                });
            }
        });

        rd_question_choice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Question> questions = getRecentQuestionList();
                lv_recent.setAdapter(new QuestionListAdapter(questions, getActivity()));
                lv_recent.setAdapter(new QuestionListAdapter(questions, getActivity()));
                lv_recent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        pet.project.pet.model.Question questionSelected = (pet.project.pet.model.Question) lv_recent.getAdapter().getItem(position);

                        Intent intent = new Intent(getActivity().getApplicationContext(), QuestionActivity.class);
                        intent.putExtra("QuestionSelected", questionSelected);

                        SharedPrefApp sharedPrefApp = SharedPrefApp.getInstance();
                        int userId = sharedPrefApp.getCurrentUserId(getActivity().getApplicationContext());
                        History history = new History(userId, questionSelected.getQuesId(), 2);
                        addToHistory(history);

                        startActivity(intent);

                    }
                });
            }
        });

    }

    private List<Question> getRecentQuestionList() {
        List<History> recentHistory = getRecentHistoryByUser(userId, 2);
        int questionId;
        List<Question> quesToshow = new ArrayList<>();
        for (History h : recentHistory) {
            questionId = h.getPostId();
            quesToshow.add(getQuestionByQuesId(questionId));
        }
        return quesToshow;

    }

    private Question getQuestionByQuesId(int questionId) {
        questionService = ApiUtils.getQuestionService();
        List<Question> questionList = null;
        Question question = null;
        Call<List<Question>> call = questionService.getQuestionsByQuesId(questionId);
        try {
            questionList = call.execute().body();
            for (Question q : questionList) {
                question = q;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return question;

    }

    private boolean checkParticipantInGroup(int groupId, int userId) {
        groupParticipantService = ApiUtils.getGroupParticipantService();
        Call<ResObj> call = groupParticipantService.checkParticipantInGroup(groupId, userId);
        ResObj resObj = null;
        try {
            resObj = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resObj.isMessage();
    }

    private List<Group> getRecentGroupList() {
        List<History> recentHistory = getRecentHistoryByUser(userId, 1);
        int groupId;
        List<Group> groupToShow = new ArrayList<>();
        for (History h : recentHistory) {
            groupId = h.getPostId();
            groupToShow.add(getGroupByGroupId(groupId));
        }
        return groupToShow;
    }

    private void addUserToGroupParticipant(GroupParticipant groupParticipant) {
        groupParticipantService = ApiUtils.getGroupParticipantService();
        Call<ResObj> call = groupParticipantService.addUserToGroupParticipant(groupParticipant);
        ResObj resObj = null;
        try {
            resObj = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addToHistory(History history) {
        historyService = ApiUtils.getHistoryService();
        Call<ResObj> call = historyService.addToHistory(history);
        try {
            call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Group getGroupByGroupId(int groupId) {
        groupService = ApiUtils.getGroupService();
        List<Group> groupList = null;
        Group group = null;
        Call<List<Group>> call = groupService.getGroupById(groupId);
        try {
            groupList = call.execute().body();
            for (Group g : groupList) {
                group = g;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return group;

    }

    private List<History> getRecentHistoryByUser(int userId, int postType) {
        historyService = ApiUtils.getHistoryService();
        Call<List<History>> call = historyService.getRecentGroupHistoryByUser(userId, postType);
        List<History> historyList = null;
        try {
            historyList = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return historyList;
    }
}
