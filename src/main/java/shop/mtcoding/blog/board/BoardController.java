package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardNativeRepository boardNativeRepository; // 의존

    @PostMapping("/board/save")
    public String save(String title, String content, String username){
        boardNativeRepository.save(title, content, username);

        return "redirect:/";
    }

    @GetMapping( "/")
    public String index(HttpServletRequest request) { // controller 의 요청이 왔을 때 모델에 데이터를 넣는다. (모델은 내부에 request가 있다.)
        return "index";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id) {
        return "board/detail";
    }
}
