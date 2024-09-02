package no.hvl.dat250.pollApp;

import no.hvl.dat250.pollApp.entity.User;
import no.hvl.dat250.pollApp.repo.DomainManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PollTest {

    @Autowired
    private DomainManager domainManager;

    private User user1, user2;

    @BeforeEach
    void setUp() {
        domainManager = new DomainManager();
        user1 = new User("user1", "user1@gmail.com");
        user2 = new User("user2", "user2@gmail.com");
    }

    @Test
    void createPollTest() {
        
    }
}
