package com.example.english_test.utils;

import com.example.english_test.dto.UserDTO;

public class USerHolder {
    private static final ThreadLocal<UserDTO> tl=new ThreadLocal<>();
    public static void saveUSer(UserDTO user)
    {
        tl.set(user);
    }
    public static UserDTO getUser()
    {
        return tl.get();
    }
    public static void removeUser()
    {
        tl.remove();
    }
}
