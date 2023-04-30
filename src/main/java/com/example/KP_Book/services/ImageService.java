package com.example.KP_Book.services;
import com.example.KP_Book.entity.Image;
import com.example.KP_Book.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    public  Iterable<Image>  readImg()
    {
        Iterable<Image> images = imageRepository.findAll();
        return  images;
    }

    public void deleteImageByBookId(Long id) {
        imageRepository.deleteById(id);
    }
}