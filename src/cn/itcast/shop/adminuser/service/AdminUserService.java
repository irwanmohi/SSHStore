package cn.itcast.shop.adminuser.service;

import cn.itcast.shop.adminuser.dao.AdminUserDao;
import cn.itcast.shop.adminuser.vo.AdminUser;
import org.springframework.transaction.annotation.Transactional;

/**
 * 后台登陆的业务层
 */
@Transactional
public class AdminUserService {

    //注入Dao
    private AdminUserDao adminUserDao;
    public void setAdminUserDao(AdminUserDao adminUserDao) {
        this.adminUserDao = adminUserDao;
    }

    /**
     * 业务层登录方法
     * @param adminUser
     * @return
     */
    public AdminUser login(AdminUser adminUser) {
        return adminUserDao.login(adminUser);
    }
}
