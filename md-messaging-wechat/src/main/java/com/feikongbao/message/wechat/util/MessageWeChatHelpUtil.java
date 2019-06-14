package com.feikongbao.message.wechat.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feikongbao.message.wechat.exception.MessageWeChatException;
import com.feikongbao.message.wechat.model.data_type.MessageWeChatResponseMessage;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 实现 json xml object之间的转换
 *
 * @author zili.wang
 * @date 2019 /5/6 15:15
 */
@SuppressWarnings("Duplicates")
public class MessageWeChatHelpUtil {
    /**
     * 手机正则表达式
     */
    private static final String REG_EXP = "^((13[0-9])|(14[1,5,7,9])|(15[0-9])|(16[2,5,6,7])|(17[0-8])|(18[0-9])|(19[1,8,9]))\\d{8}$";

    private static ObjectMapper objectMapper;
    static {
        objectMapper = new ObjectMapper();
    }

    /**
     * Json 2 map .
     *
     * @param json the json
     * @return the map
     * @author zili.wang
     * @date 2019/05/06 19:21:06
     */
    public static Map<String, String> json2Map(String json) {
        Map<String, String> map = null;
        try {
            map = new ConcurrentHashMap<>(16);
            JsonNode jsonNode = objectMapper.readTree(json);
            if (jsonNode.isNull()) {
                return map;
            }
            Iterator<Map.Entry<String, JsonNode>> iterator = jsonNode.fields();
            while (iterator.hasNext()) {
                Map.Entry<String, JsonNode> entry = iterator.next();
                map.put(entry.getKey(), entry.getValue().asText());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }

    /**
     * Xml 2 map .
     *
     * @param inputStream the input stream
     * @return the map
     * @throws DocumentException the document exception
     * @throws IOException       the io exception
     * @author zili.wang
     * @date 2019/05/06 19:20:51
     */
    public static Map<String, String> xml2Map(InputStream inputStream) throws DocumentException, IOException {
        Map<String, String> map = new ConcurrentHashMap<>(16);
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(inputStream);
        Element root = document.getRootElement();
        List<Element> list = root.elements();
        for (Element e : list) {
            map.put(e.getName(), e.getText());
        }
        inputStream.close();
        return map;
    }

    public static Map<String, String> xml2Map(String message) throws DocumentException {
        Map<String, String> map = new ConcurrentHashMap<>(16);
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(message);
        Element root = document.getRootElement();
        List<Element> list = root.elements();
        for (Element e : list) {
            map.put(e.getName(), e.getText());
        }
        return map;
    }

    /**
     * 将MessageWeChatResponseMessage转成微信需要的xml格式.
     *
     * @param responseMessage the response message
     * @return the string
     * @author zili.wang
     * @date 2019/05/06 19:20:08
     */
    public static String object2Xml(MessageWeChatResponseMessage responseMessage) {
        XStream xStream = new XStream();
        xStream.alias("xml", responseMessage.getClass());
        return xStream.toXML(responseMessage);
    }

    /**
     * 验证电话的正确性.
     *
     * @param phoneNum the phone num
     * @return the boolean
     * @author zili.wang
     * @date 2019/05/06 19:19:10
     */
    public static boolean validatePhoneNumber(String phoneNum) {
        Pattern pattern = Pattern.compile(REG_EXP);
        Matcher matcher = pattern.matcher(phoneNum);
        return matcher.matches();
    }

    public static String object2Json(Object valueType) throws MessageWeChatException {
        String json = null;
        try {
            json = objectMapper.writeValueAsString(valueType);
        } catch (JsonProcessingException e) {
            throw new MessageWeChatException("Json Processing Exception: " + valueType.getClass().getName());
        }
        return json;
    }


    public static <T> T json2Object(byte[] json, Class<T> valueType) throws IOException {
        return (T) objectMapper.readValue(json, valueType);
    }

    /**
     * 格式化json字符串
     *
     * @param json the json
     * @return the string
     * @throws IOException the io exception
     * @author zili.wang
     * @date 2019 /06/13 20:35:00
     */
    public static String formatJson(String json) throws IOException {
        Object obj = objectMapper.readValue(json, Object.class);
        String jsonFormat = objectMapper.writeValueAsString(obj);
        return jsonFormat;
    }

}
