package com.simon.backstage.service.impl;

import com.simon.backstage.dao.ExcelUserMapper;
import com.simon.backstage.domain.vo.ExcelQueryParam;
import com.simon.backstage.domain.vo.ExcelUser;
import com.simon.backstage.service.ExcelUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fengtianying
 * @date 2019/2/21 15:57
 */
@Service
public class ExcelUserServiceImpl implements ExcelUserService {
    @Autowired
    private ExcelUserMapper excelUserMapper;
    @Override
    public List<ExcelUser> execlUser(ExcelQueryParam excelQueryParam) {
        return excelUserMapper.execlUser(excelQueryParam);
    }
}
