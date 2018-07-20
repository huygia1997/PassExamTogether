package pet.project.pet.remote;

import java.util.List;

import pet.project.pet.model.Subject;
import retrofit2.Call;
import retrofit2.http.GET;

public interface SubjectService {
    @GET("api/subject/getAllSubjects")
    Call<List<Subject>> getAllSubjects();

}
