package com.example.english_test.service.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.english_test.dto.UserDTO;
import com.example.english_test.mapper.UserDTOMapper;
import com.example.english_test.service.IUserDTOService;
import org.springframework.stereotype.Service;

@Service
public class UserDTOServiceImpl extends ServiceImpl<UserDTOMapper,UserDTO> implements IUserDTOService {
}
