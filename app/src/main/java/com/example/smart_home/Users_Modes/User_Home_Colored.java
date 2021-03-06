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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smart_home.Adapter.MessageAdapter;
import com.example.smart_home.Contact_Person_Chat_Room.APIService;
import com.example.smart_home.GlobalVariables;
import com.example.smart_home.MainActivity;
import com.example.smart_home.Model.Chat;
import com.example.smart_home.Notifications.Client;
import com.example.smart_home.Notifications.Data;
import com.example.smart_home.Notifications.MyResponse;
import com.example.smart_home.Notifications.Sender;
import com.example.smart_home.Notifications.Token;
import com.example.smart_home.User_Chat_Room.Test_Chatting;
import com.example.smart_home.User_Chat_Room.User_Contact_List;
import com.example.smart_home.User_Modes_Black_White.User_Home;
import com.example.smart_home.Weather_Information.Common.Common;
import com.example.smart_home.Weather_Information.Helper.Helper;
import com.example.smart_home.Weather_Information.Model.OpenWeatherMap;
import com.example.smart_home.Weather_Information.Model.Sys;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smart_home.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import com.example.smart_home.User_Chat_Room.User_Message_Activity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private Button sleep_mode,moveout_mode,automatic_mode,manual_mode,contact,log_out;
    FirebaseAuth mFirebaseAuth;
    FirebaseFirestore fStore;
    String userID,userid,message,name;
    ScrollView home_layout;
    String City, LastUpdate, Description, Humidity, TimeSunset, TimeSunrise, Celcius, Main, WindSpeed,Temperature;

    ProgressDialog temporery;
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
        contact = (Button) findViewById(R.id.user_home_contact_person_button);
        log_out = (Button) findViewById(R.id.user_logout);

        intentfilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        User_Home_Colored.this.registerReceiver(broadcastreceiver,intentfilter);

        userID = mFirebaseAuth.getCurrentUser().getUid();

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

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_Home_Colored.this, Test_Chatting.class);
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
            String temp = String.valueOf(batteryTemp);

            DocumentReference documentReference = fStore.collection("USER").document(userID).collection("Temperature").document("Temperature");
            Map<String, Object> user = new HashMap<>();
            user.put("Temperature", temp);

            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                    Log.d(TAG, "OnSuccess: User Details Added " + userID);

                    System.out.println("Temperature success");

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d(TAG, "OnFailire: User Details Note added " + e.toString());

                }
            });


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
    public void onDestroy() {
        unregisterReceiver(broadcastreceiver);
        super.onDestroy();
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
            Temperature = String.format("Temperature :%s", openWeatherMap.getMain().getTemp());
            TimeSunset = String.format("%s", Common.unixTimeStampToDateTine(openWeatherMap.getSys().getSunset()));
            TimeSunrise = String.format("%s", Common.unixTimeStampToDateTine(openWeatherMap.getSys().getSunrise()));
            Celcius = String.format("Temperature : %.2f  °C", openWeatherMap.getMain().getTemp());


            DocumentReference documentReference = fStore.collection("USER").document(userID).collection("Weather").document("Weather");
            Map<String, Object> user = new HashMap<>();
            user.put("City", City );
            user.put("WindSpeed", WindSpeed );
            user.put("LastUpdate", LastUpdate );
            user.put("Description", Description );
            user.put("Main", Main );
            user.put("Humidity", Humidity );
            user.put("Temperature", Temperature );
            user.put("TimeSunset", TimeSunset );
            user.put("TimeSunrise", TimeSunrise );
            user.put("Celcius", Celcius );

            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                    Log.d(TAG, "OnSuccess: User Details Added " + userID);

                    System.out.println("Temperature success");

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d(TAG, "OnFailire: User Details Note added " + e.toString());

                }
            });


            GlobalVariables globalVariables = (GlobalVariables) getApplication();
            globalVariables.setCity(City);
            globalVariables.setWindspeed(WindSpeed);
            globalVariables.setWeather(Main);
            globalVariables.setHumidity(Humidity);
            globalVariables.setSunrise(TimeSunrise);
            globalVariables.setSunset(TimeSunset);
            globalVariables.setTemperature(Temperature);

//            String global = globalVariables.getCity();
//            String Main = String.format("%s", openWeatherMap.getWeather().get(0).getMain());
//            String Clear = "Clear";
//            String Rain = "Rain";
//            String Clouds = "Clouds";
//            Boolean found = Arrays.asList(Main.split(" ")).contains(Clouds);
//            Boolean foundtwo = Arrays.asList(Main.split(" ")).contains(Rain);
//            Boolean foundthree = Arrays.asList(Main.split(" ")).contains(Clear);
//            if (found) {
//                System.out.println("Show" + Clouds + "Weather is Cloudy");
//                //Toast.makeText(User_Home_Colored.this, "Show" + Clouds + "Weather is Cloudy", Toast.LENGTH_SHORT).show();
//            }
//            if (foundtwo) {
//                System.out.println("Show" + Rain + "Weather is Rainy");
//                //Toast.makeText(User_Home_Colored.this, "Show" + Rain + "Weather is Rainy", Toast.LENGTH_SHORT).show();
//            }
//            if (foundthree) {
//                System.out.println("Show" + Clear + "Weather is Clear");
//               // Toast.makeText(User_Home_Colored.this, "Show" + Clear + "Weather is Clear", Toast.LENGTH_SHORT).show();
//            }
//
//            if(global != null){
////                Intent intent = new Intent(User_Home_Colored.this, User_Home_Colored.class);
////                startActivity(intent);
//                home_layout.setVisibility(View.VISIBLE);
//            }


        }
    }

}
