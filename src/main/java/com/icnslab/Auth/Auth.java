package com.icnslab.Auth;

import com.google.gson.Gson;
import com.icnslab.Database.PlatformDao;
import com.icnslab.Message.AckResponse;
import com.icnslab.Message.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by alicek106 on 2017-07-20.
 */
@Repository
public class Auth {
    @Autowired
    PlatformDao platformDao;

    public String login(String id, String password){
        List<User> user = platformDao.getUser(id, password);
        String result = (user.size() == 0)?"false":"true";

        return new Gson().toJson(new AckResponse(result));
    }
}
