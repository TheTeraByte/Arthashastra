package org.aakashlabs.arthashastra;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;
import android.view.MenuItem;
import android.widget.TextView;


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
 * This activity shows the videos and ebooks that are suitable for absolute beginners
 * Some of the content serves to answer the important question - Why learn about financial
 * literacy.
 * 
 * Documentation for some repeated code is also to be found here.
 * 
 * The UI is simple, it consists of buttons shaped as clapboards (denoting video) or documents
 * (denoting PDF ebooks/articles). A few lines of description are written below (TextView).
 * Clicking them causes the respective 'intents' to be triggered.
 * 
 * This UI pattern is repeated in other activities as well. 
 * 
 * Also there is tabbed browsing, each tab is represented by a fragment (Part1B is the first
 * tab for Beginner activity and so on). Each fragment has some subtle differences from a 
 * normal activity (in particular the 'Context' has to be obtained from the base view). 
 * 
 * However, once you understand how one fragment functions you understand all the rest.
 * 
 * Note: Refer to Home Activity for documentation of standard functions/methods like view_PDF 
 * 
 * @author Tushar Bhargava
 *
 */

public class Beginner_Activity extends Activity
{
	//Global variables
	public static final String file_name="score_file";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_beginner);
		
		// Initializing the Action Bar
		ActionBar actionbar=getActionBar();
		
		// Tell the ActionBar to use tabs for navigation
		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		// Initiating the tabs and setting text for them 
		ActionBar.Tab Part1BTab = actionbar.newTab().setText("Part 1");
		ActionBar.Tab Part2BTab = actionbar.newTab().setText("Part 2");
		
		// Create the framents that are displayed in each tab (two types of fragment available
		// ) ; confirm 
		Fragment Part1BFragment=new Part1B();
		Fragment Part2BFragment=new Part2B();
		
		// Set the tab listener 
		Part1BTab.setTabListener(new MyTabsListener1(Part1BFragment));
		Part2BTab.setTabListener(new MyTabsListener1(Part2BFragment));
		
		// Adding the tabs
		actionbar.addTab(Part1BTab);
		actionbar.addTab(Part2BTab);
		
	}// end onCreate function
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
			
     // Inflate the menu; this adds items to the action bar if it is present.
     getMenuInflater().inflate(R.menu.home_, menu);
	   
	 // Updating score and making sure it's shown in the action bar
     MenuItem score_keeper=menu.add(Integer.toString(get_score()));
     score_keeper.setShowAsAction(2);  
     
    
    
	 
	return true;
	}// end onCreateOptionsMenu function
	
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
			
			default:
				return super.onOptionsItemSelected(item);
			
		}// end switch statement
		
		
	} // end function
	
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
			Log.d(TEXT_SERVICES_MANAGER_SERVICE, "File does not exist !");
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
	
	/*
	 * This function updates the score by adding 'add_val' to it.
	 * It first retrieves the original score using the 'get_score' function and then adds
	 * 'add_val' to it. It then writes the new score to the file, deleting the old score.
	 */

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

/*
 * This class is a tab listener. It defines what functions are to be performed when a tab
 * is selected or unselected or reselected. In this app when a tab is selected it shows the 
 * fragment associated with it.
 */

class MyTabsListener1 implements ActionBar.TabListener {
public Fragment fragment;
 
public MyTabsListener1(Fragment fragment) {
this.fragment = fragment;
}
 
@Override
public void onTabReselected(Tab tab, FragmentTransaction ft) {
// no need to do anything here
}
 
@Override
public void onTabSelected(Tab tab, FragmentTransaction ft) {
ft.replace(R.id.fragment_container_B, fragment);
}
 
@Override
public void onTabUnselected(Tab tab, FragmentTransaction ft) {
ft.remove(fragment);
}
 
}// end class MyTabsListener
