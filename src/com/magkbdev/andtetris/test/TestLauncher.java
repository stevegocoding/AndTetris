/**
 * 
 */
package com.magkbdev.andtetris.test;

import com.magkbdev.andtetris.R; 
import org.andengine.AndEngine;

import android.os.Bundle;
import android.content.Intent;
import android.view.View; 
import android.app.ExpandableListActivity;
import android.widget.ExpandableListView;

/**
 * @author Guangfu Shi
 *
 */
public class TestLauncher extends ExpandableListActivity
{
	private ExpandableTestLauncherListAdapter mExpandableTestListAdapter;
	public void onCreate(final Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		if (!AndEngine.isDeviceSupported()) 
		{
			//this.showDialog(); 
		}
		
		this.setContentView(R.layout.list_tests);
		this.mExpandableTestListAdapter = new ExpandableTestLauncherListAdapter(this); 
		this.setListAdapter(this.mExpandableTestListAdapter); 
	}

	public boolean onChildClick(final ExpandableListView parent, final View v, final int groupIdx, final int childIdx, final long id)
	{
		final Test test = this.mExpandableTestListAdapter.getChild(groupIdx, childIdx); 
		
		this.startActivity(new Intent(this, test.CLASS));
		
		return super.onChildClick(parent, v, groupIdx, childIdx, id); 
	} 
}
