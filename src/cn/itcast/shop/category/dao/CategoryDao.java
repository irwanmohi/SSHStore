package cn.itcast.shop.category.dao;

import cn.itcast.shop.category.vo.Category;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * 一级分类持久层对象
 */
public class CategoryDao extends HibernateDaoSupport {

    //查询所有一级分类的方法
    public List<Category> findAll() {
        String hql = "from Category";
        List<Category> list = this.getHibernateTemplate().find(hql);
        return list;
    }

    /**
     * DAO层保存一级分类的方法
     * @param category
     */
    public void save(Category category) {
        this.getHibernateTemplate().save(category);
    }

    /**
     * Dao层查询一级分类的方法
     * @param cid
     * @return
     */
    public Category findByCid(Integer cid) {
        return this.getHibernateTemplate().get(Category.class,cid);
    }

    /**
     * DAO层删除一级分类的方法
     * @param category
     */
    public void delete(Category category) {
        this.getHibernateTemplate().delete(category);
    }

    /**
     * DAO层的修改一级分类的方法
     * @param category
     */
    public void update(Category category) {
        this.getHibernateTemplate().update(category);
    }
}
