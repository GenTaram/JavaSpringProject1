package idusw.springboot.ckjmall.controller;


import idusw.springboot.ckjmall.model.Member;
import idusw.springboot.ckjmall.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    // tightly-coupled, 기존 제어 : 개발자가 객체를 제어함
    // MemberService memberService = new MemberServiceImpl3();
    final MemberService memberService;
    // loosely-coupled : IoC (Inversion of Control) 제어의 역전 , 프레임워크가 객체를 제어함
    // IoC : DL(dependency lookup), DI(dependency injection) ...
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("members/{idx}")
    //Mapping의 idx와 PathVariable의 idx가 같을 때 Path 쪽 생략 가능
    public String getOne(@PathVariable("idx") Long idx, Model model) {
        Member member = memberService.read(Member.builder().idx(idx).build());
        model.addAttribute("dto", member);
        return "./members/profile";
    }
    @GetMapping("members/register")
    public String getRegister(Model model) { // 로그인 페이지로
        model.addAttribute("member", Member.builder().build());
        return "./members/register"; // main/register.html : 버튼 -> post 메소드 호출
    }
    @PostMapping("members/register")
    public String postRegister(@ModelAttribute Member member, Model model) { // 로그인 처리해서 지정된 페이지에 전달
        model.addAttribute("msg", "등록에 성공/실패하셨습니다.");
        return "./members/reg"; // main/reg.html
    }
    @GetMapping("members/login")
    public String getLogin(Model model) { // 로그인 페이지로
        //memberService.read(new Member());
        model.addAttribute("member", Member.builder().build());
        return "./members/login"; // templates/main/login.html : surffix, prefix 설정을 안한 기본 상태
    }
    @PostMapping("members/login")
    public String postLogin(@ModelAttribute Member member, Model model, HttpSession session) { // 로그인 처리해서 지정된 페이지에 전달
        String message = "로그인 실패하셨습니다.";
        Member ret = null; // DBMS에 저장된 정보를 접근하여 생성한 객체
        if((ret = memberService.login(member)) != null) {
            session.setAttribute("id", ret.getEmail());
            session.setAttribute("idx", ret.getIdx());
            message = ret.getId() + "님 " + "로그인 성공";
        }
        model.addAttribute("msg", message);
        return "./members/welcome";
    }
    @GetMapping("members/logout")
    public String getLogout(HttpSession session) { // 로그인 페이지로
        session.invalidate(); // session 객체 무효화
        // return "./main/index"; // main/index.html : surffix, prefix 설정을 안한 기본 상태
        return "redirect:/"; // HomeController @GetMapping("/")으로 재지정
    }

    @GetMapping(value = {"members/", "members"})
    public String getMembers(HttpSession session, Model model) { // 회원 목록 조회 요청
        List<Member> memberList = memberService.readList();
        model.addAttribute("dtolist", memberList);
        return "members/list"; // members/list.html을 view로 지정하고, model을 전달함
    }
}