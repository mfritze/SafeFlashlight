package fritze.apps;

import android.app.NotificationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.view.View;

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
	}

	public void toggleFlashlight(View v){
		flashController.toggleFlashLight();
		launchNotification();
	}
	
	public void launchNotification(){
		// TODO some check that it's not already there? or make a take down if it is? that way you dont have to return it
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		manager.notify(NOTIFICATIONID, builder.build());
	}
	
}
