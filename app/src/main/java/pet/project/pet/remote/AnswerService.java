package pet.project.pet.remote;

import java.io.Serializable;
import java.util.List;

import pet.project.pet.model.Answer;
import pet.project.pet.model.ResObj;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AnswerService {

    @GET("api/answer/getAnswersByQuestion/{quesId}")
    Call<List<Answer>> getAnswersByQuestion (
            @Path ("quesId") int quesId
    );

    @POST("api/answer/createAnswer")
    Call<ResObj> createAnswer(@Body Answer answer);

    @POST("api/answer/updateAnswer")
    Call<ResObj> updateAnswer(@Body Answer answer);


}
