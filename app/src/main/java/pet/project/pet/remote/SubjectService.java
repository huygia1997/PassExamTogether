package pet.project.pet.remote;

import java.util.List;

import pet.project.pet.model.ResObj;
import pet.project.pet.model.Subject;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface SubjectService {
    @GET("api/subject/getAllActiveSubjects")
    Call<List<Subject>> getAllSubjects();

    @POST("api/subject/createSubject")
    Call<ResObj> createNewSubject(@Body Subject subject);

}
