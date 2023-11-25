package com.example.english_test.config;


import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.concurrent.*;
@Configuration
@EnableCaching
public class ThreadPoolConfig {
    @Bean("pool")
    public Executor doSomethingExecurot()
    {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                3,//线程核心数量
                16,//线程池大小
                60,//空闲时间
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2),//队列大小
                Executors.defaultThreadFactory(),//构造线程的方法
                new ThreadPoolExecutor.AbortPolicy()//阻塞队列的

        );
        return pool;
    }
}
