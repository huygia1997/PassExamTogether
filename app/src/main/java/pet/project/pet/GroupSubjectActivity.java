package pet.project.pet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class GroupSubjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_subject);
//        List<GroupDTO> list = getListData();
//        ListView listView = (ListView) findViewById(R.id.listView_listGroupSubject);
//        GroupListAdapter groupAdapt = new GroupListAdapter(list, this);
//        listView.setAdapter(groupAdapt);
    }

    private List<GroupDTO> getListData(){
        List<GroupDTO> list = new ArrayList<GroupDTO>();
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
