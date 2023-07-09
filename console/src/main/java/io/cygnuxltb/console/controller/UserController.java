package io.cygnuxltb.console.controller;

import io.cygnuxltb.console.controller.base.ResponseBean;
import io.cygnuxltb.console.controller.base.ResponseStatus;
import io.cygnuxltb.console.service.UserService;
import io.mercury.common.http.MimeType;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户服务
 */
@RestController
@RequestMapping(path = "/user", produces = MimeType.APPLICATION_JSON_UTF8)
public class UserController {

    private static final Logger log = Log4j2LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService service;

    /**
     * 用户登陆
     *
     * @param sign     用户名/邮箱/手机号
     * @param password 密码
     * @return boolean
     */
    @PostMapping(path = "/signin")
    public boolean signIn(String sign, String password) {
        return service.signIn(sign, password);
    }

    /**
     * 用户注册, 当前不支持新用户注册
     *
     * @param sign     标识
     * @param type     标识类型
     * @param password 密码
     * @return ResponseBean
     */
    @PostMapping(path = "/signup")
    public ResponseBean signUp(String sign, int type, String password) {
        return ResponseStatus.FORBIDDEN.response();
    }

}
