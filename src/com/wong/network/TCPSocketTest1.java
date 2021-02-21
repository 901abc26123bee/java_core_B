package com.wong.network;

/*
TCP socket編程
題目：
    客戶端發送內容給服務端，服務端將內容打印到控制台上。
# ServerSocket類
## 構造器(沒有特殊說明時，都是public構造器)
ServerSocket() 創建未綁定IP、端口等的ServerSocket對象
ServerSocket(int port) 創建服務器端的ServerSocket對象，指定要綁定的監聽端口，綁定所有IP(即0.0.0.0)，端口範圍：[0, 65535]，0：自動分配端口，下同; 請求連接隊列的最大長度為50
ServerSocket(int port, int backlog) 建服務器端的ServerSocket對象，指定要綁定的監聽端口，綁定所有IP(即0.0.0.0)，指定請求連接隊列的最大長度
ServerSocket(int port, int backlog, InetAddress bindAddr) 建服務器端的ServerSocket對象，指定要綁定的監聽端口，指定請求連接隊列的最大長度，指定綁定的IP(InetAddress對象)
## 方法(沒有特殊說明，都是public方法)
Socket accept() 創建並返回一個Socket對象，開始偵聽該socket並接收請求連接，阻塞的，直到有請求連接進來
void bind(SocketAddress endpoint) 綁定SocketAddress，即綁定IP和端口，如 ServerSocket對象.bind(new InetSocketAddress(InetAddress.getByName("hostName")), 端口)
void bind(SocketAddress endpoint, int backlog) 綁定SocketAddress，並指定請求連接隊列的最大長度
void close() 關閉此socket
ServerSocketChannel getChannel() 返回此ServerSocket對象相關的唯一的ServerSocketChannel 對象
InetAddress getInetAddress() 獲取此socket的IP信息
int getLocalPort() 獲取偵聽的端口
SocketAddress getLocalSocketAddress() 獲取綁定的IP信息
int getReceiveBufferSize()
boolean getReuseAddress() 獲取請求客戶端的address信息
int getSoTimeout() 獲取socket 超時設置值
protected void implAccept(Socket s) 重寫accept()方法
boolean isBound() 返回ServerSocket是否已經綁定
oolean isClosed() 返回ServerSocket是否已關閉
void setPerformancePreferences(int connectionTime, int latency, int bandwidth) 設置此ServerSocket性能偏好：
                connectionTime：連接保持時間，對於短鏈接來說此參數相對重要
                latency：延遲時間，對於要求低延遲的連接，此參數相對重要
                bandwidth：帶寬，如要求帶寬比較高的，此參數比較重要
void setReceiveBufferSize(int size) 重置socket接收緩存的大小，默認的大小將被修改
void setReuseAddress(boolean on) 開啟/關閉 SO_REUSEADDR socket 選項，當需要使用多進程時，需要開啟address重用
static void setSocketFactory(SocketImplFactory fac)
void setSoTimeout(int timeout) 設置socket的超時時間，單位ms，0：表示不超時
String toString()
# Socket類
## 構造器
public Socket() 創建一個未綁定IP、端口等的Socket對象
public Socket(InetAddress address, int port) throws IOException 創建一個流Socket對象(即TCP socket)，並綁定IP、端口
public Socket(String host, int port) throws UnknownHostException, IOException 創建一個流Socket對象(即TCP socket)，並綁定IP、端口
public Socket(InetAddress address, int port, InetAddress localAddr, int localPort) throws IOException 創建一個Socket對象，指定連接遠端的IP和端口，同時綁定本地的IP和端口
public Socket(String host, int port, InetAddress localAddr, int localPort) throws IOException 創建一個Socket對象，指定連接遠端的IP和端口，同時綁定本地的IP和端口
public Socket(InetAddress host, int port, boolean stream) throws IOException // Deprecated. 創建一個綁定了IP和端口的流Socket對象(TCP)或數據報Socket對象(UDP)，stream為true時創建流Socket對象，stream為false時創建數據報Socket
public Socket(String host, int port, boolean stream) throws IOException // Deprecated. 創建一個綁定了IP和端口的流Socket對象(TCP)或數據報Socket對象(UDP)，stream為true時創建流Socket對象，stream為false時創建數據報Socket
public Socket(Proxy proxy) 創建一個未連接的代理Socket，使用代理的設置，如調用代理的IP、端口，
    示例：
    Socket s = new Socket(Proxy.NO_PROXY); will create a plain socket ignoring any other proxy configuration.
    Socket s = new Socket(new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("socks.mydom.com", 1080))); will create a socket connecting through the specified SOCKS proxy server.
protected Socket(SocketImpl impl) throws SocketException 創建一個由用戶實現的SocketImpl且未連接的Socket對象。

## 方法
void bind(SocketAddress bindpoint) 綁定SocketAddress，即IP和端口
void close() 關閉此socket
void connect(SocketAddress endpoint) 連接此socket到服務器
void connect(SocketAddress endpoint, int timeout) 連接此socket到服務器，並指定連接超時時間
SocketChannel getChannel() 返回唯一的SocketChannel 對象，如果存在的話
InetAddress getInetAddress() 返回此socket連接到遠端的IP
InputStream getInputStream() 獲取此socket的InputStream輸入流，此InputStream.read()、InputStream.read(byte[] b) 都是是阻塞的
boolean getKeepAlive() 測試SO_KEEPALIVE 是否開啟，返回此socket是否開啟回話保持
InetAddress getLocalAddress() 獲取此socket綁定的本地IP
int getLocalPort() 獲取此socket綁定的本地端口
SocketAddress getLocalSocketAddress() 獲取此socket綁定的本地SocketAddress信息，即綁定的本地IP、本地端口
boolean getOOBInline() 獲取此socket的SO_OOBINLINE是否開啟
OutputStream getOutputStream() 獲取此socket的OutputStream輸出流，此OutputStream.write(byte[] b) 非阻塞的
int getPort() 返回此socket連接的遠端端口
int getReceiveBufferSize() 獲取此socket的SO_RCVBUF值
SocketAddress getRemoteSocketAddress() 返回此socket連接著遠端的SocketAddress信息(IP、port)
boolean getReuseAddress() 獲取SO_REUSEADDR是否可重用
int getSendBufferSize() 獲取此socket的SO_SNDBUF返送緩衝大小
int getSoLinger() 獲取 SO_LINGER值
int getSoTimeout() 獲取此socket的SO_TIMEOUT設置的值
boolean getTcpNoDelay() 獲取此socket的TCP_NODELAY是否開啟，關閉Nagle算法，即要發送到網絡的數據不緩衝
int getTrafficClass() 從發送的IP頭包裡獲取traffic跟踪類或服務類型
boolean isBound() 返回此socket是綁定狀態
boolean isClosed() 返回此socket是關閉狀態
boolean isConnected() 返回此socket是連接狀態
boolean isInputShutdown() 在此socket輸入流讀取過程中，返回此socket連接是否為是關閉狀態，是關閉則返回true
boolean isOutputShutdown() 在此socket輸出流讀取過程中，返回此socket連接是否為是關閉狀態，是關閉則返回true
void sendUrgentData(int data) 發送一個字節的緊急數據到此socket
void setKeepAlive(boolean on) 設置此suocket的SO_KEEPALIVE值，即socket TCP的超時時間
void setOOBInline(boolean on)
void setPerformancePreferences(int connectionTime, int latency, int bandwidth) 設置此Socket性能偏好：
                connectionTime：連接保持時間，對於短鏈接來說此參數相對重要
                latency：延遲時間，對於要求低延遲的連接，此參數相對重要
                bandwidth：帶寬，如要求帶寬比較高的，此參數比較重要
void setReceiveBufferSize(int size) 設置此socket的SO_RCVBUF值
void setReuseAddress(boolean on) 設置address是否可重用
void setSendBufferSize(int size) 設置SO_SNDBUF值
static void setSocketImplFactory(SocketImplFactory fac)
void setSoLinger(boolean on, int linger) 開啟/關閉 SO_LINGER，指定linger時間為linger，單位s
void setSoTimeout(int timeout) 設置此socket超時時間(單位ms)，以timeout為0時無限超時，read()將一直阻塞，如果timeout > 0,在read()時做多阻塞timeout 毫秒，超時後拋出java.net.SocketTimeoutException異常
void setTcpNoDelay(boolean on) 設置此socket的TCP_NODELAY 值
void setTrafficClass(int tc)
void shutdownInput() 關閉此socket的InputStream流，在read socket InputStream時，調用此方法後，InputStream的read()方法返回-1，其他可用方法都將返回0，不可恢復
void shutdownOutput() 關閉此socket的OutputStream流。對於TCP，調用此方法前需要發送的數據還未完成發送的將繼續正常的連接終止順序發送，不可恢復
String toString() //"Socket[addr=" + getImpl().getInetAddress() +
                    ",port=" + getImpl().getPort() +
                    ",localport=" + getImpl().getLocalPort() + "]";
注意：
調用socket.close() 或者socket.shutdownOutput()方法，都會結束客戶端socket，且不可恢復。
socket.close() 將socket關閉連接，那邊如果有服務端給客戶端反饋信息，此時客戶端是收不到的。
socket.shutdownOutput() 是將輸出流關閉，此時，如果服務端有信息返回，則客戶端是可以正常接受的
inputStream.readAllBytes()，只有等對端的socket關閉了，才能讀取完成，是阻塞的
* */


