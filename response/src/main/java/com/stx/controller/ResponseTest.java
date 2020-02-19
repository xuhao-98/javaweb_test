package com.stx.controller;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

public class ResponseTest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取下载文件的路径
        String realpath= "F:\\javaweb_test\\response\\src\\main\\resources\\xuhao.JPG";
        System.out.println("文件的路径是"+realpath);
        //2.下载的文件名
        String filename=realpath.substring(realpath.indexOf("\\")+1);
        //3.设想办法让浏览器能够支持下载
        resp.setHeader("Content-Disposition","attachment:filename="+filename+ URLEncoder.encode(filename,"utf-8"));
        //4.获取下载文件的输入流
        FileInputStream in = new FileInputStream(realpath);
        //5.创建缓冲区
        int len = 0;
        byte[] buffer = new byte[1024];
        //6.获取OutputStream对象
        ServletOutputStream out = resp.getOutputStream();
        //7.将FileOutputStream流写入到buffer缓存区并写出
        while ((len=in.read())>0){
            out.write(buffer,0,len);
        }
        in.close();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
