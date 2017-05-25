package net.slipp.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by woowahan on 2017. 5. 25..
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class QuestionRepositoryTest {
    @Autowired
    private QuestionRepository qRepo;

    @Autowired
    private UserRepository uRepo;

    @Test
    public void create() throws Exception {

    }
}
