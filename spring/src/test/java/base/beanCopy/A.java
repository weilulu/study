package base.beanCopy;

import java.io.Serializable;

/**
 * @Author:weilu
 * @Date:2020/5/22 17:46
 * @Description: ${Description}
 */
public class A implements Serializable{
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
