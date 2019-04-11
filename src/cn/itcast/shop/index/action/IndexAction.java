package cn.itcast.shop.index.action;

import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.category.vo.Category;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.product.vo.Product;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

/**
 * 访问首页的Action
 */
public class IndexAction extends ActionSupport {

    //注入一级分类的Service
    private CategoryService categoryService;
    //注入商品的service
    private ProductService productService;

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    /**
     * 执行访问首页的方法
     */
    public String execute(){
        //出巡所有一个分类
        List<Category> cList = categoryService.findAll();
        //将一级分类存入到Session的范围
        ActionContext.getContext().getSession().put("cList",cList);

        //查询热门商品
        List<Product> hList = productService.findHot();
        //保存到值栈中
        ActionContext.getContext().getValueStack().set("hList",hList);

        //查询最新商品
        List<Product> nList = productService.findNew();
        ActionContext.getContext().getValueStack().set("nList",nList);
        return "index";
    }

}
