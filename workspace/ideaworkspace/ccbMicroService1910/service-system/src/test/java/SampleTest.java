import com.sys.app.sysAPP;
import com.sys.entity.User;
import com.sys.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 描述
 * @author: LinWeiQi
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes =sysAPP.class)
public class SampleTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.findAll(null);
        //Assert.assertEquals(5, userList.size());
        //userList.forEach(System.out::println);
        userList.stream().forEach(System.out::println);
    }

}

