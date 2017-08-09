package com.aaron.mybatisgenerator.util;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.net.URL;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.AndFileFilter;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.NotFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.lang.StringUtils;

public class FileUtil
{
    public static void copyFile(String resFilePath, String distFolder)
            throws IOException
    {
        File resFile = new File(resFilePath);
        File distFile = new File(distFolder);
        if (resFile.isDirectory())
            FileUtils.copyDirectoryToDirectory(resFile, distFile);
        else if (resFile.isFile())
            FileUtils.copyFileToDirectory(resFile, distFile, true);
    }

    public static void deleteFile(String targetPath)
            throws IOException
    {
        File targetFile = new File(targetPath);
        if (targetFile.isDirectory())
            FileUtils.deleteDirectory(targetFile);
        else if (targetFile.isFile())
            targetFile.delete();
    }

    public static long genFileSize(String distFilePath)
    {
        File distFile = new File(distFilePath);
        if (distFile.isFile())
            return distFile.length();
        if (distFile.isDirectory()) {
            return FileUtils.sizeOfDirectory(distFile);
        }
        return -1L;
    }

    public static boolean isExist(String filePath)
    {
        return new File(filePath).exists();
    }

    public static String[] listFilebySuffix(String folder, String suffix)
    {
        IOFileFilter fileFilter1 = new SuffixFileFilter(suffix);
        IOFileFilter fileFilter2 = new NotFileFilter(DirectoryFileFilter.INSTANCE);
        FilenameFilter filenameFilter = new AndFileFilter(fileFilter1, fileFilter2);
        return new File(folder).list(filenameFilter);
    }

    public static boolean string2File(String res, String filePath)
    {
        boolean flag = true;
        try
        {
            res = new String(res.getBytes("UTF-8"));
            File file = new File(filePath);

            if (!file.getParentFile().exists())
            {
                file.getParentFile().mkdirs();
            }

            FileOutputStream fis = new FileOutputStream(file);
            OutputStreamWriter isr = new OutputStreamWriter(fis, "UTF-8");

            OutputStream out = new FileOutputStream(file);
            BufferedWriter rd = new BufferedWriter(isr);
            rd.write(res);
            rd.close();
            out.close();
            System.out.println(filePath);
        }
        catch (Exception e) {
            flag = false;
        }

        return flag;
    }

    public static String getClassPath()
    {
        String path = "";
        try {
            path = FileUtil.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            if(StringUtils.isNotEmpty(path)&&path.endsWith(".jar")) {
                path = path.substring(0,path.indexOf("/install"));
                System.out.println("sub path==="+path);
                path = path + "/classes/main/";
            } else {
                path = FileUtil.class.getResource("/").getPath();
            }
            //System.out.println(path);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        //System.out.println(FileUtil.class.getResource("/").getPath());
        return path;
    }
}
