package cn.itcast.shop.user.dao.daoImpl;

import cn.itcast.shop.user.dao.IUserDao;
import cn.itcast.shop.user.domain.User;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class IUserDaoImpl extends HibernateDaoSupport implements IUserDao {

    @Override
    public User findByUsername(String username) {
        String hql = " from User where username = ? ";
        List<User> list = this.getHibernateTemplate().find(hql, username);
        if (list != null && list.size() > 0){
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void save(User user) {
        this.getHibernateTemplate().save(user);
    }

    @Override
    public User findByCode(String code) {
        String hql = "from User where code = ?";
        List<User> list = this.getHibernateTemplate().find(hql,code);
        if (list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public void update(User existUser) {
        this.getHibernateTemplate().update(existUser);
    }

    @Override
    public User login(User user) {
        String hql = "from User where username = ? and password = ? and state = ?";
        List<User> list = this.getHibernateTemplate().find(hql,user.getUsername(),user.getPassword(),1);
        if (list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }
}
