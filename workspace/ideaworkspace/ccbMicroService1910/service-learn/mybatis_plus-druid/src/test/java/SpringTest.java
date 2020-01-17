import com.druid.MybatisDruidApp;
import com.druid.entity.User;
import com.druid.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Description: 描述
 * @author: LinWeiQi
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisDruidApp.class)
public class SpringTest {

    @Autowired
    UserMapper userMapper;
    @Test
    public void testSelect() {
        List<User> list = userMapper.getUser();
        System.out.println(list);
    }

}

