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
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import pet.project.pet.GroupActivity;
import pet.project.pet.GroupDTO;
import pet.project.pet.GroupSubjectActivity;
import pet.project.pet.R;
import pet.project.pet.SubjectDTO;
import pet.project.pet.SubjectListAdapter;

public class SubjectFragment extends Fragment {

    public SubjectFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_subject, container, false);
        List<SubjectDTO> list = getListData();
        ListView listView = (ListView) rootView.findViewById(R.id.listView_listSubject);
        listView.setAdapter(new SubjectListAdapter(list, getActivity()));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity().getApplication(), GroupSubjectActivity.class);
                startActivity(intent);
            }
        });

        return rootView;

    }
    private List<SubjectDTO> getListData(){
        List<SubjectDTO> list = new ArrayList<SubjectDTO>();
        SubjectDTO subject1 = new SubjectDTO("SWD", "Design", "phukhanh", "2018", true);
        SubjectDTO subject2 = new SubjectDTO("PRM", "A", "giahuy", "2013", true);
        SubjectDTO subject3 = new SubjectDTO("ACC", "B", "thinhphat", "2017", true);
        SubjectDTO subject4 = new SubjectDTO("HCI", "C", "phukhanh", "2018", true);
        list.add(subject1);
        list.add(subject2);
        list.add(subject3);
        list.add(subject4);
        return list;
    }

}
