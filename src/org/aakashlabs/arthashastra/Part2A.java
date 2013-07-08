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

public class Part2A extends Fragment 
{

	// Global variables
    public static final String file_name="score_file";
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    
     View V = inflater.inflate(R.layout.part2alayout, container, false);  
     
     // Setting button actions now
     Button open_ended_mut_f=(Button)V.findViewById(R.id.intro_mutual_fund);
     open_ended_mut_f.setOnClickListener(new View.OnClickListener(){
			
			public void onClick(View v)
			{
				score_add(150);
				// the URL of the Khan Academy video
				Intent i=new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/watch?feature=player_embedded&v=DzYwB3uSU-c"));
				startActivity(i);
			}// end innermost function
		});// end onClick function
     
     Button open_ended_mut_f_red=(Button)V.findViewById(R.id.oem_redemption);
     open_ended_mut_f_red.setOnClickListener(new View.OnClickListener(){
			
			public void onClick(View v)
			{
				score_add(150);
				// the URL of the Khan Academy video
				Intent i=new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/watch?feature=player_embedded&v=nW1rxuQft1o"));
				startActivity(i);
			}// end innermost function
		});// end onClick function
     
     Button closed_mut_f=(Button)V.findViewById(R.id.closed_mutual_fund);
     closed_mut_f.setOnClickListener(new View.OnClickListener(){
			
			public void onClick(View v)
			{
				score_add(150);
				// the URL of the Khan Academy video
				Intent i=new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/watch?feature=player_embedded&v=VpVu25KhozQ"));
				startActivity(i);
			}// end innermost function
		});// end onClick function
     
     Button etf=(Button)V.findViewById(R.id.etfs);
     etf.setOnClickListener(new View.OnClickListener(){
			
			public void onClick(View v)
			{
				score_add(150);
				// the URL of the Khan Academy video
				Intent i=new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/watch?feature=player_embedded&v=SFdsY9Rdh6w"));
				startActivity(i);
			}// end innermost function
		});// end onClick function
     
     Button way_forward=(Button)V.findViewById(R.id.way_forward);
     way_forward.setOnClickListener(new View.OnClickListener(){
			
			public void onClick(View v)
			{
				score_add(200);
			    view_PDF("TheWayForward.pdf");
			}// end innermost function
		});// end onClick function
     
     Button challenge2=(Button)V.findViewById(R.id.challenge_2);
     challenge2.setOnClickListener(new View.OnClickListener(){
			
			public void onClick(View v)
			{
            startActivity(new Intent("org.aakashlabs.arthashastra.Challenge2_Activity"));
			}// end innermost function
		});// end onClick function
     
     return V;
    }// end onCreateView function
    
    // ---------------------------- Standard Methods now ----------------------------------
	 


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

    
} // end class
