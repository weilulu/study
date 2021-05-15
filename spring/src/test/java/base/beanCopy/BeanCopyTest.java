package base.beanCopy;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;

/**
 * @Author:weilu
 * @Date:2020/5/22 17:47
 * @Description: ${Description}
 */
public class BeanCopyTest {

    public static void main(String[] args) {
        A a = new A();
        a.setId(1);
        a.setName("a");
        B b = new B();
        BeanUtils.copyProperties(a,b);
        //{"name":"a"}不再有id的值，因为在类B中id的类型是D,而在A类里是integer类型，所以不会copy(底层的校验)
        System.out.println(JSON.toJSONString(b));
    }
}
