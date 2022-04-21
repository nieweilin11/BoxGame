package com.example.game;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveWrite extends RoundInfo {


    public void Writhe (){
        //要写入的数据

/*

        File f = new File("/player.json");
        //将数据写入.json文件--start
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f,false), "UTF-8"));
            writer.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(writer != null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
*/

    }
    public void createXML(){
        //1.创建document对象，代表整个xml文档
        Document document = DocumentHelper.createDocument();
        //2.创建根节点rss
        Element rss = document.addElement("rss");
        //3.向rss节点中添加version属性
        rss.addAttribute("version", "2.0");
        //4.生成子节点及节点内容
        Element channel = rss.addElement("channel");
        Element title = channel.addElement("title");
        title.setText(getName());
        //5.设置生成xml的格式
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        //6.生成xml文件
        File file = new File(getName()+".xml");
        XMLWriter writer;
        try {
            writer = new XMLWriter(new FileOutputStream(file), format);
            //设置是否转义，默认是true，代表转义
            writer.setEscapeText(false);
            writer.write(document);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
