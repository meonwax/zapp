package de.christinecoenen.code.zapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.BaseAdapter;
import android.widget.GridView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import de.christinecoenen.code.zapp.adapters.ChannelListAdapter;
import de.christinecoenen.code.zapp.model.IChannelList;
import de.christinecoenen.code.zapp.model.json.JsonChannelList;

public class ChannelListActivity extends AppCompatActivity {

	protected @BindView(R.id.toolbar) Toolbar toolbar;
	protected @BindView(R.id.gridview_channels) GridView channelGridView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_channel_list);
		ButterKnife.bind(this);

		setSupportActionBar(toolbar);
		ViewCompat.setNestedScrollingEnabled(channelGridView, true);

		IChannelList channelList = new JsonChannelList(this);
		BaseAdapter gridAdapter = new ChannelListAdapter(this, channelList);
		channelGridView.setAdapter(gridAdapter);
	}

	@OnItemClick(R.id.gridview_channels)
	void onGridItemClick(int position) {
		Intent intent = ChannelDetailActivity.getStartIntent(this, position);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_channel_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menu_changelog:
				Intent intent = ChangelogActivity.getStartIntent(this);
				startActivity(intent);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}
