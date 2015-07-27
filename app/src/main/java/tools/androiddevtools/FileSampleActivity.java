package tools.androiddevtools;

import DevToolsFactory.DevToolsFactory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FileSampleActivity extends ActionBarActivity implements View.OnClickListener {

	private Button createFolder;
	private TextView tvFile;
	DevToolsFactory devTool;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_sample);
		initialize();
		devTool = new DevToolsFactory();
		devTool.getFileTools().initialize(this);
		createFolder.setOnClickListener(this);

	}

	// 点击事件
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.createfolder :
				tvFile.setText(devTool.getFileTools().createFolder("t1/t2/t3"));
				break;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_file_sample, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = new Intent();
		switch (item.getItemId()) {
			case R.id.dataactivity :
				intent.setClass(this, SampleActivity.class);
				startActivity(intent);
				break;
		}

		return super.onOptionsItemSelected(item);
	}

	private void initialize() {

		createFolder = (Button) findViewById(R.id.createfolder);
		tvFile = (TextView) findViewById(R.id.tv_file);
	}

}
