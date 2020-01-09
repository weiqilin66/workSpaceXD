import com.sys.app.sysAPP;
import com.sys.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

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

    }

}

