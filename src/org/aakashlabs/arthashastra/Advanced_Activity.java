package org.aakashlabs.arthashastra;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;


import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;
import android.view.MenuItem;
import android.widget.TextView;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.app.ListFragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.content.Context;

/*
 *  Arthashastra - a financial literacy app
    Copyright (C) 2013 Made by Tushar Bhargava (tushar1995@gmail.com)

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 *
 */

/**
 * This module shows videos and articles suitable for advanced users. It also includes a 
 * challenge and a 'Way Forward' PDF.
 * 
 * Refer to: Home and Beginner activity's for documentation. 
 * 
 * @author Tushar Bhargava
 *
 */

public class Advanced_Activity extends Activity
{

	public static Context appContext;
	public static final String file_name="score_file";
	 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced);
 
       //ActionBar gets initiated
        ActionBar actionbar = getActionBar();
      //Tell the ActionBar we want to use Tabs.
        actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
      //initiating both tabs and set text to it.
        ActionBar.Tab Part1ATab = actionbar.newTab().setText("Part 1");
        ActionBar.Tab Part2ATab = actionbar.newTab().setText("Part 2");
      //  ActionBar.Tab Part3ATab = actionbar.newTab().setText("Part 3");
 
     //create the two fragments we want to use for display content
     // these are two objects of the fragment classes (yet to be made)   
        Fragment Part1AFragment = new Part1A();
        Fragment Part2AFragment = new Part2A();
       // Fragment Part3AFragment=  new Part3A();
 
    //set the Tab listener. Now we can listen for clicks.
        Part1ATab.setTabListener(new MyTabsListener3(Part1AFragment));
        Part2ATab.setTabListener(new MyTabsListener3(Part2AFragment));
  //      Part3ATab.setTabListener(new MyTabsListener3(Part3AFragment));
 
   //add the two tabs to the action bar
        actionbar.addTab(Part1ATab);
        actionbar.addTab(Part2ATab);
       // actionbar.addTab(Part3ATab);
        
           } // end onCreateActivity 
    

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
			
     // Inflate the menu; this adds items to the action bar if it is present.
     getMenuInflater().inflate(R.menu.home_, menu);

	// Updating score and making sure it's shown in the action bar
     MenuItem score_keeper=menu.add(Integer.toString(get_score()));
     score_keeper.setShowAsAction(2);  
    
   
	 
	return true;
	}
	
	// This determines the actions performed when menu items in the action bar are clicked
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle item selection
		
		switch(item.getItemId())
		{
		case R.id.about_the_app:
			view_PDF("AboutV1.pdf");
			return true;
			
		case R.id.help_for_app:
			view_PDF("Help.pdf");
			return true;
			
			// The default action does nothing
			default:
				return super.onOptionsItemSelected(item);
			
		}// end switch statement
		
		
	} // end function
	
	//------------------------------- Standard methods now ---------------------------------
	

	 public void view_PDF(String file_name)
		{
			// I'm assuming all my files will be stored on the SD card directly ; using bluetooth
			// for tablet testing

			File file = new File(Environment.getExternalStorageDirectory()+"/bluetooth/"+file_name);
			
			if(file.exists())
			{
			// Now I am going to use an intent to let the default application handle PDF viewing
			Uri path=Uri.fromFile(file);
			Intent pdf_view = new Intent(android.content.Intent.ACTION_VIEW);
		    pdf_view.setDataAndType(path,"application/pdf");
		    pdf_view.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		    
		    
		    try
		    {
		    	startActivity(pdf_view);
		    }// end try statement
			
			catch(ActivityNotFoundException e)
			{
				Toast no_app=Toast.makeText(getBaseContext(), "Application to view PDF missing", Toast.LENGTH_SHORT);
				no_app.show();
			}// end catch statement
			}// end if statement
			
			else
			{
				System.out.println("File does not exist !");
			}// end else statement

			
		}// end function 
	
	public int get_score()
	{
		int score=0;
		
		File file=new File(getFilesDir(), file_name);
		
		if(!file.exists())
		{
			return score;
		}// end if statement
			
		   try
		   {
			FileInputStream fis = openFileInput(file_name);
			InputStreamReader in=new InputStreamReader(fis);
			BufferedReader br=new BufferedReader(in);
			score=Integer.parseInt(br.readLine());
		   }// end try statement
		   
		   catch (Exception e)
		   {
			e.printStackTrace();
		   }// end catch statement
		
		   return score;
		   
	}// end function

	public void score_add(int add_val)
	{
		int original_sc=get_score();
		int new_sc=original_sc+add_val;
		
		// a string to write to the file
		String score=Integer.toString(new_sc);
		
		FileOutputStream outputStream;
		
		try
		{
			outputStream=openFileOutput(file_name,Context.MODE_PRIVATE);
			outputStream.write(score.getBytes());
			outputStream.close();
		}
		catch(Exception e)
		{
		e.printStackTrace();	
		}// end catch statement
		
	}// end function

	
}// end class 

class MyTabsListener3 implements ActionBar.TabListener {
public Fragment fragment;
 
public MyTabsListener3(Fragment fragment) {
this.fragment = fragment;
}
 
@Override
public void onTabReselected(Tab tab, FragmentTransaction ft) {
// no need to do anything here
}
 
@Override
public void onTabSelected(Tab tab, FragmentTransaction ft) {
ft.replace(R.id.fragment_container_A, fragment);
}
 
@Override
public void onTabUnselected(Tab tab, FragmentTransaction ft) {
ft.remove(fragment);
}
 
}// end class MyTabsListener
	

