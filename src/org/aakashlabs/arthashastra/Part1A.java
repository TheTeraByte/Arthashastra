package org.aakashlabs.arthashastra;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.net.Uri;
import android.os.Bundle;
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
 * This is a fragment, see - http://developer.android.com/guide/components/fragments.html
 * The action of the buttons on being clicked are listed here.
 * It's layout is 'part1alayout' (the same nomenclature is used in all other fragments).
 * 
 * @author Tushar Bhargava
 *
 */

public class Part1A extends Fragment
{

	// Global variables
	public static final String file_name="score_file";
	
	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	    
	     View V = inflater.inflate(R.layout.part1alayout, container, false);   
	     
	     // Set Button actions 
	     
	     Button infl_overview=(Button)V.findViewById(R.id.intro_inflation);
	     infl_overview.setOnClickListener(new View.OnClickListener(){
				
				public void onClick(View v)
				{
					score_add(150);
					// the URL of the Khan Academy video
					Intent i=new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("http://www.youtube.com/watch?feature=player_embedded&v=mO6y4KON1e8"));
					startActivity(i);
				}// end innermost function
			});// end onClick function
	     
	     Button what_inflation=(Button)V.findViewById(R.id.what_is_inflation);
	     what_inflation.setOnClickListener(new View.OnClickListener(){
				
				public void onClick(View v)
				{
					score_add(150);
					// the URL of the Khan Academy video
					Intent i=new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/watch?feature=player_embedded&v=yahEP620480"));
					startActivity(i);
				}// end innermost function
			});// end onClick function
	     
	     Button infl_data=(Button)V.findViewById(R.id.inflation_data);
	     infl_data.setOnClickListener(new View.OnClickListener(){
				
				public void onClick(View v)
				{
					score_add(150);
					// the URL of the Khan Academy video
					Intent i=new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("http://www.youtube.com/watch?feature=player_embedded&v=DK1lCc9b7bg"));
					startActivity(i);
				}// end innermost function
			});// end onClick function
	     
	     Button cpi_index=(Button)V.findViewById(R.id.cpi_index);
	     cpi_index.setOnClickListener(new View.OnClickListener(){
				
				public void onClick(View v)
				{
					score_add(150);
					// the URL of the Khan Academy video
					Intent i=new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/watch?feature=player_embedded&v=pRIELoITIHI"));
					startActivity(i);
				}// end innermost function
			});// end onClick function
	     
	     Button buy_comp_stock=(Button)V.findViewById(R.id.intro_stocks);
	     buy_comp_stock.setOnClickListener(new View.OnClickListener(){
				
				public void onClick(View v)
				{
					score_add(150);
					// the URL of the Khan Academy video
					Intent i=new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/watch?feature=player_embedded&v=WvITkPn83gc"));
					startActivity(i);
				}// end innermost function
			});// end onClick function
	     
	     Button bonds_vs_stocks=(Button)V.findViewById(R.id.bonds_vs_stocks);
	     bonds_vs_stocks.setOnClickListener(new View.OnClickListener(){
				
				public void onClick(View v)
				{
					score_add(150);
					// the URL of the Khan Academy video
					Intent i=new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/watch?feature=player_embedded&v=rs1md3e4aYU"));
					startActivity(i);
				}// end innermost function
			});// end onClick function
	     
	     return V;
	 }// end function
	 
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
		 
	
} // end class
