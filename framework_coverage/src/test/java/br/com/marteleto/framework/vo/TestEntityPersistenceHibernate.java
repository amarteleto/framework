package br.com.marteleto.framework.vo;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import br.com.marteleto.framework.persistence.vo.abstracts.APersistenceEntity;

@Entity
@Table(
		name="TB_TEST_ENTITY_PERSISTENCE"
		,uniqueConstraints={
				@UniqueConstraint(name="UN_TEEP_CANDIDATE_KEY",columnNames={"NM_TEEP_NAME"})
		}
		,indexes={
				@Index(name="PK_TEEP",columnList="SQ_TEEP_SEQUENCE"),
				@Index(name="IX_TEEP_CANDIDATE_KEY",columnList="NM_TEEP_NAME")
		}
)
@SequenceGenerator(name="SEQUENCE", sequenceName="SQ_TEEP_SEQUENCE", allocationSize = 1)
@AttributeOverride(name="id", column=@Column(name="SQ_TEEP_SEQUENCE",nullable=false,length=5))
public class TestEntityPersistenceHibernate extends APersistenceEntity {
	private static final long serialVersionUID = 1L;
	
	@Column(name="NM_TEEP_NAME",nullable=false,length=100)
	private String name;
	
	@Transient
	private List<TestEntityPersistenceHibernateChild> childrens;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TestEntityPersistenceHibernateChild> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<TestEntityPersistenceHibernateChild> childrens) {
		this.childrens = childrens;
	}
}