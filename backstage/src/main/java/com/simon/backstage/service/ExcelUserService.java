package com.simon.backstage.service;

import com.simon.backstage.domain.vo.ExcelQueryParam;
import com.simon.backstage.domain.vo.ExcelUser;

import java.util.List;

/**
 * @author fengtianying
 * @date 2019/2/21 15:56
 */
public interface ExcelUserService {
    List<ExcelUser> execlUser(ExcelQueryParam excelQueryParam);
}
