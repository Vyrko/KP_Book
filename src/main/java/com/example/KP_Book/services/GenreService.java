package com.example.KP_Book.services;

import com.example.KP_Book.entity.Genre;
import com.example.KP_Book.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GenreService {
    private final GenreRepository genreRepository;


   /* public Iterable<Genre> readGenreByBookId(Long id) {
        return genreRepository(id);
    }*/
    public Iterable<Genre> readAllGenre(){
        return genreRepository.findAll();
    }
    public List<Genre> readAllGenresById(List<Long> id) {
        return genreRepository.findAllById(id);
    }
//    public Genre readGenreById(Long id) {
//        return genreRepository.findById(id);
//    }
}
