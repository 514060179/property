package com.simon.app.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.simon.app.config.ResourceConfig;
import com.simon.app.model.vo.Code;
import com.simon.app.model.vo.ReturnMsg;
import com.simon.app.service.UserService;
import com.simon.app.util.ClaimsUtil;
import com.simon.app.util.ImageType;
import com.simon.dal.model.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/file")
@Api(tags="file", description="文件上传")
public class FileController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private ResourceConfig resourceConfig;
	
	public static final String[] FORMAT = {
			".jpg", ".png", ".jpeg", ".bmp", ".gif", ".amr", ".mp3", ".wma", ".wav",
			".JPG", ".PNG", ".JPEG", ".BMP", ".GIF", ".AMR", ".MP3", ".WMA", ".WAV"
	}; 
	
	@PostMapping("upload")
    @ApiOperation("文件上传")
	@ApiImplicitParams({
			@ApiImplicitParam(name="type",value="图片类型0投诉/保修1头像4其他",paramType="query"),
		})
    public ReturnMsg<Object> upload(@RequestParam String type,
    		@RequestParam("file") MultipartFile files, HttpServletRequest request)
    		throws IllegalStateException, IOException{
		String userId = ClaimsUtil.getUserId(request);
    	//根目录
		String rootPath = resourceConfig.getRootPath();
		String realPath = "/";
		if(ImageType.IMAGE_TYPE_USER.equals(type)){
			realPath = resourceConfig.getImagePath();
			rootPath += realPath;
		}else if(ImageType.IMAGE_TYPE_COMPLAIN.equals(type)){
			realPath = resourceConfig.getImagePath();
			rootPath += realPath;
		}else{
			realPath = resourceConfig.getFilePath();
			rootPath += realPath;
		}
		//判断文件夹是否存在
		File fileIo = new File(rootPath);
		if(!fileIo.exists()){
			fileIo.mkdirs();
		}
		MultipartHttpServletRequest multipart= (MultipartHttpServletRequest)request;
		List<MultipartFile> fileNames = multipart.getFiles("file");
		Map<String,String> map = new HashMap<>();
		String paths = "";
    	for (MultipartFile file : fileNames) {
    		String fileName = file.getOriginalFilename();
    		String sufName = fileName.substring(fileName.lastIndexOf("."));
    		//校验文件格式
    		if(!verifyFormat(sufName)){
    			return ReturnMsg.fail("请选择合法的文件", Code.illegalFile);
    		}
    		String newName = System.currentTimeMillis() + sufName; //上传后的新名字
    		if(!file.isEmpty() && file!=null){
    			File localFile = new File(rootPath + newName);
    			file.transferTo(localFile);
    			if(ImageType.IMAGE_TYPE_USER .equals(type)){//用户头像，更新用户信息
    				User user = new User();
    				user.setUserId(userId);
    				user.setPortrait(realPath + newName);
         			int i = userService.updateByPrimaryKeySelective(user);
         			if(i < 1){
         				return ReturnMsg.fail();
         			}
         			return ReturnMsg.success();
//    			}else if(ImageType.IMAGE_TYPE_COMPLAIN_VOICE == type){//投诉/报修 语音上传
//    				Complain complain =new Complain();
//    				complain.setComplainId(id);
//    				complain.setComplainVoice(path);
//    				int i = complainService.updateByPrimaryKeySelective(complain);
//    				if(i < 1){
//         				return ReturnMsg.fail();
//         			}
//    			}else{
//    				Image image = new Image();
//    				image.setImageId(UUIDUtil.uidString());
//    				image.setImageUrl(path);
//    				if(ImageType.IMAGE_TYPE_COMPLAIN == type){
//    					image.setComplainId(id);
//    				}
//    				//新增图片
//    				int i = imageService.insertSelective(image);
//    				if(i < 1){
//         				return ReturnMsg.fail();
//         			}
    			}
    			paths = realPath + newName + "," + paths;
        	}
		}
    	String substring = paths.substring(0, paths.length()-1);
    	map.put("path", substring);
    	return ReturnMsg.success(map);
	}
	
	public boolean verifyFormat(String sufName) {
		for (int i = 0; i < FORMAT.length; i++) {
			if(sufName.equals(FORMAT[i])){
				return true;
			}
		}
		return false;
	}
}
