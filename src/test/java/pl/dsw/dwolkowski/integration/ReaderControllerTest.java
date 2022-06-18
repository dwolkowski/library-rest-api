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
import pl.dsw.dwolkowski.api.metadata.model.Reader;
import pl.dsw.dwolkowski.api.repository.ReaderRepository;

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
class ReaderControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ReaderRepository readerRepository;

    private Reader testReader;

    @BeforeEach
    private void setup(){
        if( testReader == null || !readerRepository.existsById(testReader.getReader_id())){
            testReader = new Reader();
            testReader.setName("Test");
            testReader.setLast_name("Test");
            testReader.setBirth_date(LocalDate.now());
            testReader.setPhone_number("Test");
            testReader.setAddress("Test");
            testReader.setEmail("Test");

            readerRepository.save(testReader);
        }
    }

    @Test
    void getAllReaders() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/library/v1/reader/all"))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        List<Reader> readerResponse = Arrays.asList(
                objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Reader[].class));

        assertThat(readerResponse).isNotNull();
        assertThat(readerResponse.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    void getReader() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/library/v1/reader/" + testReader.getReader_id()))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        Reader readerResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Reader.class);

        assertThat(readerResponse).isNotNull();
        assertThat(readerResponse.getReader_id()).isEqualTo(testReader.getReader_id());
    }

    @Test
    void createReader() throws Exception {
        readerRepository.deleteById(testReader.getReader_id());

        MvcResult mvcResult = mockMvc.perform(post("/api/library/v1/reader/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testReader)))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        Reader readerResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Reader.class);

        assertThat(readerResponse).isNotNull();
    }

    @Test
    void deleteReader() throws Exception {
        if(!readerRepository.existsById(testReader.getReader_id()))
            readerRepository.save(testReader);

        mockMvc.perform(delete("/api/library/v1/reader/" + testReader.getReader_id()))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        assertThat(readerRepository.existsById(testReader.getReader_id())).isFalse();
    }
}