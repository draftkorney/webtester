package ua.alex.source.webtester.dao;

import ua.alex.source.webtester.entities.Question;

import java.util.List;

public interface QuestionDao extends IEntityDao<Question> {
    int countQuestionsByTestId(long testId);

    List<Question> getQuestionByTestId(int row, int count, Long idTest);

    List<Question> getQuestionByTestId(long idTest);

    List<Question> getQuestionByTestIdWithAnswers(Long idTest);
}
