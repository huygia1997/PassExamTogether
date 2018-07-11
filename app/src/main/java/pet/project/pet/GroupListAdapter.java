package pet.project.pet;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.sql.Date;
import java.util.List;

public class GroupListAdapter extends BaseAdapter {
    private List<GroupDTO> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public GroupListAdapter(List<GroupDTO> listData, Context context) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
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
            holder.tag = (TextView) convertView.findViewById(R.id.textView_tag);
            holder.timeCreated = (TextView) convertView.findViewById(R.id.textView_timeCreated);
            convertView.setTag(holder);
        } else{
            holder = (ViewHolder) convertView.getTag();
        }
        GroupDTO group = this.listData.get(position);
        holder.numberQuestion.setText(group.getNumberQuestion());
        holder.groupName.setText(group.getGroupName());
        holder.groupOwner.setText(group.getGroupOwner());
        holder.tag.setText(group.getTag());
        holder.timeCreated.setText(group.getTimeCreated().toString());

        return convertView;
    }

    static class ViewHolder{
        TextView numberQuestion;
        TextView groupName;
        TextView groupOwner;
        TextView tag;
        TextView timeCreated;
    }
}
