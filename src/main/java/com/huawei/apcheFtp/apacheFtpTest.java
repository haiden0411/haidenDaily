package com.huawei.apcheFtp;
import com.huawei.utils.FtpUtil;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPCmd;
import org.apache.commons.net.ftp.FTPFile;

import java.io.IOException;
/**
 * Author：胡灯
 * Date：2019-08-17 11:52
 * Description：<描述>
 */
public class apacheFtpTest
{
    public static void main(String[] args) throws IOException
    {
        FTPClient client = FtpUtil.getConnection("192.168.1.109", 2121, "icpci", "admin");
        client.changeWorkingDirectory("test");
        client.sendCommand("mdelete","*");
        client.enterLocalPassiveMode();
        FTPFile[] ftpFiles = client.listFiles();
        for (FTPFile ftpFile : ftpFiles)
        {
            System.out.println(ftpFile.getName());
        }
        FtpUtil.close(client);
    }
}
