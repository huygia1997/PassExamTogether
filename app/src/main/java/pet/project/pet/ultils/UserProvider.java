package pet.project.pet.ultils;

import android.util.Log;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import pet.project.pet.model.User;

public class UserProvider {

    Config config = new Config();
    private String BASE_URL = config.getBASE_URL() + "user/";
    private RestTemplate restTemplate = new RestTemplate();

    public void checkLogin(String username, String password){
        String Url = BASE_URL + "getAllUsers";
        Object result = restTemplate.exchange(
                //BASE_URL + "getUser?username=" + username + "&password=" + password,
                Url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {
                }).getBody();

        //Log.d("result", result.toString());
    }

}
