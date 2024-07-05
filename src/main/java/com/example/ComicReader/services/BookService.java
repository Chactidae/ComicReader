package com.example.ComicReader.services;

import com.example.ComicReader.model.Book;
import com.example.ComicReader.model.Image;
import com.example.ComicReader.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final String folderPath = "C:\\ComicData\\";
    public List<Book> list(String b_name)
    {
        if (b_name != null) return bookRepository.findByBname(b_name);
        return bookRepository.findAllBooks();

    }
    public Long getIdInfo(Long id){
        return bookRepository.findInfo(id);
    }

    public Long getIdAuthor(Long id){
        return bookRepository.findAuthor(id);
    }
    public Book getBookById(Long id){
        return bookRepository.findById(id).orElse(null);
    }

    public void saveBook(Book book, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        Image image1;
        Image image2;
        Image image3;

        if (file1.getSize() != 0){
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            book.addImageToBook(image1);
        }
        if (file2.getSize() != 0){
            image2 = toImageEntity(file2);
            book.addImageToBook(image2);
        }
        if (file3.getSize() != 0){
            image3 = toImageEntity(file3);
            book.addImageToBook(image3);
        }
        log.info("Saving new Book. Title: {};", book.getTitle());
        Book bookFromDb = bookRepository.save(book);
        bookFromDb.setPreviewImageId(bookFromDb.getImageList().get(0).getId());
        bookRepository.save(book);
    }
    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }
    public Book addPage(Book book, MultipartFile page) throws IOException {
        if (!page.isEmpty()){
            Image newPage = toImageEntity(page);
            book.addImageToBook(newPage);
            bookRepository.save(book);
            log.info("Saving new page to Title: {};", book.getTitle());
        }
        return book;
    }

    private Image toImageEntity(MultipartFile file1) throws IOException {
        Image image = new Image();
        image.setName(file1.getName());
        image.setFileName(file1.getOriginalFilename());
        image.setContentType(file1.getContentType());
        image.setSize(file1.getSize());
        image.setFilePath(folderPath + file1.getOriginalFilename());
        try(InputStream inputStream = file1.getInputStream();
        OutputStream outputStream = new FileOutputStream(folderPath + file1.getOriginalFilename())){
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1){
                outputStream.write(buffer, 0, bytesRead);
            }
            System.out.println("Успешно загружено");

        }
        return image;
    }

}
