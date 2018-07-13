package pet.project.pet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class QuestionListAdapter extends BaseAdapter {
    private List<QuestionDTO> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public QuestionListAdapter(List<QuestionDTO> listData, Context context) {
        this.listData = listData;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView = layoutInflater.inflate(R.layout.question_item_layout, null);
            holder = new ViewHolder();
            holder.numberAnswerView = (TextView) convertView.findViewById(R.id.textView_numberAnswer);
            holder.questionTitleView = (TextView) convertView.findViewById(R.id.textView_questionTitle);
            holder.questionCreatedByView = (TextView) convertView.findViewById(R.id.textView_questionOwner);
            holder.questionTagView = (TextView) convertView.findViewById(R.id.textView_questionTag);
            convertView.setTag(holder);
        } else{
            holder = (ViewHolder) convertView.getTag();
        }
        QuestionDTO question = this.listData.get(position);
        holder.numberAnswerView.setText(question.getNumberAnswer() + "");
        holder.questionTitleView.setText(question.getTitle());
        holder.questionCreatedByView.setText(question.getUserID());
        holder.questionTagView.setText(question.getTag());
        return convertView;
    }

    static class ViewHolder{
        TextView numberAnswerView;
        TextView questionTitleView;
        TextView questionCreatedByView;
        TextView questionTagView;
    }
}
