package com.example.book.controller;

import com.example.book.dto.BookDTO;
import com.example.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final BookService bookService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/save")
    public String save() {
        return "save";
    }

    @PostMapping("/save")
    public String saveParam(@ModelAttribute BookDTO bookDTO) {
        System.out.println("bookDTO = " + bookDTO);
        bookService.save(bookDTO);
        return "index";
    }

    @GetMapping("/book/{id}")
    public String findById(@PathVariable Long id, Model model) {
        BookDTO bookDTO = bookService.findById(id);
        model.addAttribute("book", bookDTO);
        return "detail";
    }

    //    @GetMapping("/books")
//    public String books(Model model) {
//
//        List<BookEntity> bookDTOList = bookService.findAll();
//        System.out.println("bookDTOList = " + bookDTOList);
//        for (BookEntity book : bookDTOList) {
//            System.out.println(book.getBookName());
//            System.out.println(book.getBookPrice());
//        }
//        model.addAttribute("list", bookDTOList);
//        return "books";
//    }
    @GetMapping("/books")
    public String books(Model model) {
        List<BookDTO> bookDTOList = bookService.findAll();
        System.out.println("bookDTOList = " + bookDTOList);
        model.addAttribute("list", bookDTOList);
        return "list";
    }

    @GetMapping("/book/delete/{id}")
    public String delete(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/books";
    }

    @GetMapping("/book/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        BookDTO bookDTO = bookService.findById(id);
        model.addAttribute("book", bookDTO);
        return "update";
    }

    @PostMapping("/book/update")
    public String update(@ModelAttribute BookDTO bookDTO) {
        bookService.update(bookDTO);
        return "redirect:/books";
    }
}
