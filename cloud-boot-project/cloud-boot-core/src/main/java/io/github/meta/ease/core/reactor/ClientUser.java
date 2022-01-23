package io.github.meta.ease.core.reactor;

/**
 * @author leijian
 * @version 1.0
 * @date 2022/1/22 22:07
 */
public class ClientUser {
    private String name;

    private String desc;

    public ClientUser(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
