package pet.project.pet.remote;

import java.util.List;

import pet.project.pet.model.Group;
import pet.project.pet.model.ResObj;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GroupService {

    @GET("api/group/getAllActiveGroups")
    Call<List<Group>> getAllGroups();

    @POST("api/group/createGroup")
    Call<ResObj> createGroup(@Body Group group);

    @GET("api/group/getGroupById/{groupId}")
    Call<List<Group>> getGroupById(@Path("groupId") int groupId);

    @POST("api/group/updateGroup")
    Call<ResObj> updateGroup(@Body Group group);
}
