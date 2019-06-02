package com.group3.basic.netcracker.backend.util.file;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;

@Slf4j
public class ImageConverter {
    public static boolean convertToImage(byte[] file, String filepath, String fileExtension) {
        ByteArrayInputStream bis = new ByteArrayInputStream(file);
        BufferedImage bImage2;
        try {
            bImage2 = ImageIO.read(bis);
            ImageIO.write(bImage2, fileExtension, new File(filepath));
            return true;
        } catch (IOException e) {
            log.error(e.getMessage());
            return false;
        }
    }

    public static String convertToString(String filepath) {
        String encodedfile = null;
        FileInputStream fileInputStreamReader;
        try {
            File file = new File(filepath);
            fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            fileInputStreamReader.read(bytes);
            encodedfile = new String(Base64.encodeBase64(bytes), StandardCharsets.UTF_8);
        } catch (FileNotFoundException e) {
            log.warn("Your server don't have user's photo");
        } catch (NullPointerException e) {
            log.warn("User hasn't downloaded photo yet");
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return encodedfile;
    }
}
