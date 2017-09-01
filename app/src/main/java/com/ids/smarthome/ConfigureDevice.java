package com.ids.smarthome;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ConfigureDevice extends AppCompatActivity {

    Context cx = this;
    String SERVER_IP;
    Integer SERVER_PORT;
    private ListView list;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure_device);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        list = (ListView) findViewById(R.id.deviceslv);
        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(ConfigureDevice.this, android.R.layout.simple_spinner_item, arrayList);
        list.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(cx);
                LinearLayout layout = new LinearLayout(ConfigureDevice.this);
                layout.setOrientation(LinearLayout.VERTICAL);

                final EditText roomno = new EditText(ConfigureDevice.this);
                roomno.setHint("IP Address");
                layout.addView(roomno);

                final EditText lastname = new EditText(ConfigureDevice.this);
                lastname.setHint("Port Number");
                layout.addView(lastname);

                alertDialogBuilder.setView(layout);
                alertDialogBuilder.setMessage("Enter IP Adress and Port Number");
                alertDialogBuilder.setPositiveButton("Okay",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                SERVER_IP = roomno.getText().toString();
                                SERVER_PORT = Integer.valueOf(lastname.getText().toString());
                                if(SERVER_PORT.equals("[a-zA-Z]+")){
                                    Toast.makeText(ConfigureDevice.this, "Please only enter number for port", Toast.LENGTH_SHORT).show();

                                }else {
                                    arrayList.add("IP Address : " + SERVER_IP + "  " + "Port : " + SERVER_PORT);
                                    // next thing you have to do is check if your adapter has changed
                                    adapter.notifyDataSetChanged();
                                }

//                        WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
//                        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
//
//                        if (SERVER_IP.contentEquals(ip)){
//                            Toast.makeText(Toggle.this, "IP : " + SERVER_IP + " PORT : " + SERVER_PORT, Toast.LENGTH_SHORT).show();
//                        }else{
//                            Toast.makeText(Toggle.this, "Your IP address is wrong please configure", Toast.LENGTH_SHORT).show();
//                        }
                            }
                        });

                alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        }

        );
}}