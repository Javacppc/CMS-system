package com.itcast.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class TestLog {

	@Test
	public void test() {
		Log logger = LogFactory.getLog(getClass());
		logger.debug("debug調試級別日誌");
		logger.info("info 調試級別日誌");
		logger.warn("warn 調試級別日誌");
		logger.error("~~~~~-------error 調試級別日誌");
		logger.fatal("~~~~~-------fatal 調試級別日誌");
	}

}
