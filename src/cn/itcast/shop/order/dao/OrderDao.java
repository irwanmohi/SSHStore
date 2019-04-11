package cn.itcast.shop.order.dao;

import cn.itcast.shop.order.vo.Order;
import cn.itcast.shop.order.vo.OrderItem;
import cn.itcast.shop.util.PageBean;
import cn.itcast.shop.util.PageHibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * 订单模块Dao层
 */
public class OrderDao extends HibernateDaoSupport {

    //Dao层的保存订单的方法
    public void save(Order order) {
        this.getHibernateTemplate().save(order);
    }

    //Dao层我的订单的个数的统计
    public Integer findByCountUid(Integer uid) {
        String hql = "select count(*) from Order o where o.user.uid = ?";
        List<Long> list = this.getHibernateTemplate().find(hql,uid);
        if (list != null && list.size() > 0){
            return list.get(0).intValue();
        }
        return null;
    }

    //Dao层我的订单的查询
    public List<Order> findByPageUid(Integer uid, int beginIndex, int limit) {
        String hql = "from Order o where o.user.uid = ? order by ordertime desc";
        List<Order> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql,new Object[]{uid},beginIndex,limit));
        return  list;
    }

    public Order findByOid(Integer oid) {
        return this.getHibernateTemplate().get(Order.class,oid);
    }

    //DAO层修改订单
    public void update(Order currOrder) {
        this.getHibernateTemplate().update(currOrder);
    }

    /**
     * Dao层统计订单个数的方法
     * @return
     */
    public int findByCount() {
        String hql = "select count(*) from Order";
        List<Long> list = this.getHibernateTemplate().find(hql);
        if (list != null && list.size() > 0){
            return list.get(0).intValue();
        }
        return 0;
    }

    //Dao层的带分页查询的方法
    public List<Order> findList(int beginIndex, int limit) {
        String hql = "from Order order by ordertime desc";
        List<Order> oList = this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql,null,beginIndex,limit));
        if (oList != null && oList.size() > 0){
            return oList;
        }
        return null;
    }

    /**
     * Dao层根据订单id查询订单项的方法
     * @param oid
     * @return
     */
    public List<OrderItem> findOrderItem(Integer oid) {
        String hql = "from OrderItem oi where oi.order.oid = ? ";
        List<OrderItem> list = this.getHibernateTemplate().find(hql,oid);
        if (list != null && list.size() > 0){
            return list;
        }
        return null;
    }
}
