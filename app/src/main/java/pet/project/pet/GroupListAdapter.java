package pet.project.pet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class GroupListAdapter extends BaseAdapter {
    private List<GroupDTO> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public GroupListAdapter(List<GroupDTO> listData, Context context) {
        this.listData = listData;
        //layoutInflater = LayoutInflater.from(context);
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView ==null){
            convertView = layoutInflater.inflate(R.layout.group_item_layout, null);
            holder = new ViewHolder();
            holder.numberQuestion = (TextView) convertView.findViewById(R.id.textView_numberQuestion);
            holder.groupName = (TextView) convertView.findViewById(R.id.textView_groupName);
            holder.groupOwner = (TextView) convertView.findViewById(R.id.textView_groupOwner);
            holder.tagView = (TextView) convertView.findViewById(R.id.textView_tag);
            holder.timeCreatedView = (TextView) convertView.findViewById(R.id.textView_timeCreated);
            convertView.setTag(holder);
        } else{
            holder = (ViewHolder) convertView.getTag();
        }
        GroupDTO group = this.listData.get(position);
        holder.numberQuestion.setText(group.getNumberQuestion() + "");
        holder.groupName.setText(group.getGroupName());
        holder.groupOwner.setText(group.getGroupOwner());
        holder.tagView.setText(group.getTag());
        holder.timeCreatedView.setText(group.getTimeCreated());
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



    static class ViewHolder{
        TextView numberQuestion;
        TextView groupName;
        TextView groupOwner;
        TextView tagView;
        TextView timeCreatedView;
    }
}