package pet.project.pet.remote;

import pet.project.pet.model.Subject;

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

    public static AnswerService getAnswerService(){
        return RetrofitClient.getClient(BASE_UTL).create((AnswerService.class));
    }

    public static SubjectService getSubjectService(){
        return RetrofitClient.getClient(BASE_UTL).create((SubjectService.class));
    }

    public static GroupParticipantService getGroupParticipantService(){
        return RetrofitClient.getClient(BASE_UTL).create((GroupParticipantService.class));
    }

    public static VoteService getVoteService(){
        return RetrofitClient.getClient(BASE_UTL).create(VoteService.class);
    }
}

