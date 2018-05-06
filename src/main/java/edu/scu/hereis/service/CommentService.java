package edu.scu.hereis.service;

import edu.scu.hereis.dao.CommentMapper;
import edu.scu.hereis.entity.Comment;
import edu.scu.hereis.entity.CommentExample;
import edu.scu.hereis.exception.CommentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static edu.scu.hereis.exception.CommentException.*;

/**
 * Created by Vicent_Chen on 2018/5/6.
 */
@Service
public class CommentService {
    @Autowired
    CommentMapper commentMapper;

    /**
     * 添加评论
     * @param comment 插入评论时id会自动被赋值为null
     */
    @Transactional
    public void insertComment(Comment comment) {
        if (comment == null) {
            throw new CommentException(EMPTY_COMMENT_CODE, EMPTY_COMMENT);
        }
        try {
            comment.setId(null);
            commentMapper.insertSelective(comment);
        }
        catch (DataIntegrityViolationException e) {
            if (comment.getComment() == null) {
                throw new CommentException(EMPTY_CONTENT_CODE, EMPTY_CONTENT);
            }
            else if (comment.getSpotId() == null) {
                throw new CommentException(EMPTY_SPOT_ID_CODE, EMPTY_SPOT_ID);
            }
            else if (comment.getUserId() == null) {
                throw new CommentException(EMPTY_USER_ID_CODE, EMPTY_USER_ID);
            }
            else if (comment.getTime() == null) {
                throw new CommentException(EMPTY_TIME_CODE, EMPTY_TIME);
            }
            else {
                throw new CommentException(UNKNOWN_ERROR_CODE, e.getMessage());
            }
        }
    }

    /**
     * 删除评论
     * @param id 忽略不存在的评论
     */
    @Transactional
    public void deleteCommentById(Integer id) {
        try {
            commentMapper.deleteByPrimaryKey(id);
        }
        catch (RuntimeException e) {
            throw new CommentException(UNKNOWN_ERROR_CODE, e.getMessage());
        }
    }

    /**
     * 根据景点ID获取评论
     * @param spotId
     * @return 可能返回空列表
     */
    public List<Comment> getCommentBySpot(Integer spotId) {
        if (spotId == null) {
            throw new CommentException(EMPTY_SPOT_ID_CODE, EMPTY_SPOT_ID);
        }
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andSpotIdEqualTo(spotId);
        List<Comment> result = commentMapper.selectByExample(commentExample);
        return result;
    }
}
