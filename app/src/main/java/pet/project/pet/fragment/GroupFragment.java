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

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

import pet.project.pet.FrameActivity;
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
//        String[] menuItem = {"abc", "acb", "bcd"};
//        ArrayAdapter<String> adap = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, menuItem);
        ListView listView = (ListView)rootView.findViewById(R.id.listGroup);
//        listView.setAdapter(adap);
        GroupListAdapter groupAdapt = new GroupListAdapter(list, getActivity());
        listView.setAdapter(groupAdapt);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FrameActivity.class, GroupActivity.class);
                startActivity(intent);
            }
        });


        return rootView;
    }

    private List<GroupDTO> getListData(){
        List<GroupDTO> list = new ArrayList<GroupDTO>();
        GroupDTO group1 = new GroupDTO("SWD", "Gia Huy", "2018", true, 50, "SWD");
        GroupDTO group2 = new GroupDTO("HCI", "Phu Khanh", "2018", true, 250, "HCI");
        GroupDTO group3 = new GroupDTO("Mobile", "Thinh Phat", "2017", false, 150, "Mobile");
        list.add(group1);
        list.add(group2);
        list.add(group3);
        return list;
    }


}
