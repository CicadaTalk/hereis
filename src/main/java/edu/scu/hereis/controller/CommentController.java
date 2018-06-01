package edu.scu.hereis.controller;

import com.alibaba.fastjson.JSONObject;
import edu.scu.hereis.configuration.Configuration;
import edu.scu.hereis.entity.Comment;
import edu.scu.hereis.entity.User;
import edu.scu.hereis.security.SecurityUser;
import edu.scu.hereis.service.ActivityService;
import edu.scu.hereis.service.CommentService;
import edu.scu.hereis.service.UserService;
import edu.scu.hereis.wxresult.CommentResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Controller
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    private Configuration configuration;//微信相关配置

    @Autowired
    private UserService userService;//用户服务类

    @ResponseBody
    @GetMapping("/getCommentsById")
    public List<CommentResult> getCommentById(int spotId) {

        List<Comment> commentList = commentService.getCommentBySpot(spotId);
        List<CommentResult> commentResults = CommentResult.toList(commentList);

        return  commentResults;
    }

    @ResponseBody
    @PostMapping("/addComment")
    public String addComment(String code,String content,Integer spotId){

        //判断登录用户的信息
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
            return "登录失败";
        }

        //如果不存在此用户，则新增到数据库
        User user = null;
        if (!userService.isExsist(openid)) {
            user = new User();
            user.setImgPath(configuration.getDefaultImagePath());
            user.setHereisId(openid);
            user.setRole(UserService.UNAUTHORIZED);
            userService.addUser(user);
        } else {//如果存在，则读取用户信息
            user = userService.getUserHereisId(openid);
        }

        //添加评论
        Comment comment = new Comment();
        comment.setComment(content);
        comment.setUserId(user.getHereisId());
        comment.setTime(new Date());
        comment.setSpotId(spotId);
        commentService.insertComment(comment);

        return "success";
    }
}
