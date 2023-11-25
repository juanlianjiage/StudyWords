package com.example.english_test.service.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.english_test.entity.StudentReview;
import com.example.english_test.mapper.ReviewWordsRecord;
import com.example.english_test.service.IStuReviewWordsService;
import org.springframework.stereotype.Service;

@Service
public class StuReviewWordsServiceIpml extends ServiceImpl<ReviewWordsRecord,StudentReview> implements IStuReviewWordsService {
}