import org.junit.Test;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPSocketTest1 {
    /*
    先啟動 server，再啟動client
    客戶端連接的端口要與服務器端偵聽的端口相同
    * */

    @Test
    public void server() {
        /*
        服務器端
        * */
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream inputStream = null;
        ByteArrayOutputStream baos = null;
        try {
            // 1. 創建ServerSocket對象，並指定監聽的port
            serverSocket = new ServerSocket(9090);
            // System.out.println(serverSocket.toString()); // ServerSocket[addr=0.0.0.0/0.0.0.0,localport=9090]
            // 2. 調用 ServerSocket對象.accept()方法獲得socket ---> 收客戶端socket消息
            socket = serverSocket.accept();
            // 3. 通過Socket對象.getInputStream()獲取InputStream對象
            inputStream = socket.getInputStream();
            // 4. 通過InputStream對象 讀取內容
            
            // 不建議醬寫，會有亂碼問題（控制台輸出時）
//            byte[] b = new byte[1024];
//            int len;
//            while ((len = inputStream.read(b)) != -1) {
//                String s = new String(b, 0, len);
//                System.out.println(s);
//            }
            
            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[5];
            int len;
            while((len=inputStream.read(buffer)) != -1) {
            	baos.write(buffer, 0, len);
            }
            System.out.println(baos.toString());
            System.out.println("此連接的客戶IP：" + socket.getInetAddress().getHostAddress());
            System.out.println("此連接的客戶address：" + socket.getInetAddress().getHostAddress());
            System.out.println("ServerSocket超時時間(s):" + serverSocket.getSoTimeout());
            System.out.println("ServerSocket本地IP信息:" + serverSocket.getLocalSocketAddress());
            System.out.println(socket.getLocalSocketAddress());
            System.out.println("此連接的遠端客戶IP、端口信息" + socket.getRemoteSocketAddress()); // 如 /127.0.0.1:57870
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	if(baos != null) {
        		try {
					baos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
            // 5. 關閉InputStream流、Socket、ServerSocket連接
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Test
    public void client() {
        /*
        客戶端
        * */
        Socket socket = null;
        OutputStream outputStream = null;
        try {
            // 1. 創建 Socket對象，並指定要連接的服務器的IP、端口
// socket = new Socket("localhost", 9090);
            socket = new Socket(InetAddress.getByName("localhost"), 9090);
            // 2. 通過Socket對象獲得OutputStream流
            outputStream = socket.getOutputStream();
            // 3. 通過OutputStream流輸出內容
            outputStream.write("哈哈，你看到你還在線呢...".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
             // 4. 關閉OutputStream流、Socket連接
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

