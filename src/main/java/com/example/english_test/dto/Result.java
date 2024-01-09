package com.example.english_test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Boolean success;
    private String errorMsg;
    private Object data;
    private Object time1;
    private Long total;
    public static Result ok()
    {
        return new Result(true,null,null,null,null);

    }

    public static Result ok(Object data)
    {
        return new Result(true,null,data,null,null);
    }

    public static Result ok(List<?> data,Long total)
    {
        return new Result(true,null,data,null,total);
    }
    public static Result fail(String errorMsg)
    {
        return new Result(false,errorMsg,null,null,null);
    }
    public static Result ok(List<?> data,List<?> time1)
    {
        return new Result(true,null,data,time1,null);
    }
}
