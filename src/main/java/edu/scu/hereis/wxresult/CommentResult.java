package edu.scu.hereis.wxresult;

import edu.scu.hereis.entity.Comment;
import edu.scu.hereis.entity.User;
import java.text.SimpleDateFormat;

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
    private String time;

    //用户头像
    private String imagePath;
    //用户昵称
    private String name;

    public CommentResult(Comment comment, User user) {
        this.commentId = comment.getId();
        this.comment = comment.getComment();
        this.spotId = comment.getSpotId();
        this.userId = comment.getUserId();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        this.time = format.format(comment.getTime());

        //获取用户的头像和昵称信息
        if (null != user) {
            this.imagePath = user.getImgPath();
            this.name = user.getName();
        }
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


