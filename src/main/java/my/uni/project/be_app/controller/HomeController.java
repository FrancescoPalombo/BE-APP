package my.uni.project.be_app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {

    @RequestMapping("/")    //Test di prova Mostro stringa a video
    public String greet(){
        return "Welcome to Chat";
    }
}
