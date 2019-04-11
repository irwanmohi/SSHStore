package cn.itcast.shop.product.action;

import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.category.vo.Category;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.util.List;

/**
 * 商品的Action对象
 */
public class ProductAction extends ActionSupport implements ModelDriven<Product> {

    //用于接收数据的模型驱动
    private Product product = new Product();
    private ProductService productService;
    //接收商品分类
    private Integer cid;
    //注入一级分类的Service
    private CategoryService categoryService;
    //接收当前页数
    private int page;
    //接收二级分类的id
    private Integer csid;

    public Integer getCsid() {
        return csid;
    }

    public void setCsid(Integer csid) {
        this.csid = csid;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getCid() {
        return cid;
    }

    @Override
    public Product getModel() {
        return product;
    }

    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    //根据商品的id查询商品
    public String findByPid(){
        //调用service的方法完成查询
        product = productService.findByPid(product.getPid());
        return "findByPid";
    }

    //根据分类的id查询商品
    public String findByCid(){
        //List<Category> cList = categoryService.findAll();
        PageBean<Product> productPageBean = new PageBean<Product>();
        //设置当前页数
        productPageBean.setPage(page);
        //设置每页显示的记录数
        int limit = 8;
        productPageBean.setLimit(limit);
        productPageBean = productService.findByPageCid(productPageBean,cid); //根据一级分类查询商品,带分页
        //讲pageBean存入到值栈中
        ActionContext.getContext().getValueStack().set("pageBean",productPageBean);
        return "findByCid";
    }

    //根据二级分类查询商品
    public String findByCsid(){
        PageBean<Product> pageBean = new PageBean<Product>();
        pageBean.setPage(page);
        int limit = 8;
        pageBean.setLimit(limit);
        pageBean = productService.findByPageCsid(pageBean, csid);
        ActionContext.getContext().getValueStack().set("pageBean",pageBean);
        return "findByCsid";
    }

}
