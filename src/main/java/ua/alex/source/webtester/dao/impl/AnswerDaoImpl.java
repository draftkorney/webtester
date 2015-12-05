package ua.alex.source.webtester.dao.impl;

import ua.alex.source.webtester.dao.AnswerDao;
import ua.alex.source.webtester.entities.Account;
import ua.alex.source.webtester.entities.Answer;


public class AnswerDaoImpl extends AbstractEntityDao<Answer> implements AnswerDao {

    @Override
    protected Class<Answer> getEntityClass() {
        return Answer.class;
    }



}
