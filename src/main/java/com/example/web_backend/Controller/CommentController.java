package com.example.web_backend.Controller;

import com.example.web_backend.config.StateConstant;
import com.example.web_backend.entity.MessageEntity;
import com.example.web_backend.entity.Book;
import com.example.web_backend.entity.Comment;
import com.example.web_backend.entity.User;
import com.example.web_backend.mapper.BookMapper;
import com.example.web_backend.mapper.CommentMapper;
import com.example.web_backend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/comment/getByBookid")
    public MessageEntity<List<Comment> > getAllCommentsOfABook(@RequestParam int bookId){
        return MessageEntity.success(commentMapper.selectByBook(bookId));
    }

    @PostMapping("/comment/add")
    public MessageEntity<String> addComment(@RequestParam int bookId, @RequestParam String username, @RequestParam String commentMes){
        User user = userMapper.selectByUsername(username);
        Book book = bookMapper.selectById(bookId);
        Comment comment = new Comment();
        comment.setUid(user.getId());
        comment.setBookId(book.getId());
        comment.setComment(commentMes);
        commentMapper.insert(comment);
        return MessageEntity.success(StateConstant.HTTP_OK_MSG);
    }

    @GetMapping("/Comment/personalComment")
    public MessageEntity<List<Comment >> getAllCommentsOfOneUser(@RequestParam String username){
        User user = userMapper.selectByUsername(username);
        if (user == null) // 用户不存在
            return MessageEntity.error(StateConstant.USER_NOT_FOUND_CODE,StateConstant.USER_NOT_FOUND_MSG);
        return MessageEntity.success(commentMapper.selectByUid(user.getId()));
    }

}
