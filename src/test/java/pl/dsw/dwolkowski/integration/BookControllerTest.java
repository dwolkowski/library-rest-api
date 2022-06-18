package pl.dsw.dwolkowski.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import pl.dsw.dwolkowski.api.metadata.model.Book;
import pl.dsw.dwolkowski.api.repository.BookRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
@Transactional
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ObjectMapper objectMapper;

    private Book testBook;

    @BeforeEach
     private void setup(){
        if( testBook == null || !bookRepository.existsById(testBook.getBook_id())){
            testBook = new Book();
            testBook.setTitle("Test Book");
            testBook.setPublisher("Test");
            testBook.setRelease_date(LocalDate.now());
            testBook.setLanguage("Test");
            testBook.setAvailable(true);
            testBook.setPrice(1.11);

            bookRepository.save(testBook);
        }
    }

    @Test
    void shouldGetBookById() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/library/v1/book/" + testBook.getBook_id()))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        Book bookResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Book.class);

        assertThat(bookResponse).isNotNull();
        assertThat(bookResponse.getBook_id()).isEqualTo(testBook.getBook_id());
    }

    @Test
    void shouldGetAllBooks() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/library/v1/book/all"))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        List<Book> bookResponse = Arrays.asList(
                objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Book[].class));

        assertThat(bookResponse).isNotNull();
        assertThat(bookResponse.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    void addBook() throws Exception {
        bookRepository.deleteById(testBook.getBook_id());

        MvcResult mvcResult = mockMvc.perform(post("/api/library/v1/book/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testBook)))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        Book bookResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Book.class);

        assertThat(bookResponse).isNotNull();
    }

    @Test
    void setAvailable() throws Exception {
        testBook.setAvailable(false);
        bookRepository.save(testBook);

        MvcResult mvcResult = mockMvc.perform(put("/api/library/v1/book/" +
                        testBook.getBook_id() + "/return"))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        Book bookResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Book.class);

        assertThat(bookResponse).isNotNull();
        assertThat(bookResponse.isAvailable()).isTrue();
    }

    @Test
    void setUnavailable() throws Exception {
        testBook.setAvailable(true);
        bookRepository.save(testBook);

        MvcResult mvcResult = mockMvc.perform(put("/api/library/v1/book/" +
                        testBook.getBook_id() + "/borrow/1"))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        Book bookResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Book.class);

        assertThat(bookResponse).isNotNull();
        assertThat(bookResponse.isAvailable()).isFalse();
        assertThat(bookResponse.getReader_id()).isEqualTo(1);
    }

}