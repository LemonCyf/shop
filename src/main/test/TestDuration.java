import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/11/24 17:45
 * @description:
 */
public class TestDuration {
    @Test
    public void test() throws InterruptedException {
        LocalDateTime now1 = LocalDateTime.now();
        Thread.sleep(8);
        LocalDateTime now2 = LocalDateTime.now();
        System.out.println(Duration.between(now2,now1).toMillis());
    }

}
