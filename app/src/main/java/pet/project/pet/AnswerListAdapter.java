package pet.project.pet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import pet.project.pet.model.Answer;

public class AnswerListAdapter extends BaseAdapter implements Serializable {
    private List<Answer> listData;
    private LayoutInflater layoutInflater;
    private Context context;
    private Answer selectedAnswer;

    public AnswerListAdapter(List<Answer> listData, Context context) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.answer_item_layout, null);
            //View row = layoutInflater.inflate(R.layout.answer_item_layout, parent, false);
            holder = new ViewHolder();
            holder.numberUpvote = (TextView) convertView.findViewById(R.id.textView_numberUpvote);
            holder.answerContent = (TextView) convertView.findViewById(R.id.textView_answerContent);
            holder.userCommentID = (TextView) convertView.findViewById(R.id.textView_userCommentID);
            holder.btn_Up = (ImageButton) convertView.findViewById(R.id.btn_Up);
            holder.btn_Down = (ImageButton) convertView.findViewById(R.id.btn_Down);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        selectedAnswer = this.listData.get(position);
        holder.numberUpvote.setText(selectedAnswer.getTotalVote() + "");
        holder.answerContent.setText(selectedAnswer.getContent());
        String userAnser = "";
        if (selectedAnswer.getDisplayName().isEmpty() || selectedAnswer.getDisplayName() == "" || selectedAnswer.getDisplayName() == null) {
            userAnser = selectedAnswer.getUsername();
        } else {
            userAnser = selectedAnswer.getDisplayName();
        }
        holder.userCommentID.setText(userAnser);

        holder.btn_Up.setOnClickListener(onUpvoteClickListener);
        holder.btn_Down.setOnClickListener(onDownvoteClickListener);

        return convertView;
    }

    private View.OnClickListener onUpvoteClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ViewHolder viewHolder = (ViewHolder) v.getTag();

        }
    };

    private View.OnClickListener onDownvoteClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ViewHolder viewHolder = (ViewHolder) v.getTag();

        }
    };

    static class ViewHolder {
        TextView numberUpvote;
        TextView answerContent;
        TextView userCommentID;
        ImageButton btn_Up, btn_Down;

    }
}
