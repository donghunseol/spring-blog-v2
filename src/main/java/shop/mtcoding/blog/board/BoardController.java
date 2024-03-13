package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardRepository boardRepository; // final 적어서 DI 하자!

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable Integer id) {
        return "redirect:/board/" + id;
    }

    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable Integer id, HttpServletRequest request) {
        return "board/update-form";
    }

    @PostMapping("/board/{id}/delete")
    public String del(@PathVariable Integer id) {
        return "redirect:/";
    }

    @PostMapping("/board/save")
    public String save() {
        return "redirect:/";
    }

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        return "index";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id, HttpServletRequest request) {
        Board board = boardRepository.findByIdJoinUser(id);
        request.setAttribute("board", board);
        return "board/detail";
    }
}
