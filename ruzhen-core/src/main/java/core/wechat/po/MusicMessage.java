package core.wechat.po;

/**
 * 〈一句话功能简述〉<br>
 * 〈音频消息〉
 *
 * @author lizhen
 * @create 2018/6/25
 * @since 1.0.0
 */
public class MusicMessage extends BaseMessage{
    private Music music;

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }
}