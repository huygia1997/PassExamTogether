package pet.project.pet.remote;

import java.util.List;

import pet.project.pet.model.Group;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GroupService {

    @GET("api/group/getAllGroups")
    Call<List<Group>> getAllGroups();

}
