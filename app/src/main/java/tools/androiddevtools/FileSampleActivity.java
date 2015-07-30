package tools.androiddevtools;

import java.io.File;
import java.io.IOException;

import DevToolsFactory.DevToolsFactory;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FileSampleActivity extends ActionBarActivity implements View.OnClickListener {

	private Button createFolder;
	private Button copyFiles;
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
		copyFiles.setOnClickListener(this);

	}

	// 点击事件
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.createfolder :
				tvFile.setText(devTool.getFileTools().createFolder("/data/data/" + getPackageName(), "t1/t2/t3"));
				break;
			case R.id.copyfiles :
				String path = Environment.getExternalStorageDirectory() + "/copyTest1.txt";
				String toPath = Environment.getExternalStorageDirectory() + "/copytest/copiedTest2.txt";
				File file = new File(path);
				try {
					if (!file.exists()) {
						file.createNewFile();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					tvFile.setText(devTool.getFileTools().copyfile(path, toPath, true));
				} catch (IOException e) {
					Toast.makeText(FileSampleActivity.this, "复制失败", Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}
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
			case R.id.file_toview :
				intent.setClass(this, ViewSampleActivity.class);
				startActivity(intent);
				break;

		}

		return super.onOptionsItemSelected(item);
	}

	private void initialize() {

		createFolder = (Button) findViewById(R.id.createfolder);
		copyFiles = (Button) findViewById(R.id.copyfiles);
		tvFile = (TextView) findViewById(R.id.tv_file);
	}

}
