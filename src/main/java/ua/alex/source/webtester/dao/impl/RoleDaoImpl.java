package ua.alex.source.webtester.dao.impl;


import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ua.alex.source.webtester.dao.QuestionDao;
import ua.alex.source.webtester.dao.RoleDao;
import ua.alex.source.webtester.entities.Question;
import ua.alex.source.webtester.entities.Role;

import java.util.List;

@Repository
public class RoleDaoImpl extends AbstractEntityDao<Role> implements RoleDao {
    @Override
    protected Class<Role> getEntityClass() {
        return Role.class;
    }

    @Override
    public Role getStudentRole() {
        return (Role) getSession().createCriteria(getEntityClass()).add(Restrictions.eq("idRole", 4)).uniqueResult();
    }

    @Override
    public Role getByName(String name) {
        return (Role) getSession().createCriteria(getEntityClass()).add(Restrictions.eq("name", name)).uniqueResult();
    }

    @Override
    public List<Role> getListByIds(List<Integer> ids) {
        return getSession().createCriteria(getEntityClass()).add(Restrictions.in("id", ids)).list();
    }
}
