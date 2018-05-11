package edu.scu.hereis.wxresult;

import edu.scu.hereis.entity.Comment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentResult {

    //评论ID
    private int commentId;
    //评论内容
    private String comment;
    //被评论的景点ID
    private int spotId;
    //进行评论的用户ID
    private String userId;
    //评论时间
    private Date time;

    /**
     * 根据传入的Comment列表生成CommentResult列表
     * @param commentList 评论列表
     * @return CommentResult列表
     */
    public static List<CommentResult> toList(List<Comment> commentList) {
        List<CommentResult> commentResultList = new ArrayList<>();
        for (Comment comment : commentList) {
            commentResultList.add(new CommentResult(comment));
        }
        return commentResultList;
    }

    public CommentResult(Comment comment) {
        this.commentId = comment.getId();
        this.comment = comment.getComment();
        this.spotId = comment.getSpotId();
        this.userId = comment.getUserId();
        this.time = comment.getTime();
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getSpotId() {
        return spotId;
    }

    public void setSpotId(int spotId) {
        this.spotId = spotId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
