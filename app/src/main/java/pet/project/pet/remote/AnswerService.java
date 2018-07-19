package pet.project.pet.remote;

import java.util.List;

import pet.project.pet.model.Answer;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AnswerService {

    @GET("api/answer/getAnswersByQuestion/{quesId}")
    Call<List<Answer>> getAnswersByQuestion (
            @Path ("quesId") int quesId
    );

}
