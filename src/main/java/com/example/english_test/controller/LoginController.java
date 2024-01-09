package com.example.english_test.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.english_test.dto.Result;
import com.example.english_test.dto.UserDTO;
import com.example.english_test.entity.Student;
import com.example.english_test.service.IStudentService;
import com.example.english_test.service.IUserDTOService;
import com.example.english_test.utils.USerHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
@Slf4j
@RestController
@RequestMapping("/student_login")
public class LoginController {
    @Autowired
    private IStudentService studentService;

    @Autowired
    private IUserDTOService iUserDTOService;
    /*验证码
    * */


    /*学生登陆*/
    @PostMapping()
    public Result student_login(String studentId, String password, HttpSession session)
    {

        log.info("studentId"+studentId+"------"+password);
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Student::getStudentId,studentId);
        if(studentService.count(queryWrapper)>0)
        {
            queryWrapper.eq(Student::getPassword,password);
            if(studentService.count(queryWrapper)>0)
            {
                Student one = studentService.getOne(queryWrapper);

                session.setAttribute("user",one);
                UserDTO userDTO = BeanUtil.copyProperties(one, UserDTO.class);
                userDTO.setLoginTime(LocalDateTime.now());
                userDTO.setLatestLoginTime(LocalDateTime.now());
                USerHolder.saveUSer(userDTO);


                return Result.ok("登陆成功！");
            }
            else
            {
                return Result.fail("密码错误！");
            }
        }else{
            return Result.fail("用户不存在！");
        }


    }

    /*获取验证码，验证码刷新
    * */
    @GetMapping("/getCodeImg")
    public void getCodeImage(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) throws IOException, InterruptedException {
        BufferedImage image=new BufferedImage(80, 32, BufferedImage.TYPE_3BYTE_BGR);
        //编辑图像
        //获取绘图对象
        Graphics g=image.getGraphics();
        g.setColor(new Color(239, 239, 239));
        g.fillRect(0,0,80,32);
        //设置字体颜色
        g.setColor(new Color(49, 49, 49));
        //设置字体
        g.setFont(new Font("SimSong",Font.ITALIC,20));
        //绘制字符串；
        String text="";
        for(int i=0;i<4;i++) {
            text +=(int) (Math.random()*10);
        }
        //字符串输出内容，水平起始坐标，垂直起始坐标。
        g.drawString(text, 17, 24);
        //画线条
        for (int i = 0; i < 10; i++) {
            g.setColor(new Color((int) (Math.random()*255), (int) (Math.random()*255), (int) (Math.random()*255)));
            g.drawLine((int) (Math.random()*50),(int) (Math.random()*30),(int) (Math.random()*80),(int) (Math.random()*80));
        }
        //设置session
        httpSession.setAttribute("code",text);
        //输出图像
        //ImageIO.write(image, "png", new FileOutputStream("C:/Users/H/Desktop/"+tet+".png"));

        //写入到response中
        ImageIO.write(image, "png",response.getOutputStream());
        g.dispose();

    }

    @GetMapping("/getCode")
    public Result getCode(HttpSession httpSession){
        return Result.ok((String) httpSession.getAttribute("code"));
    }


    //TODO 用户退出登录，清除session和线程
    /*用户退出登录
    * */
    @GetMapping("/login_out")
    public Result student_login_out()
    {
        //TODO 记录学生登录信息，插入student_visit，待完善！！
        UserDTO user = USerHolder.getUser();
        LocalDateTime loginTime = user.getLoginTime();
        LocalDateTime loginOutTime = LocalDateTime.now();
        Duration between = Duration.between(loginOutTime, loginTime);
        user.setOnlineTime(String.valueOf((between.toMinutes())/60.0));
        iUserDTOService.save(user);
        USerHolder.removeUser();
        return Result.ok("退出成功！！");
    }

}
