package base;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * @Author:weilu
 * @Date: 2019/3/18 17:38
 */
@ContextConfiguration(locations = {"classpath:aop.xml"})
public class BaseTestNG extends AbstractTestNGSpringContextTests {
}
