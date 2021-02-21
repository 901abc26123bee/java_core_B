package com.wong.network;

/*
題目：
    從客戶端發送文件給服務端，服務端保存到本地。並返回“發送成功”給客戶端。並關閉相應的連接
* */


import org.junit.Test;


import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPSocketTest3 {
	/*
    先啟動 server，再啟動client
    客戶端連接的端口要與服務器端偵聽的端口相同
    * */
    @Test
    public void server() {
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream inputStream = null;
        FileOutputStream fos = null;
        ByteArrayOutputStream baos = null;
        OutputStream outputStream = null;
        try {
        	// 1. 創建ServerSocket對象，並指定監聽的port
            serverSocket = new ServerSocket(8090);
            // 2. 調用 ServerSocket對象.accept()方法獲得socket ---> 收客戶端socket消息
            socket = serverSocket.accept();
            // 3. 通過Socket對象.getInputStream()獲取InputStream對象
            inputStream = socket.getInputStream();
            // 4. 通過InputStream對象 讀取內容，放入一個文件中
            fos = new FileOutputStream(new File("image_server_recive.png"));
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            // 這裡使用 ByteArrayOutputStream() 接手的圖檔會打不開，因為ByteArrayOutputStream()寫完之後，
            // 是調用toByteArray()方法，以字節數組的形式獲得所有 ByteArrayOutputStream()中已寫的數據
//            baos = new ByteArrayOutputStream();
//            byte[] buffer = new byte[1024];
//            int len;
//            while((len=inputStream.read(buffer)) != -1) {
//            	baos.write(buffer, 0, len);
//            }
            System.out.println("文件接收成功");

            // 5. 響應客戶端
            outputStream = socket.getOutputStream();
            outputStream.write("發送成功".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 關閉流、socket
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if(baos != null) {
                	baos.close();
                }
                if (fos != null) {
                    fos.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (socket != null) {
                    socket.close();
                }
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    @Test
    public void client() {
        Socket socket = null;
        OutputStream outputStream = null;
        FileInputStream fis = null;
        InputStream inputStream = null;
        ByteArrayOutputStream baos = null;
        try {
        	// 1. 創建 Socket對象，並指定要連接的服務器的IP、端口
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 8090);
            // 2. 通過Socket對象獲得OutputStream流
            outputStream = socket.getOutputStream();
            // 3. 通過OutputStream流輸出內容 
            // FileInputStream 先讀入檔案，在寫入 socket 的 outputStream
            fis = new FileInputStream(new File("image.png"));
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            // 文件發送完畢
            socket.shutdownOutput(); // 關閉socket的OutputStream流 --> 沒有關閉的情況下，對端的服務器 InputStream對象.read()方法將一直阻塞。

            // 6. 接收服務器信息
            inputStream = socket.getInputStream();
            baos = new ByteArrayOutputStream();
            byte[] buffer2 = new byte[1024];
            int len2;
            while ((len2 = inputStream.read(buffer2)) != -1) {
                baos.write(buffer2, 0, len2);
            }
            System.out.println("接收到服務器端的信息：" + baos.toString());
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
            	if(baos != null) {
            		baos.close();
            	}
                if (inputStream != null) {
                    inputStream.close();
                }
                if (fis != null) {
                    fis.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
