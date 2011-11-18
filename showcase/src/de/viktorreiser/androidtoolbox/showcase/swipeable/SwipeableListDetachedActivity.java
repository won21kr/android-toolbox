package de.viktorreiser.androidtoolbox.showcase.swipeable;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import de.viktorreiser.androidtoolbox.showcase.R;
import de.viktorreiser.androidtoolbox.showcase.SwipeableShowcaseActivity;
import de.viktorreiser.toolbox.widget.HiddenQuickActionSetup;
import de.viktorreiser.toolbox.widget.SwipeableHiddenView;
import de.viktorreiser.toolbox.widget.SwipeableHiddenView.HiddenViewSetup;

public class SwipeableListDetachedActivity extends Activity {
	
	// OVERRIDDEN =================================================================================
	
	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		
		setTitle(SwipeableShowcaseActivity.getActivityTitle(getClass()));
		setContentView(R.layout.swipeable_detached);
		
		HiddenQuickActionSetup s1 = new HiddenQuickActionSetup(this);
		s1.setDetachFromList(true);
		s1.setSwipeOnLongClick(true);
		s1.setBackgroundResource(R.drawable.quickaction_background);
		s1.addAction(1, 0, R.drawable.quickaction_url);
		s1.addAction(2, 0, R.drawable.quickaction_urlopen);
		((SwipeableHiddenView) findViewById(R.id.quick_action_view)).setHiddenViewSetup(s1);
		findViewById(R.id.quick_action_view).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(SwipeableListDetachedActivity.this,
						"Clicked!", Toast.LENGTH_SHORT).show();
			}
		});
		
		final HiddenColorTriggerSetup s2 = new HiddenColorTriggerSetup();
		
		((SwipeableHiddenView) findViewById(R.id.hidden_view)).setHiddenViewSetup(s2);
		findViewById(R.id.swipeable_button).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				s2.reset();
			}
		});
	}
	
	// PRIVATE ====================================================================================
	
	private class HiddenColorTriggerSetup extends HiddenViewSetup {
		
		private LinearLayout mHiddenLayout;
		
		public HiddenColorTriggerSetup() {
			final Context context = SwipeableListDetachedActivity.this;
			setDetachFromList(true);
			
			mHiddenLayout = new LinearLayout(context);
			mHiddenLayout.setLayoutParams(new LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
			
			for (int i = 0; i < 3; i++) {
				Button b = new Button(context);
				LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
						0, LinearLayout.LayoutParams.FILL_PARENT);
				lp.weight = 1f;
				b.setLayoutParams(lp);
				mHiddenLayout.addView(b);
			}
			
			((Button) mHiddenLayout.getChildAt(0)).setText("Red");
			mHiddenLayout.getChildAt(0).setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					getWindow().getDecorView().findViewById(R.id.root_view)
							.setBackgroundColor(0xff440000);
				}
			});
			
			((Button) mHiddenLayout.getChildAt(1)).setText("Green");
			mHiddenLayout.getChildAt(1).setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					getWindow().getDecorView().findViewById(R.id.root_view)
							.setBackgroundColor(0xff004400);
				}
			});
			
			((Button) mHiddenLayout.getChildAt(2)).setText("Blue");
			mHiddenLayout.getChildAt(2).setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					getWindow().getDecorView().findViewById(R.id.root_view)
							.setBackgroundColor(0xff000044);
				}
			});
		}
		
		public void reset() {
			getWindow().getDecorView().findViewById(R.id.root_view).setBackgroundColor(0);
		}
		
		@Override
		public View getHiddenView() {
			return mHiddenLayout;
		}
		
	}
}
