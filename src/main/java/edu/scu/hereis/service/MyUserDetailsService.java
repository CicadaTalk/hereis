package edu.scu.hereis.service;

import com.alibaba.fastjson.JSONObject;
import edu.scu.hereis.configuration.Configuration;
import edu.scu.hereis.entity.User;
import edu.scu.hereis.security.SecurityUser;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    SqlSession sqlSession;
    @Autowired
    private Configuration configuration;//微信相关配置
    @Autowired
    private UserService userService;//用户服务类

    @Override
    public UserDetails loadUserByUsername(String code) throws UsernameNotFoundException {

        //进行Http请求从微信服务器获取用户信息
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(configuration.getLOGIN_URL() +
                "?appid=" + configuration.getAppID() +
                "&secret=" + configuration.getAppSecret() +
                "&js_code=" + code +
                "&grant_type=" + configuration.getGrant_type(), String.class);

        //解析获取用户的OpenId
        JSONObject jsonObject = JSONObject.parseObject(response);

        String openid = (String) jsonObject.get("openid");

        //如果没有获取到openid，则登录失败
        if (null == openid) {
            throw new UsernameNotFoundException("登录失败");
        }

        //如果不存在此用户，则新增到数据库
        User user = null;
//        if (!userService.isExsist(openid)) {
//            user = new User();
//            user.setImgPath(configuration.getDefaultImagePath());
//            user.setHereisId(openid);
//            user.setRole(UserService.UNAUTHORIZED);
//            userService.addUser(user);
//        } else {//如果存在，则读取用户信息
//            user = userService.getUserHereisId(openid);
//        }

        SecurityUser securityUser = new SecurityUser(user);
        securityUser.setLoginCode(code);

        System.out.println(openid);

        return securityUser;
    }
}
