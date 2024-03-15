package shop.mtcoding.blog.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog._core.errors.exception.Exception401;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserRepository userRepository;
    private final HttpSession session;

    @PostMapping("/user/update")
    public String update(UserRequest.UpdateDTO requestDTO) {
        User user = (User) session.getAttribute("sessionUser");
        User updateSessionUser = userRepository.updateById(user.getId(), requestDTO.getPassword(), requestDTO.getEmail());
        session.setAttribute("sessionUser", updateSessionUser);

        return "redirect:/";
    }

    @PostMapping("/join")
    public String join(UserRequest.JoinDTO requestDTO) {
        User sessionUser = userRepository.save(requestDTO.toEntity());
        session.setAttribute("sessionUser", sessionUser);
        return "redirect:/";
    }

    @GetMapping("/join-form")
    public String joinForm() {
        return "user/join-form";
    }

    @PostMapping("/login")
    public String login(String username, String password) {
        User sessionUser = userRepository.findByUsername(username, password);
        session.setAttribute("sessionUser", sessionUser);
        return "redirect:/";
    }

    @GetMapping("/login-form")
    public String loginForm() {
        return "/user/login-form";
    }

    @GetMapping("/user/update-form")
    public String updateForm(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User user = userRepository.findById(sessionUser.getId());
        request.setAttribute("user", user);
        return "user/update-form";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }
}