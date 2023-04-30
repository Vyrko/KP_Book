package com.example.KP_Book.controller;

import com.example.KP_Book.entity.Book;
import com.example.KP_Book.entity.Genre;
import com.example.KP_Book.services.BookService;
import com.example.KP_Book.services.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/adminHome")
public class AdminHomeController {
    private final GenreService genreService;
    private final BookService bookService;

    @GetMapping
    public String adminHome(Model model) {
        Iterable<Book> books=bookService.AllBook();
        Iterable<Genre> genres =genreService.readAllGenre();
                model.addAttribute("genres", genres);
                model.addAttribute("books", books);
        return "adminHome";
    }

    @PostMapping("/book/create")
    public String createBook(@RequestParam("file1") MultipartFile file1,
                             @RequestParam("checkboxGenre") List<Long> idGenre,
                             Book book) throws IOException {
        bookService.saveBook(book, file1,genreService.readAllGenresById(idGenre));
        return "redirect:/adminHome";
    }

    @GetMapping("/book/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/adminHome";
    }
    @GetMapping("/book/update/{id}")
    public String BookToUpdate(@PathVariable Long id,Model model) {
        model.addAttribute("book", bookService.getBookById(id));
        model.addAttribute("image",bookService.getBookById(id).getImages());
        return "updateBook";
    }
    @PostMapping("/book/update/{id}")
    public String updateBook(@RequestParam("file1") MultipartFile file1,
                             Book book) throws IOException {
        bookService.updateBook(book, file1);
        return "redirect:/adminHome";
    }
}
