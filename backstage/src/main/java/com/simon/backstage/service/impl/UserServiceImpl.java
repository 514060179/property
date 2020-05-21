package com.simon.backstage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.backstage.dao.UnitMapper;
import com.simon.backstage.dao.UserUnitMapper;
import com.simon.backstage.domain.model.Unit;
import com.simon.backstage.domain.model.UserUnit;
import com.simon.backstage.service.UserService;
import com.simon.dal.dao.UserMapper;
import com.simon.dal.model.User;
import com.simon.dal.model.UserWithCommunity;
import com.simon.dal.util.EncryUtil;
import com.simon.dal.util.UUIDUtil;
import com.simon.dal.vo.BaseClaims;
import com.simon.dal.vo.BaseQueryParam;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author fengtianying
 * @date 2018/11/7 13:32
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UnitMapper unitMapper;
    @Autowired
    private UserUnitMapper userUnitMapper;
    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    @Transactional
    public User add(User user) {
        user.setUserId(UUIDUtil.uidString());
        user.setPassword(EncryUtil.getMD5(user.getPassword()));
        user.setCommunityId(!StringUtils.isEmpty(user.getCommunityId())?user.getCommunityId():user.getUserWithCommunities().get(0).getCommunityId());
        if(userMapper.insertSelective(user)>0){
            user.getUserWithCommunities().forEach(userWithCommunity -> {
                User u = new User();
                u.setUserId(user.getUserId());
                u.setCommunityId(userWithCommunity.getCommunityId());
                userMapper.insertUserCommunity(u);
            });
            return user;
        }
        return user;
    }

    @Override
    public User detail(String userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        user.setUserWithCommunities(userMapper.selectUserCommunitys(userId));
        return user;
    }

    @Override
    public int delUserCommunity(String userId, String communityId) {
        return userMapper.deleteUserCommunity(userId,communityId);
    }

    @Override
    @Transactional
    public int addUserCommunity(List<UserWithCommunity> userWithCommunityList) {
        userWithCommunityList.forEach(userWithCommunity -> {
            User user = new User();
            user.setUserId(userWithCommunity.getUserId());
            user.setCommunityId(userWithCommunity.getCommunityId());
            userMapper.insertUserCommunity(user);
        });
        return userWithCommunityList.size();
    }

    @Override
    public int upd(User user) {
        if (!StringUtils.isEmpty(user.getPassword())){
            user.setPassword(EncryUtil.getMD5(user.getPassword()));
        }
        if (userMapper.updateByPrimaryKeySelective(user)>0&&user.getUserWithCommunities()!=null){
            //删除之前设定的社区
            userMapper.delUserCommunity(user.getUserId());
            user.getUserWithCommunities().forEach(userWithCommunity -> {
                User u = new User();
                u.setUserId(userWithCommunity.getUserId());
                u.setCommunityId(userWithCommunity.getCommunityId());
                userMapper.insertUserCommunity(u);
            });
        }
        return 1;
    }

    @Override
    public int del(String userId) {
        //todo 删除对应的关系
        return userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public PageInfo<User> list(BaseClaims baseClaims) {
        PageHelper.startPage(baseClaims.getPageNo(),baseClaims.getPageSize());
        return new PageInfo<>(userMapper.selectByCondition(baseClaims));
    }

    @Override
    public List<Map<String, Object>> excelUserList(BaseQueryParam baseQueryParam) {
        return userMapper.excrlUserList(baseQueryParam);
    }

    @Override
    public String importExcel(HttpServletRequest request, String communityId) {
        MultipartFile files =((MultipartHttpServletRequest)request).getFile("file");
        StringBuffer responseMsg = new StringBuffer();
        StringBuffer failMsg = new StringBuffer();
        InputStream is = null;
        try {
            is = files.getInputStream();
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
            Map<String,User> map = new HashMap<String,User>();
            int success = 0;
            int fail = 0;
            for (int i = 1; i < xssfSheet.getLastRowNum(); i++){
                try {
                    XSSFRow row = xssfSheet.getRow(i);
                    if(row==null || invalidData(row)){
                        continue;
                    }
                    userService.save(communityId,row,map);
                    //todo 处理事务问题
                    success++;
                } catch (Exception e) {
                    fail++;
                    failMsg.append(i+1).append("、");
                    e.printStackTrace();
                }
            }
            responseMsg.append("成功导入：").append(success).append("导入失败：").append(fail).append(failMsg);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("导入excel异常",e.getMessage());
        }finally {
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return responseMsg.toString();
    }


    @Transactional
    public void save(String communityId,XSSFRow xssfRow,Map<String,User> map){
        XSSFCell username = xssfRow.getCell(0);
        XSSFCell name = xssfRow.getCell(1);
        XSSFCell ename = xssfRow.getCell(2);
        XSSFCell sex = xssfRow.getCell(3);
        XSSFCell quhao = xssfRow.getCell(4);
        if(quhao != null){
            quhao.setCellType(Cell.CELL_TYPE_STRING);
        }
        XSSFCell tel = xssfRow.getCell(5);
        if(tel != null){
            tel.setCellType(Cell.CELL_TYPE_STRING);
        }
        XSSFCell hunyinzhidu = xssfRow.getCell(6);
        XSSFCell peiou = xssfRow.getCell(7);
        XSSFCell email = xssfRow.getCell(8);
        XSSFCell unitNo = xssfRow.getCell(9);
        XSSFCell yequan = xssfRow.getCell(10);

        UserUnit userUnit = new UserUnit();
        Map<String,UserUnit> mapUnit = new HashMap<String,UserUnit>();
        User user = new User();
        user.setUserId(UUIDUtil.uidString());
        user.setCommunityId(communityId);
        user.setName(name.getStringCellValue());
        user.setEnglishName(ename==null?null:ename.getStringCellValue());
        user.setSex("男".equals(sex.getStringCellValue())?1:0);
        user.setCountryCode(quhao==null?null:quhao.getStringCellValue());
        user.setTel(tel==null?null:tel.getStringCellValue());
        user.setBirthday(new Date());
        user.setUsername(username.getStringCellValue());
        user.setPassword(EncryUtil.getMD5("123456"));
        user.setEmail(email==null?null:email.getStringCellValue());
        user.setMarriageSystem(hunyinzhidu==null?null:hunyinzhidu.getStringCellValue());
        user.setMateName(peiou==null?null:peiou.getStringCellValue());
        if (!map.containsKey(user.getName())){
            userMapper.insertSelective(user);
            userMapper.insertUserCommunity(user);
            map.put(user.getName(),user);
        }
        System.out.println(user);
        //获取单元id
        Unit unit = unitMapper.selectByUnitNo(unitNo.getStringCellValue());
        if (unit!=null){
            userUnit.setUserId(map.get(user.getName()).getUserId());
            userUnit.setUnitId(unit.getUnitId());
            userUnit.setTitle((int)yequan.getNumericCellValue());
            if (unit.getOwner()){
                userUnit.setOwner(false);
                userUnit.setConvincing(false);
            }else{
                userUnit.setOwner(true);
                userUnit.setConvincing(true);
            }
            userUnitMapper.insertSelective(userUnit);
        }
    }

    //判断是否为空行
    private boolean invalidData(XSSFRow row){
        for(int i = 0; i < row.getLastCellNum(); i++){
            XSSFCell cell = row.getCell(i);
            if(cell!=null && !StringUtils.isEmpty(cell.getStringCellValue())){
                return false;
            }
        }
        return true;
    }
}
