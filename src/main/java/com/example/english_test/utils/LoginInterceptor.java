package com.example.english_test.utils;

import cn.hutool.core.bean.BeanUtil;
import com.example.english_test.dto.UserDTO;
import com.example.english_test.entity.Student;
import com.example.english_test.service.IStudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

import static com.example.english_test.utils.UserInfo.TODAY_NEED_REVIEW;

@Slf4j
/*登录拦截器
 * */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  {
        HttpSession session = request.getSession();

        UserDTO userDTO =(UserDTO) session.getAttribute("user");
        if (userDTO == null) {
            response.setStatus(401);
            return false;
        }
        /*保存用户登录信息
         * */
        userDTO.setLatestLoginTime(LocalDateTime.now());
        userDTO.setReviewed(0);
        userDTO.setTodayNeedReview(TODAY_NEED_REVIEW);
        USerHolder.saveUSer(userDTO);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        /*删除用户登录信息*/
//        request.getSession().removeAttribute("user");
//        USerHolder.removeUser();
    }
}
