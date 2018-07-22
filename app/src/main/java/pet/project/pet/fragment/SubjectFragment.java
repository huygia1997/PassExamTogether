package pet.project.pet.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import pet.project.pet.R;
import pet.project.pet.adapter.SubjectListAdapter;
import pet.project.pet.model.Subject;

public class SubjectFragment extends Fragment {

    public SubjectFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_subject, container, false);
        List<Subject> subjectList = (List<Subject>) getArguments().getSerializable("subjectList");
        final ListView listView = (ListView) rootView.findViewById(R.id.listView_listSubject);
        final SubjectListAdapter adapter = new SubjectListAdapter(subjectList, getActivity());
        listView.setAdapter(adapter);

        return rootView;
    }
}
