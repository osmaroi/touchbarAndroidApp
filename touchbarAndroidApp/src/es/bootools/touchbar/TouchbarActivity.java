package es.bootools.touchbar;

import java.util.UUID;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import es.tipdot.zxing.integration.android.IntentIntegrator;
import es.tipdot.zxing.integration.android.IntentResult;

public class TouchbarActivity extends Activity {
    /** Called when the activity is first created. */
	Button mEscanear;
	Activity mActivity;
	String qr = "";
	WebView mWebView;
	Intent  i;
	String deviceId = "";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
    	final String tmDevice = "" + tm.getDeviceId();
    	final String tmSerial = "" + tm.getSimSerialNumber();
    	final String androidId = "" + android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
//
	    UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
	    deviceId = deviceUuid.toString();

        mEscanear = (Button) findViewById(R.id.button1);
        mActivity = this;
        mWebView = (WebView) findViewById(R.id.webview);
        
        i = new Intent(this, WebActivity.class);
        
        mEscanear.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

		        IntentIntegrator intentintegrator = new IntentIntegrator(mActivity);
			    intentintegrator.initiateScan();
				
				
				// i.putExtra("WEB",
				// "http://lluna.sytes.net:8080/touchbar-0.0.1-SNAPSHOT/");
				// startActivityForResult(i, 0);
				
			}
		});
    }
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case IntentIntegrator.REQUEST_CODE: {
			if (resultCode != RESULT_CANCELED) {
				IntentResult scanResult = IntentIntegrator.parseActivityResult(
						requestCode, resultCode, data);
				if (scanResult != null) {
					qr = scanResult.getContents();

					// put whatever you want to do with the code here
//					TextView tv = new TextView(this);
//					tv.setText(qr);
//					setContentView(tv);
					
					Intent  i = new Intent(this, WebActivity.class);
					i.putExtra("WEB", qr + "&codUnicoMovil=" + deviceId);
					startActivityForResult(i, 0);
					String s;

				}
				
			}
			break;
		}
		}
//		Intent  i = new Intent(this, WebActivity.class);
//		i.putExtra("WEB", qr);
//		startActivityForResult(i, 0);
	}
}