package core.wechat.po;

/**
 * 〈一句话功能简述〉<br>
 * 〈图片信息〉
 *
 * @author lizhen
 * @create 2018/6/25
 * @since 1.0.0
 */
public class ImageMessage extends BaseMessage{
    private Image Image;

    public Image getImage() {
        return Image;
    }

    public void setImage(Image image) {
        this.Image = image;
    }
}