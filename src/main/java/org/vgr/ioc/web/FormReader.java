package org.vgr.ioc.web;

import org.vgr.http.server.HttpResponse;

public class FormReader<T> {
	
	public T getFormData(T obj, HttpResponse servletRequest){
		//System.out.println(servletRequest.getParameterMap().toString());
		return obj;
	}
	
}
