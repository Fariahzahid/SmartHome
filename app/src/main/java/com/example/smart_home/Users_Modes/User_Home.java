package com.example.smart_home.Users_Modes;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
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
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.Arrays;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.smart_home.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class User_Home  extends AppCompatActivity implements LocationListener {
    private static final String TAG = "My Activity";
    String txtCity, txtLastUpdate, txtDescription, txtHumidity, txtTimeSunset,txtTimeSunrise, txtCelcius,txtMain,txtWindSpeed;
    ImageView imageView;
    LocationManager locationManager;
    String provider;
    static double lat, lng;
    OpenWeatherMap openWeatherMap = new OpenWeatherMap();

    int MY_PERMISSION = 0;

    private String value;
    private Button sleep_mode,moveout_mode,automatic_mode,manual_mode,log_out;
    FirebaseAuth mFirebaseAuth;
    FirebaseFirestore fStore;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_home);

        fStore = FirebaseFirestore.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();


        sleep_mode = (Button) findViewById(R.id.user_home_sleep_mode_button);
        moveout_mode = (Button) findViewById(R.id.user_home_moveoutmode_button);
        automatic_mode = (Button) findViewById(R.id.user_home_automatic_mode_button);
        manual_mode = (Button) findViewById(R.id.user_home_manual_mode_button);
        log_out = (Button) findViewById(R.id.user_logout);


        sleep_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(User_Home.this, User_Sleep_Mode.class);
                startActivity(intent);
            }
        });
        moveout_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_Home.this, User_Move_Out_Mode.class);
                startActivity(intent);
            }
        });
        automatic_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_Home.this, User_Automatic_Mode_Bedroom.class);
                startActivity(intent);
            }
        });
        manual_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_Home.this, User_Manual_Mode_Bedroom.class);
                startActivity(intent);
            }
        });
        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseAuth.signOut();
                Intent intent = new Intent(User_Home.this, MainActivity.class);
                startActivity(intent);
                finish();


            }
        });

        //Get Coordinates
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        provider = locationManager.getBestProvider(new Criteria(), false);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(User_Home.this, new String[]{
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

    }


    @Override
    protected void onPause() {
        super.onPause();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(User_Home.this, new String[]{
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
            ActivityCompat.requestPermissions(User_Home.this, new String[]{
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

        new GetWeather().execute(Common.apiRequest(String.valueOf(lat),String.valueOf(lng)));



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
    private class GetWeather extends AsyncTask<String,Void,String> {
        ProgressDialog pd = new ProgressDialog(User_Home.this);

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            // pd.setTitle("Please wait ....");
            // pd.show();
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
        protected void onPostExecute(String s){
            super.onPostExecute(s);
            if(s.contains("Error.. Not found city")){
                pd.dismiss();
                return;
            }
            Gson gson = new Gson();
            Type mType = new TypeToken<OpenWeatherMap>(){}.getType();
            openWeatherMap = gson.fromJson(s,mType);
            pd.dismiss();
//            txtCity.setText(String.format("%s,%s",openWeatherMap.getName(),openWeatherMap.getSys().getCountry()));
//            txtLastUpdate.setText(String.format("Last Updated: %s", Common.getDateNow()));
//            txtDescription.setText(String.format("%s",openWeatherMap.getWeather().get(0).getDescription()));
//            txtMain.setText(String.format("%s",openWeatherMap.getWeather().get(0).getMain()));
//            txtHumidity.setText(String.format("Humidity :%d%%",openWeatherMap.getMain().getHumidity()));
//            txtWindSpeed.setText(String.format("Wind Speed :%s",openWeatherMap.getWind().getSpeed()));
//            txtTime.setText(String.format("Sun Set Time/ Sun Rise Time :%s/%s",Common.unixTimeStampToDateTine(openWeatherMap.getSys().getSunset()),Common.unixTimeStampToDateTine(openWeatherMap.getSys().getSunrise())));
//            txtCelcius.setText(String.format("Temperature : %.2f  °C",openWeatherMap.getMain().getTemp()));
//            Picasso.with(MainActivity.this)
//                    .load(Common.gerImage(openWeatherMap.getWeather().get(0).getIcon()))
//                    .into(imageView);

            txtCity = String.format("%s,%s",openWeatherMap.getName(),openWeatherMap.getSys().getCountry());
            txtWindSpeed = String.format("Wind Speed :%s",openWeatherMap.getWind().getSpeed());
            txtLastUpdate = String.format("Last Updated: %s", Common.getDateNow());
            txtDescription = String.format("%s",openWeatherMap.getWeather().get(0).getDescription());
            txtMain = String.format("%s",openWeatherMap.getWeather().get(0).getMain());
            txtHumidity = String.format("Humidity :%d%%",openWeatherMap.getMain().getHumidity());
            txtTimeSunset = String.format("Sun Set Time:%s",Common.unixTimeStampToDateTine(openWeatherMap.getSys().getSunset()));
            txtTimeSunrise = String.format("Sun Set Time:%s",Common.unixTimeStampToDateTine(openWeatherMap.getSys().getSunrise()));
            txtCelcius = String.format("Temperature : %.2f  °C",openWeatherMap.getMain().getTemp());

            GlobalVariables globalVariables = (GlobalVariables)getApplication();
            globalVariables.setCity(txtCity);
            globalVariables.setWindspeed(txtWindSpeed);
            globalVariables.setWeather(txtMain);
            globalVariables.setHumidity(txtHumidity);
            globalVariables.setSunrise(txtTimeSunrise);
            globalVariables.setSunset(txtTimeSunset);
            globalVariables.setTemperature(txtCelcius);



            String Main = String.format("%s",openWeatherMap.getWeather().get(0).getMain());
            String Clear = "Clear";
            String Rain = "Rain";
            String Clouds = "Clouds";

            Boolean found = Arrays.asList(Main.split(" ")).contains(Clouds);
            Boolean foundtwo = Arrays.asList(Main.split(" ")).contains(Rain);
            Boolean foundthree = Arrays.asList(Main.split(" ")).contains(Clear);

            if(found){
                System.out.println("Show" +Clouds +"Weather is Cloudy");
                Toast.makeText(User_Home.this, "Show" +Clouds +"Weather is Cloudy", Toast.LENGTH_SHORT).show();

            }
            if(foundtwo){
                System.out.println("Show" +Rain +"Weather is Rainy");
                Toast.makeText(User_Home.this, "Show" +Rain +"Weather is Rainy", Toast.LENGTH_SHORT).show();

            }
            if(foundthree){
                System.out.println("Show" +Clear +"Weather is Clear");
                Toast.makeText(User_Home.this, "Show" +Clear +"Weather is Clear", Toast.LENGTH_SHORT).show();

            }
        }
    }
}
