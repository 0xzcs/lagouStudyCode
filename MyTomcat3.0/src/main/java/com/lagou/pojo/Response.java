package com.lagou.pojo;

import com.lagou.utils.HttpProtocolUtil;
import com.lagou.utils.StaticResourceUtil;

import java.io.*;

public class Response {

    private OutputStream outputStream;

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }


    public void outHtml(String path) throws IOException {

        // 获取静态资源文件的绝对路径
        String absoluteResourcePath = StaticResourceUtil.getAbsolutePath(path);

        File file = new File(absoluteResourcePath);
        if(file.exists() && file.isFile()){

            StaticResourceUtil.outputStaticResource(new FileInputStream(file),outputStream);
        }else {
            // 输出 404
            output(HttpProtocolUtil.getHttpHead404());
        }

    }

    public void output(String content) throws IOException {

        outputStream.write(content.getBytes());

    }



}
