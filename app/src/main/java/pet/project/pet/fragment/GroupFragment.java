package pet.project.pet.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

import pet.project.pet.GroupDTO;
import pet.project.pet.GroupListAdapter;
import pet.project.pet.R;

public class GroupFragment extends Fragment {

    public GroupFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        List<GroupDTO> list = getListData();
        ListView listView = (ListView) getActivity().findViewById(R.id.listGroup);
        listView.setAdapter(new GroupListAdapter(list, getActivity()));

        return inflater.inflate(R.layout.fragment_group, container, false);
    }

    private List<GroupDTO> getListData(){
        List<GroupDTO> list = new ArrayList<GroupDTO>();
        GroupDTO group1 = new GroupDTO("SWD", "Gia Huy", "2018", true, 50, "SWD");
        GroupDTO group2 = new GroupDTO("HCI", "Phu Khanh", "2018", true, 250, "HCI");
        GroupDTO group3 = new GroupDTO("Mobile", "Thinh Phat", "2017", false, 150, "Mobile");
        return list;
    }


}
