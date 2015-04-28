package fritze.apps;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

public class ToggleActivity extends FragmentActivity {

	private static FlashController flashController = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.toggle_layout);
		flashController = new FlashController(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		// TODO method to update the icon and place mark
		flashController.updateImage();
	}
	
	public void toggleFlashlight(View v){
		flashController.toggleFlashLight();
	}
	

	
}
