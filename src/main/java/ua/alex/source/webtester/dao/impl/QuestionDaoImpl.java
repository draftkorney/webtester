package ua.alex.source.webtester.dao.impl;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ua.alex.source.webtester.dao.QuestionDao;
import ua.alex.source.webtester.entities.Question;

import java.util.List;

@Repository
public class QuestionDaoImpl extends AbstractEntityDao<Question> implements QuestionDao {

    @Override
    protected Class<Question> getEntityClass() {
        return Question.class;
    }

    @Override
    public int countQuestionsByTestId(long testId) {
        String q = "FROM Question q WHERE q.test.id = :idTest";
        Query query = getSession().createQuery(q);
        query.setParameter("idTest", testId);
        return query.list().size();
    }

    @Override
    public List<Question> getQuestionByTestId(int row, int count, Long idTest) {
        int first = (row - 1) * count;

        String q = "FROM Question q WHERE q.test.id = :idTest";
        Query query = getSession().createQuery(q);
        query.setParameter("idTest", idTest);
        query.setFirstResult(first);
        query.setMaxResults(count);

        return query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Question> getQuestionByTestId(long idTest) {
        String q = "FROM Question q WHERE q.test.id = :idTest";
        Query query = getSession().createQuery(q);
        query.setParameter("idTest", idTest);
        return query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Question> getQuestionByTestIdWithAnswers(Long idTest) {
        String q = "FROM Question q WHERE q.test.id = :idTest AND q.answers.size > 0";
        Query query = getSession().createQuery(q);
        query.setParameter("idTest", idTest);
        return query.list();
    }
}
