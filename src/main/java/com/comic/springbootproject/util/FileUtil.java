package com.comic.springbootproject.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: File Util
 * @author: HymanHu
 * @date: 2020年4月15日
 */
public class FileUtil {

    private final static Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    public final static String FILE_SEPARATOR = ".";
    public final static Map<String, String> FILE_TYPE_MAP = new HashMap<String, String>();

    static {
        FILE_TYPE_MAP.put("jpg", "FFD8FF");
        FILE_TYPE_MAP.put("png", "89504E47");
        FILE_TYPE_MAP.put("gif", "47494638");
        FILE_TYPE_MAP.put("tif", "49492A00");
        FILE_TYPE_MAP.put("bmp", "424D");
        FILE_TYPE_MAP.put("dwg", "41433130");
        FILE_TYPE_MAP.put("html", "68746D6C3E");
        FILE_TYPE_MAP.put("rtf", "7B5C727466");
        FILE_TYPE_MAP.put("xml", "3C3F786D6C");
        FILE_TYPE_MAP.put("zip", "504B0304");
        FILE_TYPE_MAP.put("rar", "52617221");
        FILE_TYPE_MAP.put("psd", "38425053");
        FILE_TYPE_MAP.put("eml", "44656C69766572792D646174653A");
        FILE_TYPE_MAP.put("dbx", "CFAD12FEC5FD746F");
        FILE_TYPE_MAP.put("pst", "2142444E");
        FILE_TYPE_MAP.put("xls", "D0CF11E0");
        FILE_TYPE_MAP.put("doc", "D0CF11E0");
        FILE_TYPE_MAP.put("mdb", "5374616E64617264204A");
        FILE_TYPE_MAP.put("wpd", "FF575043");
        FILE_TYPE_MAP.put("eps", "252150532D41646F6265");
        FILE_TYPE_MAP.put("ps", "252150532D41646F6265");
        FILE_TYPE_MAP.put("pdf", "255044462D312E");
        FILE_TYPE_MAP.put("qdf", "AC9EBD8F");
        FILE_TYPE_MAP.put("pwl", "E3828596");
        FILE_TYPE_MAP.put("wav", "57415645");
        FILE_TYPE_MAP.put("avi", "41564920");
        FILE_TYPE_MAP.put("ram", "2E7261FD");
        FILE_TYPE_MAP.put("rm", "2E524D46");
        FILE_TYPE_MAP.put("mpg", "000001BA");
        FILE_TYPE_MAP.put("mov", "6D6F6F76");
        FILE_TYPE_MAP.put("asf", "3026B2758E66CF11");
        FILE_TYPE_MAP.put("mid", "4D546864");
    }

    public static boolean isImage(File file) {
        try {
            BufferedImage bufreader = ImageIO.read(file);
            int width = bufreader.getWidth();
            int height = bufreader.getHeight();
            if (width > 0 && height > 0) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean isImage(MultipartFile file) {
        try {
            BufferedImage bufreader = ImageIO.read(file.getInputStream());
            if (bufreader == null) {
                return false;
            }
            int width = bufreader.getWidth();
            int height = bufreader.getHeight();
            if (width > 0 && height > 0) {
                return true;
            }
        } catch (IOException e) {
            LOGGER.debug(e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    public static String getImageType(File file) {
        if (isImage(file)) {
            ImageInputStream iis = null;
            try {
                iis = ImageIO.createImageInputStream(file);
                Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
                while(!iter.hasNext()) {
                    return null;
                }

                ImageReader reader = iter.next();
                return reader.getFormatName();
            } catch (IOException e) {
                LOGGER.debug(e.getMessage());
                e.printStackTrace();
            } finally {
                closeImageStream(iis, null);
            }
        }

        return null;
    }

    public static String getFileHexString(byte[] bytes) {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }

        StringBuffer sb = new StringBuffer();
        for (byte by : bytes) {
            int v = by & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                sb.append(0);
            }
            sb.append(hv);
        }

        return sb.toString();
    }

    public static String getFileType(byte[] bytes) {

        String filetypeHex = String.valueOf(getFileHexString(bytes));
        return FILE_TYPE_MAP.entrySet()
                .stream()
                .filter(item -> filetypeHex.toUpperCase().startsWith(item.getValue()))
                .map(item -> item.getKey())
                .collect(Collectors.joining());
    }

    public static String getFileType(File file) {
        String filetype = null;
        byte[] bytes = new byte[50];

        InputStream is = null;
        try {
            is = new FileInputStream(file);
            is.read(bytes);
            filetype = getFileType(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStream(is, null);
        }

        return filetype;
    }

    public static String getFileType(String fileName) {
        if (StringUtils.isBlank(fileName)) {
            return null;
        }
        return fileName.substring(fileName.lastIndexOf(FILE_SEPARATOR) + 1, fileName.length());
    }

    public static void changeImageSize(String sourcePath, String destPath, int width, int height) {
        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        try {
            in = new BufferedInputStream(new FileInputStream(sourcePath));
            Image bi = ImageIO.read(in);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            tag.getGraphics().drawImage(bi, 0, 0, width, height, null);
            out = new BufferedOutputStream(new FileOutputStream(destPath));
            ImageIO.write(tag, "PNG", out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStream(in, out);
        }
    }

    public static void closeImageStream(ImageInputStream iis, ImageOutputStream ios) {
        try {
            if (iis != null) {
                iis.close();
            }
            if (ios != null) {
                ios.close();
            }
        } catch (IOException e) {
            LOGGER.debug(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void closeStream(InputStream is, OutputStream os) {
        try {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.close();
            }
        } catch (IOException e) {
            LOGGER.debug(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("getFileType: " + getFileType("i.zeze.com_app_2002_size_f9999,10000_q_a80_n_0_g_0n_fmt_jpeg.png"));
//		File file = new File("D:\\upload\\qqqqqqq.jpg");
//		System.out.println("getImageType: " + getImageType(file));
//		System.out.println("getFileType: " + getFileType(file));

//		changeImageSize("D:\\upload\\qqqqqqq.jpg", "D:\\upload\\qqqqqqq_middle.jpg", 100, 100);
//		changeImageSize("D:\\upload\\qqqqqqq.jpg", "D:\\upload\\qqqqqqq_small.jpg", 55, 55);

        FileUtil.changeImageSize("/1.jpg", "/4.jpg",
                1920, 366);
    }
}
