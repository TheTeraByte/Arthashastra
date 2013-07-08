package org.aakashlabs.arthashastra;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.Toast;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
 * Refer to Part1A fragment for documentation.
 * 
 * @author Tushar Bhargava
 *
 */

public class Part1B extends Fragment
{
	// Global variables
	public static final String file_name="score_file";

	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	    
	     View V = inflater.inflate(R.layout.part1blayout, container, false);  
	     

			// Set buttons action
			final Button tortoise_b=(Button)V.findViewById(R.id.tortoise_buffet);
			tortoise_b.setOnClickListener(new View.OnClickListener(){
					
				public void onClick(View v)
				{
					score_add(200);
					
					// showing a tick mark on being clicked - very ugly change
					// must also write to a file which will later be read to determine 
					// whether this task is done or not
				//	tortoise_b.setBackgroundResource(R.drawable.tick);
					view_PDF("TortoiseBuffett.pdf");
					
				}// end innermost function
			});// end onClick function
			
			Button interest_basic=(Button)V.findViewById(R.id.interest_vid);
			interest_basic.setOnClickListener(new View.OnClickListener(){
				
				public void onClick(View v)
				{
					score_add(150);
					// the URL of the Khan Academy video
					Intent i=new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("http://youtu.be/GtaoP0skPWc"));
					startActivity(i);
				}// end innermost function
			});// end onClick function
			
			Button interest_part2=(Button)V.findViewById(R.id.interest_part_2);
			interest_part2.setOnClickListener(new View.OnClickListener(){
				
				public void onClick(View v)
				{
					score_add(150);
					// the URL of the Khan Academy video
					Intent i=new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/watch?feature=player_embedded&v=t4zfiBw0hwM"));
					startActivity(i);
				}// end innermost function
			});// end onClick function
			
			Button rule_72=(Button)V.findViewById(R.id.rule_of_72);
			rule_72.setOnClickListener(new View.OnClickListener(){
				
				public void onClick(View v)
				{
					score_add(150);
					// the URL of the Khan Academy video
					Intent i=new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/watch?feature=player_embedded&v=pLG5j6isgRs"));
					startActivity(i);
				}// end innermost function
			});// end onClick function
			
			Button one_idiot=(Button)V.findViewById(R.id.one_idiot);
			one_idiot.setOnClickListener(new View.OnClickListener(){
				
				public void onClick(View v)
				{
					score_add(600);
					Intent i=new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("http://www.youtube.com/watch?v=vU1l1TB7GzI"));
					startActivity(i);
				}// end innermost function
			});// end onClick function
			
			Button frugal_guide=(Button)V.findViewById(R.id.cheapskate);
			frugal_guide.setOnClickListener(new View.OnClickListener(){
				
				public void onClick(View v)
				{
					score_add(150);
				view_PDF("frugalguide.pdf");
				}// end innermost function
			});// end onClick function
		
	     
	     return V;
	 }// end onCreateView function
	 
	 // --------------------------- Standard Functions now --------------------------------
	 
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
				Toast no_app=Toast.makeText(getActivity().getBaseContext(), "Application to view PDF missing", Toast.LENGTH_SHORT);
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
			
			File file=new File(getActivity().getFilesDir(), file_name);
			
			if(!file.exists())
			{
				return score;
			}// end if statement
				
			   try
			   {
				FileInputStream fis = getActivity().openFileInput(file_name);
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
				outputStream=getActivity().openFileOutput(file_name,Context.MODE_PRIVATE);
				outputStream.write(score.getBytes());
				outputStream.close();
			}
			catch(Exception e)
			{
			e.printStackTrace();	
			}// end catch statement
			
		}// end function

	
}// end class
