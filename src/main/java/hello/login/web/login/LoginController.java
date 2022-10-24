package hello.login.web.login;
import hello.login.domain.login.LoginService;
import hello.login.domain.member.Member;
import hello.login.web.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
    /**
     * 로그인 미인증시에 redirect된다.
     * getmapping이 실행이되고 loginform으로 이동한다.
     */
    private final LoginService loginService;
    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form) {

        return "login/loginForm";
    }

    /**
     * 로그인 이후 redirect 처리
     *getmapping으로 loginform으로 오면 그때의 url은 /login/redirectURL={String url}이 된다.
     * 이 상태에서 post하면 url쿼리파라미터가 같이 넘어오기 때문에
     * @RequestParam(defaultValue = "/") String redirectURL를 해준다.
     */
    @PostMapping("/login")
    public String loginV4(
            @Valid @ModelAttribute LoginForm form, BindingResult bindingResult,
            @RequestParam(defaultValue = "/") String redirectURL,
            HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }
        Member loginMember = loginService.login(form.getLoginId(),
                form.getPassword());
        log.info("login? {}", loginMember);
        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }
        //로그인 성공 처리
        //세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성
        HttpSession session = request.getSession();
        //세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
        //redirectURL 적용
        return "redirect:" + redirectURL;
    }
    @PostMapping("/logout")
    public String logoutV3(HttpServletRequest request) {
        //세션을 삭제한다.
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}

