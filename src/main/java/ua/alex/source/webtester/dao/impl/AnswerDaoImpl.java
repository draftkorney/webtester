package ua.alex.source.webtester.dao.impl;

import org.springframework.stereotype.Service;
import ua.alex.source.webtester.dao.AnswerDao;
import ua.alex.source.webtester.entities.Account;
import ua.alex.source.webtester.entities.Answer;

@Service
public class AnswerDaoImpl extends AbstractEntityDao<Answer> implements AnswerDao {

    @Override
    protected Class<Answer> getEntityClass() {
        return Answer.class;
    }



}
