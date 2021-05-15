package base.beanCopy;

import java.io.Serializable;

/**
 * @Author:weilu
 * @Date:2020/5/22 17:47
 * @Description: ${Description}
 */
public class C<T extends Serializable> {
    private T id;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }
}
