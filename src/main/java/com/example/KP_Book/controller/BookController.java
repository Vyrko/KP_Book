package com.example.KP_Book.controller;

import com.example.KP_Book.entity.Book;
import com.example.KP_Book.entity.Image;
import com.example.KP_Book.services.BookService;
import com.example.KP_Book.services.GenreService;
import com.example.KP_Book.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/home")
public class BookController {
    private final BookService bookService;
    private final ImageService imageService;
    private final GenreService genreService;
    @GetMapping()
    public String home(@RequestParam(name = "nameBook", required = false) String nameBook, Model model){
        model.addAttribute("books", bookService.listBooks(nameBook));
        return "home";
    }
    /*@PostMapping("/book/create")
    public String createBook(@RequestParam("file1") MultipartFile file1,
                             Book book) throws IOException {
        bookService.saveBook(book,file1);
        return "redirect:/adminHome";
    }*/
    @GetMapping("/book/{id}")
    public String bookInfo(@PathVariable Long id, Model model){
        Book book=bookService.getBookById(id);
        /*Iterable<Genre> genres = genreService.readGenreByBookId(id);*/
        model.addAttribute("book",book);
        model.addAttribute("image",book.getImages());
        /*model.addAttribute("genre", genres);*/
        return "Book-info";
    }
    @GetMapping("/book")
    public String bookInfo(Model model)
    {
      Iterable<Book> books = bookService.readBook();
      Iterable<Image> images = imageService.readImg();
        model.addAttribute("book",books);
        model.addAttribute("image",images);
        return "Book-info";
    }
    @GetMapping("/book/delete/{id}")
    public String deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return "redirect:/home";
    }
}
