package pet.project.pet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        List<QuestionDTO> list = getListData();
        ListView listView = (ListView) findViewById(R.id.listQuestion);
        listView.setAdapter(new QuestionListAdapter(list, this));
    }

    private List<QuestionDTO> getListData(){
        List<QuestionDTO> list = new ArrayList<QuestionDTO>();
        QuestionDTO question1 = new QuestionDTO("Q01", "SWD", "Day la cau 01", "SWD", "2018", "phukhanh", 10, true, false);
        QuestionDTO question2 = new QuestionDTO("Q02", "HCI", "Day la cau 02", "SWD", "2017", "giahuy", 10, false, true);
        QuestionDTO question3 = new QuestionDTO("Q03", "PRM", "Day la cau 03", "SWD", "2018", "phukhanh", 10, true, false);
        list.add(question1);
        list.add(question2);
        list.add(question3);
        return list;
    }
}
