package org.fanlychie;

import org.fanlychie.dao.UserDao;
import org.fanlychie.model.User;
import org.fanlychie.service.MessageService;
import org.fanlychie.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AppTest {

    // mock 依赖
    @Mock
    private UserDao userDao;

    // mock 依赖
    @Mock
    private MessageService messageService;

    // 将依赖注入目标对象
    @InjectMocks
    private UserService userService;

    @Test
    public void testQuery() {
        // 当调用userDao.getByName时, mock一个User并返回
        when(userDao.getByName(anyString())).thenAnswer(invocation -> {
            User user = new User();
            user.setUsername(invocation.getArgument(0));
            user.setPassword("123456789");
            return user;
        });
        User user = userService.query("fanlychie");
        assertNotNull(user);
        System.out.println(user);
    }

    @Test
    public void testRegister() {
        // mock当调用userDao.add时, 返回真
        when(userDao.add(anyString(), anyString())).thenReturn(true);
        // mock当调用void方法时, 什么都不做
        // doNothing, doAnswer, doThrow, doCallRealMethod
        doNothing().when(messageService).send(anyString());
        boolean success = userService.register("fanlychie", "123456789");
        System.out.println(String.format("--- 注册结果: %b ---", success));
        assertTrue(success);
    }

}