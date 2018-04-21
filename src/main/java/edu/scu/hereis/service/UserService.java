package edu.scu.hereis.service;

import edu.scu.hereis.dao.UserMapper;
import edu.scu.hereis.entity.User;
import edu.scu.hereis.exception.UserServiceException;
import org.apache.ibatis.session.SqlSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户相关服务类
 */
@Service
public class UserService {

    public final static String AUTHORIZED = "RECORDER";//授权编辑
    public final static String UNAUTHORIZED = "NORMAL";//未授权编辑

    @Autowired
    private SqlSession sqlSession;

    /**
     * 修改用户信息，不会修改用户权限
     *
     * @param user 要修改的用户信息
     */
    @Transactional
    public void updateUser(User user) {

        //检查输入是否合法
        if (null == user || null == user.getHereisId()) {
            throw new UserServiceException(UserServiceException.INVALID_INPUT);
        }

        //修改用户信息
        user.setRole(null);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.updateByPrimaryKeySelective(user);

        return;
    }

    /**
     * 传入用户的hereisId，将对应用户删除
     *
     * @param hereisId 用户id
     */
    @Transactional
    public void deleteUser(String hereisId) {

        //检查输入是否合法
        if (null == hereisId) {
            throw new UserServiceException(UserServiceException.INVALID_INPUT);
        }

        //从数据库中删除用户信息
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.deleteByPrimaryKey(hereisId);

        return;
    }

    /**
     * 传入一个新的用户，将用户添加到数据库中
     *
     * @param user 要添加的用户
     */
    @Transactional
    public void addUser(User user) {

        //检查输入，如果输入的用户为null或者输入的用户hereisId不存在，则抛出异常
        if (null == user || null == user.getHereisId()) {
            throw new UserServiceException(UserServiceException.INVALID_INPUT);
        }

        //插入用户信息到数据库
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.insert(user);

        return;
    }

    /**
     * 传入hereisId获取该Id对应的用户信息，输入参数为null或者用户不存在会抛出<code>UserServiceException</code>
     *
     * @param hereisId 用户id
     */
    @Transactional
    public User getUserHereisId(String hereisId) {

        //检查输入是否合法
        if (null == hereisId) {
            throw new UserServiceException(UserServiceException.INVALID_INPUT);
        }

        //获取用户信息
        User user = null;
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        user = userMapper.selectByPrimaryKey(hereisId);

        //判断用户是否存在
        if (null == user) {
            throw new UserServiceException(UserServiceException.USER_NOT_EXSIST);
        }

        return user;

    }

    /**
     * 传入hereisId，赋给用户进行热点编辑的权限。
     *
     * @param hereisId
     */
    @Transactional
    public void setUserAuthority(String hereisId) {

        //检查输入是否合法
        if (null == hereisId) {
            throw new UserServiceException(UserServiceException.INVALID_INPUT);
        }

        //修改用户的权限为可修改热点
        setUserRole(hereisId, AUTHORIZED);

        return;

    }

    /**
     * 传入hereisId，取消用户进行热点编辑的权限。
     *
     * @param hereisId
     */
    @Transactional
    public void cancelUserAuthority(String hereisId) {

        //检查输入是否合法
        if (null == hereisId) {
            throw new UserServiceException(UserServiceException.INVALID_INPUT);
        }

        //修改用户的权限为不可修改热点
        setUserRole(hereisId, UNAUTHORIZED);

        return;
    }


    /**
     * 传入hereisId和role修改对应的用户的角色
     *
     * @param hereisId 用户id
     * @param role     用户角色
     */
    private void setUserRole(String hereisId, String role) {


        //修改用户的权限为不可修改热点
        User user = new User();
        user.setHereisId(hereisId);
        user.setRole(role);

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.updateByPrimaryKeySelective(user);

        return;
    }
}
