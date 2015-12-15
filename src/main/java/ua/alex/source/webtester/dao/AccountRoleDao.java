package ua.alex.source.webtester.dao;


import ua.alex.source.webtester.entities.AccountRole;

/**
 * @author nedis
 * @version 1.0
 */
public interface AccountRoleDao extends IEntityDao<AccountRole> {

    void deleteRolesByAccountId(Long idAccount);
}
