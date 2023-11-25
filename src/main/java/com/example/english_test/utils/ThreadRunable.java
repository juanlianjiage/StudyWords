package com.example.english_test.utils;

import com.example.english_test.entity.UnknowWord;
import com.example.english_test.service.IUnknowWordService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ThreadRunable {
    public UnknowWord unknowWord;

    public ThreadRunable(UnknowWord unknowWord) {
        this.unknowWord = unknowWord;
    }

    public UnknowWord getUnknowWord() {
        return unknowWord;
    }
    public void setUnknowWord(UnknowWord unknowWord) {
        this.unknowWord = unknowWord;
    }

    @Resource
    private IUnknowWordService iUnknowWordService;


    @Async("pool")
    public void run() {
        System.out.println();
        System.out.println("第"+Thread.currentThread().getName()+"个线程开始执行！");
        iUnknowWordService.save(this.unknowWord);
        System.out.println("第"+Thread.currentThread().getName()+"个线程执行成功！");
    }
}
