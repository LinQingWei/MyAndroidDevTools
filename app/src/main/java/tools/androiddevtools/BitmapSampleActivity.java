package tools.androiddevtools;

import DevToolsFactory.DevToolsFactory;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class BitmapSampleActivity extends ActionBarActivity implements View.OnClickListener {

	private ImageView bitmapMask;
	private ImageView bitmapBg;
	private ImageView bitmapCompose;
	private Button btn_without_gradient;
	private Button btn_with_gradient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bitmap_sample);
		initialize();
		btn_with_gradient.setOnClickListener(this);
		btn_without_gradient.setOnClickListener(this);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		Intent intent = new Intent();
		switch (id) {
			case R.id.bitmap_tomain :
				intent.setClass(this, SampleActivity.class);
				startActivity(intent);
				break;
			case R.id.bitmap_tofile :
				intent.setClass(this, FileSampleActivity.class);
				startActivity(intent);
				break;
			case R.id.bitmap_toview :
				intent.setClass(this, ViewSampleActivity.class);
				startActivity(intent);
				break;
			default :
				break;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		Bitmap bgPic = BitmapFactory.decodeResource(getResources(), R.drawable.test);
		switch (v.getId()) {
			case R.id.btn_compose_without_gadient :
				Bitmap maskPic = BitmapFactory.decodeResource(getResources(), R.drawable.matte_with_alpha);
				bitmapMask.setImageBitmap(maskPic);
				Bitmap composeBitmap = null;
				composeBitmap = DevToolsFactory.getBitmapTools().createBitmapWithAlphaMatte(getApplicationContext(),
						maskPic, bgPic, true);

				if (composeBitmap != null) {
					bitmapCompose.setImageBitmap(composeBitmap);
				} else {
					Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
				}

				break;
			case R.id.btn_compose_gadient :
				Bitmap maskPic_withGradient = BitmapFactory.decodeResource(getResources(),
						R.drawable.matte_without_alpha);
				bitmapMask.setImageBitmap(maskPic_withGradient);
				Bitmap composeWithGradient = null;
				composeWithGradient = DevToolsFactory.getBitmapTools()
						.createBitmapWithAlphaMatte(getApplicationContext(), maskPic_withGradient, bgPic, false);
				if (composeWithGradient != null) {
					bitmapCompose.setImageBitmap(composeWithGradient);
				} else {
					Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
				}
				break;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_bitmap_sample, menu);
		return true;
	}

	private void initialize() {

		bitmapMask = (ImageView) findViewById(R.id.bitmap_mask);
		bitmapBg = (ImageView) findViewById(R.id.bitmap_bg);
		bitmapCompose = (ImageView) findViewById(R.id.bitmap_compose);
		btn_without_gradient = (Button) findViewById(R.id.btn_compose_without_gadient);
		btn_with_gradient = (Button) findViewById(R.id.btn_compose_gadient);
	}

}
