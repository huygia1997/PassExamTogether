package pet.project.pet.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import pet.project.pet.R;


public class RecentFragment extends Fragment {


    public RecentFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recent, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        RadioGroup rdG_filter = (RadioGroup)getActivity().findViewById(R.id.rdG_filter);
        RadioButton rd_group_choice = (RadioButton)getActivity().findViewById(R.id.rd_group_choice);
        rd_group_choice.setChecked(true);
    }
}
