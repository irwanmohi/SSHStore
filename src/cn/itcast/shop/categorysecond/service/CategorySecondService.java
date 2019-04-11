package cn.itcast.shop.categorysecond.service;

import cn.itcast.shop.categorysecond.dao.CategorySecondDao;
import cn.itcast.shop.categorysecond.vo.CategorySecond;
import cn.itcast.shop.util.PageBean;

import java.util.List;

public class CategorySecondService {

    //注入二级分类的Dao
    private CategorySecondDao categorySecondDao;
    public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
        this.categorySecondDao = categorySecondDao;
    }

    /**
     * 业务层分页查询二级分类的方法
     * @param pageBean
     * @return
     */
    public PageBean<CategorySecond> findByPage(PageBean<CategorySecond> pageBean) {
        pageBean.setLimit(10);
        int totalCount = categorySecondDao.findCount();
        pageBean.setTotalCount(totalCount);
        List<CategorySecond> cList = categorySecondDao.findList(pageBean);
        pageBean.setList(cList);
        return pageBean;
    }

    /**
     * 业务层添加二级分类的方法
     * @param categorySecond
     */
    public void save(CategorySecond categorySecond) {
        categorySecondDao.save(categorySecond);
    }

    /**
     * 业务层根据csid查询二级分类
     * @param csid
     * @return
     */
    public CategorySecond findByCsid(Integer csid) {
        return categorySecondDao.findByCsid(csid);
    }

    /**
     * 业务层删除二级分类的方法
     * @param categorySecond
     */
    public void delete(CategorySecond categorySecond) {
        categorySecondDao.delete(categorySecond);
    }

    /**
     * 业务层更新二级分类的方法
     * @param categorySecond
     */
    public void update(CategorySecond categorySecond) {
        categorySecondDao.update(categorySecond);
    }

    /**
     * 业务层查询所有二级分类的方法
     * @return
     */
    public List<CategorySecond> findAll() {
        return categorySecondDao.findAll();
    }
}
