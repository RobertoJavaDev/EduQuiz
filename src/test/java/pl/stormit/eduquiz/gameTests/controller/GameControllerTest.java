package pl.stormit.eduquiz.gameTests.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import pl.stormit.eduquiz.game.dto.GameDto;
import pl.stormit.eduquiz.game.service.GameService;
import pl.stormit.eduquiz.quizcreator.domain.quiz.dto.QuizDto;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class GameControllerTest {

    private static final UUID ID_1 = UUID.fromString("5d1b4c2c-9f1c-11ed-a8fc-0242ac120002");

    @MockBean
    private GameService gameService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private GameDto gameDto;
    private QuizDto quizDto;

    @Test
    void shouldReturnStatusCreatedWhenCreateGameCorrectly() throws Exception {
        //given
        List<UUID> listExample = Collections.emptyList();
        gameDto = new GameDto(ID_1, listExample);
        when(gameService.createGame(quizDto))
                .thenReturn(new GameDto(ID_1, listExample));

        //when
        ResultActions result = mockMvc.perform(post("/api/v1/games/singleGame")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(objectMapper.writeValueAsString(gameDto)))
        );

        //then
        result.andExpect(status().isCreated());
    }
}
