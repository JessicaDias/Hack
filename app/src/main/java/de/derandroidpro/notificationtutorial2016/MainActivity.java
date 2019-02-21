package de.derandroidpro.notificationtutorial2016;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnNotification;

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNotification = (Button) findViewById(R.id.button);
        btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Chamou btnNotification.setOnClickListener");
                Intent startNotificationServiceIntent = new Intent(MainActivity.this, NotificationDisplayService.class);
                Log.d(TAG, "Chamou startService");
                startService(startNotificationServiceIntent);
            }
        });
    }

}
