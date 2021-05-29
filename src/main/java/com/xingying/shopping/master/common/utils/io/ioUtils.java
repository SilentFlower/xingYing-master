package com.xingying.shopping.master.common.utils.io;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author SiletFlower
 * @date 2021/5/26 10:23:50
 * @description
 */
public class ioUtils {
    /**
     * 写入方法字符流（未完）
     * @param url
     * @param multipartFile
     * @return
     */
    public static boolean writerString(String url, MultipartFile multipartFile) {
        File file = new File(url);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 写入方法字节流
     * @param url
     * @param multipartFile
     * @return
     */
    public static boolean writerBytes(String url, MultipartFile multipartFile) {
        File file = new File(url);
        if (!file.exists()) {
            try {
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        BufferedOutputStream writer = null;
        try {
            writer = new BufferedOutputStream(new FileOutputStream(url));
            writer.write(multipartFile.getBytes());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return true;
    }


    /**
     * 根据路径删除目录，不管是否存在该目录
     * @param sPath 要删除的目录path
     * @return 删除成功返回true，否则返回false
     */
    public static boolean delete(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        if(!file.exists()) {
            return false;
        }else {
            if(file.isFile()) {
                flag = deleteFile(sPath);
            }else {
                flag = deleteDirectory(sPath);
            }
        }
        return flag;
    }

    /**
     * 删除单个文件
     * @param sPath 被删除文件path
     * @return 文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        //如果文件存在并且是文件，删除改文件
        if(file.exists()&&file.isFile()) {
            flag = file.delete();
            return flag;
        }
        return false;
    }

    /**
     * 删除目录以及目录下的文件
     * @param sPath
     * @return
     */
    public static boolean deleteDirectory(String sPath) {
        //如果文件不是以文件分隔符结尾，自动添加
        if(!sPath.endsWith(File.separator)) {
            sPath = sPath+File.separator;
        }
        File file = new File(sPath);
        //如果file不存在或者不是目录则退出
        if(!file.exists()||!file.isDirectory()) {
            return false;
        }
        boolean flag = false;
        //删除文件夹下的所有文件（包括子目录）
        File[] files = file.listFiles();
        for(File f : files) {
            if(f.isFile()) {
                flag = deleteFile(f.getAbsolutePath());
                if(!flag) {
                    break;
                }
            }else {
                flag = deleteDirectory(f.getAbsolutePath());
                if(!flag) {
                    break;
                }
            }
        }
        //删除当前目录
        if(file.delete()) {
            return true;
        }else {
            return false;
        }
    }

}
