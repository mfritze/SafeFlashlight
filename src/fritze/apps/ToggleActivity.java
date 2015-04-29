package fritze.apps;

import android.app.NotificationManager;
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
	}

	public void toggleFlashlight(View v){
		String flash = flashController.toggleFlashLight();
		// the method will return the old flash mode, so
		// set the isOff flag to the opposite of the toggle
		if(flash.equals(Parameters.FLASH_MODE_TORCH)){
			launchNotification(true);
		}else{
			launchNotification(false);
		}
	}
	
	public void launchNotification(boolean isOff){
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		if(isOff){
			manager.cancel(NOTIFICATIONID);
		} else{
			manager.notify(NOTIFICATIONID, builder.build());

		}
	}
	
}
