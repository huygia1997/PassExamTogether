package pet.project.pet.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import pet.project.pet.R;
import pet.project.pet.model.ResObj;
import pet.project.pet.model.Subject;
import pet.project.pet.model.User;
import pet.project.pet.remote.ApiUtils;
import pet.project.pet.remote.SubjectService;
import pet.project.pet.remote.UserService;
import retrofit2.Call;

public class SubjectListAdapter extends BaseAdapter implements Serializable {
    private List<Subject> listData;
    private LayoutInflater layoutInflater;
    private Context context;
    customButtonListener customListner;
    private int currentUserId;
    SubjectService subjectService;

    public SubjectListAdapter(List<Subject> listData, Context context, int currentUserId) {
        this.listData = listData;
        this.context = context;
        this.currentUserId = currentUserId;
        layoutInflater = LayoutInflater.from(context);
    }

    public SubjectListAdapter(List<Subject> listData, Context context) {
        this.listData = listData;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public interface customButtonListener{
        public void onButtonClick(int position);
    }

    public void setCustomButtonListener(customButtonListener listener){
        this.customListner = listener;
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
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.subject_item_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.subjectNameView = (TextView) convertView.findViewById(R.id.textView_subjectName);
            viewHolder.subjectUserCreatedView = (TextView) convertView.findViewById(R.id.textView_subjectUserCreated);
            viewHolder.txtEditSubject = convertView.findViewById(R.id.txtEditSubject);
            viewHolder.subjectSubIdView = (TextView) convertView.findViewById(R.id.textView_subjectSubId);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Subject subject = this.listData.get(position);
        viewHolder.subjectNameView.setText(subject.getSubName());
        if (subject.getDisplayName().isEmpty() || subject.getDisplayName() == "") {
            viewHolder.subjectUserCreatedView.setText(subject.getUsername());
        } else {
            viewHolder.subjectUserCreatedView.setText(subject.getDisplayName());
        }

        if(subject.getUserId() != currentUserId){
            viewHolder.txtEditSubject.setVisibility(View.GONE);
        } else {
            viewHolder.txtEditSubject.setVisibility(View.VISIBLE);
        }

        viewHolder.txtEditSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //anh lam edit o day
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_edit_subject);

                final TextView txt_edit_name = (TextView) dialog.findViewById(R.id.txt_edit_subject_name);
                final TextView txt_edit_code = (TextView) dialog.findViewById(R.id.txt_edit_subject_code);

                txt_edit_name.setText(subject.getSubName());
                txt_edit_code.setText(subject.getSubCode());
                dialog.show();

                Button buttonSubmit = (Button) dialog.findViewById(R.id.btn_submit_edit_subject);
                buttonSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String subName = txt_edit_name.getText().toString();
                        String subCode = txt_edit_code.getText().toString();
                        if(validToEditSubject(subName, subCode)){
                            subject.setSubName(subName);
                            subject.setSubCode(subCode);
                            editSubject(subject);
                            dialog.dismiss();
                        }
                    }
                });
            }
        });
        if(!(subject.getSubCode() == null)){
            viewHolder.subjectSubIdView.setText(subject.getSubCode() + "");
        } else {
            viewHolder.subjectSubIdView.setText("");
        }

        return convertView;
    }

    private boolean validToEditSubject(String name, String code) {
        if (name.trim().isEmpty() || name.trim()==""){
            Toast.makeText(context, "Subject name is required!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(code.trim().isEmpty() || code.trim() == ""){
            Toast.makeText(context, "Subject Code is required!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void editSubject(Subject subject){
        subjectService = ApiUtils.getSubjectService();
        Call<ResObj> call = subjectService.updateSubject(subject);
        try {
            call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ViewHolder {
        TextView subjectNameView;
        TextView subjectUserCreatedView;
        TextView subjectSubIdView;
        TextView txtEditSubject;
    }
}
