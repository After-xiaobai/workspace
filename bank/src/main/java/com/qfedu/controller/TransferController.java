package com.qfedu.controller;

import com.qfedu.common.JsonResult;
import com.qfedu.entity.User;
import com.qfedu.service.TransferService;
import com.qfedu.utils.StrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class TransferController {

    @Autowired
    private TransferService transferService;


    @RequestMapping("/transfer.do")
    public JsonResult transfer(String otherCode, Double money, HttpSession session){

        User user = (User)session.getAttribute(StrUtils.LOGIN_INFO);
        if(user == null){
            throw new RuntimeException("还未登陆");
        }

        transferService.transfer(user.getBankCode(), otherCode, money);

        return new JsonResult(0, "转账成功");

    }

    // @ExceptionHandler 修饰处理异常的方法
    // 注解的参数表示要处理的异常，注解修饰的方法，要使用异常作为参数
//    @ExceptionHandler(Exception.class)
//    public JsonResult exception(Exception ex){
//
//        return new JsonResult(1, ex.getMessage());
//    }
}
