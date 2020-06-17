package com.example.smart_home.User_Voice_Mode;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.smart_home.GlobalVariables;
import com.example.smart_home.R;
import com.example.smart_home.Weather_Information.Common.Common;
import com.example.smart_home.Weather_Information.Helper.Helper;
import com.example.smart_home.Weather_Information.Model.OpenWeatherMap;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

public class User_Home_Weather extends AppCompatActivity implements LocationListener {
    private static final String TAG = "My Activity";
    TextView txtCity, txtLastUpdate, txtDescription, txtHumidity, txtTimeSunset, txtTimeSunrise,
            txtTime,txtCelcius, txtMain, txtWindSpeed,txtwait;
    ImageView imageView;
    LocationManager locationManager;
    LinearLayout weatherLayout;
    String provider;
    static double lat, lng;
    OpenWeatherMap openWeatherMap = new OpenWeatherMap();
    String City, LastUpdate, Description, Humidity, TimeSunset, TimeSunrise, Celcius, Main, WindSpeed;

    int MY_PERMISSION = 0;
    ProgressDialog temporery;
    TextToSpeech textToSpeech;

    String currentTime;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_weather_content);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        currentTime = df.format(c.getTime());
        temporery = new ProgressDialog(User_Home_Weather.this);
        temporery.setTitle("Please wait ....");
        temporery.show();

        weatherLayout = (LinearLayout) findViewById(R.id.weatherLayout);
        txtwait = (TextView) findViewById(R.id.waittext);
        txtCity = (TextView) findViewById(R.id.txtCity);
        txtLastUpdate = (TextView) findViewById(R.id.txtLastUpdate);
        txtDescription = (TextView) findViewById(R.id.txtDescription);
        txtHumidity = (TextView) findViewById(R.id.txtHumidity);
        txtTime = (TextView) findViewById(R.id.txtTime);
        txtCelcius = (TextView) findViewById(R.id.txtCelcius);
        txtWindSpeed = (TextView) findViewById(R.id.txtWindSpeed);
        txtMain = (TextView) findViewById(R.id.txtMain);
        imageView = (ImageView) findViewById(R.id.imageView);
        txtTimeSunset = (TextView) findViewById(R.id.txtTimeSunset);
        txtTimeSunrise = (TextView) findViewById(R.id.txtTimeSunrise);

        txtwait.setText("Please Wait for the weather Update");
        // Init TextToSpeech
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(Locale.ENGLISH);
                    if (result == TextToSpeech.LANG_MISSING_DATA ||
                            result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Toast.makeText(getApplicationContext(), "This language is not supported!",
                                Toast.LENGTH_SHORT);
                    } else {
                        //weatherLayout.setEnabled(true);
                        textToSpeech.setPitch(0.6f);
                        textToSpeech.setSpeechRate(1.0f);

                        textspeak();
                    }
                }
            }
        });
        //Get Coordinates
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        provider = locationManager.getBestProvider(new Criteria(), false);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(User_Home_Weather.this, new String[]{
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
    private void textspeak() {
        String text = txtwait.getText().toString();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(User_Home_Weather.this, new String[]{
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
            ActivityCompat.requestPermissions(User_Home_Weather.this, new String[]{
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

        new GetWeather().execute(Common.apiRequest(String.valueOf(lat), String.valueOf(lng)));
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
        ProgressDialog pd = new ProgressDialog(User_Home_Weather.this);


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
            pd.dismiss();

            temporery.dismiss();
            textToSpeech.stop();
            txtCity.setText(String.format("%s,%s",openWeatherMap.getName(),openWeatherMap.getSys().getCountry()));
            txtLastUpdate.setText(String.format("Last Updated: %s", Common.getDateNow()));
            txtDescription.setText(String.format("%s",openWeatherMap.getWeather().get(0).getDescription()));
            txtMain.setText(String.format("%s",openWeatherMap.getWeather().get(0).getMain()));
            txtHumidity.setText(String.format("Humidity :%d%%",openWeatherMap.getMain().getHumidity()));
            txtWindSpeed.setText(String.format("Wind Speed :%s",openWeatherMap.getWind().getSpeed()));
            txtCelcius.setText(String.format("Temperature : %.2f  °C",openWeatherMap.getMain().getTemp()));
            txtTimeSunset.setText(String.format("%s", Common.unixTimeStampToDateTine(openWeatherMap.getSys().getSunset())));
            txtTimeSunrise.setText(String.format("%s", Common.unixTimeStampToDateTine(openWeatherMap.getSys().getSunrise())));
            Picasso.get()
                    .load(Common.gerImage(openWeatherMap.getWeather().get(0).getIcon()))
                    .into(imageView);

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
                Toast.makeText(User_Home_Weather.this, "Show" + Clouds + "Weather is Cloudy", Toast.LENGTH_SHORT).show();
            }
            if (foundtwo) {
                System.out.println("Show" + Rain + "Weather is Rainy");
                Toast.makeText(User_Home_Weather.this, "Show" + Rain + "Weather is Rainy", Toast.LENGTH_SHORT).show();
            }
            if (foundthree) {
                System.out.println("Show" + Clear + "Weather is Clear");
                Toast.makeText(User_Home_Weather.this, "Show" + Clear + "Weather is Clear", Toast.LENGTH_SHORT).show();
            }

            if(global != null){
               // weatherLayout.setVisibility(View.VISIBLE);
                Intent intent = new Intent(User_Home_Weather.this, User_Home_Wooden_Voice_Mode.class);
                startActivity(intent);
            }

        }
    }
}