package base.beanCopy;

import java.io.Serializable;

/**
 * @Author:weilu
 * @Date:2020/5/22 17:46
 * @Description: ${Description}
 */
public class B {
    private D id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class D implements Serializable{}
