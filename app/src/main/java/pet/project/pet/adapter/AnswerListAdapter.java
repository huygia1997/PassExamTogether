package pet.project.pet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import pet.project.pet.R;
import pet.project.pet.model.Answer;
import pet.project.pet.model.ResObj;
import pet.project.pet.model.Vote;
import pet.project.pet.remote.AnswerService;
import pet.project.pet.remote.ApiUtils;
import pet.project.pet.remote.VoteService;
import pet.project.pet.sharedPrefApp.SharedPrefApp;
import retrofit2.Call;

public class AnswerListAdapter extends BaseAdapter implements Serializable {
    private List<Answer> listData;
    private LayoutInflater layoutInflater;
    private Context context;
    private Answer selectedAnswer;
    AnswerService answerService;
    VoteService voteService;
    private int userId;
    private int userRoleId;

    public AnswerListAdapter(List<Answer> listData, Context context, int userId, int userRoleId) {
        this.listData = listData;
        this.context = context;
        this.userId = userId;
        this.userRoleId = userRoleId;
        layoutInflater = LayoutInflater.from(context);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

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
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.answer_item_layout, null);
            //View row = layoutInflater.inflate(R.layout.answer_item_layout, parent, false);
            holder = new ViewHolder();
            holder.numberUpvote = (TextView) convertView.findViewById(R.id.textView_numberUpvote);
            holder.answerContent = (TextView) convertView.findViewById(R.id.textView_answerContent);
            holder.userCommentID = (TextView) convertView.findViewById(R.id.textView_userCommentID);
            holder.btn_Up = (ImageButton) convertView.findViewById(R.id.btn_Up);
            holder.btn_Down = (ImageButton) convertView.findViewById(R.id.btn_Down);
            holder.chkApproveAnswer = (CheckBox) convertView.findViewById(R.id.chkApproveAnswer);
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

        if (userRoleId == 3) {
            holder.chkApproveAnswer.setEnabled(true);
        } else {
            holder.chkApproveAnswer.setEnabled(false);
        }

        int statusVoteAnswer = getStatusVoteAnswer(selectedAnswer.getAnsId(), userId);
        if ((statusVoteAnswer == 1) || (selectedAnswer.getUserId() == userId)) {
            holder.btn_Up.setEnabled(false);
        } else {
            holder.btn_Up.setEnabled(true);
        }
        if ((statusVoteAnswer == -1) || (selectedAnswer.getUserId() == userId)) {
            holder.btn_Down.setEnabled(false);
        } else {
            holder.btn_Down.setEnabled(true);
        }

        if (selectedAnswer.isApprovedByMentor()) {
            holder.chkApproveAnswer.setChecked(true);
        } else {
            holder.chkApproveAnswer.setChecked(false);
        }

        holder.btn_Up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View parentRow = (View) view.getParent();
                ListView listView = (ListView) parentRow.getParent();
                final int pos = listView.getPositionForView(parentRow);
                Answer selected = listData.get(pos);

                int newTotalUpVotes = selected.getTotalVote() + 1;
                holder.numberUpvote.setText(newTotalUpVotes + "");
                selected.setTotalVote(newTotalUpVotes);
                updateAnswerTotalVote(selected);

                int answerId = selected.getAnsId();
                if (getStatusVoteAnswer(answerId, userId) == -2) {
                    Vote vote = new Vote(answerId, userId, true, 1);
                    addUpvoteSelectedAnswerbyCurrentUser(vote);
                } else {
                    int newVote = getStatusVoteAnswer(answerId, userId) + 1;
                    Vote vote = new Vote(answerId, userId, true, newVote);
                    updateVoteSelectedAnswerbyCurrentUser(vote);
                }

                if (getStatusVoteAnswer(answerId, userId) == 1) {
                    holder.btn_Up.setEnabled(false);
                }
                holder.btn_Down.setEnabled(true);
            }
        });

        holder.btn_Down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View parentRow = (View) view.getParent();
                ListView listView = (ListView) parentRow.getParent();
                final int pos = listView.getPositionForView(parentRow);
                Answer selected = listData.get(pos);

                int newTotalDownVotes = selected.getTotalVote() - 1;
                holder.numberUpvote.setText(newTotalDownVotes + "");
                selected.setTotalVote(newTotalDownVotes);
                updateAnswerTotalVote(selected);

                int answerId = selected.getAnsId();
                int voteStatus = getStatusVoteAnswer(answerId, userId);
                if (voteStatus == -2) {
                    Vote vote = new Vote(answerId, userId, true, -1);
                    addUpvoteSelectedAnswerbyCurrentUser(vote);
                } else {
                    int newVote = voteStatus - 1;
                    Vote vote = new Vote(answerId, userId, true, newVote);
                    updateVoteSelectedAnswerbyCurrentUser(vote);
                }
                if (getStatusVoteAnswer(answerId, userId) == -1) {
                    holder.btn_Down.setEnabled(false);
                }
                holder.btn_Up.setEnabled(true);
            }
        });


        holder.chkApproveAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View parentRow = (View) view.getParent();
                ListView listView = (ListView) parentRow.getParent();
                final int pos = listView.getPositionForView(parentRow);
                Answer selected = listData.get(pos);

                if (holder.chkApproveAnswer.isChecked()) {
                    selected.setApprovedByMentor(true);
                    selected.setApprovedMentorId(userId);
                    updateAnswerTotalVote(selected);
                } else {
                    selected.setApprovedByMentor(false);
                    selected.setApprovedMentorId(userId);
                    updateAnswerTotalVote(selected);
                }
            }
        });

        return convertView;
    }

    private int getStatusVoteAnswer(int ansId, int userId) {
        voteService = ApiUtils.getVoteService();
        Call<ResObj> call = voteService.getStatusVoteAnswer(ansId, userId);
        ResObj resObj = new ResObj();
        try {
            resObj = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resObj.getVoteStatus();
    }

    private void updateAnswerTotalVote(Answer answer) {
        answerService = ApiUtils.getAnswerService();
        Call<ResObj> call = answerService.updateAnswer(answer);
        try {
            call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addUpvoteSelectedAnswerbyCurrentUser(Vote vote) {
        voteService = ApiUtils.getVoteService();
        Call<ResObj> call = voteService.addUpvoteSelectedAnswerbyCurrentUser(vote);
        try {
            call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateVoteSelectedAnswerbyCurrentUser(Vote vote) {
        voteService = ApiUtils.getVoteService();
        Call<ResObj> call = voteService.updateSelectedAnswerVote(vote);
        try {
            call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ViewHolder {
        TextView numberUpvote;
        TextView answerContent;
        TextView userCommentID;
        ImageButton btn_Up, btn_Down;
        CheckBox chkApproveAnswer;
    }
}
