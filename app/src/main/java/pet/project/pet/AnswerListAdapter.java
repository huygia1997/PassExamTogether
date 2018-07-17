package pet.project.pet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class AnswerListAdapter extends BaseAdapter implements Serializable{
    private List<AnswerDTO> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public AnswerListAdapter(List<AnswerDTO> listData, Context context) {
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
            convertView = layoutInflater.inflate(R.layout.answer_item_layout, null);
            holder = new ViewHolder();
            holder.numberUpvote = (TextView) convertView.findViewById(R.id.textView_numberUpvote);
            holder.answerContent = (TextView) convertView.findViewById(R.id.textView_answerContent);
            holder.userCommentID = (TextView) convertView.findViewById(R.id.textView_userCommentID);
            convertView.setTag(holder);
        } else{
            holder = (ViewHolder) convertView.getTag();
        }
        AnswerDTO answer = this.listData.get(position);
        holder.numberUpvote.setText(answer.getTotalVote() + "");
        holder.answerContent.setText(answer.getContent());
        holder.userCommentID.setText(answer.getUserID());
        return convertView;
    }
    static class ViewHolder{
        TextView numberUpvote;
        TextView answerContent;
        TextView userCommentID;
    }
}
