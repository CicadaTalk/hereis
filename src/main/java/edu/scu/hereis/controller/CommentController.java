package edu.scu.hereis.controller;

import edu.scu.hereis.entity.Comment;
import edu.scu.hereis.service.ActivityService;
import edu.scu.hereis.service.CommentService;
import edu.scu.hereis.wxresult.CommentResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Controller
public class CommentController {

    @Autowired
    CommentService commentService;

    @ResponseBody
    @GetMapping("/getCommentsById")
    public List<CommentResult> getCommentById(int spotId) {

        List<Comment> commentList = commentService.getCommentBySpot(spotId);
        List<CommentResult> commentResults = CommentResult.toList(commentList);

        return  commentResults;
    }
}
