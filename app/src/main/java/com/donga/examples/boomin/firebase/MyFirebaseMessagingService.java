/**
 * Copyright 2016 Google Inc. All Rights Reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.donga.examples.boomin.firebase;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.donga.examples.boomin.activity.AlertDialogActivity;
import com.donga.examples.boomin.activity.FirstActivity;
import com.donga.examples.boomin.R;
import com.donga.examples.boomin.Singleton.PushSingleton;
import com.donga.examples.boomin.activity.Wisper_OkDialogActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.orhanobut.logger.Logger;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import me.leolin.shortcutbadger.ShortcutBadger;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
//    Context context = getApplicationContext();
//
//    SharedPreferences sharedPreferences = context.getSharedPreferences(getResources().getString(R.string.SFLAG), Context.MODE_PRIVATE);
//    SharedPreferences.Editor editor = sharedPreferences.edit();
//    if(sharedPreferences.getInt("PushCount", 0) != null){
//        int pushCount = sharedPreferences.getInt("PushCount", 0);
//
//    }

    private static final String TAG = "MyFirebaseMsgService";

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // [START_EXCLUDE]
        // There are two types of messages data messages and notification messages. Data messages are handled
        // here in onMessageReceived whether the app is in the foreground or background. Data messages are the type
        // traditionally used with GCM. Notification messages are only received here in onMessageReceived when the app
        // is in the foreground. When the app is in the background an automatically generated notification is displayed.
        // When the user taps on the notification they are returned to the app. Messages containing both notification
        // and data payloads are treated as notification messages. The Firebase console always sends notification
        // messages. For more see: https://firebase.google.com/docs/cloud-messaging/concept-options
        // [END_EXCLUDE]

        SharedPreferences sharedPreferences = getSharedPreferences(getResources().getString(R.string.SFLAG), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int pushCount = sharedPreferences.getInt("pushCount", 0);
        pushCount++;
        editor.putInt("pushCount", pushCount);
        editor.commit();
        ShortcutBadger.applyCount(getApplicationContext(), pushCount);

        Map data = remoteMessage.getData();

        PushSingleton.getInstance().setmMap(data);
        Log.i("FROM SINGLETON", String.valueOf(PushSingleton.getInstance().getmMap().get("contents")));
        Log.i("FROM SINGLETON", String.valueOf(PushSingleton.getInstance().getmMap().get("category")));

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            if(isAppIsInBackground(getApplicationContext())){
                //background
                if(PushSingleton.getInstance().getmMap().get("category").equals("normal")){
                    Bundle bun = new Bundle();
                    try {
                        JSONObject jObject = new JSONObject(String.valueOf(PushSingleton.getInstance().getmMap().get("contents")));

                        String title = jObject.getString("title");
                        String send = jObject.getString("body");
                        String contents = jObject.getString("contents");

                        bun.putString("send", send);
                        bun.putString("title", title);
                        bun.putString("contents", contents);
                        Intent popupIntent = new Intent(getApplicationContext(), FirstActivity.class);
                        popupIntent.putExtras(bun);

                        NotificationManager manager= (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                        NotificationCompat.Builder builder= new NotificationCompat.Builder(this);
                        builder.setSmallIcon(android.R.drawable.ic_dialog_email);//상태표시줄에 보이는 아이콘 모양
                        builder.setTicker("Notification"); //알림이 발생될 때 잠시 보이는 글씨
                        builder.setContentTitle(title);    //알림창에서의 제목
                        builder.setContentText(send);   //알림창에서의 글씨
                        builder.setVibrate(new long[]{0,1500});

                        PendingIntent pie = PendingIntent.getActivity(this, 1, popupIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                        builder.setContentIntent(pie);
                        builder.setAutoCancel(true);         //클릭하면 자동으로 알림 삭제
                        Notification notification= builder.build();   //Notification 객체 생성
                        manager.notify(1, notification);             //NotificationManager가 알림(Notification)을 표시
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else if(PushSingleton.getInstance().getmMap().get("category").equals("circle")){
                    Bundle bun = new Bundle();
                    try {
                        JSONObject jObject = new JSONObject(String.valueOf(PushSingleton.getInstance().getmMap().get("contents")));

                        String title = jObject.getString("title");
                        String send = jObject.getString("body");
                        String contents = jObject.getString("contents");

                        bun.putString("send", send);
                        bun.putString("title", title);
                        bun.putString("contents", contents);
//                        Intent popupIntent = new Intent(getApplicationContext(), Wisper_OkDialogActivity.class);
                        Intent popupIntent = new Intent(getApplicationContext(), FirstActivity.class);
                        popupIntent.putExtras(bun);

                        NotificationManager manager= (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                        NotificationCompat.Builder builder= new NotificationCompat.Builder(this);
                        builder.setSmallIcon(android.R.drawable.ic_dialog_email);//상태표시줄에 보이는 아이콘 모양
                        builder.setTicker("Notification"); //알림이 발생될 때 잠시 보이는 글씨
                        builder.setContentTitle(title);    //알림창에서의 제목
                        builder.setContentText(send);   //알림창에서의 글씨
                        builder.setVibrate(new long[]{0,1500});

                        PendingIntent pie = PendingIntent.getActivity(this, 1, popupIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                        builder.setContentIntent(pie);
                        builder.setAutoCancel(true);         //클릭하면 자동으로 알림 삭제
                        Notification notification= builder.build();   //Notification 객체 생성
                        manager.notify(1, notification);             //NotificationManager가 알림(Notification)을 표시
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }else{
                //foreground
                if(PushSingleton.getInstance().getmMap().get("category").equals("normal")){
                    Bundle bun = new Bundle();
                    try {
                        JSONObject jObject = new JSONObject(String.valueOf(PushSingleton.getInstance().getmMap().get("contents")));
                        String title = jObject.getString("title");
                        String send = jObject.getString("body");
                        String contents = jObject.getString("contents");
                        bun.putString("send", send);
                        bun.putString("title", title);
                        bun.putString("contents", contents);

                        Intent popupIntent = new Intent(getApplicationContext(), AlertDialogActivity.class);
                        popupIntent.putExtras(bun);
                        PendingIntent pie = PendingIntent.getActivity(getApplicationContext(), 0, popupIntent, PendingIntent.FLAG_ONE_SHOT);
                        pie.send();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else if(PushSingleton.getInstance().getmMap().get("category").equals("circle")){
//                    Log.i("inMessagingService", "circle");
//                    Bundle bun = new Bundle();
//                    bun.putString("send", remoteMessage.getNotification().getTitle());
//                    bun.putString("title", remoteMessage.getNotification().getBody());
//                    bun.putString("contents", String.valueOf(PushSingleton.getInstance().getmMap().get("contents")));
//                    Intent popupIntent = new Intent(getApplicationContext(), Wisper_OkDialogActivity.class);
//                    popupIntent.putExtras(bun);
//                    PendingIntent pie = PendingIntent.getActivity(getApplicationContext(), 0, popupIntent, PendingIntent.FLAG_ONE_SHOT);
//                    try {
//                        pie.send();
//                    } catch (PendingIntent.CanceledException e) {
//                        e.printStackTrace();
//                    }
                    Bundle bun = new Bundle();
                    try {
                        JSONObject jObject = new JSONObject(String.valueOf(PushSingleton.getInstance().getmMap().get("contents")));
                        String title = jObject.getString("title");
                        String send = jObject.getString("body");
                        String contents = jObject.getString("contents");
                        bun.putString("send", send);
                        bun.putString("title", title);
                        bun.putString("contents", contents);

//                        Intent popupIntent = new Intent(getApplicationContext(), Wisper_OkDialogActivity.class);
                        Intent popupIntent = new Intent(getApplicationContext(), AlertDialogActivity.class);
                        popupIntent.putExtras(bun);
                        PendingIntent pie = PendingIntent.getActivity(getApplicationContext(), 0, popupIntent, PendingIntent.FLAG_ONE_SHOT);
                        pie.send();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }




        }


        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
    // [END receive_message]

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    private void sendNotification(String messageBody) {
        Intent intent = new Intent(this, FirstActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.boo)
                .setContentTitle("FCM Message")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }


    private boolean isAppIsInBackground(Context context) {
        //true = background, false = foreground
        boolean isInBackground = true;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
            List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
                if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    for (String activeProcess : processInfo.pkgList) {
                        if (activeProcess.equals(context.getPackageName())) {
                            isInBackground = false;
                        }
                    }
                }
            }
        } else {
            List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
            ComponentName componentInfo = taskInfo.get(0).topActivity;
            if (componentInfo.getPackageName().equals(context.getPackageName())) {
                isInBackground = false;
            }
        }
        return isInBackground;
    }


}
