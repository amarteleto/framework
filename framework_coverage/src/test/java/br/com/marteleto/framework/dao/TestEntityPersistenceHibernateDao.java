package br.com.marteleto.framework.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.apache.commons.collections.map.HashedMap;
import org.hibernate.query.Query;

import br.com.marteleto.framework.dao.abstracts.AHibernateTestDao;
import br.com.marteleto.framework.dao.interfaces.ITestEntityPersistenceHibernateDao;
import br.com.marteleto.framework.filter.TestEntityPersistenceHibernateFilter;
import br.com.marteleto.framework.persistence.exception.DaoException;
import br.com.marteleto.framework.persistence.hibernate.transformer.HibernateResultTransformer;
import br.com.marteleto.framework.vo.TestEntityPersistenceHibernate;
import br.com.marteleto.framework.vo.TestEntityPersistenceHibernateChild;


@SuppressWarnings({"unchecked","rawtypes","deprecation"})
public class TestEntityPersistenceHibernateDao extends AHibernateTestDao implements ITestEntityPersistenceHibernateDao {
	private static final long serialVersionUID = 1L;

	public TestEntityPersistenceHibernateDao(EntityManager entityManager) {
		super(entityManager);
	}

	public List<TestEntityPersistenceHibernate> listTestEntityPersistenceByFilter(TestEntityPersistenceHibernateFilter filter) throws DaoException {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT");
			sql.append(" 	TEEP.SQ_TEEP_SEQUENCE as id,");
			sql.append(" 	TEEP.NM_TEEP_NAME as aliasname,");
			sql.append(" 	TEPC.SQ_TEPC_SEQUENCE as \"childrens.id\",");
			sql.append(" 	TEPC.TX_TEEP_TEXT as \"childrens.text\",");
			sql.append(" 	TEEP.SQ_TEEP_SEQUENCE as xxxx,");
			sql.append(" 	TEEP.SQ_TEEP_SEQUENCE as ROWNUM_");
			sql.append(" FROM");
			sql.append(" 	TB_TEST_ENTITY_PERSISTENCE TEEP");
			sql.append(" 	INNER JOIN TB_TEST_ENTITY_PERSI_CHILD TEPC ON TEPC.SQ_TEEP_SEQUENCE = TEEP.SQ_TEEP_SEQUENCE");
			sql.append(" WHERE 1=1");
			if (filter != null) {
				if (filter.getText() != null && !"".equals(filter.getText().trim())) {
					sql.append(" 	AND LOWER(TEEP.NM_TEEP_NAME) like :text");
				}
				if (filter.getSortField() != null) {
					sql.append(" ORDER BY");
					sql.append(" 	" + filter.getSortField());
					if (filter.getSortOrder() != null) {
						sql.append(" " + filter.getSortOrder());
					}
				}
			}
			Map<String, String> alias = new HashedMap();
			alias.put("aliasname", "name");
			alias.put("aliasname2", "name");
			HibernateResultTransformer resultTransformer = HibernateResultTransformer.aliasToBean(TestEntityPersistenceHibernate.class);
			resultTransformer.addAlias("aliasname", "name");
			resultTransformer.setAlias(alias);
			resultTransformer.removeAlias("aliasname2");
			Query query = this.getSession().createSQLQuery(sql.toString());
			query.setResultTransformer(resultTransformer);
			if (filter != null) {
				if (filter.getText() != null && !"".equals(filter.getText().trim())) {
					query.setParameter("text", "%" + filter.getText().toLowerCase() + "%");
				}
				if (filter.getFirst() != null) {
					query.setFirstResult(filter.getFirst());
				}
				if (filter.getPageSize() != null) {
					query.setMaxResults(filter.getPageSize());
				}
			}
			return query.getResultList();
		} catch (Exception ex) {
			throw new DaoException(ex);
		}
	}
	
	public List<TestEntityPersistenceHibernateChild> listTestEntityPersistenceChild() throws DaoException {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT");
			sql.append(" 	TEPC.SQ_TEPC_SEQUENCE as \"id\",");
			sql.append(" 	TEPC.TX_TEEP_TEXT as \"text\",");
			sql.append(" 	TEEP.SQ_TEEP_SEQUENCE as \"parent.id\",");
			sql.append(" 	TEEP.NM_TEEP_NAME as \"parent.name\"");
			sql.append(" FROM");
			sql.append(" 	TB_TEST_ENTITY_PERSISTENCE TEEP");
			sql.append(" 	INNER JOIN TB_TEST_ENTITY_PERSI_CHILD TEPC ON TEPC.SQ_TEEP_SEQUENCE = TEEP.SQ_TEEP_SEQUENCE");
			sql.append(" WHERE 1=1");
			HibernateResultTransformer resultTransformer = HibernateResultTransformer.aliasToBean(TestEntityPersistenceHibernateChild.class);
			Query query = this.getSession().createSQLQuery(sql.toString());
			query.setResultTransformer(resultTransformer);
			return query.getResultList();
		} catch (Exception ex) {
			throw new DaoException(ex);
		}
	}
}