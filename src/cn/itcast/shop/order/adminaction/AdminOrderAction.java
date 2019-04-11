package cn.itcast.shop.order.adminaction;

import cn.itcast.shop.order.service.OrderService;
import cn.itcast.shop.order.vo.Order;
import cn.itcast.shop.order.vo.OrderItem;
import cn.itcast.shop.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.util.List;


/**
 * 后台订单管理的action
 */
public class AdminOrderAction extends ActionSupport implements ModelDriven<Order> {
    //模型驱动使用的对象
    private Order order = new Order();
    @Override
    public Order getModel() {
        return order;
    }

    //接收page参数
    private Integer page;
    public void setPage(Integer page) {
        this.page = page;
    }

    //注入订单管理的service
    private OrderService orderService;
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    //带分页查询的执行的方法
    public String findAll(){
        //分页的查询
        PageBean<Order> pageBean = new PageBean<Order>();
        pageBean.setPage(page);
        pageBean = orderService.findByPage(pageBean);
        ActionContext.getContext().getValueStack().set("pageBean",pageBean);
        //页面的跳转
        return "findAllSuccess";
    }

    /**
     * 根据订单id查询订单项
     * @return
     */
    public String findOrderItem(){
        //根据订单id查询订单项
        List<OrderItem> odList = orderService.findOderItem(order.getOid());
        ActionContext.getContext().getValueStack().set("odList",odList);
        return "itemSuccess";
    }

    /**
     * 后台修改订单状态的方法
     * @return
     */
    public String updateState(){
        //1、根据订单id查询订单
        Order currorder = orderService.findByOid(order.getOid());
        //2、修改订单状态
        currorder.setState(3);
        orderService.update(currorder);
        //3、页面跳转
        return "updateStateSuccess";
    }

}
