package com.qfedu.service.impl;

import com.qfedu.dao.TransferDao;
import com.qfedu.dao.UserDao;
import com.qfedu.entity.Transfer;
import com.qfedu.entity.User;
import com.qfedu.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferServiceImpl implements TransferService {

    @Autowired(required = false)
    private TransferDao transferDao;

    @Autowired(required = false)
    private UserDao userDao;

    @Override
    public void transfer(String sourceCode, String descCode, Double money) {

        User sourceUser = userDao.findByCode(sourceCode);
        User user = userDao.findByCode(descCode);
        if(sourceCode.equals(descCode)){
            throw new RuntimeException("转账卡号不能相同");
        }
        if(user == null){
            throw new RuntimeException("转账卡号输入有误");
        }
        if(sourceUser.getBalance() < money){
            throw new RuntimeException("余额不足");
        }
        Transfer sTransfer = new Transfer();
        sTransfer.setUid(sourceUser.getUid());
        sTransfer.setMoney(0 - money);
        sTransfer.setBalance(sourceUser.getBalance() - money);
        transferDao.add(sTransfer);

        Transfer dTransfer = new Transfer();
        dTransfer.setUid(user.getUid());
        dTransfer.setMoney(money);
        dTransfer.setBalance(user.getBalance() + money);
        transferDao.add(dTransfer);





    }
}
