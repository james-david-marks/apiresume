/* Copyright (C) 2020 James D. Marks. All Rights Reserved.                                                                   */
/* You may use, distribute and modify this code under the terms of the MIT License https://en.wikipedia.org/wiki/MIT_License */
package com.jimmarks.apiresume;

import java.util.Enumeration;
import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.jimmarks.model.ResumeLine;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("")
public class AppErrorController implements ErrorController
{
    private final static String ERROR_PATH = "/error";

	public String getErrorPath() {
		return ERROR_PATH;
	}
	
	@RequestMapping(ERROR_PATH)
	public @ResponseBody String handleError(HttpServletRequest request) {
		App.APPLOGGER.info("error service invoked");
		HtmlFormatter html = new HtmlFormatter();
		String errorHtml = getErrorHtml(request);
		return html.getLandingPage_build(errorHtml);
	}

	private String getErrorHtml(HttpServletRequest request) {
		final String CONTEXT = "org.springframework.web.servlet.DispatcherServlet.CONTEXT";
		final String ERROR = "org.springframework.boot.web.servlet.error.DefaultErrorAttributes.ERROR";
		final String EXCEPTION = "org.springframework.web.servlet.DispatcherServlet.EXCEPTION";
		final String MESSAGE = "jakarta.servlet.error.message";
		final String REQUEST_URI = "jakarta.servlet.error.request_uri";
		final String RESOURCE_URL_PROVIDER = "org.springframework.web.servlet.resource.ResourceUrlProvider";
		final String SERVLET_PATH = "org.springframework.web.util.ServletRequestPathUtils.PATH";
		final String STATUS_CODE = "jakarta.servlet.error.status_code";
		
		String context = "unknown";
		String error = "unknown";
		String exception = "unknown";
		String message = "unknown";
		String resourceUrlProvider = "unknown";
		String request_uri = "unknown";
		String servletPath = "unknown";
		String status_code = "unknown";
		
		HtmlFormatter html = new HtmlFormatter();
		Enumeration<String> attributeNames = request.getAttributeNames();
		while(attributeNames.hasMoreElements())
		{
			String name = attributeNames.nextElement().toString();
			switch(name)
			{
				default:
					break;
				case CONTEXT:
					context = request.getAttribute(name).toString();
					break;
				case ERROR:
					error = request.getAttribute(name).toString();
					break;
				case EXCEPTION:
					exception = request.getAttribute(name).toString();
					break;
				case MESSAGE:
					message = request.getAttribute(name).toString();
					break;
				case REQUEST_URI:
					request_uri = request.getAttribute(name).toString();
					break;
				case RESOURCE_URL_PROVIDER:
					resourceUrlProvider = request.getAttribute(name).toString();
					break;
				case SERVLET_PATH:
					servletPath = request.getAttribute(name).toString();
					break;
				case STATUS_CODE:
					status_code = request.getAttribute(name).toString();
					break;
			}
		}
		return html.getErrorDivs(status_code, message, context);
	}	
}


