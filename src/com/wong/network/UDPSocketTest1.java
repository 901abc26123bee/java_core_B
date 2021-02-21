package com.wong.network;

/*
UDP socket 網絡編程
功能：
客戶端發信息到服務端，服務器端接收信息
# DatagramSocket類
## 構造器
DatagramSocket() 創建一個數據報socket，不綁定IP、端口
protected DatagramSocket(DatagramSocketImpl impl) 創建一個未綁定IP、端口的 DatagramScoket對象，並指定DatagramSocketImpl
DatagramSocket(int port) 創建一個DatagramSocket對象，並綁定指定的port端口，IP默認為0.0.0.0
DatagramSocket(int port, InetAddress laddr) 創建一個DatagramSocket對象,綁定指定的IP(laddr)、端口(port)
DatagramSocket(SocketAddress bindaddr) 創建一個DatagramSocket對象，綁定指定的SocketAddress，如：new DatagramSocket(new InetSocketAddress("10.100.0.2", 3030))
## 方法
void bind(SocketAddress addr) 綁定Socket地址，即綁定IP和端口
void close() 關閉此數據報socket
void connect(InetAddress address, int port) 連接到指定IP、端口
void connect(SocketAddress addr) 連接到指定的Socket地址([ip, 端口])
void disconnect() 斷開數據報socket連接，如果socket已經關閉或未連接，則沒有任何影響
boolean getBroadcast() 測試SO_BROADCAST 是否是開啟
DatagramChannel getChannel() 返回唯一DatagramChannel，如果DatagramChannel 存在的話
InetAddress getLocalAddress() 獲取socket本地的InetAddress
int getLocalPort() 獲取socket在本地綁定的端口
SocketAddress getLocalSocketAddress() 獲取socket本地的SocketAddress(即[ip, 端口])
InetAddress getInetAddress() 獲取socket遠端的InetAddress地址
int getPort() 獲取此socket遠端的端口
SocketAddress getRemoteSocketAddress() 獲取socket遠端的SocketAddress
int getReceiveBufferSize() 獲取SO_RCVBUF的值，即平台用在此DatagramSocket上的輸入的緩衝大小
boolean getReuseAddress() 測試SO_REUSEADDR 是否是開啟，即SocketAddress是否可複用。
int getSendBufferSize() 獲取SO_SNDBUF的值，即平台用在此DatagramSocket上的輸出的緩衝大小
int getSoTimeout() 獲取SO_TIMEOUT值，即socket的超時時間，默認為0，單位為：毫秒，即無限超時
int getTrafficClass() Gets traffic class or type-of-service in the IP datagram header for packets sent from this DatagramSocket.
boolean isBound() 返回此socket是否是已經綁定好IP、端口
boolean isClosed() 返回此socket是否是關閉
boolean isConnected() 返回此socket是否是連接
void receive(DatagramPacket p) 從此socket上用一個指定數據報包來接收一個數據報包
void send(DatagramPacket p) 從此socket上發送一個指定的數據報包p
void setBroadcast(boolean on) 設置SO_BROADCAST開啟/關閉
static void setDatagramSocketImplFactory(DatagramSocketImplFactory fac) Sets the datagram socket implementation factory for the application.
void setReceiveBufferSize(int size) 設置SO_RCVBUF大小，設置socket接收緩衝區大小
void setReuseAddress(boolean on) 設置此socket SO_REUSEADDR值，開啟/關閉此SocketAddress是否可複用
void setSendBufferSize(int size) 設置 SO_SNDBUF 大小，設置socket發送緩衝區大小
void setSoTimeout(int timeout) 設置此socket超時時間，單位milliseconds 毫秒
void setTrafficClass(int tc) Sets traffic class or type-of-service octet in the IP datagram header for datagrams sent from this DatagramSocket.
# DatagramPacket類
## 構造器
DatagramPacket(byte[] buf, int length) 創建一個DatagramPacket對象，用於接收數據報包，使用字節數組buf來存放，長度length，默認從0開始
DatagramPacket(byte[] buf, int length, InetAddress address, int port) 創建一個DatagramPacket對象，用於發送數據報包，內容為buf，長度為length，默認從0開始，指定接收端的IP為address，端口為port
DatagramPacket(byte[] buf, int offset, int length) 創建一個DatagramPacket對象，用於接收數據報包，使用字節數組buf來存放，長度length，從offset開始
DatagramPacket(byte[] buf, int offset, int length, InetAddress address, int port) 創建一個DatagramPacket對象，用於發送數據報包，內容為buf，長度為length，從offset開始，指定接收端的IP為address，端口為port
DatagramPacket(byte[] buf, int offset, int length, SocketAddress address) 創建一個DatagramPacket對象，用於發送數據報包，內容為buf，長度為length，從offset開始，並指定SocketAddress為address
DatagramPacket(byte[] buf, int length, SocketAddress address) 創建一個DatagramPacket對象，用於發送數據報包，內容為buf，長度為length，默認從0開始，並指定SocketAddress為address
## 方法
InetAddress getAddress() 返回發送數據報中的接收端的IP 或 返回接收數據報中的發送端的IP
byte[] getData() 從接收到或要發送的數據報包中獲取緩衝數據，從偏移量offset開始讀取length個字節
int getLength() 返回接收到或要發送的數據報包數據的大小
int getOffset() 返回讀取數據報包中數據的指定的偏移量，不指定默認為0
int getPort() 返回發送或接收數據報中遠端的端口
SocketAddress getSocketAddress() 返回發送或接收數據報中遠端的SocketAddress，包含了IP、端口，接收端要知道數據報包從哪發過來的，用此方法就可以知道發送端的IP和端口
void setAddress(InetAddress iaddr) 設置要發送數據報的接收端的IP
void setPort(int iport) 設置要發送數據報的接收端的端口
void setData(byte[] buf) 設置此數據報包的數據緩衝區
void setData(byte[] buf, int offset, int length) 設置此數據報包的數據緩衝區，指定從偏移量offset開啟，length個長度
void setLength(int length) 設置數據報包的長度
void setSocketAddress(SocketAddress address)
* */

