package idusw.springboot.ckjmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping(value={"api/v2", "api/v2/"}) // http://<host-ip>:<port>/api/v1/을 get 방식으로 요청하는 경우 처리
    public String getRestApiV1(Model model){
        // org.springframework.ui.model : UI(User Interface, View, Template 에서 사용하는 Model 클래스
        // model 을 활용하여서 view 에게 속성을 전달
        model.addAttribute("name", "조경준"); // model 객체에 속성을 추가(지정)
        model.addAttribute("dept", "컴퓨터소프트웨어학과"); // model 객체에 속성을 추가(지정)
        // view or template 파일을 지정해주어야 함.
        return "./main/index"; // template 아래 main/index.html을 접근, (기본 dynamic web page)
    }
    @GetMapping(value = {"400"})
    public String go400(Model model){
        model.addAttribute("msg","정보전달");
        return "./error/400";
    }

    @GetMapping(value = {"", "/"})
    public String goHome(Model model){
        model.addAttribute("msg", "정보전달");
        return "./main/index"; // index.html에 전달
    }

}