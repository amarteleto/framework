package br.com.marteleto.framework.web.filter.abstracts;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.servlet.Filter;

public abstract class AFilter implements Filter, Serializable {
	private static final long serialVersionUID = 1L;
	protected final transient Logger logger = Logger.getLogger(AFilter.class.getName());
}