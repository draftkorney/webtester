package ua.alex.source.webtester.dao.impl;

import org.springframework.stereotype.Repository;
import ua.alex.source.webtester.dao.AccountRoleDao;
import ua.alex.source.webtester.entities.AccountRole;


@Repository("hiberanteAccountRoleDao")
public class AccountRoleDaoImpl extends AbstractEntityDao<AccountRole> implements AccountRoleDao {

	@Override
	protected Class<AccountRole> getEntityClass() {
		return AccountRole.class;
	}

}
