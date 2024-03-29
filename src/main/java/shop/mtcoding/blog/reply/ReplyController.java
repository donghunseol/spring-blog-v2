package shop.mtcoding.blog.reply;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import shop.mtcoding.blog._core.utils.ApiUtil;
import shop.mtcoding.blog.user.User;

@RequiredArgsConstructor
@Controller
public class ReplyController {
    private final ReplyService replyService;
    private final HttpSession session;

    @DeleteMapping("/replies/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        replyService.댓글삭제(id, sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    @PostMapping("/api/replies")
    public ResponseEntity<?> save(@RequestBody ReplyRequest.SaveDTO requestDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Reply reply = replyService.댓글쓰기(requestDTO, sessionUser);
        return ResponseEntity.ok(new ApiUtil<>(reply));
    }
}
