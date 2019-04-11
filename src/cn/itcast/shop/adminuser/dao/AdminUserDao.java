package cn.itcast.shop.adminuser.dao;

import cn.itcast.shop.adminuser.vo.AdminUser;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * 后台登录Dao
 */
public class AdminUserDao extends HibernateDaoSupport {

    /**
     * dao中登录方法
     * @param adminUser
     * @return
     */
    public AdminUser login(AdminUser adminUser) {
        String hql = "from AdminUser where username = ? and password = ?";
        List<AdminUser> list = this.getHibernateTemplate().find(hql,adminUser.getUsername(),adminUser.getPassword());
        if (list != null && list.size() > 0) {
            return list.get(0);
        }

        return null;
    }
}
