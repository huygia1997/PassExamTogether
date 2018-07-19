package pet.project.pet.remote;

import java.util.List;

import pet.project.pet.model.Question;
import pet.project.pet.model.ResObj;
import pet.project.pet.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface QuestionService  {
    @GET("api/question/getQuestionsByGroup/{groupId}")
    Call<List<Question>> getQuestionById(
            @Path("groupId") int groupId
    );

//    @POST("api/user/createUser")
//    Call<ResObj> createUser(@Body User user);
}
