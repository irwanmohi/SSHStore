package cn.itcast.shop.user.dao;

import cn.itcast.shop.user.domain.User;

/**
 * 用户模块持久层规范
 */
public interface IUserDao {

    //按名称查询是否有该用户
    public User findByUsername(String username);

    //注册用户存入数据库
    public void save(User user);

    //根据激活码查询用户
    public User findByCode(String code);

    //修改用户的状态
    public void update(User existUser);

    //用户登录
    public User login(User user);
}
