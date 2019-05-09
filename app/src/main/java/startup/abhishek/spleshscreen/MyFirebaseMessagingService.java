package startup.abhishek.spleshscreen;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    public static int NOTIFICATION_ID=1;
    public static String  tokken;

    public MyFirebaseMessagingService()
    {

    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        genereteNotification(remoteMessage.getNotification().getBody(),remoteMessage.getNotification().getTitle());
    }


    private void genereteNotification(String body, String title) {

        Intent intent = new Intent(this, startup.abhishek.spleshscreen.NotificationManager.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);

        Uri soundUrl = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle(title)
                .setContentText(body)
                .setContentIntent(pendingIntent);


        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if(NOTIFICATION_ID > 1073741824){
            NOTIFICATION_ID=0;
            }
            notificationManager.notify(NOTIFICATION_ID++,notificationBuilder.build());



    }
}
