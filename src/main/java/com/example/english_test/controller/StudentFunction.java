package com.example.english_test.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.english_test.dto.Result;
import com.example.english_test.dto.stuUnkownWord;
import com.example.english_test.entity.StuSelfTest;
import com.example.english_test.entity.UnknowWord;
import com.example.english_test.mapper.WordMapper;
import com.example.english_test.service.IStuSelfTestService;
import com.example.english_test.service.IUnknowWordService;
import com.example.english_test.service.IWordService;
import com.example.english_test.utils.USerHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/studentFunction")
//TODO 学生功能controller
public class StudentFunction {

    @Resource
    private WordMapper wordMapper;
    @Resource
    private IUnknowWordService iUnknowWordService;
    @Resource
    private IStuSelfTestService iStuSelfTestService;
    /*查询生词本
    * */

    @PostMapping("/unkonwlist")
    public Result select_unknowwords()
    {

        String studentId = USerHolder.getUser().getStudentId();
        List<stuUnkownWord> allstuUnkownWord = wordMapper.getAllstuUnkownWord(studentId);
        if(allstuUnkownWord.size()<=0) {
            return Result.fail("生词集为空，请先添加生词！");
        }
        return Result.ok(allstuUnkownWord);
    }
    /*自测成绩查询
    * */
    @PostMapping("/self_Test")
    public Result select_selfTest()
    {

        String studentId = USerHolder.getUser().getStudentId();
        QueryWrapper<StuSelfTest> queryWrapper = new QueryWrapper<>();

        queryWrapper
                .eq("student_id",studentId).orderByDesc("time");

        List<StuSelfTest> stuSelfTestList = iStuSelfTestService.list(queryWrapper);

        if (stuSelfTestList.size()<=0)
        {
            return Result.fail("自测成绩为空，请前去学习！");
        }
        return Result.ok(stuSelfTestList);
    }
}
