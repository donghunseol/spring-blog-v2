package shop.mtcoding.blog.reply;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception403;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.board.BoardJPARepository;
import shop.mtcoding.blog.user.User;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final BoardJPARepository boardJPARepository;
    private final ReplyJPARepository replyJPARepository;

    @Transactional
    public void 댓글삭제(Integer replyId, Integer sessionUserId) {
        Reply reply = replyJPARepository.findById(replyId)
                .orElseThrow(() -> new Exception404("댓글을 찾을 수 없습니다"));

        if (sessionUserId != reply.getUser().getId()) {
            throw new Exception403("댓글을 삭제할 권한이 없습니다");
        }
        replyJPARepository.deleteById(replyId);
    }

    @Transactional
    public Reply 댓글쓰기(ReplyRequest.SaveDTO requestDTO, User sessionUser) {
        Board board = boardJPARepository.findById(requestDTO.getBoardId())
                        .orElseThrow(() -> new Exception404("없는 게시글에 댓글을 작성할 수 없어요"));

        Reply reply = requestDTO.toEntity(sessionUser, board);

        replyJPARepository.save(reply);

        return reply;
    }
}
