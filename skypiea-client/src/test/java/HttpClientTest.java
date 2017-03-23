import com.skypiea.client.AppClient;
import com.skypiea.common.utils.HttpUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-03-22 15:18
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppClient.class)
public class HttpClientTest {

    @Test
    public void testHttpClient() {
        String s = HttpUtils.doGet("http://localhost:8081");
        System.out.println(s);
    }
}
