import com.fre.FreeApp;
import com.fre.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Description:
 * @author: LinWeiQi
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FreeApp.class)
public class SpringTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testSelect() {

    }

}

