package cn.itcast.shop.product.dao;

import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.util.PageBean;
import cn.itcast.shop.util.PageHibernateCallback;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * 商品持久层
 */
public class ProductDao extends HibernateDaoSupport {

    //首页上热门商品的查询
    public List<Product> findHot() {
        //使用离线条件查询。
        DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
        //查询热门的商品
        criteria.add(Restrictions.eq("is_hot",1));
        //倒叙排序输出
        criteria.addOrder(Order.desc("pdate"));
        //执行查询
        List<Product> list = this.getHibernateTemplate().findByCriteria(criteria,0,10);
        return list;
    }

    //首页上最新商品的查询
    public List<Product> findNew() {
        //使用离线条件查询
        DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
        //按日期倒叙排序
        criteria.addOrder(Order.desc("pdate"));
        //执行查询
        List<Product> list = this.getHibernateTemplate().findByCriteria(criteria,0,10);
        return list;
    }

    //根据pid查询商品
    public Product findByPid(Integer pid) {
        return this.getHibernateTemplate().get(Product.class,pid);
    }

    //根据分类的id查询商品的个数
    public PageBean<Product> findCountByCid(PageBean<Product> productPageBean, int cid) {
        String hql = "select count(*) from Product p where p.categorySecond.category.cid = ?";
        List<Long> list = this.getHibernateTemplate().find(hql,cid);
        if (list != null && list.size() > 0){
            productPageBean.setTotalCount(list.get(0).intValue());
        } else {
            productPageBean.setTotalCount(0);
        }
        return productPageBean;
    }

    //根据分类id查询商品的集合
    public PageBean<Product> findPageByCid(PageBean<Product> productPageBean, int cid) {
        String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
        //分页的另一种方法
        List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql,new Object[]{cid},productPageBean.getBeginIndex(),productPageBean.getLimit()));
        productPageBean.setList(list);
        return productPageBean;
    }

    //根据二级分类id查询记录数
    public PageBean<Product> findCountByCsid(PageBean<Product> pageBean, Integer csid) {
        String hql = "select count(*) from Product p where p.categorySecond.csid = ?";
        List<Long> list = this.getHibernateTemplate().find(hql, csid);
        if (list != null && list.size() > 0){
            pageBean.setTotalCount(list.get(0).intValue());
        } else {
            pageBean.setTotalCount(0);
        }
        return pageBean;
    }

    //根据二级分类id查询商品的集合
    public PageBean<Product> findPageByCsid(PageBean<Product> pageBean, Integer csid) {
        String hql = "select p from Product p join p.categorySecond cs where cs.csid = ?";
        List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql,new Object[]{csid},pageBean.getBeginIndex(),pageBean.getLimit()));
        pageBean.setList(list);
        return pageBean;
    }

    /**
     * Dao层统计商品个数的方法
     * @return
     */
    public int findCount() {
        String hql = "select count(*) from Product";
        List<Long> list = this.getHibernateTemplate().find(hql);
        if (list != null && list.size() > 0){
            return list.get(0).intValue();
        }
        return 0;
    }

    /**
     * Dao层分页查询商品的方法
     * @param beginIndex
     * @param limit
     * @return
     */
    public List<Product> findAll(int beginIndex, int limit) {
        String hql = "from Product order by pdate desc";
        List<Product> pLicst = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql,null,beginIndex,limit));
        if (pLicst != null && pLicst.size() > 0){
            return pLicst;
        }
        return null;
    }

    /**
     * Dao层保存商品的方法
     * @param product
     */
    public void save(Product product) {
        this.getHibernateTemplate().save(product);
    }

    /**
     * Dao层删除商品的方法
     * @param product
     */
    public void delete(Product product) {
        this.getHibernateTemplate().delete(product);
    }

    /**
     * Dao层修改商品的方法
     * @param product
     */
    public void update(Product product) {
        this.getHibernateTemplate().update(product);
    }
}
