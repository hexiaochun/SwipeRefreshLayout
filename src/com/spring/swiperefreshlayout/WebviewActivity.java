package com.spring.swiperefreshlayout;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.spring.swiperefreshlayout.view.ScrollSwipeRefreshLayout;

/**
 * 浏览器的下拉更新
 * 
 * @author yd
 * 
 */
public class WebviewActivity extends Activity implements OnRefreshListener {

	private WebView webView = null;

	private ScrollSwipeRefreshLayout refreshLayout = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview_layout);
		webView = (WebView) this.findViewById(R.id.webview);
		refreshLayout = (ScrollSwipeRefreshLayout) this.findViewById(R.id.refresh_layout);
		refreshLayout.setViewGroup(webView);
		refreshLayout.setOnRefreshListener(this);
		//设置颜色
		refreshLayout.setColorScheme(R.color.green, R.color.gray, R.color.blue_50, R.color.light_white);
		webView.loadUrl("http://blog.csdn.net/spring_he/article/details/19359099");
		webView.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				super.onProgressChanged(view, newProgress);
				if (newProgress == 100) {
					//设置加载完成后结束动画
					refreshLayout.setRefreshing(false);
				}
			}
		});
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});
	}

	@Override
	public void onRefresh() {
		//下拉重新加载
		webView.reload();
	}

}
