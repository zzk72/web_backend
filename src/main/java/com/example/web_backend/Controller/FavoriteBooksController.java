package com.example.web_backend.Controller;

import com.example.web_backend.config.StateConstant;
import com.example.web_backend.entity.*;
import com.example.web_backend.mapper.BookMapper;
import com.example.web_backend.mapper.FavoriteBooksMapper;
import com.example.web_backend.mapper.UserMapper;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FavoriteBooksController {
    @Autowired
    private FavoriteBooksMapper favoriteBooksMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BookMapper bookMapper;
    @GetMapping("home/getFavoriteBooksByUid")//获取用户收藏的书籍，包含图片
    public MessageEntity<JSONObject> getFavoriteBooksByUid(int uid) throws IOException {
        List<Integer> favoriteBooksList = favoriteBooksMapper.selectByUid(uid);
        List<Book> bookList =new ArrayList<>();
        for (Integer integer : favoriteBooksList) {
            Book book = bookMapper.selectById(integer);
            bookList.add(book);
            ImageObject imageObject = new ImageObject(book.getImagePath());
            book.setImageResource(imageObject.getImageResource());
            book.setImageType(imageObject.getImageType());
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("favoriteBooksList",bookList);
        jsonObject.put("uid",uid);
        return MessageEntity.success(jsonObject);
    }
    @GetMapping("home/getFavoriteBooksTxtByUid")//获取用户收藏的书籍，不包含图片
    public MessageEntity<JSONObject> getFavoriteBooksTxtByUid(int uid){
        List<Integer> favoriteBooksList = favoriteBooksMapper.selectByUid(uid);
        List<Book> bookList =new ArrayList<>();
        for (Integer integer : favoriteBooksList) {
            Book book = bookMapper.selectById(integer);
            bookList.add(book);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("favoriteBooksInfoList",bookList);
        jsonObject.put("uid",uid);
        return MessageEntity.success(jsonObject);
    }
    @GetMapping("home/getUserByFavoriteBookId")//获取收藏该书籍的用户
    public MessageEntity<JSONObject> getUserByFavoriteBookId(int bookId){
        List<Integer> userList = favoriteBooksMapper.selectByBookId(bookId);
        List<User> users = new ArrayList<>();
        for (Integer integer : userList) {
            User user = userMapper.selectById(integer);
            users.add(user);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userList",users);
        jsonObject.put("bookId",bookId);
        return MessageEntity.success(jsonObject);
    }
    @PostMapping("home/addFavoriteBook")//添加收藏书籍
    public MessageEntity<String> addFavoriteBook(int uid,int bookId){
        FavoriteBooks favoriteBooks=new FavoriteBooks();
        favoriteBooks.setUid(uid);
        favoriteBooks.setBookId(bookId);
        favoriteBooksMapper.insert(favoriteBooks);
        return MessageEntity.success(StateConstant.HTTP_OK_MSG);
    }
    @PostMapping("home/addFavoriteBookList")//添加收藏书籍列表
    public MessageEntity<String> addFavoriteBookList(int uid,List<Integer> bookList){
        for(int bookId:bookList){
            FavoriteBooks favoriteBooks=new FavoriteBooks();
            favoriteBooks.setUid(uid);
            favoriteBooks.setBookId(bookId);
            favoriteBooksMapper.insert(favoriteBooks);
        }
        return MessageEntity.success(StateConstant.HTTP_OK_MSG);
    }
    @PostMapping("home/deleteFavoriteBook")
    public MessageEntity<String> deleteFavoriteBook(int uid,int bookId){//删除收藏书籍
        favoriteBooksMapper.deleteByUidAndBookId(uid,bookId);
        return MessageEntity.success(StateConstant.HTTP_OK_MSG);
    }

}
