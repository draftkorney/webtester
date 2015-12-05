package ua.alex.source.webtester.dao;

import ua.alex.source.webtester.entities.Role;

public interface RoleDao extends IEntityDao<Role> {
    Role getStudentRole();
}
