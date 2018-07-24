package pet.project.pet.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import pet.project.pet.GroupActivity;
import pet.project.pet.adapter.GroupListAdapter;
import pet.project.pet.R;
import pet.project.pet.model.GroupParticipant;
import pet.project.pet.model.ResObj;
import pet.project.pet.remote.ApiUtils;
import pet.project.pet.remote.GroupParticipantService;
import retrofit2.Call;

public class GroupFragment extends Fragment {
    private EditText txt_group_password;
    private Button btn_group_login;
    private pet.project.pet.model.Group groupSelected;
    GroupParticipantService groupParticipantService;

    public GroupFragment() {
        // Required empty public constructor
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_group, container, false);
        List<pet.project.pet.model.Group> groupList = (List<pet.project.pet.model.Group>) getArguments().getSerializable("groupList");

        groupParticipantService = ApiUtils.getGroupParticipantService();
        final ListView listView = (ListView) rootView.findViewById(R.id.listGroup);
        final GroupListAdapter groupAdapt = new GroupListAdapter(groupList, getActivity());
        listView.setAdapter(groupAdapt);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                groupSelected = (pet.project.pet.model.Group) listView.getAdapter().getItem(position);


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
                                    getActivity().startActivity(intent);
                                    dialog.dismiss();
                                } else {
                                    Toast.makeText(getContext(), "Password is not correct!", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                        });
                    } else {
                        Intent intent = new Intent(getActivity(), GroupActivity.class);
                        intent.putExtra("GroupId", groupSelected.getGroupId());
                        intent.putExtra("SelectedGroup", groupSelected);
                        getActivity().startActivity(intent);
                    }
                } else {
                    Intent intent = new Intent(getActivity(), GroupActivity.class);
                    intent.putExtra("GroupId", groupSelected.getGroupId());
                    intent.putExtra("SelectedGroup", groupSelected);
                    getActivity().startActivity(intent);
                }
            }
        });


        return rootView;
    }

    private boolean checkParticipantInGroup(int groupId, int userId) {
        Call<ResObj> call = groupParticipantService.checkParticipantInGroup(groupId, userId);
        ResObj resObj = null;
        try {
            resObj = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resObj.isMessage();
    }

    private void addUserToGroupParticipant(GroupParticipant groupParticipant) {
        Call<ResObj> call = groupParticipantService.addUserToGroupParticipant(groupParticipant);
        ResObj resObj = null;
        try {
            resObj = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}





