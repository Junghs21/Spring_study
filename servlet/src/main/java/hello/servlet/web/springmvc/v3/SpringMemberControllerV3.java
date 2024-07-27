package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * v3
 * Model 도입
 * ViewName 직접 반환
 * @RequestParam 사용
 * @RequestMapping -> @GetMapping, @PostMapping
 */

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    //사용될 HTTP Method를 지정할 수 있음
    //해당 RequestMapping함수는 method를 GET으로 설정해서 HTTP Get 메서드에만 호출됨
    //해당 method 설정이 없으면 POST, GET와 같이 HTTP Method의 모든 종류에서 호출이 가능
//    @RequestMapping(value = "/new-form", method = RequestMethod.GET)
    @GetMapping("/new-form")
    public String newForm() {   //Spring 어노테이션 기반의 컨트롤러는 인터페이스로 구현되어 있으므로 ModelAndView를 반환해도 되고 유연하게 문자로 반환해두 됨
        return "new-form";
    }

//    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @PostMapping("/save")   //RequestMapping을 통하여 메서드를 지정해주는 것보다 해당 메서드 전용 Mapping 기능을 제공함
    public String save(
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            Model model) {

        Member member  = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);
        return "save-result";
    }

    //HTTP method GET에서만 사용 가능
//    @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public String members(Model model) {

        List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);

        return "members";
    }
}
