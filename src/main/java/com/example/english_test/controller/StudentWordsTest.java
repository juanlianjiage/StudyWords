package com.example.english_test.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.english_test.dto.Result;
import com.example.english_test.dto.UnkonwWord;
import com.example.english_test.dto.UserDTO;
import com.example.english_test.entity.*;
import com.example.english_test.mapper.StudentMapper;
import com.example.english_test.service.*;

import com.example.english_test.service.serviceimpl.UnknowWordServiceImpl;
import com.example.english_test.utils.USerHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.object.UpdatableSqlQuery;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import java.time.LocalDateTime;
import java.util.*;


@Slf4j
@RestController
    @RequestMapping("/student")
    public class StudentWordsTest {

    @Autowired
    private IStuSelfTestService iStuSelfTestService;
    @Autowired
    private IUnknowWordService iUnknowWordService;
    @Autowired
    private UnknowWordServiceImpl unknowWordService;

    @Autowired
    private IStuReviewWordsService stuReviewWordsService;

    @Autowired
    private StudentMapper studentMapper;
    /*背四级单词
    * */
    @Autowired
    private ICet4WordService iCet4WordService;

    /*
    * 学习四级单词，数量20个
    *
    * */
    @GetMapping("/cet4Study")
    public Result studyCet4Word()
    {
        return iCet4WordService.get_CET4Words_List();
    }


    /*接受前台单词选择的结果

    * */
//    @PostMapping("/wordstest/submit")
//    public Result TestReslut(@RequestBody List<WordsSelectResult> wordsSelectsResult){
//        int count=wordsSelectsResult.size();
//        int right=0;
//
//        for (int i = 0; i < count; i++) {
//               if (wordsSelectsResult.get(i).getWordMeaning().equals(wordsSelectsResult.get(i).getSelectMeaning()))
//            {
//                right++;
//            }
//        }
//
//        /*插入学生自测表self_Test
//        * */
//        float accuracy_rate=Float.valueOf(right)/Float.valueOf(count);
//        DecimalFormat decimalFormat = new DecimalFormat("#.00");
//        System.out.println(wordsSelectsResult);
//        String format = decimalFormat.format(accuracy_rate*100);
//        //1.从Threalocal中获取学生id
//        String studentId = USerHolder.getUser().getStudentId();
//        StuSelfTest stuSelfTest = new StuSelfTest();
//        stuSelfTest.setStudentId(studentId);
//
//        stuSelfTest.setTime(LocalDateTime.now());
//        stuSelfTest.setScore(Float.valueOf(format));
//        stuSelfTest.setVocabulary(count);
//        //保存到学生自测表
//        iStuSelfTestService.save(stuSelfTest);
//
//        return Result.ok("您的正确率为"+format+"%");
//    }

    /*学生添加复习单词
    * */
    @Transactional
    @PostMapping("/wordstest/review")
    public Result addReview(HttpSession session){
      //获取学生id
        UserDTO user = USerHolder.getUser();
        String studentId = user.getStudentId();
        //获取添加时间
        StudentReview studentReview = new StudentReview();
        studentReview.setStudentId(studentId);
        studentReview.setLevel(user.getLevel());
        studentReview.setAddTime(LocalDateTime.now());
        studentReview.setBeginId(user.getStudyed());
        studentReview.setEndId(user.getStudyed()+20);
        int a[]={0,1,3,6,15};
        for (int i : a) {
            studentReview.setDay(i);
            stuReviewWordsService.save(studentReview);
        }

        //更新session
        Student student = (Student) session.getAttribute("user");
        session.removeAttribute("user");
        student.setStudyed(user.getStudyed()+20);
        session.setAttribute("user",student);
        //更新student studyed字段
        studentMapper.updateStuStudyed(student.getStudyed(),student.getStudentId());
        return Result.ok("恭喜您成功学习了20个单词，是否要对本组单词进行拼写？");
    }

    /*学生添加,学生生词表
    * */
    @PostMapping("/unknownWords")
    @Async
    public Result addstudentWords(@RequestBody UnkonwWord unknow){

        return  iUnknowWordService.saveUnkonwWords(unknow);
    }
}
