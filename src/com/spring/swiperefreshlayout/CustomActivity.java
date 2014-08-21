package com.spring.swiperefreshlayout;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.widget.Toast;

/**
 * 一般页面的下拉更新
 * 这个页面可以做在类似个人信息页，消息页等
 * @author yd
 *
 */
public class CustomActivity extends Activity implements OnRefreshListener {

	private SwipeRefreshLayout refreshLayout = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custom_layout);
		refreshLayout = (SwipeRefreshLayout) this.findViewById(R.id.refresh_layout);
		refreshLayout.setColorScheme(R.color.green, R.color.gray, R.color.blue_50, R.color.light_white);
		refreshLayout.setOnRefreshListener(this);
	}

	@Override
	public void onRefresh() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				handler.sendEmptyMessage(0);
			}
		}).start();
	}

	private MyHandler handler = new MyHandler();
	class MyHandler extends Handler{
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				Toast.makeText(getApplicationContext(), "refresh success...", Toast.LENGTH_SHORT).show();
				refreshLayout.setRefreshing(false);
				break;
			default:
				break;
			}
		}
	}
	
}
