package com.spring.swiperefreshlayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity implements OnItemClickListener {

	private ListView listView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ListView) this.findViewById(R.id.listview);
		ArrayAdapter<String> adapter = new ArrayAdapter<>
		(this, android.R.layout.simple_list_item_1, android.R.id.text1,
				new String[]{"listview","webview","custom view"});
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = new Intent();
		switch (position) {
		case 0:
			intent.setClass(this, ListviewActivity.class);
			startActivity(intent);
			break;
		case 1:
			intent.setClass(this, WebviewActivity.class);
			startActivity(intent);
			break;
		case 2:
			intent.setClass(this, CustomActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

}
