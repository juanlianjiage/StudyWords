package com.example.english_test.utils;

import cn.hutool.core.bean.BeanUtil;
import com.example.english_test.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@Slf4j
/*登录拦截器
* */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  {
        HttpSession session = request.getSession();

        Object user = session.getAttribute("user");
        if (user == null) {
            response.setStatus(401);
            return false;
        }
        /*保存用户登录信息
        * */
        log.info("拦截器——————————————————————"+user.toString());
        UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);
        USerHolder.saveUSer(userDTO);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        /*删除用户登录信息*/
        //TODO 用户退出登录，清除session和线程
//        request.getSession().removeAttribute("user");
//        USerHolder.removeUser();
    }
}
