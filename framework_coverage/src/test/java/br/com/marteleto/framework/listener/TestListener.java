package br.com.marteleto.framework.listener;

import java.util.ArrayList;
import java.util.List;

import br.com.marteleto.framework.core.listener.abstracts.AListener;

public class TestListener extends AListener {
	private static final long serialVersionUID = 1L;
	private Integer count;
	
	public boolean executeTest() {
		count = 0;
		this.getProperties();
		this.getResources();
		this.initProperties();
		this.initResources();
		count = 1;
		this.getProperties();
		this.getResources();
		this.initProperties();
		this.initResources();
		count = 2;
		this.getProperties();
		this.getResources();
		this.initProperties();
		this.initResources();
		count = 3;
		this.getProperties();
		this.getResources();
		this.initProperties();
		this.initResources();
		count = 4;
		this.getProperties();
		this.getResources();
		this.initProperties();
		this.initResources();
		this.initListener();
		this.finishListener();
		return true;
	}
	
	protected List<String> getProperties() {
		if (count.equals(0)) {
			return super.getProperties();
		} else if (count.equals(1)) {
			List<String> properties = new ArrayList<String>();
			properties.add("remote-error.properties");
			return properties;
		} else if (count.equals(2)) {
			List<String> properties = new ArrayList<String>();
			properties.add(null);
			return properties;
		} else if (count.equals(3)) {
			List<String> properties = new ArrayList<String>();
			properties.add("");
			return properties;
		} else if (count.equals(4)) {
			List<String> properties = new ArrayList<String>();
			properties.add("remote.properties");
			return properties;
		}
		return null;
	}
	
	protected List<String> getResources() {
		if (count.equals(0)) {
			return super.getResources();
		} else if (count.equals(1)) {
			List<String> resources = new ArrayList<String>();
			resources.add("messages-error");
			return resources;
		} else if (count.equals(2)) {
			List<String> resources = new ArrayList<String>();
			resources.add(null);
			return resources;
		} else if (count.equals(3)) {
			List<String> resources = new ArrayList<String>();
			resources.add("");
			return resources;
		} else if (count.equals(4)) {
			List<String> resources = new ArrayList<String>();
			resources.add("messages");
			return resources;
		}
		return null;
	}
}
