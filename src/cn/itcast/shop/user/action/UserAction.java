package cn.itcast.shop.user.action;

import cn.itcast.shop.user.domain.User;
import cn.itcast.shop.user.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserAction extends ActionSupport implements ModelDriven<User> {
    //模型驱动使用到的对象
    private User user = new User();
    private UserService userService;

    //接受验证码
    private String checkcode;

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User getModel() {
        return user;
    }

    /**
     * 跳转到注册页面的执行方法
     * @return
     */
    public String registPage(){

        return "registPage";
    }

    /**
     * AJAX进行异步校验用户名的执行方法
     * @throws IOException
     */
    public String findByUsername() throws IOException{
        //调用Service进行查询
        User existUser = userService.findByUsername(user.getUsername());
        //获得response对象
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=UTF-8");
        //判断
        if (existUser != null){
            //查到该用户：用户名已经存在
            response.getWriter().println("<font color='red'>用户名已经存在</font>");
        } else {
            //没查询到用户：名户名可以使用
            response.getWriter().println("<font color='green'>用户名可以使用</font>");
        }
        return NONE;
    }

    /**
     *用户注册
     */
    public String regist(){
        //判断验证码：
        //从session中获取验证码的随机值
        String checkcode1 = (String)ServletActionContext.getRequest().getSession().getAttribute("checkcode");
        if (!checkcode.equalsIgnoreCase(checkcode1)){
            this.addActionError("输入验证码错误！");
            return "registPage";
        }
        userService.save(user);
        this.addActionMessage("注册成功！请去邮箱激活！");
        return "msg";
    }

    /**
     * 激活用户
     * @return
     */
    public String active(){
        //根据激活码查询用户
        User existUser = userService.findByCode(user.getCode());
        //判断
        if (existUser == null){
            // 激活码错误
            this.addActionMessage("激活失败：激活码错误！");
            return "msg";
        } else {
            //激活成功
            //修改用户状态
            existUser.setState(1);
            existUser.setCode(null);
            userService.update(existUser);
            this.addActionMessage("激活成功：请去登录！");
            return "msg";
        }
    }

    /**
     * 跳转到登录页面
     * @return
     */
    public String loginPage(){
        return "loginPage";
    }

    /**
     * 登录的方法
     * @return
     */
    public String login(){
        User existUser = userService.login(user);
        if (existUser == null){
            this.addActionError("登录失败：用户名或密码错误或用户未激活！");
            return LOGIN;
        } else {
            //将用户的信息存入session中
            ServletActionContext.getRequest().getSession().setAttribute("existUser",existUser);
            return "loginSuccess";
        }
    }

    /**
     * 用户退出的方法
     * @return
     */
    public String quit(){
        ServletActionContext.getRequest().getSession().invalidate();
        return "quit";
    }

}
