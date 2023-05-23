package com.group4.server.controller_g4;


import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
public class G4_CaptchaController {
    @Autowired
    private DefaultKaptcha defaultKaptcha;


    @ApiOperation(value = "验证码生成")
    @GetMapping(value = "/g4_captcha", produces = "image/jpeg") //produces ：设置返回数据类型及编码
    public void captcha(HttpServletRequest request, HttpServletResponse response) {

//        默认配置===================================
//        定义response输出类型为image/jpeg类型
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store,no-cache,must-revalidate");
//        Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
// Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
// return a jpeg
        response.setContentType("image/jpeg");

//        生成验证码==================================

//        获取验证码文本内容
        String text = defaultKaptcha.createText();
        System.out.println("g4_online验证码内容为："+text);
//        将验证码放入session中
        request.getSession().setAttribute("captcha",text);
//        根据文本内容创建图形验证码
        BufferedImage image = defaultKaptcha.createImage(text);
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            //输出流输出图片，格式jpg
            ImageIO.write(image,"jpg",outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
