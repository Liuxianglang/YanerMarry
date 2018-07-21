package com.iyaner.yaner.utils;

import org.springframework.util.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

public class FileUtils {

    public static String getFileMd5(String filePath){
        File file=new File(filePath);
        if(file.exists()&&file.isFile()){
            InputStream is=null;
            try{
                is=new FileInputStream(file);
                byte[] buffer=new byte[1024*8];
                MessageDigest md=MessageDigest.getInstance("MD5");
                int len;
                while((len=is.read(buffer))!=-1){
                    md.update(buffer,0,len);
                }
                return DigestUtils.md5DigestAsHex(md.digest());
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if(is!=null){
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return "";
    }
}
