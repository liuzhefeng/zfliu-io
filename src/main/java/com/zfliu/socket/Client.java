package com.zfliu.socket;


import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {

        final String QUIT = "quit";
        final String DEFAULT_SERVER_HOST = "127.0.0.1";
        final int DEFAULT_SERVER_PORT = 8888;
        Socket socket = null;
        BufferedWriter writer = null;

        try {
            // ����socket
            socket = new Socket(DEFAULT_SERVER_HOST, DEFAULT_SERVER_PORT);

            // ����IO��
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            writer = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())
            );

            // �ȴ��û�������Ϣ
            BufferedReader consoleReader =
                    new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String input = consoleReader.readLine();

                // ������Ϣ��������
                writer.write(input + "\n");
                writer.flush();

                // ��ȡ���������ص���Ϣ
                String msg = reader.readLine();
                System.out.println(msg);

                // �鿴�û��Ƿ��˳�
                if (QUIT.equals(input)) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                    System.out.println("�ر�socket");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}

