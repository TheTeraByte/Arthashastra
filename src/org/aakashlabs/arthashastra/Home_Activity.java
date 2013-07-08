package org.aakashlabs.arthashastra;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
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
 * This activity serves a similar function as that of a home page in a browser.
 * It's the first page you seen when you open the app. 
 * It shows the 3 main modules - Beginner, Novice and Advanced.
 * Clicking on any one starts the Activity associated with that button.
 * 
 * Also note that all layouts (the UI) can be found in 'res'->'layout'
 * The nomenclature of the layouts is extremely straightforward and reflects the activity
 * or fragment it is associated with.
 * 
 * The Home activity is also important because the documentation for recurring blocks of code
 * (standard methods) is to be found here. 
 * 
 * @author Tushar Bhargava
 *
 */

public class Home_Activity extends Activity {

	public static final String file_name="score_file";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_);
	  
		
		// Linking buttons to their files
		Button about=(Button)findViewById(R.id.fin_lit_basic_button);
		
		about.setOnClickListener(new View.OnClickListener(){
			
			public void onClick(View v)
			{
				startActivity(new Intent("org.aakashlabs.arthashastra.Beginner_Activity"));
			}// end innermost function
		});// end inner function
		
		Button novice=(Button)findViewById(R.id.medium_user);
		
		novice.setOnClickListener(new View.OnClickListener(){
			
			public void onClick(View v)
			{
				startActivity(new Intent("org.aakashlabs.arthashastra.Novice_Activity"));
			}// end innermost function
		});// end inner function
		
		Button advanced=(Button)findViewById(R.id.advanced_user);
		advanced.setOnClickListener(new View.OnClickListener(){
			
			public void onClick(View v)
			{
				startActivity(new Intent("org.aakashlabs.arthashastra.Advanced_Activity"));
			}// end innermost function
		});// end inner function
		
	}// end onCreate Function
		
	

	/* This code initiates the 'Action Bar'. It inflates it with menu items defined in 
	* home_.xml
	*/
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
			
     // Inflate the menu; this adds items to the action bar if it is present.
     getMenuInflater().inflate(R.menu.home_, menu);
	   
	 // Updating score and making sure it's shown in the action bar
     // The score_keeper is a dynamic addition to the menu
     MenuItem score_keeper=menu.add(Integer.toString(get_score()));
     // This ensures that score_keeper is always shown and not hidden in the overflow menu 
     score_keeper.setShowAsAction(2);  
     
     
     
	return true;
	}
	
	// This helps define the actions that are to be performed when menu items of the action
	// bar are clicked
    
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle item selection
		
		switch(item.getItemId())
		{
		
		// Clicking the 'About' item in the overflow menu opens the About PDF file
		case R.id.about_the_app:
			view_PDF("AboutV1.pdf");
			return true;
			
		// Clicking the 'Help' item in the overflow menu opens the 'Help' PDF file
		case R.id.help_for_app:
			view_PDF("Help.pdf");
			return true;
			
			// Clicking any other item (such as the 'Score' text etc.) performs the 'default'
			// action which is *nothing*. Clicking these items does not lead to any change 
			// occurring
			default:
				return super.onOptionsItemSelected(item);
			
		}// end switch statement
		
		
	} // end function
	

	/* This function provides PDF viewing functionality for the app. You pass the file name
	* with the '.pdf' postscript and it opens the file provided it is in the correct folder.
    * This function essentially calls an 'Intent' that opens the PDF file in a PDF Viewer
	* application. The PDF viewer application must be installed in the device. 
	*/
	
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
				Toast no_file=Toast.makeText(getBaseContext(), "File does not exist !", Toast.LENGTH_SHORT);
				no_file.show();
			}// end else statement

			
		}// end function 
	 
	 /*
	  *  This function returns the current score of the user. The score is stored in a file
	  *   in the Internal Storage of the app.
	  */
	 
		
		public int get_score()
		{
			int score=0;
			
			// Creates a file with the name of the string stored in 'file_name' in the 
			// internal directory of the app
			File file=new File(getFilesDir(), file_name);
			
			// If the file does not exist then we assume the user has just opened the app and
			// the score is zero
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


}// end class
