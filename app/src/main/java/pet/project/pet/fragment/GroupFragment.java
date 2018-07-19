package pet.project.pet.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

import pet.project.pet.FrameActivity;
import pet.project.pet.GroupActivity;
import pet.project.pet.GroupDTO;
import pet.project.pet.GroupListAdapter;
import pet.project.pet.R;

public class GroupFragment extends Fragment {

    public GroupFragment() {
        // Required empty public constructor
    }

    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_group, container, false);

        List<GroupDTO> list = getListData();
        ListView listView = (ListView)rootView.findViewById(R.id.listGroup);
        GroupListAdapter groupAdapt = new GroupListAdapter(list, getActivity());
        listView.setAdapter(groupAdapt);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity().getApplication(), GroupActivity.class);
                startActivity(intent);
            }
        });



        return rootView;
    }

    private List<GroupDTO> getListData(){
        List<GroupDTO> list = new ArrayList<GroupDTO>();
        Bundle bundle = new Bundle();
        ArrayList<Group> groups = (ArrayList<Group>) bundle.getSerializable("groups");
        GroupDTO group1 = new GroupDTO("SWD", "Gia Huy", "2018", true, 50, "SWD");
        GroupDTO group2 = new GroupDTO("HCI", "Phu Khanh", "2018", true, 250, "HCI");
        GroupDTO group3 = new GroupDTO("Mobile", "Thinh Phat", "2017", false, 150, "Mobile");
        GroupDTO group4 = new GroupDTO("Mobile", "Thinh Phat", "2017", false, 150, "Mobile");
        GroupDTO group5 = new GroupDTO("Mobile", "Thinh Phat", "2017", false, 150, "Mobile");
        GroupDTO group6 = new GroupDTO("Mobile", "Thinh Phat", "2017", false, 150, "Mobile");
        list.add(group1);
        list.add(group2);
        list.add(group3);
        list.add(group4);
        list.add(group5);
        list.add(group6);
        return list;
    }


}
