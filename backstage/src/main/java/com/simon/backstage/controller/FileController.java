package com.simon.backstage.controller;

import com.simon.backstage.domain.msg.ReturnMsg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author fengtianying
 * @date 2018/12/25 9:17
 */
@Controller
@RequestMapping("file")
public class FileController {


    @RequestMapping("upload")
    @ResponseBody
    public ReturnMsg upload(MultipartFile file){
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String filePath = "D://temp-rainy//"; // 上传后的路径
        fileName = UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filename = "/temp-rainy/" + fileName;
        System.out.println(filename);
//        if (files.length>0) {
//            System.out.println("文件为空空");
//        }
//        for (MultipartFile file:files){
//            String fileName = file.getOriginalFilename();  // 文件名
//            String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
//            String filePath = "D://temp-rainy//"; // 上传后的路径
//            fileName = UUID.randomUUID() + suffixName; // 新文件名
//            File dest = new File(filePath + fileName);
//            if (!dest.getParentFile().exists()) {
//                dest.getParentFile().mkdirs();
//            }
//            try {
//                file.transferTo(dest);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            String filename = "/temp-rainy/" + fileName;
//            System.out.println(filename);
//        }
        return ReturnMsg.success();
    }
}
