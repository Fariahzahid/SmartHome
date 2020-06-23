package com.example.smart_home.Users_Modes;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.smart_home.GlobalVariables;
import com.example.smart_home.MainActivity;
import com.example.smart_home.Weather_Information.Common.Common;
import com.example.smart_home.Weather_Information.Helper.Helper;
import com.example.smart_home.Weather_Information.Model.OpenWeatherMap;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Arrays;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.smart_home.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class User_Home_Colored extends AppCompatActivity implements LocationListener{
    private static final String TAG = "My Activity";
    String txtCity, txtLastUpdate, txtDescription, txtHumidity, txtTimeSunset,txtTimeSunrise, txtCelcius,txtMain,txtWindSpeed;
    ImageView imageView;
    LocationManager locationManager;
    String provider;
    static double lat, lng;
    OpenWeatherMap openWeatherMap = new OpenWeatherMap();
    IntentFilter intentfilter;
    float batteryTemp;
    String currentBatterytemp ="Current Battery temp :",currectTemperature;

    int MY_PERMISSION = 0;

    private String value;
    private Button sleep_mode,moveout_mode,automatic_mode,manual_mode,log_out;
    FirebaseAuth mFirebaseAuth;
    FirebaseFirestore fStore;
    String userID;
    ScrollView home_layout;
    String City, LastUpdate, Description, Humidity, TimeSunset, TimeSunrise, Celcius, Main, WindSpeed;

    ProgressDialog temporery;

    String currentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_home_wooden);
        fStore = FirebaseFirestore.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();

        home_layout = (ScrollView) findViewById(R.id.speechlayout);
        sleep_mode = (Button) findViewById(R.id.user_home_sleep_mode_button);
        moveout_mode = (Button) findViewById(R.id.user_home_moveoutmode_button);
        automatic_mode = (Button) findViewById(R.id.user_home_automatic_mode_button);
        manual_mode = (Button) findViewById(R.id.user_home_manual_mode_button);
        log_out = (Button) findViewById(R.id.user_logout);

        intentfilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        User_Home_Colored.this.registerReceiver(broadcastreceiver,intentfilter);

