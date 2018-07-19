package pet.project.pet.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.Serializable;
import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

import pet.project.pet.FrameActivity;
import pet.project.pet.GroupActivity;
import pet.project.pet.GroupDTO;
import pet.project.pet.GroupListAdapter;
import pet.project.pet.LoginActivity;
import pet.project.pet.QuestionActivity;
import pet.project.pet.R;
import pet.project.pet.model.Question;
import pet.project.pet.model.ResObj;
import pet.project.pet.remote.ApiUtils;
import pet.project.pet.remote.QuestionService;
import pet.project.pet.remote.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupFragment extends Fragment {

    public GroupFragment() {
        // Required empty public constructor
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_group, container, false);
        List<pet.project.pet.model.Group> groupList = (List<pet.project.pet.model.Group>) getArguments().getSerializable("groupList");

//        List<GroupDTO> list = getListData();

        final ListView listView = (ListView) rootView.findViewById(R.id.listGroup);
        final GroupListAdapter groupAdapt = new GroupListAdapter(groupList, getActivity());
        listView.setAdapter(groupAdapt);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pet.project.pet.model.Group groupSelected = (pet.project.pet.model.Group) listView.getAdapter().getItem(position);
                /*getQuestionsDataById(groupSelected.getGroupId());*/
//                Toast.makeText(getActivity(), groupSelected.getGroupId() + "", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(),GroupActivity.class);
                intent.putExtra("GroupId", groupSelected.getGroupId());
                getActivity().startActivity(intent);
            }
        });


        return rootView;
    }

    /*private void getQuestionsDataById(final int id) {
        questionService = ApiUtils.getQuestionService();
        Call<List<Question>> call = questionService.getQuestionById(id);
        List<Question> questionList = null;
        try {
                questionList = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        *//*call.enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                if (response.isSuccessful()) {
                    Intent intent = new Intent(getActivity(), GroupActivity.class);
                    try {
                        intent.putExtra("dataPass", (Serializable) call.execute().body());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    startActivity(intent);
                    *//**//*ResObj resObj = response.body();
                    if (resObj.isMessage()) {
                        Intent intent = new Intent(getActivity(), GroupActivity.class);
                        try {
                            intent.putExtra("dataPass", (Serializable) call.execute().body());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        startActivity(intent);
                    } else {
                        Toast.makeText(getActivity(), "can't receive message!", Toast.LENGTH_SHORT).show();
                    }*//**//*
                } else {
                    Toast.makeText(getActivity(), "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });*//*
    }*/

}





