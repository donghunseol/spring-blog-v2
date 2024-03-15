package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final EntityManager em;

    @Transactional
    public User save(User user){
        em.persist(user);
        return user;
    }

    public User findByUsername(String username, String password){
        Query query = em.createQuery("SELECT u FROM User u WHERE u.username=:username AND u.password=:password", User.class);
        query.setParameter("username", username);
        query.setParameter("password", password);

        return (User) query.getSingleResult();
    }
}
