package pl.stormit.eduquiz.statistic.quizstatistic;

import pl.stormit.eduquiz.quizcreator.domain.quiz.dto.QuizDto;

import java.util.List;

public interface QuizStatisticFacade {

    List<QuizDto> getThreeNewest();
}
