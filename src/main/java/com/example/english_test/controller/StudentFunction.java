package com.example.english_test.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.english_test.dto.Result;
import com.example.english_test.dto.stuUnkownWord;
import com.example.english_test.entity.Cet4Word;
import com.example.english_test.entity.StuSelfTest;
import com.example.english_test.entity.StudentReview;
import com.example.english_test.entity.WordsSelect;
import com.example.english_test.mapper.WordMapper;
import com.example.english_test.service.ICet4WordService;
import com.example.english_test.service.IStuReviewWordsService;
import com.example.english_test.service.IStuSelfTestService;
import com.example.english_test.service.IUnknowWordService;
import com.example.english_test.service.serviceimpl.Cet4WordServiceImpl;
import com.example.english_test.utils.USerHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @Resource
    private ICet4WordService iCet4WordService;
    @Resource
    private IStuReviewWordsService iStuReviewWordsService;
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

    /*查询需要复习的单词
    * */
    @PostMapping("/reviewWords")
    public  Result review_Words()
    {
        String studentId = USerHolder.getUser().getStudentId();
        LambdaQueryWrapper<StudentReview> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StudentReview::getStudentId,studentId);
        List<StudentReview> list = iStuReviewWordsService.list(queryWrapper);
        int reviewBegin=0;
        int reviewEnd=0;
        if (list.isEmpty())
        {
            return Result.ok("目前没有需要复习的单词哦，请先学习新的单词!");
        }
        else {
            reviewBegin=list.get(0).getBeginId();
            reviewEnd=list.get(0).getEndId();
            for (StudentReview review : list) {
                if (reviewBegin>review.getBeginId())
                {
                    reviewBegin=review.getBeginId();
                }
                if (reviewEnd<review.getEndId()){
                    reviewEnd=review.getEndId();
                }

            }

            LambdaQueryWrapper<Cet4Word> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.last("limit "+reviewBegin+", "+reviewEnd);
            List<Cet4Word> reviewCet4Words = iCet4WordService.list(queryWrapper1);
            int size = reviewCet4Words.size();
            ArrayList<WordsSelect> wordsSelects = Cet4WordServiceImpl.getWordsSelects(reviewCet4Words);
            return Result.ok(wordsSelects);
        }
    }
}
