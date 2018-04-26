import com.zjitc.utils.SearchExpress;
import org.junit.Test;

/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/12/9 15:42
 * @description:
 */
public class TestSearchExpress {
    @Test
    public void test(){
        SearchExpress express = new SearchExpress();
        express.search("韵达速递","3908640297037");
    }

}
