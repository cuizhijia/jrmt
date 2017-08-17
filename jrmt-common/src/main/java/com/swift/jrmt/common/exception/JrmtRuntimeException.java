package com.swift.jrmt.common.exception;

import com.swift.jrmt.common.constants.StatusCode;
import com.swift.jrmt.common.utils.StringUtil;

/**
 * 说明:
 */
public class JrmtRuntimeException extends JrmtBaseRuntimeException {
	
	private static final long serialVersionUID = 3864405014888201211L;
	
	private StatusCode statusCode;
	
	public JrmtRuntimeException(StatusCode statusCode, String defaultMessage) {
		this(StringUtil.hasLength( defaultMessage ) ? defaultMessage : statusCode.getMessage());
		this.statusCode = statusCode;
	}
	
	private JrmtRuntimeException(String message) {
		super(message);
	}

	public StatusCode getStatusCode() {
		return statusCode;
	}


}
