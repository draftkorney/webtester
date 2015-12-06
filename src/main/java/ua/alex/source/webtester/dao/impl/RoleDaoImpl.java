package ua.alex.source.webtester.dao.impl;


import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ua.alex.source.webtester.dao.QuestionDao;
import ua.alex.source.webtester.dao.RoleDao;
import ua.alex.source.webtester.entities.Question;
import ua.alex.source.webtester.entities.Role;

@Repository
public class RoleDaoImpl extends AbstractEntityDao<Role> implements RoleDao {
    @Override
    protected Class<Role> getEntityClass() {
        return Role.class;
    }

    @Override
    public Role getStudentRole() {
        return (Role) getSession().createCriteria(getEntityClass()).add(Restrictions.eq("idRole", (short)4)).uniqueResult();
    }
}
