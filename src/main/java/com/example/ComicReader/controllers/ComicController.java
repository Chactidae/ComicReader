package com.example.ComicReader.controllers;

import com.example.ComicReader.model.*;
import com.example.ComicReader.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/*Контроллер для главной страницы*/
@Controller
@RequiredArgsConstructor
public class ComicController {

    private final BookService bookService;
    @GetMapping("/")
    public String comics(@RequestParam(name = "b_name", required = false) String b_name, Model model){
        model.addAttribute("book", bookService.list(b_name));
        return "comics";
    }

    @GetMapping("/book/{id}")
    public String productInfo(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("product", book);
        model.addAttribute("images", book.getImageList());
        return "comics_info";
    }

    //@GetMapping("/reset")
    //public String reset(Model model){
     //   model.addAttribute("book", bookService.list(null));
     //   return "comics";
    //}
    @PostMapping("/book/create")
    public String createBook(@RequestParam("file1") MultipartFile file1,
                             @RequestParam("file2") MultipartFile file2,
                             @RequestParam("file3") MultipartFile file3, Book book) throws IOException {
        bookService.saveBook(book, file1, file2, file3);
        return "redirect:/";
    }

    @PostMapping("/book/{id}")
    public String deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return "redirect:/";
    }

    @PostMapping("/book/{id}/addPage")
    public String addPageToBook(@PathVariable("id") Long id, @RequestParam("page") MultipartFile page) throws IOException {
        Book book = bookService.getBookById(id);
        Book updateBook = bookService.addPage(book, page);
        return "redirect:/book/" + updateBook.getId(); // Перенаправление на страницу книги
    }

}
