package com.zfliu.bio_chatroom.server;

import java.io.*;
import java.net.Socket;

public class ChatHandler implements Runnable {

    private ChatServer server;
    private Socket socket;

    public ChatHandler(ChatServer server, Socket socket) {
        this.server = server;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // �洢�������û�
            server.addClient(socket);
            // ��ȡ�û����͵���Ϣ
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            String msg = null;
            while ((msg = reader.readLine()) != null) {
                String fwdMsg = "�ͻ���[" + socket.getPort() + "]: " + msg + "\n";
                System.out.print(fwdMsg);
                // ����Ϣת���������������ߵ������û�
                server.forwardMessage(socket, fwdMsg);
                // ����û��Ƿ�׼���˳�
                if (server.readyToQuit(msg)) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                server.removeClient(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}