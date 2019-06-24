package com.siruiman.crosslogistics.zhangzhanwei;

import com.siruiman.crosslogistics.model.dto.AdminUserLoginDto;
import com.siruiman.crosslogistics.service.AdminUserLoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 张占伟
 * @date 2019/1/19 11:48
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginTest {
    @Autowired
    private AdminUserLoginService adminUserLoginService;
    @Test
    public void login(){
        AdminUserLoginDto user = adminUserLoginService.selectByName("asdaadsvsd");
        System.out.println(user.getUserName());
    }
}
