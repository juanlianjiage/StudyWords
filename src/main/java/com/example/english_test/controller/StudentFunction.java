package com.example.english_test.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.english_test.dto.Result;
import com.example.english_test.dto.UserDTO;
import com.example.english_test.dto.stuUnkownWord;
import com.example.english_test.entity.*;
import com.example.english_test.mapper.WordMapper;
import com.example.english_test.service.*;
import com.example.english_test.service.serviceimpl.Cet4WordServiceImpl;
import com.example.english_test.utils.USerHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.wrapper.BaseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static com.example.english_test.utils.UserInfo.TODAY_NEED_REVIEW;

@RestController
@RequestMapping("/studentFunction")
@Slf4j
//TODO 学生功能controller
public class StudentFunction {
    @Resource
    private IStudentService studentService;
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
    @Resource
    private IUnknowWordService iUnknowWordService;
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
        //判断本次登录是否与上次登录相差一天
        String studentId = USerHolder.getUser().getStudentId();
        LocalDateTime loginTime = USerHolder.getUser().getLoginTime();

        LambdaQueryWrapper<UserDTO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserDTO::getStudentId,studentId);
        UserDTO lastLogin = userDTOService.getOne(wrapper);

        LocalDateTime lastLoginTime = lastLogin.getLoginTime();
        long betweenDay = ChronoUnit.DAYS.between(lastLoginTime,loginTime);
        LambdaQueryWrapper<StudentReview> queryWrapper = new LambdaQueryWrapper<>();
        int reviewBegin;
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

    /*学生移除生词
    * */
    @GetMapping("/removeUnkonwWord")
    public Result removeUnkonwWords(String word){

        LambdaQueryWrapper<Cet4Word> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Cet4Word::getWordSpell,word);
        Cet4Word one = iCet4WordService.getOne(queryWrapper);
        Integer id = one.getWordId();
        LambdaQueryWrapper<UnknowWord> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(UnknowWord::getWordId,id);

        boolean remove = iUnknowWordService.remove(queryWrapper1);

        if (remove)
        {
            return Result.ok("移除成功！");
        }
        return Result.fail("错误！");

    }
    @GetMapping("/user")
    public Result getUserDTO()
    {
        String studentId = USerHolder.getUser().getStudentId();
        LocalDateTime loginTime = USerHolder.getUser().getLoginTime();

        LambdaQueryWrapper<UserDTO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserDTO::getStudentId,studentId);
        UserDTO lastLogin = userDTOService.getOne(wrapper);

        LocalDateTime lastLoginTime = lastLogin.getLoginTime();
        long betweenDay = ChronoUnit.DAYS.between(lastLoginTime,loginTime);
        LambdaQueryWrapper<StudentReview> queryWrapper = new LambdaQueryWrapper<>();


        if (betweenDay>=1)
        {
            //查询review中day为0的单词，并且将该用户所有需要复习的单词day都减1
            queryWrapper.eq(StudentReview::getStudentId,studentId).eq(StudentReview::getDay,0);
            List<StudentReview> list = iStuReviewWordsService.list(queryWrapper);
            if (TODAY_NEED_REVIEW==0){
                TODAY_NEED_REVIEW+=20* list.size();
            }


        }
        UserDTO user = USerHolder.getUser();

        user.setTodayNeedReview(TODAY_NEED_REVIEW);
        return Result.ok(user);
    }
    @GetMapping("/chanceInfo")
    public Result changeinfo(String level,int count)
    {

        log.info("level-----------"+level,"count-------"+count);
        UserDTO userDTO = USerHolder.getUser();
        userDTO.setCount(count);
        userDTO.setLevel(level);
        Student student = BeanUtil.copyProperties(userDTO, Student.class);





        LambdaUpdateWrapper<Student> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Student::getStudentId,student.getStudentId());
        updateWrapper.set(Student::getLevel,student.getLevel());
        updateWrapper.set(Student::getCount,student.getCount());
        studentService.update(updateWrapper);
        return Result.ok("修改成功！");
    }
}
