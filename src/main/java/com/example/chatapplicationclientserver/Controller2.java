package com.example.chatapplicationclientserver;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.Socket;

public class Controller2 {
    @FXML
    private TextField PortID, HostID, msgfield;
    @FXML
    private ListView<String> testView;

    PrintWriter pw;


    @FXML
    protected void onConnect() throws IOException {
        String host = HostID.getText();
        int port = Integer.parseInt(PortID.getText());
        //Socket
        Socket s = new Socket(host,port);
        InputStream is = null; //octet
        try {
            is = s.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        InputStreamReader isr = new InputStreamReader(is); //caractere
        BufferedReader br = new BufferedReader(isr); //Chaîne de caractére
        OutputStream os = null;
        try {
            os = s.getOutputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String Ip = s.getRemoteSocketAddress().toString();
        pw = new PrintWriter(os, true);
        new Thread (()-> {
            while(true){
                try {
                    //we save the response in this variable reponse
                    String reponse = br.readLine();
                    Platform.runLater(()->{
                        testView.getItems().add(reponse);
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public void onSend(ActionEvent actionEvent) {
        String message = msgfield.getText();
        pw.println(message);
        testView.getItems().add(message);

    }
}