package pet.project.pet.remote;

public class ApiUtils {
    public static final String BASE_UTL = "http://192.168.21.135/pet.com/";

    public static UserService getUserService() {
        return RetrofitClient.getClient(BASE_UTL).create(UserService.class);
    }

    public static GroupService getGroupService(){
        return RetrofitClient.getClient(BASE_UTL).create(GroupService.class);
    }

    public static QuestionService getQuestionService(){
        return RetrofitClient.getClient(BASE_UTL).create(QuestionService.class);
    }
}

