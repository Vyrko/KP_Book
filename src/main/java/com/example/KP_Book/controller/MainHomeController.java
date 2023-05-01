package com.example.KP_Book.controller;

import com.example.KP_Book.entity.Book;
import com.example.KP_Book.entity.Genre;
import com.example.KP_Book.entity.Image;
import com.example.KP_Book.entity.User;
import com.example.KP_Book.services.BookService;
import com.example.KP_Book.services.GenreService;
import com.example.KP_Book.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("")
public class MainHomeController {
    private final BookService bookService;
    private final ImageService imageService;
    private final GenreService genreService;
    @GetMapping("/")
    public String open(@AuthenticationPrincipal User user , Model model)
    {
        Iterable<Book> books = bookService.readBook();
        Iterable<Image> images = imageService.readImg();
        Iterable<Genre> genres =genreService.readAllGenre();
        model.addAttribute("genres", genres);
        model.addAttribute("user", user);
        model.addAttribute("book",books);
        model.addAttribute("image",images);
        return "mainHome";
    }
    @GetMapping("/search")
    public String searchBookByName(@RequestParam(name = "nameBook", required = false) String nameBook, Model model){
        Iterable<Book> books = bookService.listBooks(nameBook);
        Iterable<Image> images = imageService.readImg();
        Iterable<Genre> genres =genreService.readAllGenre();
        model.addAttribute("genres", genres);
        model.addAttribute("book",books);
        model.addAttribute("image",images);
        return "mainHome";
    }
    @GetMapping("/filter")
    public String getBooksByGenres(@RequestParam("flexRadioDefault") Long idGenre,
                                   Model model){
        Iterable<Book> books=bookService.AllBookByGenre(idGenre);
        Iterable<Image> images = imageService.readImg();
        Iterable<Genre> genres =genreService.readAllGenre();
        model.addAttribute("genres", genres);
        model.addAttribute("book", books);
        model.addAttribute("image",images);
        return "mainHome";
    }
    /*@PreAuthorize("hasAuthority('ROLE_USER')")*/
    @GetMapping("bookInfo/{id}")
    public String getBooksInfo(@PathVariable Long id, Model model){
        Book book=bookService.getBookById(id);
        Iterable<Image> images = imageService.readImg();
        model.addAttribute("book", book);
        model.addAttribute("image",images);
        return "Book-info";
    }
    @GetMapping("/map")
    public String getMap(){
        return "map";
    }
}
