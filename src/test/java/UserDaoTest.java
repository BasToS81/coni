

import java.util.Arrays;
import java.util.List;

import javax.xml.xpath.XPathExpressionException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gor.sellphotos.dao.User;
import com.gor.sellphotos.repository.UserRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class OTADaemonCallableTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-config-test.xml" })
public class UserDaoTest {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoTest.class);

    /** The parser. */
    @Autowired
    private UserRepository userRepository;

    /**
     * Exception.
     *
     * @throws XPathExpressionException
     *             the x path expression exception
     */
    @Test
    public void saveUser() {
    	List<User> users = userRepository.findByName("saveUser");
    	System.out.println(users.size());
    	System.out.println(Arrays.asList(users));
    	
    	User user = new User();
    	user.setName("saveUser");
    	userRepository.save(user);
    }

}