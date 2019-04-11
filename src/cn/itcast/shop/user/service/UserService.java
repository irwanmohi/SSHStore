package cn.itcast.shop.user.service;

import cn.itcast.shop.user.dao.daoImpl.IUserDaoImpl;
import cn.itcast.shop.user.domain.User;
import cn.itcast.shop.util.MailUtil;
import cn.itcast.shop.util.UUIDUtil;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户模块业务层代码
 */
@Transactional
public class UserService {
    //注入userDao
    private IUserDaoImpl userDao;

    public void setUserDao(IUserDaoImpl userDao){
        this.userDao = userDao;
    }

    //按用户名查询用户名的方法
    public User findByUsername(String username){
        return userDao.findByUsername(username);
    }

    //业务层完成用户注册方法
    public void save(User user) {
        user.setState(0);  // 0：代表用户未激活   1：代表用户已经激活
        String code = UUIDUtil.getUUID() + UUIDUtil.getUUID();
        user.setCode(code);
        userDao.save(user);
        //发送激活邮件
        MailUtil.sendMail(user.getEmail(),user.getCode());
    }

    //业务层根据激活码查询用户
    public User findByCode(String code) {
        return userDao.findByCode(code);
    }

    //修改用户的状态
    public void update(User existUser) {
        userDao.update(existUser);
    }

    //用户登录
    public User login(User user){
        return userDao.login(user);
    }
}
