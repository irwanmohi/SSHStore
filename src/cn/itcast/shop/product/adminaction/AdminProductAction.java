package cn.itcast.shop.product.adminaction;

import cn.itcast.shop.categorysecond.service.CategorySecondService;
import cn.itcast.shop.categorysecond.vo.CategorySecond;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class AdminProductAction extends ActionSupport implements ModelDriven<Product> {

    //模型驱动
    private Product product = new Product();
    @Override
    public Product getModel() {
        product.setCategorySecond(new CategorySecond());
        return product;
    }

    //接收page
    private Integer page;
    public void setPage(Integer page) {
        this.page = page;
    }

    //注入productService
    private ProductService productService;
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    //注入categorySecondService
    private CategorySecondService categorySecondService;
    public void setCategorySecondService(CategorySecondService categorySecondService) {
        this.categorySecondService = categorySecondService;
    }

    //文件上传的参数
    private File upload;  //上传的文件，文件的名称必须与表单中的name一致
    private String uploadFileName;  //接收文件上传的文件名
    private String uploadContextType;  //接收文件上传的文件的MIME的类型

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public void setUploadContextType(String uploadContextType) {
        this.uploadContextType = uploadContextType;
    }

    /**
     * 带分页查询商品
     */
    public String findAllByPage(){
        //调用service完成查询操作
        PageBean<Product> pageBean = new PageBean<Product>();
        pageBean.setPage(page);
        pageBean = productService.findByPage(pageBean);
        //将数据传递到页面
        ActionContext.getContext().getValueStack().set("pageBean",pageBean);
        return "findAll";
    }

    /**
     * 添加商品前先查询所有二级分类，跳转到add.jsp
     * @return
     */
    public String addProduct(){
        //查询所有二级分类
        List<CategorySecond> csList = categorySecondService.findAll();
        //通过值栈保存数据
        ActionContext.getContext().getValueStack().set("csList",csList);
        //页面跳转
        return "addSuccess";
    }

    /**
     * 商品的保存方法
     * @return
     */
    public String save() throws Exception{
        product.setPdate(new Date());
        if (upload != null) {
            //获得文件上传的磁盘绝对路径
            String realPath = ServletActionContext.getServletContext().getRealPath("/products");
            //创建一个文件
            File diskFile = new File(realPath + "//" + uploadFileName);
            //文件上传
            FileUtils.copyFile(upload,diskFile);
            product.setImage("products/" + uploadFileName);

        }
        //调用service完成保存的操作
        productService.save(product);
        return "saveSuccess";
    }

    /**
     * 删除商品的方法
     * @return
     */
    public String delete(){
        //先查询再删除
        product = productService.findByPid(product.getPid());
        //删除上传的图片
        String path = product.getImage();
        if ( path != null && !"".equals(path)){
            String realPath = ServletActionContext.getServletContext().getRealPath("/"+path);
            File file = new File(realPath);
            file.delete();
        }
        //删除商品
        productService.delete(product);
        return "deleteSuccess";
    }

    /**
     * 编辑商品的方法
     * @return
     */
    public String edit(){
        //根据pid查询该商品
        product = productService.findByPid(product.getPid());
        ActionContext.getContext().getValueStack().set("edit_csid",product.getCategorySecond().getCsid());
        //查询所有二级分类的集合
        List<CategorySecond> csList = categorySecondService.findAll();
        //将数据保存到页面
        ActionContext.getContext().getValueStack().set("csList",csList);
        //页面跳转
        return "editSuccess";
    }

    /**
     * 修改商品的方法
     * @return
     */
    public String update() throws IOException {
        product.setPdate(new Date());
        //文件上传
        if (upload != null && !"".equals(upload)){
            //删除原来的图片
            String exPath = product.getImage();
            File exFile = new File(ServletActionContext.getServletContext().getRealPath("/" + exPath));
            exFile.delete();
            //文件上传
            //获得文件上传的磁盘绝对路径
            String realPath = ServletActionContext.getServletContext().getRealPath("/products");
            File diskFile = new File(realPath + "//" + uploadFileName);
            FileUtils.copyFile(upload,diskFile);
            product.setImage("products/" + uploadFileName);
        }
        //修改商品的数据到数据库
        productService.update(product);
        //页面跳转
        return "updateSuccess";
    }

}
