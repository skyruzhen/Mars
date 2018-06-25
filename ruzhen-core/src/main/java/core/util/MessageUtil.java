package core.util;

import com.thoughtworks.xstream.XStream;
import core.po.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 〈一句话功能简述〉<br>
 * 〈XML格式转换〉
 *
 * @author lizhen
 * @create 2018/6/21
 * @since 1.0.0
 */
public class MessageUtil {
    public static final String MESSAGE_TEXT="text";
    public static final String MESSAGE_IAMGE="image";
    public static final String MESSAGE_VOICE="voice";
    public static final String MESSAGE_VIDEO="video";
    public static final String MESSAGE_SUBSCIRBE="subscribe";
    public static final String MESSAGE_EVENT = "event";
    public static final String MESSAGE_EVENT_EVENT = "event";
    public static final String MESSAGE_VIEW = "view";
    public static final String MESSAGE_SCANCODE = "scancode_push";
    public static final String MESSAGE_LOCATION = "location";
    private static final String MESSAGE_NEWS ="news" ;

    /**
     * xml转换为map集合
     * @param request
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {
        Map<String, String> map = new HashMap<String, String>();

        SAXReader reader = new SAXReader();
        InputStream ins = request.getInputStream();
        Document doc =reader.read(ins);
        Element root = doc.getRootElement();
        List<Element> list = root.elements();
        for(Element e :list){
            map.put(e.getName(), e.getText());
        }
        ins.close();
        return map;
    }

    /**
     * 转换为xml
     * @param textMessage
     * @return
     */
    public static String textMassageToXml(TextMessage textMessage){
        XStream xStream = new XStream();
        xStream.alias("xml",textMessage.getClass());
        return xStream.toXML(textMessage);
    }

    public static String menuText(){
        StringBuffer sb = new StringBuffer();
        sb.append("欢迎您的关注，请按照菜单提示进行操作：\n\n");
        sb.append("1:课程介绍\n");
        sb.append("2:图文介绍");
        return sb.toString();
    }

    public static String initText(String ToUserName, String fromUserName, String Content){
        TextMessage text = new TextMessage();
        text.setFromUserName(ToUserName);
        text.setToUserName(fromUserName);
        text.setMsgType(MESSAGE_TEXT);
        text.setCreateTime(new Date().getTime());
        text.setContent(Content);
        return textMassageToXml(text);
    }

    /**
     * 图文消息转为xml
     * @param textMessage
     * @return
     */
    public static String newsMassageToXml(NewsMessage textMessage){
        XStream xStream = new XStream();
        xStream.alias("xml",textMessage.getClass());
        xStream.alias("item",new News().getClass());
        return xStream.toXML(textMessage);
    }

    public static String imageMessageToXml(ImageMessage imageMessage){
        XStream xStream = new XStream();
        xStream.alias("xml", imageMessage.getClass());
        return xStream.toXML(imageMessage);
    }

    public static String musicMessageToXml(MusicMessage musicMessage){
        XStream xStream = new XStream();
        xStream.alias("xml", musicMessage.getClass());
        return xStream.toXML(musicMessage);
    }


    public static String initNewsMessage(String toUserName, String fromUserName){
        String message = null;
        List<News> newsList = new ArrayList<News>();
        NewsMessage newsMessage = new NewsMessage();

        News news = new News();
        news.setTitle("公众号介绍");
        news.setDescription("学习测试阶段。");
        news.setPicUrl("http://ruzhen.free.ngrok.cc/ruzhen-app/images/Koala.jpg");
        news.setUrl("www.imooc.com");

        newsList.add(news);
        newsMessage.setToUserName(fromUserName);
        newsMessage.setFromUserName(toUserName);
        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setMsgType(MESSAGE_NEWS);
        newsMessage.setArticles(newsList);
        newsMessage.setArticleCount(newsList.size());

        message = newsMassageToXml(newsMessage);

        return message;
    }

    public static String initImageMessage(String toUserName, String fromUserName){
        String message = null;
        Image image = new Image();
        image.setMediaId("7mssFNHM2OUbLe8AW1EGJqkvwuhq6rcKNKG6dPpoDE7uY_Bh9AmtESkoZOnbpies");

        ImageMessage imageMessage = new ImageMessage();
        imageMessage.setFromUserName(toUserName);
        imageMessage.setToUserName(fromUserName);
        imageMessage.setImage(image);
        imageMessage.setMsgType(MESSAGE_IAMGE);
        imageMessage.setCreateTime(new Date().getTime());

        return imageMessageToXml(imageMessage);
    }

    public static String initMusicMessage(String toUserName, String fromUserName){
        String message = null;
        Music music = new Music();
        music.setThumbMediaId("7mssFNHM2OUbLe8AW1EGJqkvwuhq6rcKNKG6dPpoDE7uY_Bh9AmtESkoZOnbpies");

        MusicMessage musicMessage = new MusicMessage();
        musicMessage.setFromUserName(toUserName);
        musicMessage.setToUserName(fromUserName);
        musicMessage.setMusic(music);
        musicMessage.setMsgType(MESSAGE_IAMGE);
        musicMessage.setCreateTime(new Date().getTime());

        return musicMessageToXml(musicMessage);
    }

}