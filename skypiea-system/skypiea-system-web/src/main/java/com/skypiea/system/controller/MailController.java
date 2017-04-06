package com.skypiea.system.controller;

import com.skypiea.common.result.SPResult;
import com.skypiea.system.model.GridFSFileInfo;
import com.skypiea.system.model.MailInfo;
import com.skypiea.system.service.FileService;
import com.skypiea.system.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-04-06 13:16
 */

@RestController
@RequestMapping("/sys/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @Autowired
    private FileService fileService;

    @PostMapping("/send/simple")
    public SPResult sendSimpleMail(@RequestBody MailInfo mailInfo) {
        SPResult spResult = mailService.sendHtmlMail(mailInfo);
        return spResult;
    }

    @PostMapping("/uploadImage")
    public String uploadFile(@RequestParam("image") MultipartFile image, HttpServletResponse response) {
        SPResult result = fileService.uploadFile(image);
        GridFSFileInfo info = (GridFSFileInfo) result.getData();
        String name = info.getFilename();
        String imgUrl = "http://localhost:8080/sys/mail/img/" + name;

        return imgUrl;
    }

//    @PostMapping("/show/img")
//    public void showMailPicture(@RequestParam("image") MultipartFile image, HttpServletResponse response) {
//        BufferedInputStream bis = null;
//        BufferedOutputStream bos = null;
//        try {
//            response.setContentType(image.getContentType());
//            bis = new BufferedInputStream(image.getInputStream());
//            bos = new BufferedOutputStream(response.getOutputStream());
//            int len = 0;
//            while ((len = bis.read()) != -1) {
//                bos.write(len);
//            }
//            bos.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            IOUtils.closeIO(bis);
//            IOUtils.closeIO(bos);
//        }
//    }

    @GetMapping("/img/{imagename:.+}")
    public void showImage(@PathVariable String imagename, HttpServletResponse response) {
        fileService.showFileByName(imagename, response);
    }
}
