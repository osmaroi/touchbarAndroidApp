package es.bootools.touchbar;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;

public class WebActivity extends Activity {

	private WebView mWebView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.web);

		mWebView = (WebView) findViewById(R.id.webview);

//		
//		// webView config
		mWebView.getSettings().setJavaScriptEnabled(true);
//		mWebView.getSettings().setAllowFileAccess(true);
//		mWebView.getSettings().setPluginsEnabled(true);
		mWebView.getSettings().setDomStorageEnabled(true);
		mWebView.getSettings().setSupportMultipleWindows(false);
		mWebView.getSettings().setAppCacheEnabled(false);
//		mWebView.getSettings().setDatabaseEnabled(true);
//		// mWebView.getSettings().setDatabasePath("/sdcard/developer/");
//		mWebView.getSettings().setUserAgent(1);
//
		mWebView.setHorizontalScrollBarEnabled(false);
		mWebView.setVerticalScrollBarEnabled(false);
//
		mWebView.getSettings().setSupportZoom(false);
		mWebView.getSettings().setBuiltInZoomControls(false);
//
//		mWebView.setBackgroundColor(0);
		
		
		
//		mWebView.setWebViewClient(new WebViewClient());
		
		Bundle b = getIntent().getExtras();
		String qr = b.getString("WEB");
		
		mWebView.loadUrl(qr);
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
//			mWebView.goBack();
			mWebView.clearHistory();
			finish();
			return true;
		} else if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			mWebView.loadUrl("about:blank");
			mWebView.clearHistory();
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);

	}


	}