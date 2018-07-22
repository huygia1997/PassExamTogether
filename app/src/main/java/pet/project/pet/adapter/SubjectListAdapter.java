package pet.project.pet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import pet.project.pet.R;
import pet.project.pet.model.Subject;
import pet.project.pet.model.User;
import pet.project.pet.remote.ApiUtils;
import pet.project.pet.remote.UserService;
import retrofit2.Call;

public class SubjectListAdapter extends BaseAdapter implements Serializable {
    private List<Subject> listData;
    private LayoutInflater layoutInflater;
    private Context context;
    public SubjectListAdapter(List<Subject> listData, Context context) {
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
        ViewHolder viewHolder;
        if(convertView==null){
            convertView = layoutInflater.inflate(R.layout.subject_item_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.subjectNameView = (TextView) convertView.findViewById(R.id.textView_subjectName);
            viewHolder.subjectUserCreatedView = (TextView) convertView.findViewById(R.id.textView_subjectUserCreated);
            //viewHolder.subjectTimeCreatedView = (TextView) convertView.findViewById(R.id.textView_subjectTimeCreated);
            convertView.setTag(viewHolder);
        } else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Subject subject = this.listData.get(position);
        viewHolder.subjectNameView.setText(subject.getSubName());

        viewHolder.subjectUserCreatedView.setText(subject.getUserId() + "");
        if(subject.getCreatedDate()!=null){
            viewHolder.subjectTimeCreatedView.setText(subject.getCreatedDate().toString());
        }
        return convertView;
    }
    static class ViewHolder{
        TextView subjectNameView;
        TextView subjectUserCreatedView;
        TextView subjectTimeCreatedView;
    }
}
