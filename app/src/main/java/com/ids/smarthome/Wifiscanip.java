package com.ids.smarthome;

/**
 * Created by Zaid on 7/25/17.
 */
public class Wifiscanip {
//    Handler UIhandler;
//    private int LoopCurrentIP = 0;
//    Socket socket = null;
//    Thread Thread1 = null;
//    Thread Thread1bg = null;
//    public String   s_dns1 ;
//    public String   s_dns2;
//    public String   s_gateway;
//    public String   s_ipAddress;
//    public String   s_leaseDuration;
//    public String   s_netmask;
//    public String   s_serverAddress;
//    TextView info;
//    DhcpInfo d;
//    WifiManager wifii;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_startup);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        info = (TextView)findViewById(R.id.tv);
//
//        UIhandler = new Handler();
//
//        wifii = (WifiManager) getSystemService(Context.WIFI_SERVICE);
//        d = wifii.getDhcpInfo();
//
//        s_dns1 = "DNS 1: " + String.valueOf(d.dns1);
//        s_dns2 = "DNS 2: " + String.valueOf(d.dns2);
//        s_gateway = "Default Gateway: " + String.valueOf(d.gateway);
//        s_ipAddress = "IP Address: " + String.valueOf(d.ipAddress);
//        s_leaseDuration = "Lease Time: " + String.valueOf(d.leaseDuration);
//        s_netmask = "Subnet Mask: " + String.valueOf(d.netmask);
//        s_serverAddress = "Server IP: " + String.valueOf(d.serverAddress);
//        System.out.println("detailz" + s_dns1 + s_dns2 + s_gateway + s_ipAddress + s_leaseDuration + s_netmask + s_serverAddress);
//
//        this.Thread1bg = new Thread(new Thread1bg());
//        this.Thread1bg.start();
//
//
//    }
//
//    class Thread1bg implements Runnable {
//        public void run() {
//            //Socket socket = null;
//            String connections = "";
//            InetAddress host;
//            try
//            {
//                host = InetAddress.getByName("192.168.1.201");
//                byte[] ip = host.getAddress();
//
//                for(int i = 1; i <= 254; i++)
//                {
//                    ip[3] = (byte) i;
//                    InetAddress address = InetAddress.getByAddress(ip);
//                    if(address.isReachable(100))
//                    {
//                        System.out.println(address + " machine is turned on and can be pinged");
//                        System.out.println(address.getHostName() + " machine is turned on and can be pinged2");
//
//                        UIhandler.post(new updateUIThread(address.getHostName()));
//                        connections+= address+"\n";
//                    }
//                    else if(!address.getHostAddress().equals(address.getHostName()))
//                    {
//                        System.out.println(address + " machine is known in a DNS lookup");
//                    }
//
//                }
//            }
//            catch(UnknownHostException e1)
//            {
//                e1.printStackTrace();
//            }
//            catch(IOException e)
//            {
//                e.printStackTrace();
//            }
//            System.out.println(connections);
//            return;
//
//        }
//    }
//
//    class updateUIThread implements Runnable {
//        private String message;
//
//        public updateUIThread(String str) {
//            this.message = str;
//        }
//
//        @Override
//        public void run() {
//            info.setText(message);
//        }
//    }

}
