package com.tec.utils;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;


public class SaxHandler extends DefaultHandler {

    public static void main(String[] args) throws Exception {
        // 1.实例化SAXParserFactory对象
        SAXParserFactory factory = SAXParserFactory.newInstance();
        // 2.创建解析器
        SAXParser parser = factory.newSAXParser();
        // 3.获取需要解析的文档，生成解析器,最后解析文档
        File f = new File("D:\\test1.xml");
        com.supervise.sddxb.common.utils.SaxHandler dh = new com.supervise.sddxb.common.utils.SaxHandler();
        parser.parse(f, dh);
    }

    /* 此方法有三个参数
       arg0是传回来的字符数组，其包含元素内容
       arg1和arg2分别是数组的开始位置和结束位置 */
    @Override
    public void characters(char[] arg0, int arg1, int arg2) throws SAXException {
        String content = new String(arg0, arg1, arg2);
        System.out.println(content);
        super.characters(arg0, arg1, arg2);
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("结束解析文档");
        super.endDocument();
    }

    /* arg0是名称空间
       arg1是包含名称空间的标签，如果没有名称空间，则为空
       arg2是不包含名称空间的标签 */
    @Override
    public void endElement(String arg0, String arg1, String arg2)
            throws SAXException {
        System.out.println("结束解析元素  " + arg2);
        super.endElement(arg0, arg1, arg2);
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("开始解析文档");
        super.startDocument();
    }

    /*arg0是名称空间
      arg1是包含名称空间的标签，如果没有名称空间，则为空
      arg2是不包含名称空间的标签
      arg3很明显是属性的集合 */
    @Override
    public void startElement(String arg0, String arg1, String arg2,
                             Attributes arg3) throws SAXException {
        System.out.println("开始解析元素 " + arg2);
        if (arg3 != null) {
            for (int i = 0; i < arg3.getLength(); i++) {
                // getQName()是获取属性名称，
                System.out.print(arg3.getQName(i) + "=\"" + arg3.getValue(i) + "\"");
            }
        }
        System.out.print(arg2 + ":");
        super.startElement(arg0, arg1, arg2, arg3);
    }
}