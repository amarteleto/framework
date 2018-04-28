package br.com.marteleto.framework.persistence.producer;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.Annotated;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import br.com.marteleto.framework.core.exception.ProducerException;
import br.com.marteleto.framework.persistence.annotation.PersistenceUnitName;
import br.com.marteleto.framework.persistence.producer.abstracts.AProducer;

@ApplicationScoped
public class EntityManagerProducer extends AProducer {
	private static final long serialVersionUID = 1L;	
	private final transient Map<String, EntityManager> entityManagerMap = new HashMap<>();
	@PersistenceContext private transient EntityManager entityManager; 
	
	@Produces
	@Default
	@PersistenceUnitName
	public EntityManager getEntityManager(InjectionPoint injectionPoint) {
		String name = null;
		if (injectionPoint != null) {
			Annotated annotated = injectionPoint.getAnnotated();
			if (annotated != null) {
				PersistenceUnitName persistenceUnitName = annotated.getAnnotation(PersistenceUnitName.class);
				if (persistenceUnitName != null) {
					name = persistenceUnitName.value();
				}
			}
		}
		return this.getEntityManager(name);
	}
	
	public EntityManager getEntityManager(String name) {
		if (entityManager != null) {
			return entityManager;
		}
		this.loadPersistenceXml();
		if (entityManagerMap.size() > 0) {
			if (name != null && !"".equals(name.trim())) {
				if (entityManagerMap.containsKey(name)) {
					return entityManagerMap.get(name);
				} else {
					throw new ProducerException("FALHA: Não foi encontrado persistence-unit com o nome [ " + name + " ].");
				}
			} else {
				if (entityManagerMap.size() == 1) {
					return entityManagerMap.values().iterator().next();
				} else {
					throw new ProducerException("FALHA: Foi encontrada mais de uma persistence-unit. É necessário definir uma como padrão.");
				}
			}
		} else {
			throw new ProducerException("FALHA: Nenhuma persistence-unit foi encontrada.");
		}
	}
	
	private void loadPersistenceXml() {
		if (entityManagerMap.size() == 0) {
			try {
				DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
				Document document = documentBuilder.parse(this.getClass().getResource("/META-INF/persistence.xml").getFile());
				NodeList nodeList = document.getElementsByTagName("persistence-unit");
				for(int x=0, size = nodeList.getLength(); x < size; x++) {
					String name = nodeList.item(x).getAttributes().getNamedItem("name").getNodeValue();
					EntityManagerFactory emf = Persistence.createEntityManagerFactory(name);
					entityManagerMap.put(name, emf.createEntityManager());
					if (nodeList.getLength() == 1) {
						entityManager = entityManagerMap.get(name);
					}
				}
			} catch (Exception ex) {
				throw new ProducerException("FALHA: Houve um problema ao criar EntityManagerProducer.",ex);
			}
		}
	}
}
