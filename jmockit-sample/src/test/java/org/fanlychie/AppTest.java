package org.fanlychie;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import org.fanlychie.dao.UserDao;
import org.fanlychie.model.User;
import org.fanlychie.service.MessageService;
import org.fanlychie.service.UserService;
import org.junit.Assert;
import org.junit.Test;

public class AppTest {

    // 测试的目标对象
    @Tested
    private UserService userService;

    @Test
    public void testQuery(@Injectable UserDao userDao /** 注入目标对象所依赖的对象 */) {
        // 录制行为
        new Expectations() {
            {
                // mock 依赖对象的行为
                userDao.getByName(anyString);
                User user = new User();
                user.setUsername("fanlychie");
                user.setPassword("123456789");
                // 行为结果
                result = user;
            }
        };
        // 回放
        User user = userService.query("any string");
        // 验证
        Assert.assertNotNull(user);
        System.out.println(user);
    }

    @Test
    public void testRegister(@Injectable UserDao userDao, @Injectable MessageService messageService) {
        new Expectations() {
            {
                // 存储任何数据
                userDao.add(anyString, anyString);
                // mock存储结果返回真
                result = true;
                // 发送任何消息
                messageService.send(anyString);
                // 该接口返回空, 无需mock调用结果
            }
        };
        boolean success = userService.register("name", "paswd");
        System.out.println(String.format("--- 注册结果: %b ---", success));
        Assert.assertTrue(success);
    }

}