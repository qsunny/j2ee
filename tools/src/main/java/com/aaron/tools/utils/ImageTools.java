package com.aaron.tools.utils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import sun.font.FontDesignMetrics;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * @Description:图片相关操作工具类
 * @Author:Aaron.Qiu
 * @Since:2014-7-22
 * @Copyright:Copyright (c)  2014 ~ 2015 版权所有
 */
public class ImageTools {

    private static final String[] FONT_TYPES_ZH = { /*"宋体",*/ "Arial" };

    public static void resizeImage(String src, String dest,
        int newWidth, int newHeight, String imageType) throws IOException {
        BufferedImage buffImage = resizeImage(src, newWidth, newHeight, imageType, true);
        if (null != buffImage) {
            ImageIO.write(buffImage, imageType, new File(dest));
        }
    }

    public static BufferedImage resizeImage(String src,
        int newWidth, int newHeight, String imageType, boolean output) throws IOException {

        // 缩放比例
        double ratio = 0;

        File file = new File(src);
        BufferedImage srcBuffImage = ImageIO.read(file);
        Image itemp = srcBuffImage.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH);

        //计算比例
        if ((srcBuffImage.getHeight() > newHeight) || (srcBuffImage.getWidth() > newWidth)) {

            if (srcBuffImage.getHeight() > srcBuffImage.getWidth()) {
                ratio = (new Integer(newHeight)).doubleValue() / srcBuffImage.getHeight();
            } else {
                ratio = (new Integer(newWidth)).doubleValue() / srcBuffImage.getWidth();
            }

            AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
            itemp = op.filter(srcBuffImage, null);

            if (output) {
                return (BufferedImage)itemp;
            } else {
                ImageIO.write((BufferedImage)itemp, imageType, file);
                return null;
            }

        } else {
            return null;
        }

    }

    /**
     * 图片按比便缩小
     * @param srcFile
     * @param targetFile
     * @param newWidth
     * @param newHeight
     * @return
     */
    public static boolean resizeImage(String srcFile, String targetFile, int newWidth, int newHeight) {
        boolean ret = false;
        try{
            Image src = ImageIO.read(new File(srcFile));
            BufferedImage bi = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            bi.getGraphics().drawImage(src, 0, 0, newWidth, newHeight, null); //绘制缩小后的图
            FileOutputStream out=new FileOutputStream(targetFile); //输出到文件流
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(bi); //近JPEG编码
            out.close();
            ret = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }


    /**
     * 把图片印刷到图片上
     *
     * @param pressImg -- 水印文件
     * @param targetImg -- 目标文件
     * @param x --x坐标
     * @param y --y坐标
     * @param repeat -- 是否需要平铺
     */
    public final static void pressImage(String pressImg, String targetImg,
            int x, int y, boolean repeat/*, float alpha*/) {
        try {
            // 目标文件
            File _file = new File(targetImg);
            Image src = ImageIO.read(_file);
            int wideth = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(wideth, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();
            g.drawImage(src, 0, 0, wideth, height, null);

            // 水印文件
            Image src_biao = null;
            src_biao = resizeImage(pressImg, wideth, height, "png", true);
            if (null == src_biao) {
                File _filebiao = new File(pressImg);
                src_biao = ImageIO.read(_filebiao);
            }

            int wideth_biao = src_biao.getWidth(null);
            int height_biao = src_biao.getHeight(null);
            // 设置透明度
            /*g.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_ATOP, alpha));*/
            /*g.drawImage(src_biao, (wideth - wideth_biao) / 2,
                    (height - height_biao) / 2, wideth_biao, height_biao, null);*/
            if(repeat){
            	//需要平铺
            	int repeatHeight = height-y,
            		 repeatWideth = wideth-x;
            	int repeatX = 0, repeatY = 0;
            	int repeatRows = repeatHeight%height_biao>0?(repeatHeight/height_biao+1):(repeatHeight/height_biao);
            	int repeatCols = repeatWideth%wideth_biao>0?(repeatWideth/wideth_biao+1):(repeatWideth/wideth_biao);
            	for(int i=y;i<repeatRows;i++){
            		for(int j=x;j<repeatCols;j++){
            			repeatX = j*wideth_biao;
            			repeatY = i*height_biao;
            			g.drawImage(src_biao, x+repeatX, y+repeatY, wideth_biao, height_biao, null);
            		}
            	}
            }else{
            	g.drawImage(src_biao, x, y, wideth_biao, height_biao, null);
            }

            // 水印文件结束
            g.dispose();
            FileOutputStream out = new FileOutputStream(targetImg);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(image);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印文字水印图片
     *
     * @param pressText --文字
     * @param targetImg -- 目标图片
     * @param fontName -- 字体名
     * @param fontStyle -- 字体样式
     * @param color -- 字体颜色
     * @param fontSize -- 字体大小
     * @param x -- 偏移量
     * @param y
     */

    public static void pressText(String pressText, String targetImg,
            String fontName, int fontStyle, Color color, int fontSize, int x,
            int y) {
        try {
            File _file = new File(targetImg);
            Image src = ImageIO.read(_file);
            int wideth = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(wideth, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();
            g.drawImage(src, 0, 0, wideth, height, null);
            g.setColor(color);
            g.setFont(new Font(fontName, fontStyle, fontSize));

            g.drawString(pressText, wideth - fontSize - x, height - fontSize
                    / 2 - y);
            g.dispose();

            //ImageIO.write(image, "png", new File("c:/test.png"));

            FileOutputStream out = new FileOutputStream(targetImg);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(image);
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     *
     * @Description
     * @param text
     * @param tagImg
     * @param fontName
     * @param fontColor
     * @param fontStyle
     * @param fontSize
     * @param x
     * @param y
     * @param imgType
     */
    public static void pressText(
        String text, String tagImg, String fontName,
        Color fontColor, int fontStyle, int fontSize, int x, int y, String imgType) {
        try {
            File _file = new File(tagImg);
            Image src = ImageIO.read(_file);
            int width = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();
            image = g.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
            g.dispose();
            g = image.createGraphics();
            g.setStroke(new BasicStroke(1));
            g.drawImage(src, 0, 0, width, height, null);
            g.setColor(fontColor);
            g.setFont(new Font(fontName, fontStyle, fontSize));

            g.drawString(text, width - fontSize - x, height - fontSize
                    / 2 - y);
            g.dispose();
            ImageIO.write(image, imgType, new File(tagImg));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * 旋转图片
     * @param srcImg 需要旋转的图片
     * @param targetImg 旋转后的图片
     * @param rotate 旋转的角度
     */
    public static void rotateImage(String srcImg, String targetImg, double rotate){
    	try{
    		File _file = new File(srcImg);
            Image src = ImageIO.read(_file);
            int wideth = src.getWidth(null);
            int height = src.getHeight(null);
            //将需要旋转的图片读取到缓存总
            BufferedImage originalBufImage = new BufferedImage(wideth, height, BufferedImage.TYPE_INT_RGB);
            Graphics org = originalBufImage.getGraphics();
            org.drawImage(src, 0, 0, null);
            org.dispose();
            //旋转后的图片存储的缓存
            BufferedImage filteredBufImage = new BufferedImage(wideth, height, BufferedImage.TYPE_INT_RGB);

            AffineTransform transform = new AffineTransform(); //仿射变换对象
			transform.rotate(Math.toRadians(rotate),wideth/2,height/2); //旋转图像

			AffineTransformOp imageOp = new AffineTransformOp(transform, null);//创建仿射变换操作对象
			imageOp.filter(originalBufImage, filteredBufImage);//过滤图像，目标图像在filteredBufImage

            FileOutputStream out = new FileOutputStream(targetImg);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(filteredBufImage);
            out.close();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }

    /**
     *
     * @Description
     * @param tagImg
     * @param rotate
     * @param imgType
     */
    public static void rotateImage(String tagImg, double rotate, String imgType){
        try{
            File _file = new File(tagImg);
            Image src = ImageIO.read(_file);
            int width = src.getWidth(null);
            int height = src.getHeight(null);
            //将需要旋转的图片读取到缓存总
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D org = image.createGraphics();
            image = org.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
            org.dispose();
            org = image.createGraphics();
            org.setStroke(new BasicStroke(1));
            org.drawImage(src, 0, 0, width, height, null);
            org.dispose();
            //旋转后的图片存储的缓存
            BufferedImage filteredBufImage = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
            AffineTransform transform = new AffineTransform(); //仿射变换对象
            transform.rotate(Math.toRadians(rotate),width/2,height/2); //旋转图像
            AffineTransformOp imageOp = new AffineTransformOp(transform, null);//创建仿射变换操作对象
            imageOp.filter(image, filteredBufImage);//过滤图像，目标图像在filteredBufImage
            ImageIO.write(filteredBufImage, imgType, new File(tagImg));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static byte[] generateImageByText(String text, int fontSize) {

        int width = 0;
        int height = 0;

        if (text == null || text.trim().length() == 0) {
            return null;
        }

        Font font = new Font(FONT_TYPES_ZH[0], Font.LAYOUT_LEFT_TO_RIGHT, fontSize);
        FontMetrics fm = FontDesignMetrics.getMetrics(font);
        width = fm.stringWidth(text);
        height = fm.getHeight();

        Random rand = new Random(System.currentTimeMillis());
        Graphics2D g = null;


        String[] fontTypes = FONT_TYPES_ZH;
        int fontTypesLen = fontTypes.length;

        BufferedImage bimage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        g = bimage.createGraphics();

        Color color = new Color(255, 255, 255);

        // 填充背景
        g.setColor(color);
        g.fillRect(0, 0, width, height);

        // 设置字体
        g.setFont(new Font(fontTypes[rand.nextInt(fontTypesLen)], Font.BOLD, fontSize));

        // 随机生成字符,根据截取的位数决定产生的数字
        int w = (g.getFontMetrics()).stringWidth(text);
        int d = (g.getFontMetrics()).getDescent();
        int a = (g.getFontMetrics()).getMaxAscent();
        int x = 0, y = 0;

        // reset x and y
        x = 0;
        y = 0;

        // 设置文字出现位置为中央
        x = width / 2 - w / 2 - 20;
        y = height / 2 + a / 2 - 4;

        // 文字变形设置
        AffineTransform fontAT = new AffineTransform();
        int xp = 0;

        // 每个文字都变形
        int textLen = text.length();
        for (int c = 0, length = textLen; c < length; c++) {
            Font fx = new Font(fontTypes[rand.nextInt(fontTypesLen)], Font.LAYOUT_LEFT_TO_RIGHT, fontSize).deriveFont(fontAT);
            g.setFont(fx);

            int red = 0;
            int green = 0;
            int blue = 0;

            // 用随机产生的颜色将验证码绘制到图像中。
            g.setColor(new Color(red, green, blue));
            String ch = String.valueOf(text.charAt(c));

            // 打印字并移动位置
            g.drawString(ch, xp, fontSize);

            // 移动指针.
            xp += g.getFontMetrics().stringWidth(ch);
        }

        // 图象生效
        g.dispose();

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            ImageOutputStream imgeOut = ImageIO.createImageOutputStream(output);
            ImageIO.write(bimage, "JPEG", imgeOut);
            imgeOut.close();
            return output.toByteArray();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return null;
    }

    public static byte[] zoomImageToWeb(
            int maxWidth, int maxHeight, String src, String dest) {

        if (null == src || 0 == src.trim().length()) {
            return null;
        }

        if (dest.trim().equals("")) {
            dest = src;
        }

        BufferedImage buffImage = zoomImageHelper(maxWidth, maxHeight, src);

        if (null != buffImage) {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            try {
                ImageOutputStream out = ImageIO.createImageOutputStream(output);
                ImageIO.write(buffImage, "JPEG", out);
                out.close();
                return output.toByteArray();
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if (output != null) {
                    try {
                        output.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }

        return null;
    }

    public static void zoomImageToFile(
            int maxWidth, int maxHeight, String src, String dest) {

        if (null == src || 0 == src.trim().length()) {
            return;
        }

        if (dest.trim().equals("")) {
            dest = src;
        }

        BufferedImage buffImage = zoomImageHelper(maxWidth, maxHeight, src);

        if (null != buffImage) {
            try {
                ImageIO.write(buffImage, "JPEG", new File(dest));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @Description 缩放图片
     * @param maxWidth
     * @param maxHeight
     * @param src
     * @return
     */
    private static BufferedImage zoomImageHelper(
            int maxWidth, int maxHeight, String src) {

        int realWidth = 0;
        int realHeight = 0;
        int newWidth = 0;
        int newHeight = 0;
        float widthRate = 0f;
        float heightRate = 0f;
        BufferedImage srcImage = null;
        BufferedImage destImage = null;

        try {

            srcImage = ImageIO.read(new File(src));
            realWidth = srcImage.getWidth();
            realHeight = srcImage.getHeight();

            if ((realWidth > maxWidth) || (realHeight > maxHeight)) {

                widthRate = (float)maxWidth / realWidth;
                heightRate = (float)maxHeight / realHeight;

                newWidth = maxWidth;
                newHeight = maxHeight;
                destImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_3BYTE_BGR);

                AffineTransform transform = new AffineTransform();
                transform.setToScale(widthRate, heightRate);

                // 根据原始图片生成处理后的图片
                AffineTransformOp ato = new AffineTransformOp(transform, null);
                ato.filter(srcImage, destImage);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return destImage;
    }

    public byte[] cutImageToWeb(float x, float y, int width, int height,
            int maxWidth, int maxHeight, String src, String dest) {

        if (null == src || 0 == src.trim().length()) {
            return null;
        }

        if (dest.trim().equals("")) {
            dest = src;
        }

        BufferedImage buffImage = cutImageHelper(x, y, maxWidth, maxHeight, width, height, src);

        if (null != buffImage) {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            try {
                ImageOutputStream out = ImageIO.createImageOutputStream(output);
                ImageIO.write(buffImage, "JPEG", out);
                out.close();
                return output.toByteArray();
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if (output != null) {
                    try {
                        output.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }

        return null;
    }

    public static void cutImageToFile(float x, float y, int width, int height,
            int maxWidth, int maxHeight, String src, String dest) {

        if (null == src || 0 == src.trim().length()) {
            return;
        }

        if (dest.trim().equals("")) {
            dest = src;
        }

        BufferedImage buffImage = cutImageHelper(x, y, maxWidth, maxHeight, width, height, src);

        if (null != buffImage) {
            try {
                ImageIO.write(buffImage, "JPEG", new File(dest));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @Description 裁剪图片
     * @param x
     * @param y
     * @param maxWidth
     * @param maxHeight
     * @param width
     * @param height
     * @param src
     * @return
     */
    private static BufferedImage cutImageHelper(
            float x, float y, float maxWidth, float maxHeight,
            int width, int height, String src) {

        BufferedImage buffImage = null;
        BufferedImage destImage = null;
        float realWidth = 0f;
        float realHeight = 0f;
        float rate = 0f;

        try {

            buffImage = ImageIO.read(new File(src));

            // 计算小图片和原图比例
            if (buffImage.getWidth() < buffImage.getHeight()) {
                rate = buffImage.getHeight() / maxHeight;
            } else {
                rate = buffImage.getWidth() / maxWidth;
            }

            // 计算原图像的起始坐标
            x = Math.round(x * rate);
            y = Math.round(y * rate);

            // 按比例计算原图像截取的宽度和高度
            realWidth = width * rate;
            realHeight = height * rate;

            if (realWidth > buffImage.getWidth()) {
                realWidth = buffImage.getWidth();
            }

            if (realHeight > buffImage.getHeight()) {
                realHeight = buffImage.getHeight();
            }

            // 截取局部像素
            destImage = buffImage.getSubimage((int)x, (int)y, (int)realWidth, (int)realHeight);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return destImage;
    }

    public static void main(String[] args) throws Exception {
    }
}

