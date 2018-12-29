package com.simon.backstage.controller;

import com.simon.backstage.config.ResourceConfig;
import com.simon.backstage.domain.msg.Code;
import com.simon.backstage.domain.msg.ReturnMsg;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fengtianying
 * @date 2018/12/25 9:17
 */
@Controller
@RequestMapping("/back/file")
public class FileController {

    @Autowired
    private ResourceConfig resourceConfig;

    String[] FILETYPES = new String[]{
            ".jpg", ".bmp", ".jpeg", ".png", ".gif",
            ".JPG", ".BMP", ".JPEG", ".PNG", ".GIF"
    };
    @PostMapping("upload")
    @ResponseBody
    public ReturnMsg upload(HttpServletRequest request,@ApiParam(name = "type",value = "图片类型：1场所2公告3其他",defaultValue = "1")@RequestParam Integer type){

        List<MultipartFile> files =((MultipartHttpServletRequest)request).getFiles("file");

        String relativePath = "/";
        String filePath = resourceConfig.getRootPath(); // 上传后的路径
        if (1 == type) {//场所图片
            relativePath = resourceConfig.getPlacePath();
            filePath += relativePath;
        } else if (2 == type) {//公告
            relativePath = resourceConfig.getNoticePath();
            filePath += relativePath;
        } else {
            filePath += "/";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i =0 ; i<files.size() ; i++){
            String fileName = files.get(i).getOriginalFilename();  // 文件名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
            if (!verifyImage(fileName)) {
                return ReturnMsg.fail(Code.unknownFile, "非法文件!" + fileName);
            }
            fileName = System.currentTimeMillis() + suffixName; // 新文件名
            File dest = new File(filePath + fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                files.get(i).transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stringBuffer.append(relativePath).append(fileName);
            if (i<files.size()-1){
                stringBuffer.append(",");
            }
        }
        Map<String,String> resultMap = new HashMap<>();
        resultMap.put("paths",stringBuffer.toString());
        return ReturnMsg.success(resultMap);
    }

    private boolean verifyImage(String imgPath){
        if(!StringUtils.isEmpty(imgPath)){
            for (int i = 0; i < FILETYPES.length; i++) {
                String fileType = FILETYPES[i];
                if (imgPath.endsWith(fileType)) {
                    return true;
                }
            }
        }
        return false;
    }
}
