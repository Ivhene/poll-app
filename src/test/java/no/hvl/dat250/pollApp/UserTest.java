package no.hvl.dat250.pollApp;

import no.hvl.dat250.pollApp.entity.User;
import no.hvl.dat250.pollApp.repo.DomainManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserTest {

    @Autowired
    private DomainManager domainManager;

    @BeforeEach
    void setup() {
        domainManager = new DomainManager();
    }

    @Test
    void testCreateNewUser() {
        int originalUserCount = domainManager.getAllUsers().size();

        User user = new User("test", "test@gmail.com");
        domainManager.addUser(user);

        assertEquals(domainManager.getAllUsers().size(), originalUserCount + 1);
        assertEquals(user, domainManager.getUserById(user.getUsername()));

        User user2 = new User("tester", "tester@gmail.com");
        domainManager.addUser(user2);

        assertEquals(domainManager.getAllUsers().size(), originalUserCount + 2);
        assertEquals(user2, domainManager.getUserById(user2.getUsername()));
    }
}
