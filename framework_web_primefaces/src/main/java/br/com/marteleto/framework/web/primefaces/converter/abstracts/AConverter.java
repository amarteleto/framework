package br.com.marteleto.framework.web.primefaces.converter.abstracts;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.faces.convert.Converter;

public abstract class AConverter implements Converter, Serializable {
	private static final long serialVersionUID = 1L;
	protected final transient Logger logger = Logger.getLogger(this.getClass().getName());
}