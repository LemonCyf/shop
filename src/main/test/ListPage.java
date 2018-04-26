import org.junit.Test;

import java.util.List;

/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/11/28 9:00
 * @description:
 */
public class ListPage {
    @Test
    public void test(){
        List<Order> orders = null;
        int pdCount = 0; //当前页产品数量
        int odCount = 0; //当前页的订单数
        int pageNum = 0; //分页数量
        for(Order o: orders){
            pdCount += o.getOrderItem().size();
            odCount++;
            if(pdCount > 10){
                if(odCount <= 2){
                    odCount = 0;
                    pdCount = 0;
                }else{
                    pdCount += o.getOrderItem().size();
                    odCount = 1;
                }
                pageNum++;
            }
            }

    }

}











class Order{
    private List orderItem;
    public List getOrderItem() {
        return orderItem;
    }
}

