package br.com.marteleto.framework.vo;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.marteleto.framework.persistence.vo.abstracts.APersistenceEntity;

@Entity
@Table(
		name="TB_TEST_ENTITY_PERSI_CHILD"
		,indexes={
				@Index(name="PK_TEPC",columnList="SQ_TEPC_SEQUENCE")
		}
)
@SequenceGenerator(name="SEQUENCE", sequenceName="SQ_TEPC_SEQUENCE", allocationSize = 1)
@AttributeOverride(name="id", column=@Column(name="SQ_TEPC_SEQUENCE",nullable=false,length=5))
public class TestEntityPersistenceHibernateChild extends APersistenceEntity {
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name="SQ_TEEP_SEQUENCE", nullable=false, foreignKey = @ForeignKey(name = "FK_TEEP_TEPC"))
	private TestEntityPersistenceHibernate parent;
	
	@Lob
	@Column(name="TX_TEEP_TEXT",nullable=true)
	private String text;
	
	public TestEntityPersistenceHibernate getParent() {
		return parent;
	}
	public void setParent(TestEntityPersistenceHibernate parent) {
		this.parent = parent;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}