package com.example.english_test.controller;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.example.english_test.dto.Result;
import com.example.english_test.dto.UnkonwWords;
import com.example.english_test.dto.UserDTO;
import com.example.english_test.entity.*;
import com.example.english_test.service.IStuSelfTestService;
import com.example.english_test.service.IStudentService;
import com.example.english_test.service.IWordService;
import com.example.english_test.utils.USerHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Time;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
    @RequestMapping("/student")
    public class StudentWordsTest {
    @Resource
    private IWordService iWordService;
    @Autowired
    private IStuSelfTestService iStuSelfTestService;
    @GetMapping("/wordstest")
    public Result TestWordsList()
    {
        UserDTO user = USerHolder.getUser();
        log.info("------------------------------------"+user.toString());
        return iWordService.get_Words_List();
    }
    /*接受前台单词选择的结果

    * */
    @PostMapping("/wordstest/submit")
    public Result TestReslut(@RequestBody List<WordsSelectResult> wordsSelectsResult){
        int count=wordsSelectsResult.size();
        int right=0;

        for (int i = 0; i < count; i++) {
               if (wordsSelectsResult.get(i).getWordMeaning().equals(wordsSelectsResult.get(i).getSelectMeaning()))
            {
                right++;
            }
        }
        //TODO 插入学生自测表self_Test，需要学生id作为外键，后续实现
        /*插入学生自测表self_Test
        * */

        float accuracy_rate=Float.valueOf(right)/Float.valueOf(count);
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        System.out.println(wordsSelectsResult);
        String format = decimalFormat.format(accuracy_rate*100);
        //1.从Threalocal中获取学生id
        String studentId = USerHolder.getUser().getStudentId();
        StuSelfTest stuSelfTest = new StuSelfTest();
        stuSelfTest.setStudentId(studentId);

        stuSelfTest.setTime(LocalDateTime.now());
        stuSelfTest.setScore(Float.valueOf(format));
        stuSelfTest.setVocabulary(count);
        //保存到学生自测表
        iStuSelfTestService.save(stuSelfTest);

        return Result.ok("您的正确率为"+format+"%");
    }
    /*学生添加,学生生词表
    * */
    @PostMapping("/unknownWords")
    public Result addstudentWords(@RequestBody List<UnkonwWords> unknows){
        log.info("----------"+unknows);

        return Result.ok();
    }
}
