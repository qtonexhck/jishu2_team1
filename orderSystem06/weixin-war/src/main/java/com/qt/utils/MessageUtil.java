package com.qt.utils;

import com.qt.model.*;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


/**
 * Created by defore on 16/4/3.
 */
public class MessageUtil {
    public static final String MESSAGE_NEWS = "news";
    public static final String MESSAGE_TEXT = "text";
    public static final String MESSAGE_IMAGE = "image";
    public static final String MESSAGE_VOICE = "voice";
    public static final String MESSAGE_VIDEO = "video";
    public static final String MESSAGE_LINK = "link";
    public static final String MESSAGE_LOCATION = "location";
    public static final String MESSAGE_EVENT = "event";
    public static final String MESSAGE_SUBSCRIBE = "subscribe";
    public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
    public static final String MESSAGE_CLICK = "CLICK";
    public static final String MESSAGE_VIEW = "VIEW";
    public static final String MESSAGE_SCANCODE = "scancode_push";

    /**
     * xml转成map类型
     * @param request
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public static Map<String, String> xmlToMap (HttpServletRequest request) throws IOException, DocumentException {
//    public static Map<String, String> xmlToMap (HttpServletRequest request) throws IOException, DocumentException {
        Map<String, String> map = new HashMap<String, String>();
        SAXReader reader = new SAXReader();
        //从request中获取输入流
        InputStream ins = request.getInputStream();
        Document doc = reader.read(ins);

        //获取xml的根元素
        Element root = doc.getRootElement();

        List<Element> list = root.elements();

        for(Element e : list) {
            map.put(e.getName(), e.getText());
        }

        ins.close();
        return map;
    }

    /**
     * 将文本消息对象转为xml
     * @param textMessage
     * @return
     */
    public static String textMessageToXml(TextMessage textMessage) {
        XStream xStream = new XStream();
        xStream.alias("xml", textMessage.getClass());
        return xStream.toXML(textMessage);
    }


    /**
     * 回复模板构建
     * @param toUserName
     * @param fromUserName
     * @param content
     * @return
     */
    public static String initText(String toUserName, String fromUserName, String content) {
        TextMessage text = new TextMessage();
        text.setToUserName(fromUserName);
        text.setFromUserName(toUserName);
        text.setMsgType(MessageUtil.MESSAGE_TEXT);
        text.setCreateTime(new Date().getTime());
        text.setContent(content);
        return textMessageToXml(text);
    }

    /**
     * 主菜单
     * @return
     */
    public static String menuText() {
        StringBuffer sb = new StringBuffer();
        sb.append("欢迎您的关注,请按照菜单提示进行操作:\n");
        sb.append("1.个人介绍\n");
        sb.append("2.我的收藏\n");
        sb.append("回复?调出此菜单");
        return sb.toString();
    }


    public static String firstMenu() {
        StringBuffer sb = new StringBuffer();
        sb.append("我叫defore");
        return sb.toString();
    }

    public static String secondMenu() {
        StringBuffer sb = new StringBuffer();
        sb.append("http://www.baidu.com");
        return sb.toString();
    }

    /**
     * 图文消息转为xml
     * @param newsMessage
     * @return
     */
    public static String newsMessageToXml(NewsMessage newsMessage) {
        XStream xStream = new XStream();
        xStream.alias("xml", newsMessage.getClass());
        xStream.alias("item", new News().getClass());
        return xStream.toXML(newsMessage);
    }

    /**
     * 图片消息转为xml
     * @param imageMessage
     * @return
     */
    public static String imageMessageToXml(ImageMessage imageMessage){
        XStream xstream = new XStream();
        xstream.alias("xml", imageMessage.getClass());
        return xstream.toXML(imageMessage);
    }


    /**
     * 图文消息的组装
     * @param toUserName
     * @param fromUserName
     * @return
     */
    public static String initNewsMessage(String toUserName, String fromUserName) {
        String message = null;
        List<News> newsList = new ArrayList<News>();
        NewsMessage newsMessage = new NewsMessage();

        //设置图文消息内容
        News news = new News();
        news.setTitle("我的个人介绍");
        news.setDescription("大家猴,我叫defore");
        news.setPicUrl("http://defore.ngrok.cc/resource/images/jugg.jpg");
        news.setUrl("http://www.baidu.com");
        newsList.add(news);

        //设置图文消息属性
        newsMessage.setToUserName(fromUserName);
        newsMessage.setFromUserName(toUserName);
        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setMsgType(MESSAGE_NEWS);
        newsMessage.setArticles(newsList);
        newsMessage.setArticleCount(newsList.size());

        //转成xml
        message = newsMessageToXml(newsMessage);
        return message;
    }

    public static String initImageMessage(String toUserName, String fromUserName) {
        String message = null;
        Image image = new Image();
        image.setMediaId("noRtDqB5p--14kS8bguAlkHWcbp9mIZIWOW53nwW3YlSpVXp_j3q4cDd9ApgBKz0");
        ImageMessage imageMessage = new ImageMessage();
        imageMessage.setFromUserName(toUserName);
        imageMessage.setToUserName(fromUserName);
        imageMessage.setMsgType(MESSAGE_IMAGE);
        imageMessage.setCreateTime(new Date().getTime());
        imageMessage.setImage(image);
        message = imageMessageToXml(imageMessage);
        return message;
    }
}
