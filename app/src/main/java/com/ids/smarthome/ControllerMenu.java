package com.ids.smarthome;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ControllerMenu extends AppCompatActivity {
    Context ctx=this;
    Database db = new Database(ctx);
    Cursor cursor;
    String one,two,tree,four;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        int dups = 0;
        Intent intent = getIntent();
        Bundle extras = getIntent().getExtras();
        final String[] device_id = extras.getStringArray("device_id");
        final String[] name = extras.getStringArray("name");
        final String[] current_status = extras.getStringArray("current_status");
        final String[] command_id = extras.getStringArray("command_id");
        final String[] physical_id = extras.getStringArray("physical_id");
        final String[] master_id = extras.getStringArray("master_id");
        final String[] powerline_id = extras.getStringArray("powerline_id");
        final String[] internal_id = extras.getStringArray("internal_id");
        final String[] device_type = extras.getStringArray("device_type");
        final String[] control_type = extras.getStringArray("control_type");
        final String[] device_model = extras.getStringArray("device_model");

        cursor = db.getip();

        cursor.moveToFirst();
        do{
            one = cursor.getString(0);
            two = cursor.getString(1);
            tree = cursor.getString(2);
            four = cursor.getString(3);
            System.out.println("db info : " + one + two + tree + four);
        }while(cursor.moveToNext());



        final ListView lv2 = (ListView) findViewById(R.id.menulv);

        // Create a List from String Array elements
        final List<String> fruits_list = new ArrayList<String>(Arrays.asList(name));
        // Create an ArrayAdapter from List
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, fruits_list);
        // DataBind ListView with items from ArrayAdapter
        lv2.setAdapter(arrayAdapter);

        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                // When clicked, show a toast with the TextView text
//                Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
//                        Toast.LENGTH_SHORT).show();
                Toast.makeText(ControllerMenu.this, String.valueOf(position),
                        Toast.LENGTH_SHORT).show();

                String device_id2 = device_id[position];
                String name2 = name[position];
                String current_status2 = current_status[position];
                String command_id2 = command_id[position];
                String physical_id2 = physical_id[position];
                String master_id2 = master_id[position];
                String powerline_id2 = powerline_id[position];
                String internal_id2 = internal_id[position];
                String device_type2 = device_type[position];
                String device_model2 = device_model[position];

                if (position == 3){
                    Intent send = new Intent(ControllerMenu.this,FiveGang.class);
                    send.putExtra("name", name2);
                    send.putExtra("current_status", current_status2);
                    send.putExtra("command_id", command_id2);
                    send.putExtra("physical_id", physical_id2);
                    send.putExtra("master_id", master_id2);
                    send.putExtra("powerline_id", powerline_id2);
                    send.putExtra("internal_id", internal_id2);
                    send.putExtra("device_type", device_type2);
                    send.putExtra("device_model", device_model2);
                    startActivity(send);
                }else if(position == 2){
                    Intent send = new Intent(ControllerMenu.this, FourGang.class);
                    send.putExtra("name", name2);
                    send.putExtra("current_status", current_status2);
                    send.putExtra("command_id", command_id2);
                    send.putExtra("physical_id", physical_id2);
                    send.putExtra("master_id", master_id2);
                    send.putExtra("powerline_id", powerline_id2);
                    send.putExtra("internal_id", internal_id2);
                    send.putExtra("device_type", device_type2);
                    send.putExtra("device_model", device_model2);
                    startActivity(send);
                }else if(position == 1){
                    Intent send = new Intent(ControllerMenu.this, ThreeGang.class);
                    send.putExtra("name", name2);
                    send.putExtra("current_status", current_status2);
                    send.putExtra("command_id", command_id2);
                    send.putExtra("physical_id", physical_id2);
                    send.putExtra("master_id", master_id2);
                    send.putExtra("powerline_id", powerline_id2);
                    send.putExtra("internal_id", internal_id2);
                    send.putExtra("device_type", device_type2);
                    send.putExtra("device_model", device_model2);
                    startActivity(send);
                }else{
                    Intent send = new Intent(ControllerMenu.this, TwoGang.class);
                    send.putExtra("name", name2);
                    send.putExtra("current_status", current_status2);
                    send.putExtra("command_id", command_id2);
                    send.putExtra("physical_id", physical_id2);
                    send.putExtra("master_id", master_id2);
                    send.putExtra("powerline_id", powerline_id2);
                    send.putExtra("internal_id", internal_id2);
                    send.putExtra("device_type", device_type2);
                    send.putExtra("device_model", device_model2);
                    startActivity(send);
                }
            }

        });
    }

    class checkfordevice extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String hotelid = params[0];
//            String ip = params[1];
//            String homeid = params[2];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://event.idsworld.solutions:85/Hotel/Srv_Reservation/SRV_iCOMM_DeviceStatus/");
                //URL url = new URL("http://1.186.45.172:85/Hotel/Srv_Reservation/SRV_iCOMM_DeviceStatus/"); + "&ip=" + ip + "&homeid=" + homeid
                String urlParams = "Site_ID="+hotelid;

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();
                InputStream is = httpURLConnection.getInputStream();
                while ((tmp = is.read()) != -1) {
                    data += (char) tmp;
                }
                is.close();
                httpURLConnection.disconnect();

                return data;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            String err = null;
            if (s.equals("")) {
                s = "Data saved successfully.";
            }
        }

    }

}
