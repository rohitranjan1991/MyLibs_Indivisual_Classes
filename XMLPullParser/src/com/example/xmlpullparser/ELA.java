package com.example.xmlpullparser;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class ELA extends BaseExpandableListAdapter implements OnClickListener {
	ArrayList<songs> main;
	ImageLoader imageLoader;
	Context context;
	private LayoutInflater inflater;
	AQuery aq;
	// private int size=0;
	public ELA(Context mainActivity, ArrayList<songs> temp,AQuery aq1) {
		this.context = mainActivity;
		inflater = LayoutInflater.from(context);
		main = temp;
		aq=aq1;
		
		// size=main.size();

	}

	@Override
	public Object getChild(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getChildId(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.BaseExpandableListAdapter#notifyDataSetChanged()
	 */
	@Override
	public void notifyDataSetChanged() {
		// TODO Auto-generated method stub

		super.notifyDataSetChanged();
	}

	@Override
	public View getChildView(int arg0, int arg1, boolean arg2, View arg3,
			ViewGroup arg4) {
		
		
		String ret = null;
		switch (arg1) {
		case 0:
			ret = "Id : ";
			break;
		case 1:
			ret = "Title : ";
			break;
		case 2:
			ret = "Artist : ";
			break;
		case 3:
			ret = "Duration : ";
			break;
		case 4:
			ret=null;
			break;
		}
		
		if (arg3 == null || ret!=null) {
			arg3 = inflater.inflate(R.layout.child_list, arg4, false);
			TextView textView = (TextView) arg3.findViewById(R.id.tvchild);
			textView.setText(ret + main.get(arg0).get(arg1));
			//aq.id(R.id.tvchild).text(ret + main.get(arg0).get(arg1));
		}
		else
			{arg3 = inflater.inflate(R.layout.child_image, arg4, false);
			try{aq.id(R.id.imageView1).image(main.get(arg0).get(arg1),true, true);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			}
				return arg3;
	}

	@Override
	public int getChildrenCount(int arg0) {

		return 5;//main.get(arg0).getChildrenSize();
	}

	@Override
	public Object getGroup(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return main.size();
	}

	@Override
	public long getGroupId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getGroupView(int arg0, boolean arg1, View view, ViewGroup arg3) {

		if (view == null) {
			view = inflater.inflate(R.layout.parent_list, arg3, false);
		}

		TextView textView = (TextView) view
				.findViewById(R.id.list_item_text_view);
		textView.setText(main.get(arg0).getId());
		Button del = (Button) view.findViewById(R.id.bDel);
		del.setOnClickListener(this);
		// return the entire view
		/*view.setClickable(true);
	    view.setFocusable(true);

	    view.setBackgroundResource(android.R.drawable.menuitem_background);*/
		return view;

	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return true;
	}

	public int findPos(String toFind) {
	
		int  j;

		for (j = 0; j < main.size(); j++) {
			if (main.get(j).getId().contentEquals(toFind)) {

				break;
			}

		}
		return j;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.BaseExpandableListAdapter#getGroupTypeCount()
	 */
	@Override
	public int getGroupTypeCount() {
		// TODO Auto-generated method stub
		return super.getGroupTypeCount();
	}

	@Override
	public void onClick(View v) {
		
		// Button bDel = (Button) v.findViewById(R.id.bDel);
		View vP = (View) v.getParent();
		TextView tvParent = (TextView) vP.findViewById(R.id.list_item_text_view);
		String text = (String) tvParent.getText();
		main.remove(findPos(text));
		Integer si = main.size();
		String size = si.toString();
		// Toast.makeText(context,"size="+size, Toast.LENGTH_SHORT).show();
		// size--;
		notifyDataSetChanged();

	}

	/* (non-Javadoc)
	 * @see android.widget.BaseExpandableListAdapter#onGroupExpanded(int)
	 */
	@Override
	public void onGroupExpanded(int groupPosition) {
		// TODO Auto-generated method stub
		super.onGroupExpanded(groupPosition);
	}

}
