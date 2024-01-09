//package com.example.english_test.mongodb;
//
//
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.example.english_test.dto.Result;
//import com.example.english_test.dto.UnkonwWord;
//import com.example.english_test.dto.UserDTO;
//import com.example.english_test.entity.Cet4Word;
//import com.example.english_test.entity.Student;
//import com.example.english_test.entity.StudentReview;
//import com.example.english_test.entity.WordsSelect;
//import com.example.english_test.mapper.StudentMapper;
//import com.example.english_test.service.IStuReviewWordsService;
//import com.example.english_test.service.IUnknowWordService;
//import com.example.english_test.utils.USerHolder;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpSession;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import static com.example.english_test.service.serviceimpl.Cet4WordServiceImpl.getWordsSelects;
//
//
//@Slf4j
//@RestController
//    @RequestMapping("/mongo/student")
//    public class MongoStudentWordsTest {
//
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//    @Autowired
//    private IStuReviewWordsService stuReviewWordsService;
//    @Autowired
//    private StudentMapper studentMapper;
//    @Autowired
//    private IUnknowWordService iUnknowWordService;
//    /*
//    * 学习四级单词，数量20个
//    *
//    * */
//    @GetMapping("/cet4Study")
//    public Result studyCet4Word()
//    {
//        UserDTO user = USerHolder.getUser();
//        int studyed = user.getStudyed();
//        LambdaQueryWrapper<Cet4Word> queryWrapper = new LambdaQueryWrapper<>();
//
//        queryWrapper.last("limit "+studyed+",20");
//
//        Query query = new Query();
//        query.skip(studyed).limit(20);
//        List<Cet4Word> cet4Words = mongoTemplate.find(query,Cet4Word.class,"cet4Word");
//        ArrayList<WordsSelect> wordsSelects = getWordsSelects(cet4Words);
//        return Result.ok(wordsSelects);
//    }
//
//
//
//    /*学生添加复习单词
//    * */
//    @Transactional
//    @PostMapping("/wordstest/review")
//    public Result addReview(HttpSession session){
//      //获取学生id
//        UserDTO user = USerHolder.getUser();
//        String studentId = user.getStudentId();
//        //获取添加时间
//        StudentReview studentReview = new StudentReview();
//        studentReview.setStudentId(studentId);
//        studentReview.setLevel(user.getLevel());
//        studentReview.setAddTime(LocalDateTime.now());
//        studentReview.setBeginId(user.getStudyed());
//        studentReview.setEndId(user.getStudyed()+20);
//        int a[]={0,1,3,6,15};
//        for (int i : a) {
//            studentReview.setDay(i);
//            stuReviewWordsService.save(studentReview);
//        }
//
//        //更新session
//        Student student = (Student) session.getAttribute("user");
//        session.removeAttribute("user");
//        student.setStudyed(user.getStudyed()+20);
//        session.setAttribute("user",student);
//        //更新student studyed字段
//        studentMapper.updateStuStudyed(student.getStudyed(),student.getStudentId());
//        return Result.ok("恭喜您成功学习了20个单词，是否要对本组单词进行拼写？");
//    }
//
//    /*学生添加,学生生词表
//    * */
//    @PostMapping("/unknownWords")
//    @Async
//    public Result addstudentWords(@RequestBody UnkonwWord unknow){
//
//
//        UserDTO user = USerHolder.getUser();
//        String studentId = user.getStudentId();
//
//        mongoTemplate.insert(unknow,studentId);
//        return  Result.ok();
//    }
//}
