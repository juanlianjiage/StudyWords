package com.example.english_test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.english_test.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDTOMapper extends BaseMapper<UserDTO> {
}
