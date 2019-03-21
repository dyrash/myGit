package com.sh.common.advice;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.ThrowsAdvice;

import com.sh.common.exception.DataAccessException;




public class DataAccessThrowsAdvice implements ThrowsAdvice {

	protected final Log logger = LogFactory.getLog(this.getClass());

	public void afterThrowing(DataAccessException ex) throws Throwable {
			logger.fatal(ex.getMessage());
		throw ex;
	}

	public void afterThrowing(Exception ex) throws Throwable {
			logger.fatal(ex.getMessage());
		throw ex;
	}
}
