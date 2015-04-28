package fritze.apps;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

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
		flashController.updateImage();
	}

	
	@Override
	protected void onPause(){
		super.onPause();
		flashController.pause();
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		flashController.tearDown();
	}

	public void toggleFlashlight(View v){
		flashController.toggleFlashLight();
	}
	

	
}
