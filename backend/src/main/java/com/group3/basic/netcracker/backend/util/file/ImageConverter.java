package com.group3.basic.netcracker.backend.util.file;

import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class ImageConverter {
    public static boolean convertToImage(byte[] file, String filepath, String fileExtension) {
        ByteArrayInputStream bis = new ByteArrayInputStream(file);
        BufferedImage bImage2 = null;
        try {
            bImage2 = ImageIO.read(bis);
            ImageIO.write(bImage2, fileExtension, new File(filepath));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String convertToString(String filepath) {
        String encodedfile = null;
        FileInputStream fileInputStreamReader = null;
        try {
            File file = new File(filepath);
            fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            fileInputStreamReader.read(bytes);
            encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("User hasn't downloaded photo yet");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return encodedfile;
    }
}
