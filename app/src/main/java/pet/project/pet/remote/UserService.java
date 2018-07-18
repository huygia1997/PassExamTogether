package pet.project.pet.remote;

import pet.project.pet.model.ResObj;
import pet.project.pet.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {

    @GET("api/user/getUser")
    Call<ResObj> getUser(
            @Query("username") String username,
            @Query("password") String password
    );

    @POST("api/user/createUser")
    Call<ResObj> createUser(@Body User user);

}
