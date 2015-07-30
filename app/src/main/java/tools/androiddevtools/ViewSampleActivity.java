package tools.androiddevtools;

import java.io.File;
import java.io.IOException;

import DevToolsFactory.DevToolsFactory;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ViewSampleActivity extends ActionBarActivity implements View.OnClickListener, View.OnTouchListener {

	private ImageView viewImageview;
	private Button btnGetround;
	private TextView viewTx;
	private EditText editText;
	private RelativeLayout layout;
	private int x;
	private int y;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_sample);
		initialize();
		btnGetround.setOnClickListener(this);
		layout.setOnTouchListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_getround :
				int radius = 0;
				String str;
				Bitmap roundBitmap;
				if ((str = editText.getText().toString()) != null) {
					radius = Integer.parseInt(str);
					try {
						// 得到圆形图
						roundBitmap = DevToolsFactory.getViewTools().getBitmapRound(ViewSampleActivity.this, x, y,
								radius);
						if (roundBitmap != null) {
							DevToolsFactory.getBitmapTools().saveBitmap(roundBitmap,
									Environment.getExternalStorageDirectory() + "/", "test.jpg");
							viewTx.setText("成功捕捉到图片至：" + Environment.getExternalStorageDirectory() + "/test.jpg");
							// 打开文件
							Intent intent = new Intent();
							intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							intent.setAction(Intent.ACTION_VIEW);
							File file = new File(Environment.getExternalStorageDirectory() + "/test.jpg");
							String type = "image/jpeg";
							intent.setDataAndType(Uri.fromFile(file), type);
							startActivity(intent);
						} else {
							Toast.makeText(getApplicationContext(), "错误，请查证x或者y是否小于半径", Toast.LENGTH_LONG).show();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}

				} else {
					Toast.makeText(getApplicationContext(), "请输入半径", Toast.LENGTH_SHORT).show();
				}

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_view_sample, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		Intent intent = new Intent();
		switch (id) {
			case R.id.view_tomain :
				intent.setClass(this, SampleActivity.class);
				startActivity(intent);
				break;
			case R.id.view_tofile :
				intent.setClass(this, FileSampleActivity.class);
				startActivity(intent);
				break;
			default :
				break;
		}

		return super.onOptionsItemSelected(item);
	}

	private void initialize() {

		viewImageview = (ImageView) findViewById(R.id.view_imageview);
		btnGetround = (Button) findViewById(R.id.btn_getround);
		viewTx = (TextView) findViewById(R.id.view_tx);
		editText = (EditText) findViewById(R.id.ed_radius);
		layout = (RelativeLayout) findViewById(R.id.layout);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN :
				x = (int) event.getX();
				y = (int) event.getY();
				viewTx.setText("x=" + x + " y=" + y);
				return true;
		}
		return false;
	}
}
