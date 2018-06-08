package edu.scu.hereis.service;

import edu.scu.hereis.dao.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Vicent_Chen on 2018/5/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class FileServiceTest {

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Test
    public void testUserMapper() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.selectByPrimaryKey(null);
        sqlSession.close();
    }
}