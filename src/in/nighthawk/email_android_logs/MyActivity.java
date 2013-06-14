package in.nighthawk.email_android_logs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyActivity extends Activity implements View.OnClickListener {
	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button sendEmail = (Button) findViewById(R.id.send_email_button);
		sendEmail.setOnClickListener(this);
	}

	public void onClick(View view) {

		if(view.getId()==R.id.send_email_button) {
			startActivity(new Intent(this, LogActivity.class));
		}

	}
}

