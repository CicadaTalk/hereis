package edu.scu.hereis.service;

import edu.scu.hereis.dao.UserMapper;
import edu.scu.hereis.entity.User;
import edu.scu.hereis.security.SecurityUser;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    SqlSession sqlSession;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = null;

        //根据用户名查找用户
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        user = userMapper.selectByPrimaryKey(username);


        //没找到用户
        if (null == user) {

            throw new UsernameNotFoundException("用户名" + username + "不存在");
        }
        // SecurityUser实现UserDetails并将user_id映射为username
        SecurityUser securityUser = new SecurityUser(user);

        return securityUser;
    }
}
