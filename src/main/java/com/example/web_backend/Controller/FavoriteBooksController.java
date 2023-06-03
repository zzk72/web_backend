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
    //资源路径
    private final String SourcePath = this.getClass().getClassLoader().getResource("static/").getPath();
    private final String bookImagePath = SourcePath+"book_pic/";
    @GetMapping("home/getFavoriteBooksByUid")//获取用户收藏的书籍，包含图片
    public MessageEntity<JSONObject> getFavoriteBooksByUid(int uid) throws IOException {
        List<Integer> favoriteBooksList = favoriteBooksMapper.selectByUid(uid);
        List<Book> bookList =new ArrayList<>();
        for (Integer integer : favoriteBooksList) {
            Book book = bookMapper.selectById(integer);
            bookList.add(book);
            book.setImagePath(bookImagePath+book.getImagePath());
            ImageObjectService imageObjectService = new ImageObjectService(book.getImagePath());
            //book.setImageResource(imageObject.getImageResource());
            book.setImageType(imageObjectService.getImageType());
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
    @PostMapping("home/addFavoriteBook")//been tested
    public MessageEntity<String> addFavoriteBook(int uid,int bookId){//添加收藏书籍

        User user = userMapper.selectById(uid);
        if(user==null)
            return MessageEntity.error(StateConstant.USER_NOT_FOUND_CODE,StateConstant.USER_NOT_FOUND_MSG);
        Book book = bookMapper.selectById(bookId);
        if(book==null)
            return MessageEntity.error(StateConstant.BOOK_NOT_FOUND_CODE,StateConstant.BOOK_NOT_FOUND_MSG);
        FavoriteBooks favoriteBooks=favoriteBooksMapper.selectByUidAndBookId(uid,bookId);
        if(favoriteBooks!=null)
            return MessageEntity.error(StateConstant.FAVORITE_BOOK_ALREADY_EXIST_CODE,StateConstant.FAVORITE_BOOK_ALREADY_EXIST_MSG);
        favoriteBooks=new FavoriteBooks();
        favoriteBooks.setUid(uid);
        favoriteBooks.setBookId(bookId);
        favoriteBooksMapper.insert(favoriteBooks);
        return MessageEntity.success(StateConstant.SUCCESS_MSG);
    }
    @PostMapping("home/addFavoriteBookList")//添加收藏书籍列表
    public MessageEntity<String> addFavoriteBookList(int uid,List<Integer> bookList){
        User user = userMapper.selectById(uid);
        if(user==null)
            return MessageEntity.error(StateConstant.USER_NOT_FOUND_CODE,StateConstant.USER_NOT_FOUND_MSG);
        for(int bookId:bookList){
            Book book = bookMapper.selectById(bookId);
            if(book==null)
                return MessageEntity.error(StateConstant.BOOK_NOT_FOUND_CODE,StateConstant.BOOK_NOT_FOUND_MSG);
            FavoriteBooks favoriteBooks=favoriteBooksMapper.selectByUidAndBookId(uid,bookId);
            if(favoriteBooks!=null)
                continue;
            favoriteBooks=new FavoriteBooks();
            favoriteBooks.setUid(uid);
            favoriteBooks.setBookId(bookId);
            favoriteBooksMapper.insert(favoriteBooks);
            return MessageEntity.success(StateConstant.SUCCESS_MSG);
        }
        return MessageEntity.success(StateConstant.SUCCESS_MSG);
    }
    @PostMapping("home/deleteFavoriteBook")
    public MessageEntity<String> deleteFavoriteBook(int uid,int bookId){//删除收藏书籍
        favoriteBooksMapper.deleteByUidAndBookId(uid,bookId);
        return MessageEntity.success(StateConstant.SUCCESS_MSG);
    }

}
//    @PostMapping("/read/addFavoriteBooks")
//    public String addFavoriteBooks(@RequestParam String username,@RequestParam int bookId){
//        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
//        if(user!=null){
//            String favor= user.getFavoriteBooks();
//            favor+=(bookId+"/");
//            user.setFavoriteBooks(favor);
//            userMapper.updateById(user);
//            return "添加成功";
//        }
//        return "用户不存在";
//    }
//
//    @GetMapping("/read/getFavoriteBooks")
//    public List<Book> getFavoriteBooks(@RequestParam String username){
//        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
//        String[] bookIds = user.getFavoriteBooks().split("/");
//        return bookMapper.selectList(new QueryWrapper<Book>().in("id", (Object) bookIds));
//    }
//
//    @PostMapping("/read/deleteFavoriteBooks")
//    public String deleteFavoriteBooks(@RequestParam String username, @RequestParam int _bookId){
//        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
//        String[] bookIds = user.getFavoriteBooks().split("/");
//        List<String> remainingIds = new ArrayList<>();
//        String bookId = String.valueOf(_bookId);
//        for (String id : bookIds) {
//            if (!Objects.equals(id, bookId)) {
//                remainingIds.add(id);
//            }
//        }
//        String updatedFavoriteBooks = String.join("/", remainingIds);
//        user.setFavoriteBooks(updatedFavoriteBooks);
//        userMapper.updateById(user);
//        return "删除成功";
//    }