package pet.project.pet.remote;


import java.util.List;

import pet.project.pet.model.Group;
import pet.project.pet.model.History;
import pet.project.pet.model.ResObj;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface HistoryService {

    @POST("api/history/createHistory")
    Call<ResObj> addToHistory (@Body History history);

    @GET("api/history/getRecentHistoryByUser/{userId}/{postType}")
    Call<List<History>> getRecentGroupHistoryByUser(@Path("userId") int userId,
                                                    @Path("postType") int postType
                                                    );

}
