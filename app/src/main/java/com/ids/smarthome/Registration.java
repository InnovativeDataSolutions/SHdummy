package com.ids.smarthome;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Registration extends AppCompatActivity {
    EditText unET, pwET,homeidET,gatewayET,ipET;
    TextView tv;
    boolean dbcheck;
    Button regBUT,submitBUT;
    Context ctx=this;
    Database db = new Database(ctx);
    String homeid,un, pw, siteid = "H001", oneObjectsItem2,ip,gateway,oneObjectsItem3,oneObjectsItem4,oneObjectsItem,oneObjectsItem5;
    int i,j;
    private String[] device_id = new String[4];
    private String[] name = new String[4];
    private String[] master_id = new String[4];
    private String[] powerline_id = new String[4];
    private String[] current_status = new String[4];
    private String[] command_id = new String[4];
    private String[] physical_id = new String[4];
    private String[] internal_id = new String[4];
    private String[] control_type = new String[4];
    private String[] device_model = new String[4];
    private String[] device_type = new String[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        homeidET = (EditText) findViewById(R.id.homeidET);
        unET = (EditText) findViewById(R.id.usernameET);
        pwET = (EditText) findViewById(R.id.passwordET);
        gatewayET = (EditText) findViewById(R.id.gatewayET);
        ipET = (EditText) findViewById(R.id.ipaddressET);
        regBUT = (Button) findViewById(R.id.regbtn);
        submitBUT = (Button) findViewById(R.id.submitbtn);
        tv = (TextView) findViewById(R.id.tvinfo);

//        checkfordevice c = new checkfordevice();
//        c.execute(siteid);
        //lv2 = (ListView) findViewById(R.id.lv2);




    }

    class submitinfo extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String homeid = params[0];
            String username = params[1];
            String password = params[2];
            String gateway = params[3];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://centraserv.idsworld.solutions:50/v1/Ape_srv/Register/");
                //URL url = new URL("http://1.186.45.172:85/Hotel/Srv_Reservation/SRV_iCOMM_DeviceStatus/");
                String urlParams = "homeid=" + homeid + "&username=" + username + "&password=" + password + "&gateway=" + gateway;

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

            try {
                JSONObject jObject = new JSONObject(s);
                final JSONArray jArray = jObject.getJSONArray("DEVICES");
                for (i = 0; i < jArray.length(); i++) {
                    try {
                        JSONObject oneObject = jArray.getJSONObject(i);
                        // Pulling items from the array
                        //String oneObjectsItem = oneObject.getString("DeviceID");
                        //String oneObjectsItem2 = oneObject.getString("Name");
                        //String oneObjectsItem3 = oneObject.getString("Current_Status");
                        //String oneObjectsItem4 = oneObject.getString("CommandID");
                        //String oneObjectsItem5 = oneObject.getString("PhysicalID");
                        String oneObjectsItem6 = oneObject.getString("MasterID");
                        String oneObjectsItem7 = oneObject.getString("PowerlineID");
                        String oneObjectsItem8 = oneObject.getString("InternalID");
                        String oneObjectsItem9 = oneObject.getString("DeviceType");
                        String oneObjectsItem10 = oneObject.getString("ControlType");
                        String oneObjectsItem11 = oneObject.getString("DeviceModel");

                        final JSONArray jArray2 = jObject.getJSONArray("Controllers");
                        for (j = 0; j < jArray2.length(); j++) {
                            JSONObject oneObject2 = jArray2.getJSONObject(j);

                            oneObjectsItem = oneObject2.getString("DeviceID");
                            oneObjectsItem2 = oneObject2.getString("Name");
                            oneObjectsItem3 = oneObject2.getString("Current_Status");
                            oneObjectsItem4 = oneObject2.getString("CommandID");
                            oneObjectsItem5 = oneObject2.getString("PhysicalID");
                        }


                        System.out.println("check for status of switches : " + oneObjectsItem + " " + oneObjectsItem2 + " " + oneObjectsItem3 + " " + oneObjectsItem4 + " " + oneObjectsItem5 + " " + oneObjectsItem6 + " " + oneObjectsItem7 + " " + oneObjectsItem8 + " " + oneObjectsItem9 + " " + oneObjectsItem10 + " " + oneObjectsItem11);

                        dbcheck = db.inserthomeinfo(oneObjectsItem2,oneObjectsItem3,oneObjectsItem4,oneObjectsItem11);
                        if (dbcheck == true){
                            System.out.println("home info DB inserted " + i);
                        }else{
                            System.out.println("db failure");
                        }
                        device_id[i] = oneObjectsItem;
                        name[i] = oneObjectsItem2;
                        current_status[i] = oneObjectsItem3;
                        command_id[i] = oneObjectsItem4;
                        physical_id[i] = oneObjectsItem5;
                        master_id[i] = oneObjectsItem6;
                        powerline_id[i] = oneObjectsItem7;
                        internal_id[i] = oneObjectsItem8;
                        device_type[i] = oneObjectsItem9;
                        control_type[i] = oneObjectsItem10;
                        device_model[i] = oneObjectsItem11;


                        System.out.println(name[i] + " " + "array list");

                    } catch (JSONException e) {
                        // Oops
                    }
                }


            } catch (JSONException e) {
                e.printStackTrace();
                err = "Exception: " + e.getMessage();
            }


            //Toast.makeText(Registration.this, "Inf", Toast.LENGTH_LONG).show();
            tv.setText("Verify IP with gateway");
            submitBUT.setVisibility(View.GONE);
            homeidET.setVisibility(View.GONE);
            unET.setVisibility(View.GONE);
            pwET.setVisibility(View.GONE);
            ipET.setVisibility(View.VISIBLE);
            regBUT.setVisibility(View.VISIBLE);

            for (String z : name) {
                System.out.println("this is array" + " " + z);
            }


        }

    }

    public void regbtn(View view){
        ip = ipET.getText().toString();
        if (ip==""){
            Toast.makeText(Registration.this, "Please enter IP Address", Toast.LENGTH_SHORT).show();
        }else if (gateway.equals(ip)){
            //dbcheck = db.inserthomeinfo(homeid,un,gateway,ip);
            //if (dbcheck == true){
                Toast.makeText(Registration.this, "Succesfull", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ctx, ControllerMenu.class);
                i.putExtra("homeid",homeid);
                i.putExtra("device_id", device_id);
                i.putExtra("name", name);
                i.putExtra("current_status", current_status);
                i.putExtra("command_id", command_id);
                i.putExtra("physical_id", physical_id);
                i.putExtra("master_id", master_id);
                i.putExtra("powerline_id", powerline_id);
                i.putExtra("internal_id", internal_id);
                i.putExtra("device_type", device_type);
                i.putExtra("control_type", control_type);
                i.putExtra("device_model", device_model);
                startActivity(i);
//            }else{
//                Toast.makeText(Registration.this, "DB FAILED!", Toast.LENGTH_SHORT).show();
//            }
        }else if (!(gateway.equals(ip))){
            Toast.makeText(Registration.this, "not equal!", Toast.LENGTH_SHORT).show();
        }

    }

    public void subbtn(View view){
        homeid = homeidET.getText().toString();
        un = unET.getText().toString();
        pw = pwET.getText().toString();
        gateway = gatewayET.getText().toString();
        if (homeid =="" ||un==""|| pw ==""||gateway==""){
            Toast.makeText(Registration.this, "Please enter all infomation", Toast.LENGTH_SHORT).show();
        }else if (gateway.matches(".*[a-zA-Z]+.*")){
            Toast.makeText(Registration.this, "Please enter only numeric values for gateway", Toast.LENGTH_SHORT).show();
        }else if (un.length() < 5){
            Toast.makeText(Registration.this, "Username should be more than 4 characters", Toast.LENGTH_SHORT).show();
        }else if (pw.length() < 5){
            Toast.makeText(Registration.this, "Password should be more than 4 characters", Toast.LENGTH_SHORT).show();
        }else {

            checkfordevice c = new checkfordevice();
            c.execute(siteid);
        }
        //c.execute(homeid,un,pw,gateway);
    }
}