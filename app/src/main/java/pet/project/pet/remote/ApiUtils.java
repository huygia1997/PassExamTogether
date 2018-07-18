package pet.project.pet.remote;

public class ApiUtils {
    public static final String BASE_UTL = "http://10.82.137.193/pet.com/";

    public static UserService getUserService() {
        return RetrofitClient.getClient(BASE_UTL).create(UserService.class);
    }
}
