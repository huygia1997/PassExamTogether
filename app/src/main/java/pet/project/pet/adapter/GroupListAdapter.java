package pet.project.pet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import pet.project.pet.R;
import pet.project.pet.model.Group;

public class GroupListAdapter extends BaseAdapter {
    private List<Group> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public GroupListAdapter(List<Group> listData, Context context) {
        this.listData = listData;
        //layoutInflater = LayoutInflater.from(context);
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.group_item_layout, null);
            holder = new ViewHolder();
            holder.numberQuestion = (TextView) convertView.findViewById(R.id.textView_numberQuestion);
            holder.groupName = (TextView) convertView.findViewById(R.id.textView_groupName);
            holder.groupOwner = (TextView) convertView.findViewById(R.id.textView_groupOwner);
            holder.tagView = (TextView) convertView.findViewById(R.id.textView_tag);
            holder.img_psss_group = (TextView) convertView.findViewById(R.id.img_psss_group);
            holder.img_un_psss_group = (TextView) convertView.findViewById(R.id.img_unlock_pass_group);
            //holder.timeCreatedView = (TextView) convertView.findViewById(R.id.textView_timeCreated);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Group group = this.listData.get(position);
        holder.numberQuestion.setText(group.getTotalQuestions() + "");
        holder.groupName.setText(group.getGroupName());
        String ownerName = "";
        if (group.getDisplayName().isEmpty() || group.getDisplayName() == null || group.getDisplayName() == "") {
            ownerName = group.getUsername();
        } else {
            ownerName = group.getDisplayName();
        }
        holder.groupOwner.setText(ownerName);
        holder.tagView.setText(group.getSubCode());

        if (group.getCreatedDate() != null) {
            holder.timeCreatedView.setText(group.getCreatedDate() + "");
        }

        if (group.getPassword().isEmpty() || group.getPassword() == "" || group.getPassword() == null){
            holder.img_psss_group.setVisibility(View.GONE);
            holder.img_un_psss_group.setVisibility(View.VISIBLE);
        } else {
            holder.img_psss_group.setVisibility(View.VISIBLE);
            holder.img_un_psss_group.setVisibility(View.GONE);
        }

            return convertView;
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


    static class ViewHolder {
        TextView numberQuestion;
        TextView groupName;
        TextView groupOwner;
        TextView tagView;
        TextView timeCreatedView;
        TextView img_psss_group, img_un_psss_group;

    }
}