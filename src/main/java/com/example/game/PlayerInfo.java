package com.example.game;//import lombok.Data;

import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Data;
import java.io.*;

/**
 * @author Nie Weilin
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name"})
@Data
public class PlayerInfo {
    private String name;

    public void createPlayer(String name) {
        setName(name);
        File player = new File("C:\\Users\\Fish\\Downloads\\" + File.separator + name + ".xml");
        if (!player.exists()) {
            File dir = new File(player.getParent());
            dir.mkdirs();
            try {
                player.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public void loadPlayer(String file) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(file));
            try {
                JAXBHelper.fromXML(PlayerInfo.class, new FileInputStream(getName()+".xml"));
                JAXBHelper.fromXML(RoundInfo.class, new FileInputStream(getName()+".xml"));
            } catch (JAXBException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                assert in != null;
                if ( in.readLine() == null) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
