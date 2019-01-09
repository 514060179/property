package com.simon.app.service;

import com.simon.dal.vo.VisitorWithToken;

public interface VisitorService {
	
	/**
	 * 访问者登记
	 * @param visitor
	 * @return
	 */
	int record(VisitorWithToken visitor);
}
