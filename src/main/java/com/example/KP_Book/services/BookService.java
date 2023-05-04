package com.example.KP_Book.services;

import com.example.KP_Book.entity.Book;
import com.example.KP_Book.entity.Genre;
import com.example.KP_Book.entity.Image;
import com.example.KP_Book.repository.BookRepository;
import com.example.KP_Book.repository.GenreRepository;
import com.example.KP_Book.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {
    private final ImageRepository imageRepository;
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;

    public List<Book> AllBook() {
        return bookRepository.findAll();
    }

    public List<Book> listBooks(String name) {
        if (name != null) return bookRepository.findByName(name);
        return bookRepository.findAll();
    }

    public void deleteBook(Long id) {
        bookRepository.findById(id).orElse(null).removeBook();
        bookRepository.deleteById(id);
    }
    public void updateBook(Book newBook, MultipartFile file1) throws IOException {
        Book bookFromDb = bookRepository.findById(newBook.getId()).orElse(null);
        Image image;
        if (file1.getSize() != 0) {
            imageRepository.deleteById(bookFromDb.getImages().get(0).getId());
            bookFromDb.removeImage();
            image = toImageEntity(file1);
            image.setPreviewImage(true);
            bookFromDb.addImageToBook(image);
        }
        if (newBook.getName()!=null){bookFromDb.setName(newBook.getName());}
        if (newBook.getAuthor()!=null){bookFromDb.setAuthor(newBook.getAuthor());}
        if (newBook.getCost()!=0){bookFromDb.setCost(newBook.getCost());}
        bookFromDb= bookRepository.save(bookFromDb);
        bookFromDb.setPreviewImageId(bookFromDb.getImages().get(0).getId());
        bookRepository.save(bookFromDb);
    }


    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }
    public boolean bookExist(String name){ return bookRepository.findByName(name) != null;}

    public Iterable<Book> readBook() {
        Iterable<Book> books = bookRepository.findAll();
        return books;
    }

    public void saveBook(Book book, MultipartFile file, List<Genre> genres) throws IOException {
        Image image;
        if (file.getSize() != 0) {
            image = toImageEntity(file);
            image.setPreviewImage(true);
            book.addImageToBook(image);
        }
        log.info("Saving new Book. Name {}", book.getName());
        book.setGenres(genres);
        Book bookFromDb = bookRepository.save(book);
        bookFromDb.setPreviewImageId(bookFromDb.getImages().get(0).getId());
        bookRepository.save(bookFromDb);
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        Base64.Encoder encoder = Base64.getEncoder();
        String encoded = encoder.encodeToString(file.getBytes());
        image.setBytes(encoded);
        return image;
    }

    public Iterable<Book> AllBookByGenre(Long idGenre) {
        return bookRepository.findByGenres(idGenre);
    }


}
