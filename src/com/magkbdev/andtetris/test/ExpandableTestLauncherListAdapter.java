package com.magkbdev.andtetris.test;

import com.magkbdev.andtetris.R;
import android.content.Context; 
import android.view.View; 
import android.view.ViewGroup; 
import android.view.LayoutInflater;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView; 

public class ExpandableTestLauncherListAdapter extends BaseExpandableListAdapter 
{
	private static final TestGroup[] TEST_GROUPS = {
		TestGroup.GRAPHICS 
	}; 
	
	private final Context mContext; 
	
	public ExpandableTestLauncherListAdapter(final Context context)
	{
		this.mContext = context;
	}
	
	@Override 
	public Test getChild(final int groupIdx, final int childIdx)
	{
		return TEST_GROUPS[groupIdx].mTests[childIdx]; 
	}
	
	@Override 
	public long getChildId(final int groupIdx, final int childIdx)
	{
		return childIdx; 
	}
	
	@Override 
	public int getChildrenCount(final int groupIdx)
	{
		return TEST_GROUPS[groupIdx].mTests.length; 
	}
	
	@Override 
	public View getChildView(final int groupIdx, final int childIdx, final boolean isLastChild, final View convertView, final ViewGroup parent)
	{
		final View childView;
		if (convertView != null)
		{
			childView = convertView; 
		}
		else 
		{
			childView = LayoutInflater.from(this.mContext).inflate(R.layout.list_item_test, null); 
		}
		
		((TextView)childView.findViewById(R.id.textview_list_item_test_name)).setText(this.getChild(groupIdx, childIdx).RESID);
		return childView; 
	}
	
	@Override 
	public View getGroupView(final int groupIdx, final boolean isExpanded, final View convertView, final ViewGroup parent)
	{
		final View groupView;
		if (convertView != null)
		{
			groupView = convertView; 
		}
		else 
		{
			groupView = LayoutInflater.from(this.mContext).inflate(R.layout.list_item_testgroup, null); 
		}
		((TextView)groupView.findViewById(R.id.textview_list_item_test_group_name)).setText(this.getGroup(groupIdx).mResID);
		return groupView; 
	}
	
	@Override
	public TestGroup getGroup(final int groupIdx)
	{
		return TEST_GROUPS[groupIdx];
	}
	
	@Override 
	public int getGroupCount() 
	{
		return TEST_GROUPS.length; 
	}
	
	@Override
	public long getGroupId(final int groupIdx)
	{
		return groupIdx; 
	}
	
	@Override
	public boolean isChildSelectable(final int pGroupPosition, final int pChildPosition) 
	{
		return true;
	}

	@Override
	public boolean hasStableIds() 
	{
		return true;
	}
}; 
