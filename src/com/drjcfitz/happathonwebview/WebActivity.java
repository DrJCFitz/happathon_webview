package com.drjcfitz.happathonwebview;

import android.support.v4.app.Fragment;

public class WebActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		return new HappathonWebFragment();
	}

}
