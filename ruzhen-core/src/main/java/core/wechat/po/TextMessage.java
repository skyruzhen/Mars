package core.wechat.po;

/**
 * 〈一句话功能简述〉<br>
 * 〈当普通微信用户向公众账号发消息时，微信服务器将POST消息的XML数据包到开发者填写的URL上。〉
 *
 * @author lizhen
 * @create 2018/6/21
 * @since 1.0.0
 */
public class TextMessage extends BaseMessage{
    private String Content;
    private String MsgId;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }
}