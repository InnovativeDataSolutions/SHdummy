package com.ids.smarthome;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class OneGang extends AppCompatActivity {
    Button on,off;
    Handler UIhandler;
    Context cx = this;
    Socket socket = null;
    Thread Thread1 = null;
    Thread Thread2 = null;
    Thread Thread1bg = null;
    private PrintWriter mBufferOut;
    public static final String SERVER_IP = "192.168.1.9"; //server IP address192.168.1.125
    public static final int SERVER_PORT = 8081;
    Protocol math;
    String click = null;
    String protocolON;
    String protocolOFF;
    String protocolONM;
    String protocolOFFM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_gang);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        on = (Button)findViewById(R.id.btnon1g);
        off = (Button)findViewById(R.id.btnoff1g);

        Bundle extras = getIntent().getExtras();
        String device_id = extras.getString("device_id");
        String name = extras.getString("name");
        String current_status = extras.getString("current_status");
        String command_id = extras.getString("command_id");
        String physical_id = extras.getString("physical_id");
        String master_id = extras.getString("master_id");
        String powerline_id = extras.getString("powerline_id");
        String internal_id = extras.getString("internal_id");
        String device_type = extras.getString("device_type");
        String control_type = extras.getString("control_type");
        String device_model = extras.getString("device_model");

        UIhandler = new Handler();

        this.Thread1bg = new Thread(new Thread1bg());
        this.Thread1bg.start();
    }

    public String bytesToHex(byte[] in) {
        final StringBuilder builder = new StringBuilder();
        for (byte b : in) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

    public byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    class Thread1bg implements Runnable {
        public void run() {
            //Socket socket = null;
            try {
                //here you must put your computer's IP address.
                InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
                socket = new Socket(serverAddr, SERVER_PORT);

                Thread2 commThread2 = new Thread2(socket);
                new Thread(commThread2).start();
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class Thread1 implements Runnable {
        public void run() {
            //Socket socket = null;
            try {
                InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
                socket = new Socket(serverAddr, SERVER_PORT);
                OutputStream out = socket.getOutputStream();
                    byte[] by = hexStringToByteArray("02 03 00 00 00 83 03 47 00 00 00 00 00 00 00 30 01 01 AB 03".replaceAll(" ", ""));
                    out.write(by, 0, by.length);
                    out.flush();


                Thread2 commThread = new Thread2(socket);
                new Thread(commThread).start();
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class Thread2 implements Runnable {

        public Thread2(Socket socket) {
            InputStream in = null;
            try {
                in = socket.getInputStream();
            } catch (IOException ex) {
                System.out.println("Can't get socket input stream. ");
            }

            System.out.println("Waiting Socket events ....");
            byte[] bytes = new byte[1];
            int count;

            String input_str = "";
            String temp_prev = "";
            String temp_curr = "";
            try {
                while ((count = in.read(bytes)) > 0) {
                    temp_curr = math.bytesToHex(bytes).toUpperCase();
                    if ((temp_prev + temp_curr).equals("0003") || (temp_prev + temp_curr).equals("AB03") ) {
                        input_str += temp_curr;

                        System.out.print(input_str.toUpperCase());
                        UIhandler.post(new updateUIThread(input_str.toUpperCase()));
                        input_str += temp_curr;
                        input_str = "";
                        System.out.println();
                    } else {
                        input_str += temp_curr;
                    }
                    temp_prev = temp_curr;
                }

            } catch (Exception ex) {
                try {
                    socket.close();
                    System.out.println("Socket Closed [in]");
                } catch (IOException ex1) {
                    System.exit(0);
                }
            }
        }

        public void run() {
            //do nothing
        }
    }

    class Thread3 implements Runnable {
        public void run() {
            //Socket socket = null;
            try {
                InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
                socket = new Socket(serverAddr, SERVER_PORT);
                OutputStream out = socket.getOutputStream();
                    byte[] by = math.hexStringToByteArray("02 03 00 00 00 83 03 47 00 00 00 00 00 00 00 30 01 01 AB 03".replaceAll(" ", ""));
                    out.write(by, 0, by.length);
                    out.flush();


                Thread2 commThread = new Thread2(socket);
                new Thread(commThread).start();
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class updateUIThread implements Runnable {
        private String message;

        public updateUIThread(String str) {
            this.message = str;
        }

        @Override
        public void run() {
            if (message.equals("02370138023901400341470000000000000049304A0101AB03") || message.equals("02370138023901400341470000000000000049304A01010003") ){
//                btnon1.setVisibility(View.VISIBLE);
//                btnoff1.setVisibility(View.GONE);
            }
            else if (message.equals("02370138023901400341470000000000000049304A0102AB03") || message.equals("02370138023901400341470000000000000049304A01020003")){
//                btnoff1.setVisibility(View.VISIBLE);
//                btnon1.setVisibility(View.GONE);
            }
        }
    }




    public void btnoff1g(View view){
        on.setVisibility(View.VISIBLE);
        off.setVisibility(View.GONE);
    }

    public void btnon1g(View view){
        off.setVisibility(View.VISIBLE);
        on.setVisibility(View.GONE);
    }

}
