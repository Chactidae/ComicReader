package com.example.ComicReader;

import com.example.ComicReader.model.Book;
import com.example.ComicReader.model.Image;
import com.example.ComicReader.repositories.BookRepository;
import com.example.ComicReader.services.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class ComicReaderApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BookService bookService;

	@Autowired
	private BookRepository bookRepository;

	@Test
	void testBookSavedToDatabase() throws Exception {
		long countBefore = bookRepository.count();

		MockMultipartFile file1 = new MockMultipartFile("file1", "test1.jpg", "image/jpeg", Files.readAllBytes(Paths.get("C:\\ComicData\\testData\\test1.jpg")));
		MockMultipartFile file2 = new MockMultipartFile("file2", "test2.jpg", "image/jpeg", Files.readAllBytes(Paths.get("C:\\ComicData\\testData\\test2.jpg")));
		MockMultipartFile file3 = new MockMultipartFile("file3", "test3.jpg", "image/jpeg", Files.readAllBytes(Paths.get("C:\\ComicData\\testData\\test3.jpg")));

		mockMvc.perform(multipart("/book/create")
						.file(file1)
						.file(file2)
						.file(file3)
						.param("title", "Test8")
						.param("author", "1")
						.param("book_info_id", "1"))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/"));

		long countAfter = bookRepository.count();

		// ✅ Проверяем, что в базе стало на 1 запись больше
		assertEquals(countBefore + 1, countAfter);
	}

	@Test
	void comicControllerDeleteBookShouldCallServiceAndRedirect() throws Exception {
		Long bookId = 81L;

		// Тестирование удаления книги
		mockMvc.perform(post("/book/{id}", bookId))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/"));

		verify(bookService).deleteBook(bookId); // Теперь это будет работать
	}

	@Test
	void bookModel_ShouldCorrectlyStoreAndRetrieveData() {
		// Arrange
		Long expectedId = 71L;
		String expectedTitle = "Test Comic";
		String expectedCover = "cover.jpg";
		int expectedAuthor = 1;
		int expectedBookInfoId = 1;
		Long expectedPreviewImageId = 100L;
		LocalDateTime expectedDate = LocalDateTime.now();

		// Act
		Book book = new Book();
		book.setId(expectedId);
		book.setTitle(expectedTitle);
		book.setCover(expectedCover);
		book.setAuthor(expectedAuthor);
		book.setBook_info_id(expectedBookInfoId);
		book.setPreviewImageId(expectedPreviewImageId);
		book.setDateOfCreated(expectedDate);

		// Assert
		assertThat(book.getId()).isEqualTo(expectedId);
		assertThat(book.getTitle()).isEqualTo(expectedTitle);
		assertThat(book.getCover()).isEqualTo(expectedCover);
		assertThat(book.getAuthor()).isEqualTo(expectedAuthor);
		assertThat(book.getBook_info_id()).isEqualTo(expectedBookInfoId);
		assertThat(book.getPreviewImageId()).isEqualTo(expectedPreviewImageId);
		assertThat(book.getDateOfCreated()).isEqualTo(expectedDate);
	}

}