//        Calendar c = Calendar.getInstance();
//        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
//        currentTime = df.format(c.getTime());
        temporery = new ProgressDialog(User_Home_Colored.this);
        temporery.setTitle("Please wait ....");
        temporery.show();
        //Get Coordinates
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        provider = locationManager.getBestProvider(new Criteria(), false);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(User_Home_Colored.this, new String[]{
                    Manifest.permission.INTERNET,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.SYSTEM_ALERT_WINDOW,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, MY_PERMISSION);
        }
        Location location = locationManager.getLastKnownLocation(provider);
        if (location == null) {
            Log.d(TAG, "No Location");
        }
        sleep_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(User_Home_Colored.this, User_Sleep_Mode_Colored.class);
                startActivity(intent);
            }
        });
        moveout_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_Home_Colored.this, User_Move_Out_Mode_Colored.class);
                startActivity(intent);
            }
        });
        automatic_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_Home_Colored.this, User_Automatic_Mode_Colored.class);
                startActivity(intent);
            }
        });
        manual_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_Home_Colored.this, User_Manual_Mode_Colored.class);
                startActivity(intent);
            }
        });
        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseAuth.signOut();
                Intent intent = new Intent(User_Home_Colored.this, MainActivity.class);
                startActivity(intent);
                finish();


            }
        });

    }
    private BroadcastReceiver broadcastreceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            batteryTemp = (float)(intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE,0))/10;

            currectTemperature = currentBatterytemp +" "+batteryTemp +" "+ (char) 0x00B0 +"C";

            GlobalVariables globalVariable=(GlobalVariables)getApplication();
            globalVariable.setCurrentTemperature(batteryTemp);
            if(batteryTemp <=25){
                globalVariable.setSleep_mode_bedroom_ac("OFF");
                globalVariable.setSleep_mode_bedroom_ac_temperature("0" + (char) 0x00B0 +"C");
                globalVariable.setSleep_mode_bedroom_heating("ON");
                globalVariable.setSleep_mode_bedroom_heating_temperature("30 "+ (char) 0x00B0 +"C" );

                globalVariable.setManual_mode_bedroom_ac("OFF");
                globalVariable.setManual_mode_bedroom_ac_temperature("0" + (char) 0x00B0 +"C");
                globalVariable.setManual_mode_bedroom_heating("ON");
                globalVariable.setManual_mode_bedroom_heating_temperature("30 "+ (char) 0x00B0 +"C" );

                globalVariable.setAutomatic_mode_bedroom_ac("OFF");
                globalVariable.setAutomatic_mode_bedroom_ac_temperature("0" + (char) 0x00B0 +"C");
                globalVariable.setAutomatic_mode_bedroom_heating("ON");
                globalVariable.setAutomatic_mode_bedroom_heating_temperature("30 "+ (char) 0x00B0 +"C" );

                globalVariable.setSleep_mode_livingroom_ac("OFF");
                globalVariable.setSleep_mode_livingroom_ac_temperature("0" + (char) 0x00B0 +"C");
                globalVariable.setSleep_mode_livingroom_heating("ON");
                globalVariable.setSleep_mode_livingroom_heating_temperature("30 "+ (char) 0x00B0 +"C" );

                globalVariable.setManual_mode_livingroom_ac("OFF");
                globalVariable.setManual_mode_livingroom_ac_temperature("0" + (char) 0x00B0 +"C");
                globalVariable.setManual_mode_livingroom_heating("ON");
                globalVariable.setManual_mode_livingroom_heating_temperature("30 "+ (char) 0x00B0 +"C" );

                globalVariable.setAutomatic_mode_livingroom_ac("OFF");
                globalVariable.setAutomatic_mode_livingroom_ac_temperature("0" + (char) 0x00B0 +"C");
                globalVariable.setAutomatic_mode_livingroom_heating("ON");
                globalVariable.setAutomatic_mode_livingroom_heating_temperature("30 "+ (char) 0x00B0 +"C" );

                globalVariable.setSleep_mode_kitchen_ac("OFF");
                globalVariable.setSleep_mode_kitchen_ac_temperature("0" + (char) 0x00B0 +"C");
                globalVariable.setSleep_mode_kitchen_heating("ON");
                globalVariable.setSleep_mode_kitchen_heating_temperature("30 "+ (char) 0x00B0 +"C" );

                globalVariable.setManual_mode_kitchen_ac("OFF");
                globalVariable.setManual_mode_kitchen_ac_temperature("0" + (char) 0x00B0 +"C");
                globalVariable.setManual_mode_kitchen_heating("ON");
                globalVariable.setManual_mode_kitchen_ac_temperature("30 "+ (char) 0x00B0 +"C" );

                globalVariable.setAutomatic_mode_kitchen_ac("OFF");
                globalVariable.setAutomatic_mode_kitchen_ac_temperature("0" + (char) 0x00B0 +"C");
                globalVariable.setAutomatic_mode_kitchen_heating("ON");
                globalVariable.setAutomatic_mode_kitchen_heating_temperature("30 "+ (char) 0x00B0 +"C" );

                globalVariable.setSleep_mode_wc_heating("ON");
                globalVariable.setSleep_mode_wc_heating_temperature("30 "+ (char) 0x00B0 +"C" );

                globalVariable.setManual_mode_wc_heating("ON");
                globalVariable.setManual_mode_wc_heating_temperature("30 "+ (char) 0x00B0 +"C" );

                globalVariable.setAutomatic_mode_wc_heating("ON");
                globalVariable.setAutomatic_mode_wc_heating_temperature("30 "+ (char) 0x00B0 +"C" );
            }
            if(batteryTemp >= 25){
                globalVariable.setSleep_mode_bedroom_ac("ON");
                globalVariable.setSleep_mode_bedroom_ac_temperature("20" + (char) 0x00B0 +"C");
                globalVariable.setSleep_mode_bedroom_heating("OFF");
                globalVariable.setSleep_mode_bedroom_heating_temperature("0 "+ (char) 0x00B0 +"C" );

                globalVariable.setManual_mode_bedroom_ac("ON");
                globalVariable.setManual_mode_bedroom_ac_temperature("20" + (char) 0x00B0 +"C");
                globalVariable.setManual_mode_bedroom_heating("OFF");
                globalVariable.setManual_mode_bedroom_heating_temperature("0 "+ (char) 0x00B0 +"C" );

                globalVariable.setAutomatic_mode_bedroom_ac("ON");
                globalVariable.setAutomatic_mode_bedroom_ac_temperature("20" + (char) 0x00B0 +"C");
                globalVariable.setAutomatic_mode_bedroom_heating("OFF");
                globalVariable.setAutomatic_mode_bedroom_heating_temperature("0 "+ (char) 0x00B0 +"C" );

                globalVariable.setSleep_mode_livingroom_ac("ON");
                globalVariable.setSleep_mode_livingroom_ac_temperature("20" + (char) 0x00B0 +"C");
                globalVariable.setSleep_mode_livingroom_heating("OFF");
                globalVariable.setSleep_mode_livingroom_heating_temperature("0 "+ (char) 0x00B0 +"C" );

                globalVariable.setManual_mode_livingroom_ac("ON");
                globalVariable.setManual_mode_livingroom_ac_temperature("20" + (char) 0x00B0 +"C");
                globalVariable.setManual_mode_livingroom_heating("OFF");
                globalVariable.setManual_mode_livingroom_heating_temperature("0 "+ (char) 0x00B0 +"C" );

                globalVariable.setAutomatic_mode_livingroom_ac("ON");
                globalVariable.setAutomatic_mode_livingroom_ac_temperature("20" + (char) 0x00B0 +"C");
                globalVariable.setAutomatic_mode_livingroom_heating("OFF");
                globalVariable.setAutomatic_mode_livingroom_heating_temperature("0 "+ (char) 0x00B0 +"C" );

                globalVariable.setSleep_mode_kitchen_ac("ON");
                globalVariable.setSleep_mode_kitchen_ac_temperature("20" + (char) 0x00B0 +"C");
                globalVariable.setSleep_mode_kitchen_heating("OFF");
                globalVariable.setSleep_mode_kitchen_heating_temperature("0 "+ (char) 0x00B0 +"C" );

                globalVariable.setManual_mode_kitchen_ac("ON");
                globalVariable.setManual_mode_kitchen_ac_temperature("20" + (char) 0x00B0 +"C");
                globalVariable.setManual_mode_kitchen_heating("OFF");
                globalVariable.setManual_mode_kitchen_ac_temperature("0 "+ (char) 0x00B0 +"C" );

                globalVariable.setAutomatic_mode_kitchen_ac("ON");
                globalVariable.setAutomatic_mode_kitchen_ac_temperature("20" + (char) 0x00B0 +"C");
                globalVariable.setAutomatic_mode_kitchen_heating("OFF");
                globalVariable.setAutomatic_mode_kitchen_heating_temperature("0 "+ (char) 0x00B0 +"C" );

                globalVariable.setSleep_mode_wc_heating("OFF");
                globalVariable.setSleep_mode_wc_heating_temperature("0 "+ (char) 0x00B0 +"C" );

                globalVariable.setManual_mode_wc_heating("OFF");
                globalVariable.setManual_mode_wc_heating_temperature("0 "+ (char) 0x00B0 +"C" );

                globalVariable.setAutomatic_mode_wc_heating("OFF");
                globalVariable.setAutomatic_mode_wc_heating_temperature("0 "+ (char) 0x00B0 +"C" );
            }
        }
    };
    @Override
    protected void onPause() {
        super.onPause();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(User_Home_Colored.this, new String[]{
                    Manifest.permission.INTERNET,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.SYSTEM_ALERT_WINDOW,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, MY_PERMISSION);
        }
        locationManager.removeUpdates(this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(User_Home_Colored.this, new String[]{
                    Manifest.permission.INTERNET,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.SYSTEM_ALERT_WINDOW,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, MY_PERMISSION);
        }
        locationManager.requestLocationUpdates(provider, 400, 1, this);
    }
    @Override
    public void onLocationChanged(Location location) {
        lat = location.getLatitude();
        lng = location.getLongitude();

        new User_Home_Colored.GetWeather().execute(Common.apiRequest(String.valueOf(lat), String.valueOf(lng)));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    private class GetWeather extends AsyncTask<String, Void, String> {
        //ProgressDialog pd = new ProgressDialog(User_Home_Colored.this);


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            pd.setTitle("Please wait ....");
//            pd.show();
        }
        @Override
        protected String doInBackground(String... params) {
            String stream = null;
            String urlString = params[0];

            Helper http = new Helper();
            stream = http.getHTTPData(urlString);
            return stream;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s.contains("Error.. Not found city")) {
                //pd.dismiss();
                return;
            }
            Gson gson = new Gson();
            Type mType = new TypeToken<OpenWeatherMap>() {
            }.getType();
            openWeatherMap = gson.fromJson(s, mType);

            temporery.dismiss();
            City = String.format("%s,%s", openWeatherMap.getName(), openWeatherMap.getSys().getCountry());
            WindSpeed = String.format("%s", openWeatherMap.getWind().getSpeed());
            LastUpdate = String.format("Last Updated: %s", Common.getDateNow());
            Description = String.format("%s", openWeatherMap.getWeather().get(0).getDescription());
            Main = String.format("%s", openWeatherMap.getWeather().get(0).getMain());
            Humidity = String.format("Humidity :%d%%", openWeatherMap.getMain().getHumidity());
            TimeSunset = String.format("%s", Common.unixTimeStampToDateTine(openWeatherMap.getSys().getSunset()));
            TimeSunrise = String.format("%s", Common.unixTimeStampToDateTine(openWeatherMap.getSys().getSunrise()));
            Celcius = String.format("Temperature : %.2f  °C", openWeatherMap.getMain().getTemp());

            GlobalVariables globalVariables = (GlobalVariables) getApplication();
            globalVariables.setCity(City);
            globalVariables.setWindspeed(WindSpeed);
            globalVariables.setWeather(Main);
            globalVariables.setHumidity(Humidity);
            globalVariables.setSunrise(TimeSunrise);
            globalVariables.setSunset(TimeSunset);
            globalVariables.setTemperature(Celcius);

            String global = globalVariables.getCity();
            String Main = String.format("%s", openWeatherMap.getWeather().get(0).getMain());
            String Clear = "Clear";
            String Rain = "Rain";
            String Clouds = "Clouds";
            Boolean found = Arrays.asList(Main.split(" ")).contains(Clouds);
            Boolean foundtwo = Arrays.asList(Main.split(" ")).contains(Rain);
            Boolean foundthree = Arrays.asList(Main.split(" ")).contains(Clear);
            if (found) {
                System.out.println("Show" + Clouds + "Weather is Cloudy");
                //Toast.makeText(User_Home_Colored.this, "Show" + Clouds + "Weather is Cloudy", Toast.LENGTH_SHORT).show();
            }
            if (foundtwo) {
                System.out.println("Show" + Rain + "Weather is Rainy");
                //Toast.makeText(User_Home_Colored.this, "Show" + Rain + "Weather is Rainy", Toast.LENGTH_SHORT).show();
            }
            if (foundthree) {
                System.out.println("Show" + Clear + "Weather is Clear");
               // Toast.makeText(User_Home_Colored.this, "Show" + Clear + "Weather is Clear", Toast.LENGTH_SHORT).show();
            }

            if(global != null){
//                Intent intent = new Intent(User_Home_Colored.this, User_Home_Colored.class);
//                startActivity(intent);
                home_layout.setVisibility(View.VISIBLE);
            }


        }
    }

}
