package core.wechat.po;

/**
 * 〈一句话功能简述〉<br>
 * 〈access_token〉
 *
 * @author lizhen
 * @create 2018/6/22
 * @since 1.0.0
 */
public class AccessToken {
    private String access_token;
    private int expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }
}