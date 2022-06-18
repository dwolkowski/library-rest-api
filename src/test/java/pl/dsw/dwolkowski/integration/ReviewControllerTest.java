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
import pl.dsw.dwolkowski.api.metadata.model.Review;
import pl.dsw.dwolkowski.api.repository.ReviewRepository;

import javax.transaction.Transactional;
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
class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ReviewRepository reviewRepository;

    private Review testReview;

    @BeforeEach
    private void setup(){
        if( testReview == null || !reviewRepository.existsById(testReview.getReview_id())){
            testReview = new Review();
            testReview.setBook_id(1L);
            testReview.setReader_id(1L);
            testReview.setRating(6);
            testReview.setReview("Test");

            reviewRepository.save(testReview);
        }
    }

    @Test
    void getAllReviews() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/library/v1/review/all"))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        List<Review> reviewResponse = Arrays.asList(
                objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Review[].class));

        assertThat(reviewResponse).isNotNull();
        assertThat(reviewResponse.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    void getReviewByBook() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/library/v1/review/book/" + testReview.getBook_id()))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        List<Review> reviewResponse = Arrays.asList(
                objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Review[].class));

        assertThat(reviewResponse).isNotNull();
        assertThat(reviewResponse.size()).isGreaterThanOrEqualTo(1);
        assertThat(reviewResponse.contains(testReview)).isTrue();
    }

    @Test
    void getReview() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/library/v1/review/" + testReview.getReview_id()))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        Review reviewResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Review.class);

        assertThat(reviewResponse).isNotNull();
        assertThat(reviewResponse.getReview_id()).isEqualTo(testReview.getReview_id());
    }

    @Test
    void createReview() throws Exception {
        reviewRepository.deleteById(testReview.getReview_id());

        MvcResult mvcResult = mockMvc.perform(post("/api/library/v1/review/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testReview)))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        Review reviewResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Review.class);

        assertThat(reviewResponse).isNotNull();
    }

    @Test
    void deleteReview() throws Exception {
        if(!reviewRepository.existsById(testReview.getReview_id()))
            reviewRepository.save(testReview);

        mockMvc.perform(delete("/api/library/v1/review/" + testReview.getReview_id()))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        assertThat(reviewRepository.existsById(testReview.getReview_id())).isFalse();
    }
}