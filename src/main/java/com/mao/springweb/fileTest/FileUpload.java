package com.mao.springweb.fileTest;



import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.URLEncoder;


@Controller
public class FileUpload {
    @GetMapping("/fileIndex")
    public String fileIndex(){
        return "file";
    }
    @PostMapping("/upload")
    public String uploadFile(HttpServletRequest req, @RequestParam("description") String description,
                             @RequestParam("file") MultipartFile file) throws Exception{
        System.out.println("description: "+description);
        if(file.isEmpty()){
            return "error";
        }
        //
        String filePath=  req.getServletContext().getRealPath("/upload/");
        System.out.println("filePath: "+filePath);
        //上传文件名
        String fileName=file.getOriginalFilename();
        //创建目录
        File newFilePath=new File(filePath,fileName);
        if(!newFilePath.getParentFile().exists()){
              newFilePath.getParentFile().mkdirs();
        }
        //将上传文件保存
        file.transferTo(new File(filePath+File.pathSeparator+fileName));
        return "success";
    }
    @RequestMapping("/downloadIndex")
    public String downloadIndex(){
        return "download";
    }
    @GetMapping("/download")
    public ResponseEntity<byte[]> download(HttpServletRequest req, @RequestParam("fileName") String fileName
    , @RequestHeader("User-Agent") String userAgent, Model model)throws Exception{
        System.out.println("download");
     //下载文件路径
        String path=req.getServletContext().getRealPath("/upload/");
        File file=new File(path+File.separator+fileName);
        //http状态为200
        ResponseEntity.BodyBuilder builder= ResponseEntity.ok();
        builder.contentLength(file.length());
        //二进制流数据
        builder.contentType(MediaType.APPLICATION_OCTET_STREAM);
        //解码
        fileName= URLEncoder.encode(fileName,"UTF-8");
        //设置响应文件，下载或保存
        if(userAgent.indexOf("MSIE")>0){
            //IE
            builder.header("Content-Dispostion","attachment;filename="+fileName);
        }else{
            builder.header("Content-Disposition","attachment; filename*=UTF-8''"+fileName);
        }
    return builder.body(FileUtils.readFileToByteArray(file));

    }
}
