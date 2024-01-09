package com.example.english_test.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.english_test.dto.Result;
import com.example.english_test.dto.UserDTO;
import com.example.english_test.dto.stuUnkownWord;
import com.example.english_test.entity.Cet4Word;
import com.example.english_test.entity.StuSelfTest;
import com.example.english_test.entity.StudentReview;
import com.example.english_test.entity.WordsSelect;
import com.example.english_test.mapper.WordMapper;
import com.example.english_test.service.*;
import com.example.english_test.service.serviceimpl.Cet4WordServiceImpl;
import com.example.english_test.utils.USerHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/studentFunction")
//TODO 学生功能controller
public class StudentFunction {

    @Resource
    private WordMapper wordMapper;
    @Resource
    private IStuSelfTestService iStuSelfTestService;
    /*查询生词本
    * */
    @Resource
    private IUserDTOService userDTOService;

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

    //TODO /*查询需要复习的单词个数

    @PostMapping("/reviewWords")
    public  Result review_Words()
    {


        String studentId = USerHolder.getUser().getStudentId();
        //判断本次登录是否与上次登录相差一天
        LocalDateTime loginTime = USerHolder.getUser().getLoginTime();

        LambdaQueryWrapper<UserDTO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserDTO::getStudentId,studentId);
        UserDTO lastLogin = userDTOService.getOne(wrapper);

        LocalDateTime lastLoginTime = lastLogin.getLoginTime();
        long betweenDay = ChronoUnit.DAYS.between(lastLoginTime,loginTime);
        LambdaQueryWrapper<StudentReview> queryWrapper = new LambdaQueryWrapper<>();
        int reviewBegin;
        int reviewEnd;
        ArrayList<LocalDateTime> addTimeList=new ArrayList<>();
        ArrayList<ArrayList<WordsSelect>> reviewWordList = new ArrayList<>();
        if (betweenDay>=1)
        {
            //查询review中day为0的单词，并且将该用户所有需要复习的单词day都减1
            queryWrapper.eq(StudentReview::getStudentId,studentId).eq(StudentReview::getDay,0);
            List<StudentReview> list = iStuReviewWordsService.list(queryWrapper);
            if (list.isEmpty())
            {
                return Result.ok("目前没有需要复习的单词哦，请先学习新的单词!");
            }
            else {

                for (StudentReview review : list) {

                    reviewBegin=review.getBeginId();
                    addTimeList.add(review.getAddTime());
                    LambdaQueryWrapper<Cet4Word> queryWrapper1 = new LambdaQueryWrapper<>();
                    queryWrapper1.last("limit "+reviewBegin+", "+20);
                    List<Cet4Word> reviewCet4Words = iCet4WordService.list(queryWrapper1);

                     reviewWordList.add( Cet4WordServiceImpl.getWordsSelects(reviewCet4Words));
                }

                return Result.ok(reviewWordList,addTimeList);
        }
        }else {
            return Result.ok("今天学完新的单词，明天可以来复习哦");
        }

        //TODO 获取学生学习的过程

    }
    //TODO 添加学生复习完单词后删除数据库中的记录
}
