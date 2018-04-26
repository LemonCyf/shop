package com.zjitc.control;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.google.gson.Gson;
import com.zjitc.config.AlipayConfig;
import com.zjitc.domain.*;
import com.zjitc.service.CarService;
import com.zjitc.service.ExpressService;
import com.zjitc.service.OrderService;
import com.zjitc.service.imp.CarServiceImp;
import com.zjitc.service.imp.ExpressServiceImp;
import com.zjitc.service.imp.OrderServiceImp;
import com.zjitc.servlet.RequestMapping;
import com.zjitc.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Create By Intellij IDEA
 *
 * @author:cyf
 * @create-Time:2017/12/3 15:11
 * @description:
 */
public class OrderContorl {
    @RequestMapping(url = "/orderInfo")
    public void orderInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object o = request.getSession().getAttribute("user");
        User user = (User) o;
        CarService carService = new CarServiceImp();
        List<CarItem> carItems = carService.findAll(user.getUid());
        Order order = new Order();
        order.setOid(Utils.getUUID());
        order.setOrdertime(new Date());
        order.setState(0);
        order.setUid(user.getUid());
        order.setName(user.getName());
        order.setTelephone(user.getTelephone());

        List<OrderItem> products = new ArrayList<>();
        double totalprice = 0;
        for (CarItem item : carItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setItemid(Utils.getUUID());
            orderItem.setOid(order.getOid());
            orderItem.setPid(item.getProduct().getPid());
            orderItem.setProduct(item.getProduct());
            orderItem.setCount(item.getCount());
            orderItem.setSubtotal(item.getPrice());

            products.add(orderItem);
            totalprice += orderItem.getSubtotal();
        }
        order.setTotal(totalprice);
        order.setItems(products);
        //添加到数据库中
        OrderService orderService = new OrderServiceImp();
        orderService.add(order);
        //将购物车中的数据清空
        carService.deleteAll(user.getUid());

        request.setAttribute("order", order);
        request.getRequestDispatcher("/jsp/order_info.jsp").forward(request, response);
    }

    @RequestMapping(url = "/orderList")
    public void orderList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        OrderService service = new OrderServiceImp();
        int currPage = Integer.parseInt(request.getParameter("currPage"));
        Page<Order> page = service.findLimit(user.getUid(), currPage);
        request.setAttribute("page", page);
        request.getRequestDispatcher("/jsp/order_list.jsp").forward(request, response);
    }

    @RequestMapping(url = "/orderPay")
    public void orderPay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oid = request.getParameter("oid");
        OrderService orderService = new OrderServiceImp();
        Order order = orderService.findByOid(oid);
        request.setAttribute("order", order);
        request.getRequestDispatcher("/jsp/order_info.jsp").forward(request, response);
    }

    @RequestMapping(url = "/orderState")
    public void orderState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oid = String.valueOf(request.getAttribute("oid"));
        OrderService orderService = new OrderServiceImp();
        int value = orderService.updateByOid(oid);
        if (value > 0) {
            //将订单号和快递单号（自动生成)存入表中
            ExpressService expressService = new ExpressServiceImp();
            String eid = Utils.RandomEid(13);
            expressService.insertInExpress(eid, oid);
            String msg = "该订单已支付成功";
            String address = request.getContextPath() + "/orderList?currPage=0";
            request.setAttribute("msg", msg);
            request.setAttribute("address", address);
            request.getRequestDispatcher("/jsp/msg.jsp").forward(request, response);
        } else {
            String msg = "支付失败";
            String address = request.getContextPath() + "/orderList?currPage=0";
            request.setAttribute("msg", msg);
            request.setAttribute("address", address);
            request.getRequestDispatcher("/jsp/msg.jsp").forward(request, response);
        }
    }

    @RequestMapping(url = "/returnMoney")
    public void returnMoney(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, AlipayApiException {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();

        //商户订单号，商户网站订单系统中唯一订单号
        String out_trade_no = new String(request.getParameter("WIDTRout_trade_no").getBytes("ISO-8859-1"), "UTF-8");
        //支付宝交易号
        String trade_no = new String(request.getParameter("WIDTRtrade_no").getBytes("ISO-8859-1"), "UTF-8");
        //请二选一设置
        //需要退款的金额，该金额不能大于订单金额，必填
        String refund_amount = new String(request.getParameter("WIDTRrefund_amount").getBytes("ISO-8859-1"), "UTF-8");
        //退款的原因说明
        String refund_reason = new String(request.getParameter("WIDTRrefund_reason").getBytes("ISO-8859-1"), "UTF-8");
        //标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
        String out_request_no = new String(request.getParameter("WIDTRout_request_no").getBytes("ISO-8859-1"), "UTF-8");

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"trade_no\":\"" + trade_no + "\","
                + "\"refund_amount\":\"" + refund_amount + "\","
                + "\"refund_reason\":\"" + refund_reason + "\","
                + "\"out_request_no\":\"" + out_request_no + "\"}");

        //请求
        String result = alipayClient.execute(alipayRequest).getBody();
        System.out.println(result);
        Gson gson = new Gson();
        AlipayRefund refundObject = gson.fromJson(result, AlipayRefund.class);
        AResponse alipayTradeRefundResponse = refundObject.getAlipayTradeRefundResponse();
        String msg = alipayTradeRefundResponse.getMsg();
        if (!msg.equals("Success")) {
            request.setAttribute("msg", "退款失败");
            String address = request.getContextPath() + "/orderList?currPage=0";
            request.setAttribute("address", address);
            request.getRequestDispatcher("/jsp/msg.jsp").forward(request, response);
            ExpressService expressService = new ExpressServiceImp();
            expressService.deleteByOid(refundObject.getAlipayTradeRefundResponse().getOut_trade_no());
        }
        //在这里应该设置state 2 表示已经退 到我的订单的时候应该显示该订单“已退款”款这几个字 不可点
        String oid = refundObject.getAlipayTradeRefundResponse().getOut_trade_no();
        OrderService orderService = new OrderServiceImp();
        int value = orderService.updateByOid2(oid);
        if (value > 0) {
            request.getRequestDispatcher("/orderList?currPage=0").forward(request, response);
        }

        //输出
        //out.println(result);
    }

    @RequestMapping(url = "/deleteOrder")
    public void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oid = request.getParameter("oid");
        OrderService orderService = new OrderServiceImp();
        orderService.delete(oid);
        response.sendRedirect(request.getContextPath()+"/orderList?currPage=0");
    }
}
