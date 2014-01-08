package com.example.day07;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	// ��ť�����ʱ��ϵͳ���Զ�����onClick()
	public void onClick(View v) {
		if (v.getTag().toString().equals("linear_layout")) {
			setContentView(R.layout.linearlayout_register);
		} else if (v.getTag().toString().equals("relative_layout")) {
			setContentView(R.layout.relativelayout_register);
		} else if (v.getTag().toString().equals("table_layout")) {
			setContentView(R.layout.tablelayout_register);
		} else if (v.getTag().toString().equals("grid_layout")) {
			setContentView(R.layout.gridlayout_register);
		}
		// ����־���ڵ�main��ǩ�����Button.tag�����Ե�ֵ
		Log.i("main", v.getTag().toString());

	}

}
