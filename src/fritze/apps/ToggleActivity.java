package fritze.apps;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.view.View;

@SuppressWarnings("deprecation")
public class ToggleActivity extends FragmentActivity {
	private NotificationCompat.Builder builder;
	private final int NOTIFICATIONID = 2147483647; // 2**31 - 1 mersene prime 
	private static FlashController flashController = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.toggle_layout);
		flashController = new FlashController(this);
		
		builder = new NotificationCompat.Builder(this)
		.setSmallIcon(R.drawable.temp_notification)
		.setContentTitle("Foxlight")
		.setContentText("Flashlight");
	}


	@Override
	protected void onResume() {
		super.onResume();
		flashController.openCamera();
		flashController.updateImage();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		flashController.tearDown();
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		manager.cancel(NOTIFICATIONID);
	}

	public void toggleFlashlight(View v){
		String flash = flashController.toggleFlashLight();
		// the method will return the old flash mode, so
		// set the isOff flag to the opposite of the toggle
		if(flash.equals(Parameters.FLASH_MODE_OFF)){
			launchNotification();
		}
	}
	
	public void launchNotification(){
		// The pending intent will start this activity, 
		// but will not create a new one, from the Update Current flag
		NotificationManager manager = (NotificationManager)
				getSystemService(NOTIFICATION_SERVICE);
		Intent resultIntent = new Intent(this, ToggleActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, 
				resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		builder.setContentIntent(pendingIntent);
		builder.setAutoCancel(true);
		manager.notify(NOTIFICATIONID, builder.build());
	}
	
}
