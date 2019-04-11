package cn.itcast.shop.categorysecond.adminaction;

import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.category.vo.Category;
import cn.itcast.shop.categorysecond.service.CategorySecondService;
import cn.itcast.shop.categorysecond.vo.CategorySecond;
import cn.itcast.shop.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.util.List;

/**
 * 后台二级分类管理的Action
 */
public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond> {

    //接收page
    private Integer page;
    public void setPage(Integer page) {
        this.page = page;
    }

    //模型驱动使用的对象
    private CategorySecond categorySecond = new CategorySecond();
    @Override
    public CategorySecond getModel() {
        categorySecond.setCategory(new Category());
        return categorySecond;
    }

    //注入二级分类的Service
    private CategorySecondService categorySecondService;
    public void setCategorySecondService(CategorySecondService categorySecondService) {
        this.categorySecondService = categorySecondService;
    }

    //注入一级分类的service
    private CategoryService categoryService;
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * 分页查找所有二级分类
     * @return
     */
    public String findAllByPage(){
        PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();
        pageBean.setPage(page);
        pageBean = categorySecondService.findByPage(pageBean);
        ActionContext.getContext().getValueStack().set("pageBean",pageBean);
        return "findAllByPageSuccess";
    }

    /**
     * 跳转到添加页面
     * @return
     */
    public String addPage(){
        //查询所有一级分类
        List<Category> cList = categoryService.findAll();
        //把数据显示到页面下拉列表中
        ActionContext.getContext().getValueStack().set("cList",cList);
        //页面跳转
        return "addPageSuccess";
    }

    /**
     * 保存二级分类的方法
     * @return
     */
    public String save(){
        categorySecondService.save(categorySecond);
        return "saveSuccess";
    }

    /**
     * 删除二级分类
     * @return
     */
    public String delete(){
        //如果级联删除，先查询再删除，配置cascade
        categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
        categorySecondService.delete(categorySecond);
        return "deleteSuccess";
    }

    /**
     * 在编辑之前先根据csid查询二级分类
     * @return
     */
    public String edit(){
        //根据二级csid查询二级分类的对象
        categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
        //查询所有一级分类
        List<Category> cList = categoryService.findAll();
        ActionContext.getContext().getValueStack().set("cList",cList);
        ActionContext.getContext().getValueStack().set("edit_cid",categorySecond.getCategory().getCid());
        return "editSuccess";
    }

    /**
     * 修改二级分类
     * @return
     */
    public String update(){
        categorySecondService.update(categorySecond);
        return "updateSuccess";
    }
}
