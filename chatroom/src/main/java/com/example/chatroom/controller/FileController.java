package com.example.chatroom.controller;

import com.example.chatroom.entity.User;
import com.example.chatroom.service.impl.UserServiceImpl;
import com.example.chatroom.util.FileNameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Controller
public class FileController {
    private final ResourceLoader resourceLoader;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    public FileController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Value("${web.upload-path}")
    private String path;

    @RequestMapping(value = "/uploadImage",method = RequestMethod.POST)
    @ResponseBody
    public String uploadImage(@RequestParam("file")MultipartFile file, HttpSession httpSession){
        //获取项目classes/static的地址
        String staticPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
        System.out.println("测试点");
        System.out.println(file.getOriginalFilename());
        String fileName = FileNameUtils.getFileName(file.getOriginalFilename());  //获取文件名,以及改名

        // 图片存储目录及图片名称
        String url_path = "img" + File.separator + fileName;
        //图片保存路径
        String savePath = staticPath + File.separator + url_path;
        System.out.println("图片保存地址："+savePath);
        // 访问路径=静态资源路径+文件目录路径
//        String visitPath ="static/" + url_path;
        File saveFile = new File(savePath);
        if (!saveFile.exists()){
            saveFile.mkdirs();
        }
        try {
            file.transferTo(saveFile);  //将临时存储的文件移动到真实存储路径下
        } catch (IOException e) {
            e.printStackTrace();
        }
        String visitPath =File.separator + fileName;
        System.out.println("要存储的照片路径："+visitPath);
        User user= (User) httpSession.getAttribute("user");
        System.out.println("修改"+user.getUsername()+"的图片");
        userService.changeAvatarPath(visitPath,user.getUserId());
        visitPath="img" +File.separator + fileName;
        System.out.println("改过后图片访问uri："+visitPath);
        return visitPath;
    }


    /**
     * 显示单张图片
     * @return
     */
    @RequestMapping("/show/{username}")
    @ResponseBody
    public ResponseEntity showPhotos(@PathVariable String username){
        User user=userService.findOne(username);
        try {
            // 由于是读取本机的文件，file是一定要加上的， path是在application配置文件中的路径
            System.out.println("file:" + path + user.getAvatar());
            return ResponseEntity.ok(resourceLoader.getResource("file:" + path + user.getAvatar()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}