package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final EntityManager em;

    public User findByUsername(UserRequest.LoginDTO requestDTO){
        Query query = em.createQuery("SELECT u FROM User u WHERE u.username=:username AND u.password=:password", User.class);
        query.setParameter("username", requestDTO.getUsername());
        query.setParameter("password", requestDTO.getPassword());

        return (User) query.getSingleResult();
    }
}
