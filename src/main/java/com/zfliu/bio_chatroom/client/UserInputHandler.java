package com.zfliu.bio_chatroom.client;

import java.io.*;

public class UserInputHandler implements Runnable {

    private ChatClient chatClient;

    public UserInputHandler(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public void run() {
        try {
            // �ȴ��û�������Ϣ
            BufferedReader consoleReader =
                    new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String input = consoleReader.readLine();
                // �������������Ϣ
                chatClient.send(input);
                //���ܷ�����������Ϣ
                System.out.println("������������Ϣ" + chatClient.receive());
                // ����û��Ƿ�׼���˳�
                if (chatClient.readyToQuit(input)) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}