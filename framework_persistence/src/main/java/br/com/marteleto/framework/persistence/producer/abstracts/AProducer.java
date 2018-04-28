package br.com.marteleto.framework.persistence.producer.abstracts;

import java.io.Serializable;
import java.util.logging.Logger;

public abstract class AProducer implements Serializable {
	private static final long serialVersionUID = 1L;
	protected final transient Logger logger = Logger.getLogger(AProducer.class.getName());
}
