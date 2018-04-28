package br.com.marteleto.framework.web.view.interfaces;

import java.io.Serializable;

public interface IView extends Serializable {
	void preRenderView();
	String getViewId();
}
