import com.skypiea.client.AppClient;
import com.skypiea.client.cache.StringRedisCache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-03-23 23:15
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppClient.class)
public class RedisTest {

    @Autowired
    StringRedisCache stringRedisCache;

    @Test
    public void testStringRedis() {
        System.out.println(stringRedisCache);
        stringRedisCache.add("username","zhangsan");
    }
}
