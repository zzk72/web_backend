package com.example.web_backend.Controller;

import com.example.web_backend.config.StateConstant;
import com.example.web_backend.entity.MessageEntity;
import com.example.web_backend.entity.Book;
import com.example.web_backend.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
@RestController
public class BookController {
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BookOrderMapper bookOrderMapper;
    private final String SourcePath = this.getClass().getClassLoader().getResource("static/").getPath();
    private final String bookImagePath = SourcePath+"book_pic/";
    @GetMapping("/admin/getAllBook")
    public MessageEntity<List<Book>> getAllBook() {
        List<Book> books = bookMapper.selectAll();
        for (Book book : books) {
            String imagePath = book.getImagePath();
            try {
                File imageFile = new File(imagePath);
                byte[] imageData = Files.readAllBytes(imageFile.toPath());
                book.setImageResource(imageData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return MessageEntity.success(books);
    }

    @GetMapping("/admin/getBooksByName")
    public MessageEntity<List<Book>> getBooksByName(@RequestParam("bookName") String name) {
        //String SourcePath = this.getClass().getClassLoader().getResource("static/").getPath();
        System.out.println("Path:"+SourcePath);
        List<Book> books = bookMapper.selectByName(name);
        for (Book book : books) {
            String imagePath = bookImagePath+book.getImagePath();
            try {
                File imageFile = new File(imagePath);
                byte[] imageData = Files.readAllBytes(imageFile.toPath());
                book.setImageResource(imageData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return MessageEntity.success(books);
    }

    @PostMapping("admin/addNewBook")
    public MessageEntity<String> addNewBook(@RequestBody Book book, @RequestParam("file") MultipartFile file) {
        if (bookMapper.selectByName(book.getName()) != null)
            return MessageEntity.error(StateConstant.BOOK_ALREADY_EXIST_CODE, StateConstant.BOOK_ALREADY_EXIST_MSG);
        if (file.isEmpty()) return MessageEntity.error(StateConstant.PARAMS_NULL_CODE, StateConstant.PARAMS_NULL_MSG);

        String filePath = bookImagePath+file.getOriginalFilename();
        book.setImagePath(filePath);
        try {
            File destFile = new File(filePath);
            file.transferTo(destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        bookMapper.insert(book);
        return MessageEntity.success(StateConstant.HTTP_OK_MSG);
    }

    @PostMapping("/admin/addBook")
    public MessageEntity<String> addBook(@RequestParam int id, @RequestParam int nums) {
        Book book = bookMapper.selectById(id);
        if (book == null) return MessageEntity.error(StateConstant.BOOK_NOT_FOUND_CODE, StateConstant.BOOK_NOT_FOUND_MSG);
        bookMapper.updateStorage(book.getStorage() + nums, id);
        return MessageEntity.success(StateConstant.HTTP_OK_MSG);
    }

    @PostMapping("/admin/deleteBook")
    public MessageEntity<String> deleteBook(@RequestParam int id) {
        Book book = bookMapper.selectById(id);
        if (book == null) return MessageEntity.error(StateConstant.BOOK_NOT_FOUND_CODE, StateConstant.BOOK_NOT_FOUND_MSG);
        bookMapper.deleteById(id);
        return MessageEntity.success(StateConstant.HTTP_OK_MSG);
    }

}
