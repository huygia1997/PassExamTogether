package pet.project.pet.remote;

import java.util.List;

import pet.project.pet.model.Group;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GroupService {

    @GET("api/group/getAllGroups")
    Call<List<Group>> getAllGroups();

    @POST("api/group/createGroup")
    Call<Group> createGroup(
            @Body Group group
    );
}
