package cn.itcast.shop.product.service;

import cn.itcast.shop.product.dao.ProductDao;
import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.util.PageBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品的业务层代码
 */
@Transactional
public class ProductService {
    //注入ProductDao
    private ProductDao productDao;

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    //首页上热门商品的查询
    public List<Product> findHot() {
        return productDao.findHot();
    }

    //首页上最新商品的查询
    public List<Product> findNew(){
        return productDao.findNew();
    }

    //根据商品id查询商品
    public Product findByPid(Integer pid) {
        return productDao.findByPid(pid);
    }

    //根据一级分类的cid带有分页查询商品
    public PageBean<Product> findByPageCid(PageBean<Product> productPageBean, int cid) {
        //设置总的记录数
        productPageBean = productDao.findCountByCid(productPageBean, cid);
        //每页显示数据的集合
        productPageBean = productDao.findPageByCid(productPageBean, cid);
        return productPageBean;
    }

    //根据二级分类查询商品信息
    public PageBean<Product> findByPageCsid(PageBean<Product> pageBean, Integer csid) {
        //设置总的记录数
        pageBean = productDao.findCountByCsid(pageBean, csid);
        //每页显示的数据的集合
        pageBean = productDao.findPageByCsid(pageBean, csid);
        return pageBean;
    }

    /**
     * 业务层分页查询商品
     * @param pageBean
     * @return
     */
    public PageBean<Product> findByPage(PageBean<Product> pageBean) {
        pageBean.setLimit(10);
        int totalCount = productDao.findCount();
        pageBean.setTotalCount(totalCount);
        List<Product> pList = productDao.findAll(pageBean.getBeginIndex(),pageBean.getLimit());
        pageBean.setList(pList);
        return pageBean;
    }

    /**
     * 业务层保存商品的方法
     * @param product
     */
    public void save(Product product) {
        productDao.save(product);
    }

    /**
     * 业务层删除商品的方法
     * @param product
     */
    public void delete(Product product) {
        productDao.delete(product);
    }

    /**
     * 业务层修改商品的方法
     * @param product
     */
    public void update(Product product) {
        productDao.update(product);
    }
}
