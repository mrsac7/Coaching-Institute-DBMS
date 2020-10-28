package com.xpring.edu;

import com.xpring.edu.repository.UserRepository;
import com.xpring.edu.model.User;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    @Autowired
    private UserRepository repo;

    @Autowired
    private TestEntityManager entityManager;
    
    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("sach@gmail.com");
        user.setPassword("xyz");
        user.setFirstName("Sac");
        user.setLastName("Srv");

        User savedUser = repo.save(user);

        User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(existUser.getEmail().equals(user.getEmail()));
    }   

    @Test
    public void testFindUsesrByEmail() {
        String email = "sachin@gmail.com";
        User user = repo.findByEmail(email);

        assertThat(user).isNotNull();
    }
}
