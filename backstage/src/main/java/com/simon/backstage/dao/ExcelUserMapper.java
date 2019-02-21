package com.simon.backstage.dao;

import com.simon.backstage.domain.vo.ExcelQueryParam;
import com.simon.backstage.domain.vo.ExcelUser;

import java.util.List;

/**
 * @author fengtianying
 * @date 2019/2/21 15:54
 */
public interface ExcelUserMapper {

    List<ExcelUser> execlUser(ExcelQueryParam excelQueryParam);
}
