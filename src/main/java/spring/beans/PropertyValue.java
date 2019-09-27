package spring.beans;

/**
 * @Title PropertyValue
 * @Description 属性值依赖实体
 * @Author liuxi58
 * @Date 2019/9/27 16:39
 **/
public class PropertyValue {
    private String name;
    private Object value;

    public PropertyValue(String name, Object value) {
        super();
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
