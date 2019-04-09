package com.simon.backstage.controller;

import com.simon.backstage.config.ResourceConfig;
import com.simon.backstage.domain.msg.Code;
import com.simon.backstage.domain.msg.ReturnMsg;
import com.simon.dal.util.ImageUtil;
import com.simon.dal.vo.ImagesUrl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
import java.util.List;

/**
 * @author fengtianying
 * @date 2018/12/25 9:17
 */
@Controller
@RequestMapping("/back/file")
@Api(value = "FileController", description = "文件管理")
public class FileController {

    @Autowired
    private ResourceConfig resourceConfig;

    String[] FILETYPES = new String[]{
            ".jpg", ".bmp", ".jpeg", ".png", ".gif",
            ".JPG", ".BMP", ".JPEG", ".PNG", ".GIF"
    };
    @PostMapping("upload")
    @ResponseBody
    @ApiImplicitParam(name = "file",value = "资源文件(字节码)",paramType = "payload")
    public ReturnMsg<ImagesUrl> upload(HttpServletRequest request,@ApiParam(name = "type",value = "图片类型：1场所2公告3广告4物业资产",defaultValue = "1")@RequestParam Integer type) throws IOException {

        List<MultipartFile> files =((MultipartHttpServletRequest)request).getFiles("file");

        String relativePath = "";
        String filePath = resourceConfig.getRootPath(); // 上传后的路径
        if (1 == type) {//场所图片
            relativePath = resourceConfig.getPlacePath();
        } else if (2 == type) {//公告
            relativePath = resourceConfig.getNoticePath();
        } else if(3 == type){ //广告
            relativePath += resourceConfig.getAdvPath();
        }else if(4 == type){ //物业资产
            relativePath += resourceConfig.getAssetImgPath();
        }else {
            filePath += "/";
        }
        filePath += relativePath;
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer thumbnail = new StringBuffer();
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
            thumbnail.append(filePath).append(fileName);
            if (i<files.size()-1){
                stringBuffer.append(",");
                thumbnail.append(",");
            }
        }
        ImagesUrl imagesUrl = new ImagesUrl();
        imagesUrl.setOriginalUrl(stringBuffer.toString());
        List thumbnailList = ImageUtil.generateThumbnail2Directory(filePath, thumbnail.toString().split(","));
        StringBuffer thumbnailSb = new StringBuffer();
        for (int j = 0; j < thumbnailList.size(); j++) {
            thumbnailSb.append(thumbnailList.get(j));
            if (j<files.size()-1){
                thumbnailSb.append(",");
            }
        }
        imagesUrl.setThumbnailUrl(thumbnailSb.toString().replaceAll(resourceConfig.getRootPath(),""));
        return ReturnMsg.success(imagesUrl);
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

    @PostMapping("delFile")
    @ResponseBody
    public ReturnMsg delFile(String path){
        String filePath = resourceConfig.getRootPath(); // 上传后的路径
        File file = new File(filePath.concat(path));
        String prefix = path.substring(0,path.indexOf("."));
        String suffix = path.substring(path.indexOf("."),path.length());
        File thumbnail = new File(filePath.concat(prefix.concat("-thumbnail").concat(suffix)));
        if (file.exists()){
            file.delete();
        }
        if (thumbnail.exists()){
            thumbnail.delete();
        }
        return ReturnMsg.success();
    }

}
