package com.spring.swiperefreshlayout;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * 普通列表的下拉更新
 * @author yd
 *
 */
public class ListviewActivity extends Activity implements OnRefreshListener {

	private List<String> datas = new ArrayList<String>();
	
	private ListView listView = null;
	
	private SwipeRefreshLayout refresh_layout = null;

	private ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_layout);
		listView  = (ListView) this.findViewById(R.id.listview);
		refresh_layout = (SwipeRefreshLayout) this.findViewById(R.id.refresh_layout);
		refresh_layout.setColorScheme(R.color.green, R.color.gray, R.color.blue_50, R.color.light_white);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
				android.R.id.text1, datas);
		listView.setAdapter(adapter);
		for (int i = 0; i < 30; i++) {
			datas.add("item:"+i);
		}
		adapter.notifyDataSetChanged();
		refresh_layout.setOnRefreshListener(this);
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
				datas.add(0,"item:refresh...");
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
				refresh_layout.setRefreshing(false);
				adapter.notifyDataSetChanged();
				break;
			default:
				break;
			}
		}
	}
	
}
