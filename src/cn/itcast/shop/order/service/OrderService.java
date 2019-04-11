package cn.itcast.shop.order.service;

import cn.itcast.shop.order.dao.OrderDao;
import cn.itcast.shop.order.vo.Order;
import cn.itcast.shop.order.vo.OrderItem;
import cn.itcast.shop.util.PageBean;
import org.aspectj.weaver.ast.Or;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 订单模块的业务层
 */
@Transactional
public class OrderService {

    private OrderDao orderDao;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    //保存订单的业务层代码
    public void save(Order order) {
        orderDao.save(order);
    }

    //我的订单业务层代码
    public PageBean<Order> findByPageUid(Integer uid, Integer page){
        PageBean<Order> pageBean = new PageBean<Order>();
        //设置当前页数
        pageBean.setPage(page);
        //设置每页的记录数
        Integer limit = 5;
        pageBean.setLimit(limit);
        //设置总记录数
        Integer totalCount = null;
        totalCount = orderDao.findByCountUid(uid);
        //设置每页显示数据的集合
        List<Order> list = orderDao.findByPageUid(uid, pageBean.getBeginIndex(),limit);
        pageBean.setList(list);
        pageBean.setTotalCount(totalCount);
        return pageBean;
    }

    //根据订单的id查询订单的方法
    public Order findByOid(Integer oid) {
        return orderDao.findByOid(oid);
    }

    //业务层修改订单
    public void update(Order currOrder) {
        orderDao.update(currOrder);
    }

    /**
     * 业务层分页查询订单的方法
     * @param pageBean
     * @return
     */
    public PageBean<Order> findByPage(PageBean<Order> pageBean) {
        pageBean.setLimit(10);
        int totalCount = orderDao.findByCount();
        pageBean.setTotalCount(totalCount);
        List<Order> oList = orderDao.findList(pageBean.getBeginIndex(),pageBean.getLimit());
        pageBean.setList(oList);
        return pageBean;
    }

    /**
     * 业务层根据订单id查询订单项的方法
     * @param oid
     * @return
     */
    public List<OrderItem> findOderItem(Integer oid) {
        return orderDao.findOrderItem(oid);
    }
}
