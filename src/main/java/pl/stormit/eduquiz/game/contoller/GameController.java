package pl.stormit.eduquiz.game.contoller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.stormit.eduquiz.game.dto.GameDto;
import pl.stormit.eduquiz.game.service.GameService;
import pl.stormit.eduquiz.quizcreator.domain.quiz.dto.QuizDto;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/games")
public class GameController {

    private final GameService gameService;

    @PostMapping("/singleGame")
    public ResponseEntity<GameDto> createGame(@Valid @RequestBody QuizDto quizRequest) {

        GameDto createGame = gameService.createGame(quizRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "The new game has been successfully created");

        return new ResponseEntity<GameDto>(createGame, headers, HttpStatus.CREATED);
    }

    @DeleteMapping("/{gameId}/delete")
    public ResponseEntity<Void> deleteGame(@PathVariable UUID gameId) {
        gameService.deleteGame(gameId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
