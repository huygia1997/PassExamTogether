package pet.project.pet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        List<AnswerDTO> list = getListData();
        ListView listView = (ListView) findViewById(R.id.listView_listAnswer);
        listView.setAdapter(new AnswerListAdapter(list, this));
    }

    private List<AnswerDTO> getListData(){
        List<AnswerDTO> list = new ArrayList<AnswerDTO>();
        AnswerDTO answer1 = new AnswerDTO("answer01", "giahuy", "question01", "Day la cau tra loi 01", 101, true);
        AnswerDTO answer2 = new AnswerDTO("answer01", "phukhanh", "question01", "Day la cau tra loi 02", 121, true);
        AnswerDTO answer3 = new AnswerDTO("answer01", "thinhphat", "question01", "Day la cau tra loi 03", 301, true);

        list.add(answer1);
        list.add(answer2);
        list.add(answer3);
        return list;
    }

    public void clickToEdit(View view) {
    }

    public void clickToComment(View view) {
    }

    public void clickToReset(View view) {
    }
}
