package in.nighthawk.email_android_logs;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by IntelliJ IDEA.
 * User: Adi
 * Date: 6/14/13
 * Time: 2:49 PM
 */

public class LogActivity extends MyActivity {

	private static final String TAG = LogActivity.class.getSimpleName();

	private StringBuilder log;

	private File file;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.logcat);

		TelephonyManager tManager = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);

		try {
			Process process = Runtime.getRuntime().exec("logcat -d");
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(process.getInputStream()));

			log=new StringBuilder();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				log.append(line);
			}
			TextView tv = (TextView)findViewById(R.id.logcat_text);
			tv.setText(log.toString());
		} catch (IOException e) {
			Log.e(TAG, "Heartbeat Logcat" + e.getMessage());
		}


		//convert log to string
		final String logString = log.toString();

		//create text file in SDCard
		File sdCard = Environment.getExternalStorageDirectory();
		File dir = new File (sdCard.getAbsolutePath() + "/myLogcat");
		dir.mkdirs();
		file = new File(dir, "logcat.txt");

		try {
			//to write logcat in text file
			FileOutputStream fOut = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fOut);

			// Write the string to the file
			osw.write(logString);
			osw.flush();
			osw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Intnet to send log file as an email attachment
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"user@domain.com"});
		intent.putExtra(Intent.EXTRA_SUBJECT, "Logs from device: " + tManager.getDeviceId());
		intent.putExtra(Intent.EXTRA_TEXT, "Logs attached");
		File root = Environment.getExternalStorageDirectory();
		if (!file.exists() || !file.canRead()) {
			Toast.makeText(this, "Attachment Error", Toast.LENGTH_SHORT).show();
			finish();
			return;
		}
		Uri uri = Uri.parse("file://" + file);
		intent.putExtra(Intent.EXTRA_STREAM, uri);
		startActivity(Intent.createChooser(intent, "Email logs.."));
	}

}


