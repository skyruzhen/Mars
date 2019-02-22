package core.wechat.menu;

/**
 * 〈一句话功能简述〉<br>
 * 〈自定义菜单〉
 *
 * @author lizhen
 * @create 2018/6/22
 * @since 1.0.0
 */
public class Button {
    private String type;
    private String name;
    private Button[] sub_button;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Button[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(Button[] sub_button) {
        this.sub_button = sub_button;
    }
}