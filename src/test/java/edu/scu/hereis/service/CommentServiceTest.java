package edu.scu.hereis.service;

import edu.scu.hereis.entity.Comment;
import edu.scu.hereis.exception.CommentException;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static edu.scu.hereis.exception.CommentException.*;

/**
 * Created by Vicent_Chen on 2018/5/6.
 * 测试方法：
 * 1. 首先获取当前数据库中函数CURRENT_ROW_NUM
 * 2. 然后获取当前数据库中自增索引CURRENT_MAX_ID
 * 3. 运行测试
 * 4. 测试以后使用 alter table school_building AUTO_INCREMENT=CURRENT_MAX_ID + 1 重置数据库自增索引
 * 2. 运行测试
 * 所有测试均已通过 2018/5/6
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CommentServiceTest {

    // 当前comment表中最大的ID
    public final static int CURRENT_MAX_ID = 3;
    // 当前comment表中存在的行数
    public final static int CURRENT_ROW_NUM = 2;

    Comment nullComment;
    Comment nullCommentComment;
    Comment nullSpotIdComment;
    Comment nullUserIdComment;
    Comment nullTimeComment;

    Comment nullIdComment;
    Comment extraIdComment;

    @Autowired
    CommentService commentService;

    @Before
    public void init() {
        nullComment = null;

        nullCommentComment = new Comment();
        nullCommentComment.setComment(null);
        nullCommentComment.setSpotId(2); nullCommentComment.setUserId("haha");
        nullCommentComment.setTime(new Date());

        nullSpotIdComment = new Comment();
        nullSpotIdComment.setComment("nullSpotComment");
        nullSpotIdComment.setSpotId(null); nullSpotIdComment.setUserId("haha");
        nullSpotIdComment.setTime(new Date());

        nullUserIdComment = new Comment();
        nullUserIdComment.setComment("nullUserIdComment");
        nullUserIdComment.setSpotId(2); nullUserIdComment.setUserId(null);
        nullUserIdComment.setTime(new Date());

        nullTimeComment = new Comment();
        nullTimeComment.setComment("nullTimeComment");
        nullTimeComment.setSpotId(2); nullTimeComment.setUserId("haha");
        nullTimeComment.setTime(null);

        nullIdComment = new Comment(); nullIdComment.setId(null);
        nullIdComment.setComment("nullIdComment");
        nullIdComment.setSpotId(2); nullIdComment.setUserId("haha");
        nullIdComment.setTime(new Date());

        extraIdComment = new Comment(); extraIdComment.setId(2234);
        extraIdComment.setComment("extraIdComment");
        extraIdComment.setSpotId(2); extraIdComment.setUserId("haha");
        extraIdComment.setTime(new Date());
    }

    @Test
    public void test_001getCommentBySpot() {
        List<Comment> list;
        list = commentService.getCommentBySpot(-1);
        assertEquals(0, list.size());

        list = commentService.getCommentBySpot(2);
        assertEquals(0, list.size());
    }

    @Test
    public void test_002insertComment() {
        try {
            commentService.insertComment(nullComment);
        }
        catch (CommentException e) {
            assertEquals(EMPTY_COMMENT_CODE, e.getCode());
        }
        try {
            commentService.insertComment(nullCommentComment);
        }
        catch (CommentException e) {
            assertEquals(EMPTY_CONTENT_CODE, e.getCode());
        }
        try {
            commentService.insertComment(nullSpotIdComment);
        }
        catch (CommentException e) {
            assertEquals(EMPTY_SPOT_ID_CODE, e.getCode());
        }
        try {
            commentService.insertComment(nullUserIdComment);
        }
        catch (CommentException e) {
            assertEquals(EMPTY_USER_ID_CODE, e.getCode());
        }
        try {
            commentService.insertComment(nullTimeComment);
        }
        catch (CommentException e) {
            assertEquals(EMPTY_TIME_CODE, e.getCode());
        }
        commentService.insertComment(nullIdComment);
        commentService.insertComment(extraIdComment);
        assertEquals(2, commentService.getCommentBySpot(nullIdComment.getSpotId()).size());
    }

    @Test
    public void test_003deleteCommentById() {
        commentService.deleteCommentById(CURRENT_MAX_ID + 1);
        assertEquals(1, commentService.getCommentBySpot(nullIdComment.getSpotId()).size());
        commentService.deleteCommentById(CURRENT_MAX_ID);
        assertEquals(0, commentService.getCommentBySpot(nullIdComment.getSpotId()).size());
    }
}