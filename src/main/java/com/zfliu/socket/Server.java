package com.zfliu.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {

        final String QUIT = "quit";
        final int DEFAULT_PORT = 8888;
        ServerSocket serverSocket = null;

        try {
            // �󶨼����˿�
            serverSocket = new ServerSocket(DEFAULT_PORT);
            System.out.println("�����������������˿�" + DEFAULT_PORT);

            while (true) {
                // �ȴ��ͻ�������
                Socket socket = serverSocket.accept();
                System.out.println("�ͻ���[" + socket.getPort() + "]������");
                /**
                 * װ����ģʽ
                 * */
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream())
                );

                String msg = null;
                while ((msg = reader.readLine()) != null) {
                    // ��ȡ�ͻ��˷��͵���Ϣ
                    System.out.println("�ͻ���[" + socket.getPort() + "]: " + msg);

                    // �ظ��ͻ����͵���Ϣ
                    writer.write("������: " + msg + "\n");
                    writer.flush();

                    // �鿴�ͻ����Ƿ��˳�
                    if (QUIT.equals(msg)) {
                        System.out.println("�ͻ���[" + socket.getPort() + "]�ѶϿ�����");
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                    System.out.println("�ر�serverSocket");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}

