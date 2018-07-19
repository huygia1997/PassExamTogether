package pet.project.pet.remote;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pet.project.pet.FrameActivity;
import pet.project.pet.model.Group;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AsynTaskLoadData extends AsyncTask {
    ProgressDialog progressDialog;
    Context context;
    GroupService groupService;
    private List<Group> groups;

    public AsynTaskLoadData(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("HowKteam");
        progressDialog.setMessage("Dang xu ly...");
        progressDialog.show();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        groupService = ApiUtils.getGroupService();
        Call<List<Group>> call = groupService.getAllGroups();
        call.enqueue(new Callback<List<Group>>() {
            @Override
            public void onResponse(Call<List<Group>> call, Response<List<Group>> response) {
                groups = response.body();
                ArrayList<Group> groupsArr = new ArrayList<>();
                groupsArr.addAll(groups);
                Bundle bundle = new Bundle();
                bundle.putSerializable("groups", groupsArr);
            }

            @Override
            public void onFailure(Call<List<Group>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        progressDialog.dismiss();
    }
}
