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
import com.simon.app.model.vo.ReturnMsg;
import com.simon.app.service.ComplainService;
import com.simon.app.service.ImageService;
import com.simon.app.service.UserService;
import com.simon.app.util.ClaimsUtil;
import com.simon.app.util.ImageType;
import com.simon.dal.model.Complain;
import com.simon.dal.model.Image;
import com.simon.dal.model.User;
import com.simon.dal.util.UUIDUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/app/file")
@Api(tags="file", description="文件上传")
public class FileController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private ComplainService complainService;
	@Autowired
	private ImageService imageService;
	@Autowired
	private ResourceConfig resourceConfig;
	
	@PostMapping("upload")
    @ApiOperation("文件上传")
	@ApiImplicitParams({
			@ApiImplicitParam(name="type",value="文件类型（用户头像：1，投诉/报修图片：4，语音：44）",paramType="query"),
			@ApiImplicitParam(name="userId",value="上传头像时需要的用户id",paramType="query")
		})
    public ReturnMsg<Object> upload(String type,String userId,
    		@RequestParam("file") MultipartFile files, HttpServletRequest request)
    		throws IllegalStateException, IOException{
    	//文件夹路径
		String realPath = resourceConfig.getRootPath()+resourceConfig.getImagePath();
		File fileIo = new File(realPath);
		//判断文件夹是否存在
		if(!fileIo.exists()){
			fileIo.mkdirs();
		}
		MultipartHttpServletRequest multipart= (MultipartHttpServletRequest)request;
		List<MultipartFile> fileNames = multipart.getFiles("file");
		Map<String,String> map = new HashMap<>();
		String paths = "";
    	for (MultipartFile file : fileNames) {
    		String fileName = file.getOriginalFilename();
    		String path = resourceConfig.getImagePath() + fileName;
    		if(!file.isEmpty() && file!=null){
    			File localFile = new File(path);
    			file.transferTo(localFile);
    			if(ImageType.IMAGE_TYPE_USER .equals(type)){//用户头像，更新用户信息
    				User user = new User();
    				user.setUserId(userId);
    				user.setPortrait(path);
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
    			paths = path + "," + paths;
        	}
		}
    	String substring = paths.substring(0, paths.length()-1);
    	map.put("path", substring);
    	return ReturnMsg.success(map);
	}
}
