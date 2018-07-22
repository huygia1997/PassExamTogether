package pet.project.pet.remote;

import pet.project.pet.model.ResObj;
import pet.project.pet.model.Vote;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface VoteService {

    @POST("api/vote/addUpvoteSelectedAnswerbyCurrentUser")
    Call<ResObj> addUpvoteSelectedAnswerbyCurrentUser (@Body Vote vote);

    @GET("api/vote/getStatusVoteAnswer")
    Call<ResObj> getStatusVoteAnswer(
            @Query("ansId") int ansId,
            @Query("userId") int userId
    );

    @POST("api/vote/updateVote")
    Call<ResObj> updateSelectedAnswerVote(@Body Vote vote);

}
