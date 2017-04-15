package com.aaron.tools.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 *
 * Created by Aarron.Qiu on 2017/4/15.
 */
public class HttpUtils {

    /**
     * 从url下载一个文件到目标路径
     * @param url 下载文件的url ftp://ftp.arin.net/pub/stats/arin/delegated-arin-extended-latest
     * @param targetPath 保存的目录路径 F:\\arin.txt
     */
    public static void DownLoadFileFromUrl(String url,String targetPath) {
        try {
            //connectionTimeout, readTimeout = 10 seconds
            FileUtils.copyURLToFile(new URL(url), new File(targetPath), 10000, 10000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从url下载一个文件到目标路径
     * @param url 下载文件的url ftp://ftp.arin.net/pub/stats/arin/delegated-arin-extended-latest
     * @param targetPath 保存的目录路径 F:\\arin.txt
     */
    public static void DownLoadFileFromUrlNIO(String url,String targetPath) {
        ReadableByteChannel rbc = null;
        FileOutputStream fos = null;
        try {
            URL website = new URL(url);
             rbc = Channels.newChannel(website.openStream());
            fos = new FileOutputStream(targetPath);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(null!=fos) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null!=rbc) {
                try {
                    rbc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
