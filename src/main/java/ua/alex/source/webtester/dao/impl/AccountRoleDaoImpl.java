package ua.alex.source.webtester.dao.impl;

import org.springframework.stereotype.Repository;
import ua.alex.source.webtester.dao.AccountRoleDao;
import ua.alex.source.webtester.entities.AccountRole;


@Repository
public class AccountRoleDaoImpl extends AbstractEntityDao<AccountRole> implements AccountRoleDao {

	@Override
	protected Class<AccountRole> getEntityClass() {
		return AccountRole.class;
	}

	@Override
	public void deleteRolesByAccountId(Long idAccount) {
		String q = "DELETE FROM AccountRole ar WHERE ar.account.idAccount = :idAccount ";
		getSession().createQuery(q).setParameter("idAccount", idAccount).executeUpdate();
	}
}
