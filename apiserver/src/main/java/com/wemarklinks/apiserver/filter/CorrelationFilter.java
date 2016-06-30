package com.wemarklinks.apiserver.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.IdGenerator;
import org.springframework.util.StringUtils;

import com.wemarklinks.apiserver.common.RequestUtil;

@Component
public class CorrelationFilter implements Filter {

	private static final String CORRELATION_ID_HEADER_NAME = "X-Correlation-Id";
	private static final String CORRELATION_ID_LOG_VAR_NAME = "correlationId";
	private static final Logger logger = LoggerFactory.getLogger(CorrelationFilter.class);

	@Autowired
	private IdGenerator idGenerator;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		final String correlationId = getCorrelationIdFromHeader((HttpServletRequest) request);
		MDC.put(CORRELATION_ID_LOG_VAR_NAME, correlationId);
		try {
			logger.info("Request Info: {}", RequestUtil.requestInfo((HttpServletRequest) request));
			chain.doFilter(request, response);
		} finally {
			MDC.remove(CORRELATION_ID_LOG_VAR_NAME);
		}
	}

	private String getCorrelationIdFromHeader(final HttpServletRequest request) {
		String correlationId = request.getHeader(CORRELATION_ID_HEADER_NAME);
		if (StringUtils.isEmpty(correlationId)) {
			correlationId = generateUniqueCorrelationId();
		}

		return correlationId;
	}

	private String generateUniqueCorrelationId() {
		return idGenerator.generateId().toString();
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
