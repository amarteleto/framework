package br.com.marteleto.framework.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class TestEntityPersistenceHibernateChild extends APersistenceEntity {
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TEPC_SEQUENCE")
 	@SequenceGenerator(name="SQ_TEPC_SEQUENCE", sequenceName="SQ_TEPC_SEQUENCE", allocationSize = 1)
	@Column(name = "SQ_TEPC_SEQUENCE")
    private Long id;
	
	@ManyToOne
	@JoinColumn(name="SQ_TEEP_SEQUENCE", nullable=false, foreignKey = @ForeignKey(name = "FK_TEEP_TEPC"))
	private TestEntityPersistenceHibernate parent;
	
	@Lob
	@Column(name="TX_TEEP_TEXT",nullable=true)
	private String text;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
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