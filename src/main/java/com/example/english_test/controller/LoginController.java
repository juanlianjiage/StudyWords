package com.example.english_test.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.english_test.dto.Result;
import com.example.english_test.dto.UserDTO;
import com.example.english_test.entity.Student;
import com.example.english_test.service.IStudentService;
import com.example.english_test.utils.CaptchaProduce;
import com.example.english_test.utils.USerHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/student_login")
public class LoginController {
    @Autowired
    private IStudentService studentService;

    /*验证码
    * */
    private String s1="";

    /*学生登陆*/
    @PostMapping()
    public Result student_login(String studentId, String password, HttpServletRequest request, HttpServletResponse response, HttpSession session)
    {

        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Student::getStudentId,studentId);
        if(studentService.count(queryWrapper)>0)
        {
            queryWrapper.eq(Student::getPassword,password);
            if(studentService.count(queryWrapper)>0)
            {
                Student one = studentService.getOne(queryWrapper);

                session.setAttribute("user",one);
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
    @GetMapping()
    public Result student_login_captch()
    {

        CaptchaProduce captchaProduce = new CaptchaProduce();
        s1 = captchaProduce.captcha();
        return Result.ok(s1);
    }


}
