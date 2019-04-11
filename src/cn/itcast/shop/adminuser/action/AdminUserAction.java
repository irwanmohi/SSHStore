package cn.itcast.shop.adminuser.action;

import cn.itcast.shop.adminuser.service.AdminUserService;
import cn.itcast.shop.adminuser.vo.AdminUser;
import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.category.vo.Category;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

/**
 * 后台登录的Action
 */
public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser> {
    //模型驱动使用的对象
    private AdminUser adminUser = new AdminUser();
    @Override
    public AdminUser getModel() {
        return adminUser;
    }
    //注入Service
    private AdminUserService adminUserService;
    public void setAdminUserService(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    /**
     * 后台登录的方法
     */
    public String login(){
        //调用service完成登录
        AdminUser existUser = adminUserService.login(adminUser);
        if (existUser == null){
            //登录失败
            this.addActionError("亲！您的用户名或密码错误！");
            return "loginFail";
        } else {
            //登录成功
            ServletActionContext.getRequest().getSession().setAttribute("existAdminUser",existUser);
            return "loginSuccess";
        }
    }

}
