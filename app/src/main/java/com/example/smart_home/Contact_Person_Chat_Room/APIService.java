package com.example.smart_home.Contact_Person_Chat_Room;

import com.example.smart_home.Notifications.MyResponse;
import com.example.smart_home.Notifications.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAAsumvhG4:APA91bHpOLDZmDBLdYfDWm9S7FAZ2JEiJIh5YDDrbjOhOwUVQJY49crjP9fRcTFcb--ZMdasXat0rJoMwH_KgaC-aMPxTOpgSGH3O9j5xx-nwpdpO4JQCmAcuxpgRSbK4XGMxd8UMmpk" // Your server key refer to video for finding your server key
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotifcation(@Body Sender body);
}

