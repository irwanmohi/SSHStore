package cn.itcast.shop.categorysecond.dao;

import cn.itcast.shop.categorysecond.vo.CategorySecond;
import cn.itcast.shop.util.PageBean;
import cn.itcast.shop.util.PageHibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class CategorySecondDao extends HibernateDaoSupport {

    /**
     * Dao层的统计二级分类的个数的方法
     * @return
     */
    public int findCount() {
        String hql = "select count(*) from CategorySecond";
        List<Long> list = this.getHibernateTemplate().find(hql);
        if (list != null && list.size() > 0){
            return list.get(0).intValue();
        }
        return 0;
    }

    /**
     * Dao层分页查询二级分类的方法
     * @param pageBean
     * @return
     */
    public List<CategorySecond> findList(PageBean<CategorySecond> pageBean) {
        String hql = "from CategorySecond order by csid desc";
        List<CategorySecond> list = this.getHibernateTemplate().execute(new PageHibernateCallback<CategorySecond>(hql,null,pageBean.getBeginIndex(),pageBean.getLimit()));
        if (list != null && list.size() > 0){
            return list;
        }
        return null;
    }

    /**
     * DAO层添加二级分类的方法
     * @param categorySecond
     */
    public void save(CategorySecond categorySecond) {
        this.getHibernateTemplate().save(categorySecond);
    }

    /**
     * Dao层根据csid查询二级分类
     * @param csid
     */
    public CategorySecond findByCsid(Integer csid) {
        return this.getHibernateTemplate().get(CategorySecond.class,csid);
    }

    /**
     * Dao层删除二级分类
     * @param categorySecond
     */
    public void delete(CategorySecond categorySecond) {
        this.getHibernateTemplate().delete(categorySecond);
    }

    /**
     * Dao层更新二级分类的方法
     * @param categorySecond
     */
    public void update(CategorySecond categorySecond) {
        this.getHibernateTemplate().update(categorySecond);
    }

    /**
     * Dao层查询所有二级分类的方法
     * @return
     */
    public List<CategorySecond> findAll() {
        String hql = "from CategorySecond";
        return this.getHibernateTemplate().find(hql);
    }
}