import org.junit.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSocketTest1 {
    @Test
    public void server() {
    	// receiver
        DatagramSocket datagramSocket = null;
        try {
        	// 1.
            datagramSocket = new DatagramSocket(9090);
            System.out.println("服務端啟動好了...");
            // 2.
            byte[] b = new byte[1024];
            DatagramPacket datagramPacket = new DatagramPacket(b, 0, b.length);
            // 3.
            datagramSocket.receive(datagramPacket); // 這裡阻塞，直接有客戶連接進來
            System.out.println("客戶端IP信息：" + datagramPacket.getSocketAddress());
            // datagramPacket.getData()-->byte[], 
            String str = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	// 4.
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }
    }

    @Test
    public void client() {
    	// sender
        DatagramSocket datagramSocket = null;
        try {
            // 1.創建DatagramSocket對象
            datagramSocket = new DatagramSocket();
            System.out.println("UDP 客戶端啟動好...");
            
            // 2.創建數據報
            // 要發送的內容，轉成字節數組
            String str = "ＵＤＰ方式測試";
            byte[] b = str.getBytes();
            // 創建數據報，每個數據報不能大於64K，每個數據報都記錄了數據信息、發送端的IP、發送端的端口、接收端的IP、接收端的端口
            // 發送的數據報，只需要顯式指定數據信息、接收端的IP、接收端的端口，發送端的IP(本端)、發送端的端口(本端)會自動添加到包中
            DatagramPacket datagramPacket = new DatagramPacket(b, 0, b.length,
                    InetAddress.getByName("127.0.0.1"), 9090);
            // 3.調用datagramSocket.send(數據報對象)，把數據報發出去
            datagramSocket.send(datagramPacket);
            System.out.println("信息發送完畢.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	// 4.關閉資源
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }
    }

}