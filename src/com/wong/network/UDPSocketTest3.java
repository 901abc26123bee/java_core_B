package com.wong.network;

/*
UDP socket
功能：
    客戶端、服務端你一條、我一條依次發收數據。先從客戶端開始發送信息，輸入q退出
datagramPacket.getSocketAddress() 獲取數據報的中發送端的IP、端口，類型為SocketAddress
* */


import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPSocketTest3 {
    @Test
    public void server() {
        DatagramSocket datagramSocket = null;
        Scanner sc = null;
        try {
            datagramSocket = new DatagramSocket(6060);
            System.out.println("Server already start...");
            sc = new Scanner(System.in);

            while (true) {
                // 接收數據
                byte[] b = new byte[4];
                DatagramPacket datagramPacket = new DatagramPacket(b,0, b.length);
                System.out.print("等待接收客戶端信息... ");
                datagramSocket.receive(datagramPacket);
                String receive = new String(datagramPacket.getData());
                System.out.println("接收到客戶端的信息：" + receive);
// System.out.println("offSet: " + datagramPacket.getOffset()); // 0,就是上面指定的offset值，如果不指定該值，默認為0

                // 發送數據
                String input = sc.next();
                byte[] b1 = input.getBytes();
                DatagramPacket datagramPacket1 = new DatagramPacket(b1, 0, b1.length, datagramPacket.getSocketAddress());
                datagramSocket.send(datagramPacket1);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sc != null) {
                sc.close();
            }
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }
    }

    @Test
    public void client() {
        DatagramSocket datagramSocket = null;
        BufferedReader br = null;
        try {
            datagramSocket = new DatagramSocket();
            System.out.println("Client already start...");
            br = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                // 發送信息
                String data = br.readLine();
                if (data.equalsIgnoreCase("q")) {
                    break;
                }
                byte[] b = data.getBytes();
                DatagramPacket datagramPacket = new DatagramPacket(b, b.length, InetAddress.getByName("127.0.0.1"), 6060);
                datagramSocket.send(datagramPacket);

                // 接收信息
                byte[] b1 = new byte[1024];
                DatagramPacket datagramPacket1 = new DatagramPacket(b1, 0, b.length);
                System.out.print("等待接收服務器端信息... ");
                datagramSocket.receive(datagramPacket1);
                String receive = new String(datagramPacket1.getData());
                System.out.println("接收到服務端的信息：" + receive);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }
    }

}
