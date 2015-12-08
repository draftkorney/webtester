package ua.alex.source.webtester.dao;

import ua.alex.source.webtester.entities.Role;

import java.util.List;

public interface RoleDao extends IEntityDao<Role> {
    Role getStudentRole();

    Role getByName(String name);

    List<Role> getListByIds(List<Integer> ids);
}
