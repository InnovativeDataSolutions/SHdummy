package com.ids.smarthome;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LocationMenu extends AppCompatActivity {
    String namelocation[];
    Context ctx = this;
    String homeid;
    private String[] device_id = new String[4];
    private String[] name = new String[4];
//    private String[] master_id = new String[4];
//    private String[] powerline_id = new String[4];
//    private String[] current_status = new String[4];
//    private String[] command_id = new String[4];
//    private String[] physical_id = new String[4];
//    private String[] internal_id = new String[4];
//    private String[] control_type = new String[4];
//    private String[] device_model = new String[4];
//    private String[] device_type = new String[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle extras = getIntent().getExtras();
        homeid = extras.getString("homeid");

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
                String name = lv2.getItemAtPosition(position).toString();
                //checkfordevice c = new checkfordevice(homeid,name);

            }

        });
    }

    class checkfordevice extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String hotelid = params[0];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://event.idsworld.solutions:85/Hotel/Srv_Reservation/SRV_iCOMM_DeviceStatus/");
                //URL url = new URL("http://1.186.45.172:85/Hotel/Srv_Reservation/SRV_iCOMM_DeviceStatus/");
                String urlParams = "Site_ID=" + hotelid;

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

//            try {
//                JSONObject jObject = new JSONObject(s);
//                final JSONArray jArray = jObject.getJSONArray("DEVICES");
//                for (i = 0; i < jArray.length(); i++) {
//                    try {
//                        JSONObject oneObject = jArray.getJSONObject(i);
//                        // Pulling items from the array
//                        String oneObjectsItem = oneObject.getString("DeviceID");
//                        String oneObjectsItem2 = oneObject.getString("Name");
//                        String oneObjectsItem3 = oneObject.getString("Current_Status");
//                        String oneObjectsItem4 = oneObject.getString("CommandID");
//                        String oneObjectsItem5 = oneObject.getString("PhysicalID");
//                        String oneObjectsItem6 = oneObject.getString("MasterID");
//                        String oneObjectsItem7 = oneObject.getString("PowerlineID");
//                        String oneObjectsItem8 = oneObject.getString("InternalID");
//                        String oneObjectsItem9 = oneObject.getString("DeviceType");
//                        String oneObjectsItem10 = oneObject.getString("ControlType");
//                        String oneObjectsItem11 = oneObject.getString("DeviceModel");
//
//
//                        System.out.println("check for status of switches : " + oneObjectsItem + " " + oneObjectsItem2 + " " + oneObjectsItem3 + " " + oneObjectsItem4 + " " + oneObjectsItem5 + " " + oneObjectsItem6 + " " + oneObjectsItem7 + " " + oneObjectsItem8 + " " + oneObjectsItem9 + " " + oneObjectsItem10 + " " + oneObjectsItem11);
//
//
//                        device_id[i] = oneObjectsItem;
//                        name[i] = oneObjectsItem2;
//                        current_status[i] = oneObjectsItem3;
//                        command_id[i] = oneObjectsItem4;
//                        physical_id[i] = oneObjectsItem5;
//                        master_id[i] = oneObjectsItem6;
//                        powerline_id[i] = oneObjectsItem7;
//                        internal_id[i] = oneObjectsItem8;
//                        device_type[i] = oneObjectsItem9;
//                        control_type[i] = oneObjectsItem10;
//                        device_model[i] = oneObjectsItem11;
//
//
//                        System.out.println(name[i] + " " + "array list");
//
//                    } catch (JSONException e) {
//                        // Oops
//                    }
                }


            }
//    catch (JSONException e) {
//                e.printStackTrace();
//                err = "Exception: " + e.getMessage();
//            }

        }
