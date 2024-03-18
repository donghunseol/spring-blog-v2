package shop.mtcoding.blog.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception400;
import shop.mtcoding.blog._core.errors.exception.Exception401;

import java.util.Optional;

@RequiredArgsConstructor
@Service // IoC 등록
public class UserService {

    private final UserJPARepository userJPARepository;

    @Transactional
    public User 로그인(UserRequest.LoginDTO requestDTO){
        // 해시 검사 비교 코드는 여기에!
        User sessionUser = userJPARepository.findByUsernameAndPassword(requestDTO.getUsername(), requestDTO.getPassword())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다")); // orElseThrow 는 값이 null이면 throw를 하고 있으면 앞의 결과를 출력한다.
        return sessionUser; // sessionUser 에 던져야 하니깐 return 을 해야 한다.
    }

    @Transactional
    public void 회원가입(UserRequest.JoinDTO requestDTO){ // ex) ssar
        // 1. 유효성 검사 (컨트롤러 책임)

        // 2. 유저네임 중복검사 (서비스 체크) - DB 연결이 필요함 (DB 연결이 필요하면 서비스에서 처리 한다)
        Optional<User> userOP = userJPARepository.findByUsername(requestDTO.getUsername());

        // 예외 처리
        if(userOP.isPresent()){ // ssar이 중복 되었다
            throw new Exception400("중복된 유저네임입니다");
        }


        userJPARepository.save(requestDTO.toEntity());
    }
}
