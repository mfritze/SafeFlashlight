package fritze.apps;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class ToggleActivity extends FragmentActivity {
	// Based on http://stackoverflow.com/questions/6068803/how-to-turn-on-camera-flash-light-programmatically-in-android April 24th 2015
	static Camera camera;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.toggle_layout);
	}

	@Override
	protected void onResume() {
		super.onResume();
		// TODO method to update the icon and place mark
		if(camera == null){
			camera = Camera.open();
		}
		
	}
	
	@Override
	protected void onDestroy() {
		// Based on http://stackoverflow.com/questions/2563973/android-fail-to-connect-to-camera April 28 2015
		super.onDestroy();
//		if(camera != null){
//			camera.stopPreview();
//			camera.setPreviewCallback(null);
//			camera.release();
//			camera = null;
//		}
	}

	public void toggleFlashlight(View v){
		camera = Camera.open();     
		try{
			Parameters p = camera.getParameters();
			if(p.getFlashMode() == Parameters.FLASH_MODE_TORCH){
				flashLightOn(p);
			}else if (p.getFlashMode() == Parameters.FLASH_MODE_OFF){
				flashLightOff(p);
			}

		}catch(RuntimeException e){
			Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
			camera.release();
			camera = null;
		}

	}
	
	private void flashLightOn(Parameters par){
		par.setFlashMode(Parameters.FLASH_MODE_OFF);
		//TODO update image
		camera.stopPreview();
		camera.setPreviewCallback(null);
		camera.release();
		camera = null;
	}
	
	private void flashLightOff(Parameters par){
		par.setFlashMode(Parameters.FLASH_MODE_TORCH);
		camera.setParameters(par);
		camera.startPreview();
	}
	
	
}
