package core.util;

import com.thoughtworks.xstream.XStream;
import core.po.News;
import core.po.NewsMessage;
import core.po.TextMessage;
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
    public static final String MESSAGE_SUBSCIRBE="subscirbe";
    public static final String MESSAGE_EVENT = "event";

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
        sb.append("1:课程介绍");
        return sb.toString();
    }

    public static String initText(String ToUserName, String fromUserName, String Content){
        TextMessage text = new TextMessage();
        text.setFromUserName(ToUserName);
        text.setToUserName(fromUserName);
        text.setMsgType(MESSAGE_TEXT);
        text.setCreateTime(new Date().toString());
        text.setContent(Content);
        return textMassageToXml(text);
    }

    /**
     * 图文消息转为xml
     * @param textMessage
     * @return
     */
    public static String newsMassageToXml(TextMessage textMessage){
        XStream xStream = new XStream();
        xStream.alias("xml",textMessage.getClass());
        xStream.alias("item",new News().getClass());
        return xStream.toXML(textMessage);
    }

    public static String initNewsMessage(String toUserName, String fromUserName){
        String message = null;
        List<News> newsList = new ArrayList<News>();
        NewsMessage newsMessage = new NewsMessage();

        News news = new News();
        news.setTitle("公众号介绍");
        news.setDescription("学习测试阶段。");
        news.setPicUrl("");
    }

}