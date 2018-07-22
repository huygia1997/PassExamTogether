package pet.project.pet.remote;

import pet.project.pet.model.GroupParticipant;
import pet.project.pet.model.ResObj;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GroupParticipantService {

    @GET("api/groupParticipant/checkParticipantInGroup")
    Call<ResObj> checkParticipantInGroup(
            @Query("groupId") int groupId,
            @Query("userId") int userId
    );

    @POST("api/groupParticipant/addUserToGroupParticipant")
    Call<ResObj> addUserToGroupParticipant(@Body GroupParticipant groupParticipant);

}
