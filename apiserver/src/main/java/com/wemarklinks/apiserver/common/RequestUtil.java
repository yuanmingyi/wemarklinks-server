package com.wemarklinks.apiserver.common;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {

	public static String requestInfo(HttpServletRequest request) {
		return String.format("%s %s %s", request.getMethod(), request.getRequestURI(), request.getQueryString());
	}

}
