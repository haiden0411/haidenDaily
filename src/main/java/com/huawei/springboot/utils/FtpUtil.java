package com.huawei.springboot.utils;
import com.google.common.base.Charsets;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

import java.io.IOException;
/**
 * Author：胡灯
 * Date：2019-08-17 12:01
 * Description：<描述>
 */
public class FtpUtil
{
    private static Logger logger = Logger.getLogger(FtpUtil.class);
    private static FTPClient getConnection()
    {
        FTPClient client = new FTPClient();
        client.setControlEncoding(Charsets.UTF_8.toString());
        client.setDataTimeout(30000);
        client.setDefaultTimeout(30000);
        return client;
    }
    private static FTPClient getConnection(String host) throws IOException
    {
        FTPClient client = getConnection();
        client.connect(host);
        if (!FTPReply.isPositiveCompletion(client.getReply()))
        {
            throw new IOException("connect error");
        }
        return client;
    }
    private static FTPClient getConnection(String host, int port) throws IOException
    {
        FTPClient client = getConnection();

        client.connect(host,port);
        if (!FTPReply.isPositiveCompletion(client.getReplyCode()))
        {
            throw new IOException("connect error");
        }

        return client;
    }

    public static FTPClient getConnection(String host,int port,String username,String password) throws IOException
    {
        FTPClient client = getConnection(host,port);
        if (StringUtils.isNotBlank(username))
        {
            client.login(username,password);
        }

        return client;
    }

    public static void close(FTPClient client) {
        try {
            if (client != null) {
                client.disconnect();
            }
        } catch (IOException e) {

        }
    }

}
