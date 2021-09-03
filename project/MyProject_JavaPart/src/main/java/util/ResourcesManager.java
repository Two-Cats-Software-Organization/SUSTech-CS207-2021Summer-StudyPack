package util;

import javax.swing.*;
import java.io.UnsupportedEncodingException;
import java.net.URL;

public class ResourcesManager {
    private static ResourcesManager instance = new ResourcesManager();
    public static ResourcesManager getInstance(){ return instance; }
    private ResourcesManager(){ }

    public String CalculateProjectPath() {
        URL url = ResourcesManager.class.getClassLoader().getResource("");
        String s = url.getPath()/*.replaceAll("%20","");//解决路径中含有空格的情况*/;
        try {
            return java.net.URLDecoder.decode(s,"utf-8"); //解决路径包含中文的情况
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Icon getPlayer(int playerNow) {
        return new ImageIcon(CalculateProjectPath()+"view/player/piano"+playerNow+".jfif");
    }
    public Icon getPianoKeyBoard() {
        return new ImageIcon(CalculateProjectPath()+"view/frame/pianoKeyBoard.png");
    }
    public static void main(String[] args) {

    }
}
