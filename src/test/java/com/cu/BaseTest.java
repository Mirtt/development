package com.cu;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试类基类，用于导入spring和mybatis配置
 *
 * @authur Zyq
 * @create 2017/10/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring配置文件
@ContextConfiguration({ "classpath:spring/spring-mvc.xml", "classpath:spring/spring-mybatis.xml" })
public class BaseTest {
}
