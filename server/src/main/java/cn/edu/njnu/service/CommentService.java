package cn.edu.njnu.service;

import cn.edu.njnu.mapper.CommentMapper;
import cn.edu.njnu.mapper.UserMapper;
import cn.edu.njnu.pojo.Comment;
import cn.edu.njnu.pojo.Result;
import cn.edu.njnu.pojo.ResultFactory;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserMapper userMapper;

    public Result comment(Map<String, Object> resourceIDMap){
        int resourceID = Integer.parseInt((String) resourceIDMap.get("resourceID"));
        ArrayList<Comment> commentArrayList = commentMapper.queryComment(resourceID);
        return ResultFactory.buildSuccessResult("获取评论成功", commentArrayList);
    }

    public Result addComment(Comment comment){
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        int userID = userMapper.queryUserByName(username).getUserId();
        comment.setUserID(userID);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String addDate = formatter.format(date);
        comment.setDate(addDate);
        commentMapper.addComment(comment);
        return ResultFactory.buildSuccessResult("评论添加成功", null);
    }
}
