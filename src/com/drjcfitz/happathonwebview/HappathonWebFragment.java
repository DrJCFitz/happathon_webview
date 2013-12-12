package com.drjcfitz.happathonwebview;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

@SuppressLint("SetJavaScriptEnabled")
public class HappathonWebFragment extends Fragment {

	private WebView webview;
	private Button mButtonToggle, mButtonHappyVal;

	@Override
	public void onCreate(Bundle savedInstanceState) { 
	    super.onCreate(savedInstanceState); 
	    
	    /*Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("file:///android_asset/androidHappathonWebview.html"));
	    
	    if (isIntentAvailable(getActivity(), i)) {
		    startActivity(i);	    	
	    }*/

	    
	} 
	
    /*public static boolean isIntentAvailable(Context ctx, Intent intent) {
 	   final PackageManager mgr = ctx.getPackageManager();
 	   List<ResolveInfo> list =
 	      mgr.queryIntentActivities(intent, 
 	         PackageManager.MATCH_DEFAULT_ONLY);
 	   return list.size() > 0;
 	}*/
    
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@SuppressWarnings("deprecation")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_webfragment, container, false);
		
	    webview = (WebView)view.findViewById(R.id.webview); 
	    webview.getSettings().setJavaScriptEnabled(true);
	    webview.getSettings().setAllowFileAccessFromFileURLs(true);
	    webview.setWebViewClient(new HappathonWebViewClient()); 
	    webview.loadUrl("file:///android_asset/androidHappathonWebview.html"); 
	    WebSettings settings = webview.getSettings(); 
	    settings.setJavaScriptEnabled(true); 
	    settings.setDatabaseEnabled(true); 
	    settings.setDomStorageEnabled(true);
	    String databasePath = getActivity().getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath(); 
	    settings.setDatabasePath(databasePath);
	    webview.setWebChromeClient(new WebChromeClient() { 
	    public void onExceededDatabaseQuota(String url, String databaseIdentifier, long currentQuota, long estimatedSize, long totalUsedQuota, WebStorage.QuotaUpdater quotaUpdater) { 
	            quotaUpdater.updateQuota(5 * 1024 * 1024); 
	        } 
	    }); 
	    
	    mButtonToggle = (Button)view.findViewById(R.id.button_toggleValues);
	    mButtonToggle.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				webview.loadUrl("javascript:"+"togglePlots();");
			}
		});
	    
	    mButtonHappyVal = (Button)view.findViewById(R.id.button_setValues);
	    mButtonHappyVal.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				webview.loadUrl("javascript:"+"reSelectHappyVal();");
			}
		});

	    return view;
	}

	private class HappathonWebViewClient extends WebViewClient { 
	    public boolean shouldOverrideUrlLoading(WebView view, String url) { 
	        view.loadUrl(url); 
	        return true; 
	    } 
	}
	
	
}
