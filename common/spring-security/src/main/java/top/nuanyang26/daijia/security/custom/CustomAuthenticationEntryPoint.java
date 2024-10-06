package top.nuanyang26.daijia.security.custom;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import top.nuanyang26.daijia.common.result.Result;
import top.nuanyang26.daijia.common.result.ResultCodeEnum;
import top.nuanyang26.daijia.common.util.ResponseUtil;


@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) {

        ResponseUtil.out(response, Result.build(null, ResultCodeEnum.ACCOUNT_ERROR));
    }
}
