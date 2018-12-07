package com.icnslab.Controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.icnslab.Auth.Auth;
import com.icnslab.Database.PlatformDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alicek106 on 2017-07-17.
 */

@Controller
public class IndexController {
    @Autowired
    private PlatformDao platformDao;

    @Autowired
    private Auth auth;

    @RequestMapping("/login")
    public String loginPage(){return "login";}

    @RequestMapping("/index")
    public String indexPage(){return "index";}

    @RequestMapping("/dashboard")
    public String dashboardFrame(){return "dashboard";}

    @RequestMapping(value = "/image", method = RequestMethod.GET)
    public ModelAndView imageFrame(@RequestParam(value = "uid", required = true) String uid, HttpSession session){
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<com.icnslab.Message.Container>> rateResponse =
                restTemplate.exchange("http://localhost:8080/api/build/container?uid=" + uid,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<com.icnslab.Message.Container>>() {
                        });
        List<com.icnslab.Message.Container> containerList = rateResponse.getBody();

        ResponseEntity<List<com.icnslab.Message.Image>> rateResponse2 =
                restTemplate.exchange("http://localhost:8080/api/build/image?uid=" + uid,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<com.icnslab.Message.Image>>() {
                        });
        List<com.icnslab.Message.Image> imageList = rateResponse2.getBody();

        ModelAndView model = new ModelAndView("image");
        model.addObject("containerList", containerList);
        model.addObject("imageList", imageList);

        return model;
    }

    @RequestMapping("/cluster")
    public ModelAndView clusterFrame(@RequestParam(value = "uid", required = true) String uid){
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<com.icnslab.Message.JobContainer>> rateResponse2 =
                restTemplate.exchange("http://localhost:8080/api/job/container?uid=" + uid,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<com.icnslab.Message.JobContainer>>() {
                        });
        List<com.icnslab.Message.JobContainer> jobContainerList = rateResponse2.getBody();

        ModelAndView model = new ModelAndView("cluster");
        model.addObject("containerList", jobContainerList);

        return model;
    }

    @RequestMapping("/job")
    public ModelAndView jobFrame(@RequestParam(value = "uid", required = true) String uid){
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<com.icnslab.Message.Image>> rateResponse2 =
                restTemplate.exchange("http://localhost:8080/api/build/image?uid=" + uid,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<com.icnslab.Message.Image>>() {
                        });
        List<com.icnslab.Message.Image> imageList = rateResponse2.getBody();

        ModelAndView model = new ModelAndView("job");
        model.addObject("imageList", imageList);

        return model;
    }

    @ResponseBody
    @RequestMapping(value = "/api/writeKey", method = RequestMethod.POST)
    public String writeFile(@RequestParam(value = "uid", required = true) String uid,
                          @RequestParam(value = "containerName", required = true) String containerName,
                          @RequestParam(value = "key", required = true) String key){
        try {
            String currentDir = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\keys\\";
            BufferedWriter out = new BufferedWriter(new FileWriter(currentDir + uid + "-"+ containerName + ".pem"));
            String s = key;
            out.write(s);
            out.close();
        } catch (IOException e) {
            System.err.println(e); //
            System.exit(1);
        }

        return "true";
    }

    @ResponseBody
    @RequestMapping(value = "/api/writeResultFile", method = RequestMethod.POST)
    public String writeResultFile(@RequestParam(value = "uid", required = true) String uid,
                            @RequestParam(value = "created", required = true) String created,
                                  @RequestParam(value = "output", required = true) String output){
        try {
            String currentDir = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\result\\";
            BufferedWriter out = new BufferedWriter(new FileWriter(currentDir + uid + "-" + created + ".txt"));
            String s = output;
            out.write(s);
            out.close();
        } catch (IOException e) {
            System.err.println(e); //
            System.exit(1);
        }

        return "true";
    }
    /*
    @RequestMapping("/")
    public String getAllListforBeanPropertyRowMapper(){
        List<User> list = platformDao.listForBeanPropertyRowMapper();

        for(User user : list){
            System.out.println(user.getId());
        }

        return "login";
    }*/

    @ResponseBody
    @RequestMapping(value="/API/login", method = RequestMethod.POST)
    public String login(
        @RequestParam(value = "id", required = true) String id,
        @RequestParam(value = "password", required = true) String password){

        return auth.login(id,password);
    }
}
