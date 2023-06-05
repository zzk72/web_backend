package com.example.web_backend.Controller;

import com.example.web_backend.config.ImagePathConfig;
import com.example.web_backend.config.StateConstant;
import com.example.web_backend.entity.MessageEntity;
import com.example.web_backend.entity.Book;
import com.example.web_backend.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static java.lang.Math.min;

@RestController
public class BookController {
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BookOrderMapper bookOrderMapper;
    private final String SourcePath=new ImagePathConfig().SourcePath;
    private final String bookImagePath = new ImagePathConfig().bookImagePath;
    @GetMapping("/admin/getAllBook")
    public MessageEntity<List<Book>> getAllBook() throws IOException {
        List<Book> books = bookMapper.selectAll();
        for (Book book : books) {
            ImageObjectService imageObjectService = new ImageObjectService(bookImagePath + book.getImagePath());
//            book.setImageResource(imageObjectService.getImageResource());
            book.setImageType(imageObjectService.getImageType());
            book.setImagePath(bookImagePath+book.getImagePath());
        }
        return MessageEntity.success(books);
    }
    @GetMapping("book/getImageByBookId")//been tested
    public MessageEntity<ImageObjectService> getBookImage(@RequestParam int bookId) throws IOException {
        Book book = bookMapper.selectById(bookId);
        ImageObjectService imageObjectService = new ImageObjectService(bookImagePath+book.getImagePath());
        return MessageEntity.success(imageObjectService);
    }
    @GetMapping("admin/getBooksByName")//been tested
    public MessageEntity<List<Book>> getBooksByName(@RequestParam("bookName") String name) throws IOException {
        //String SourcePath = this.getClass().getClassLoader().getResource("static/").getPath();
        System.out.println("Path:"+SourcePath);
        List<Book> books = bookMapper.selectByName(name);
        for (Book book : books) {
            ImageObjectService imageObjectService = new ImageObjectService(bookImagePath + book.getImagePath());
            //book.setImageResource(imageObjectService.getImageResource());
            book.setImageType(imageObjectService.getImageType());
            book.setImagePath(bookImagePath+book.getImagePath());
        }
        return MessageEntity.success(books);
    }
    @GetMapping("/book/getBookLocation")
    public MessageEntity<String> findBook(@RequestParam int id){
        Book book = bookMapper.selectById(id);
        if(book==null)return MessageEntity.error(StateConstant.BOOK_NOT_FOUND_CODE,StateConstant.BOOK_NOT_FOUND_MSG);
        return MessageEntity.success(book.getLocation());
    }

//    public MessageEntity<String> addNewBook(@RequestBody Book book, @RequestParam("file") MultipartFile file) {
//        if (bookMapper.selectByName(book.getName()) != null)
//            return MessageEntity.error(StateConstant.BOOK_ALREADY_EXIST_CODE, StateConstant.BOOK_ALREADY_EXIST_MSG);
//        if (file.isEmpty()) return MessageEntity.error(StateConstant.PARAMS_NULL_CODE, StateConstant.PARAMS_NULL_MSG);
//
//        String filePath = bookImagePath+file.getOriginalFilename();
//        book.setImagePath(filePath);
//        try {
//            File destFile = new File(filePath);
//            file.transferTo(destFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        bookMapper.insert(book);
//        return MessageEntity.success(StateConstant.SUCCESS_MSG);
//    }
    @GetMapping("/book/getRecommend")//been tested
    public MessageEntity<List<Book>> recommendBook(@RequestParam("nums") int recommend_nums) throws IOException {
        int bookCount=bookMapper.getBookCount();
        List<Book> books=bookMapper.getRandomBooks(min(bookCount,recommend_nums));
        for (Book book : books) {
            //ImageObjectService imageObjectService = new ImageObjectService(bookImagePath + book.getImagePath());
            //book.setImageResource(imageObjectService.getImageResource());
            //book.setImageType(imageObjectService.getImageType());
            book.setImagePath(bookImagePath+book.getImagePath());
        }
        return MessageEntity.success(books);
    }
    @PostMapping("admin/addNewBook")//been tested
    public MessageEntity<String> addNewBook(@RequestBody Book book) throws IOException {//been tested
        if (!bookMapper.selectByName(book.getName()).isEmpty()){
            return MessageEntity.error(StateConstant.BOOK_ALREADY_EXIST_CODE,StateConstant.BOOK_ALREADY_EXIST_MSG);
        }
        String resultPath = bookImagePath+ book.getName() + ".jpg";
        ImageObjectService imageObjectService = new ImageObjectService();
        imageObjectService.copyImage(book.getImagePath(),resultPath);
        book.setImagePath(book.getName() + ".jpg");
        bookMapper.insert(book);
        return MessageEntity.success(StateConstant.SUCCESS_MSG);
    }
    @PostMapping("/admin/addBookNums")
    public MessageEntity<String> addBookNums(@RequestParam int id, @RequestParam int nums) {
        Book book = bookMapper.selectById(id);
        if (book == null) return MessageEntity.error(StateConstant.BOOK_NOT_FOUND_CODE, StateConstant.BOOK_NOT_FOUND_MSG);
        bookMapper.updateStorage(book.getStorage() + nums, id);
        return MessageEntity.success(StateConstant.SUCCESS_MSG);
    }

    @PostMapping("/admin/deleteBook")
    public MessageEntity<String> deleteBook(@RequestParam int id) {
        Book book = bookMapper.selectById(id);
        if (book == null) return MessageEntity.error(StateConstant.BOOK_NOT_FOUND_CODE, StateConstant.BOOK_NOT_FOUND_MSG);
        bookMapper.deleteById(id);
        return MessageEntity.success(StateConstant.SUCCESS_MSG);
    }

}
