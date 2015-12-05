package ua.alex.source.webtester.dao.impl;

import org.springframework.stereotype.Repository;
import ua.alex.source.webtester.dao.AnswerDao;
import ua.alex.source.webtester.dao.QuestionDao;
import ua.alex.source.webtester.entities.Answer;
import ua.alex.source.webtester.entities.Question;

@Repository
public class QuestionDaoImpl extends AbstractEntityDao<Question> implements QuestionDao {

    @Override
    protected Class<Question> getEntityClass() {
        return Question.class;
    }


}
