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
 * Refer to Part1A fragment for documentation.
 * 
 * @author Tushar Bhargava
 *
 */

public class Part1N extends Fragment {

	public static final String file_name="score_file";
	
	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	    
	     View V = inflater.inflate(R.layout.part1nlayout, container, false);   
		 
	        // Button controls
	        Button cash_acc=(Button)V.findViewById(R.id.cash_accounting);
	        cash_acc.setOnClickListener(new View.OnClickListener(){
				
				public void onClick(View v)
				{
					score_add(150);
					// the URL of the Khan Academy video
					Intent i=new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("http://youtu.be/FfdpupKByiU"));
					startActivity(i);
				}// end innermost function
			});// end onClick function
			
	        Button accrual_cash=(Button)V.findViewById(R.id.accrual_cash_accounting);
	        accrual_cash.setOnClickListener(new View.OnClickListener(){
				
				public void onClick(View v)
				{
					score_add(150);
					// the URL of the Khan Academy video
					Intent i=new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("http://youtu.be/NNhyZFHAzaA"));
					startActivity(i);
				}// end innermost function
			});// end onClick function
			
	        Button comparing_2_acc=(Button)V.findViewById(R.id.comparing_2_types_acc);
	        comparing_2_acc.setOnClickListener(new View.OnClickListener(){
				
				public void onClick(View v)
				{
					score_add(150);
					// the URL of the Khan Academy video
					Intent i=new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("http://youtu.be/LU_6amWC6H8"));
					startActivity(i);
				}// end innermost function
			});// end onClick function

	        Button balance_sheet_income=(Button)V.findViewById(R.id.bal_sheet_income_statement);
	        balance_sheet_income.setOnClickListener(new View.OnClickListener(){
				
				public void onClick(View v)
				{
					score_add(150);
					// the URL of the Khan Academy video
					Intent i=new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("http://youtu.be/hZvjH3Az87A"));
					startActivity(i);
				}// end innermost function
			});// end onClick function
	        
	        Button basic_cash_flow=(Button)V.findViewById(R.id.basic_cash_flow_st);
	        basic_cash_flow.setOnClickListener(new View.OnClickListener(){
				
				public void onClick(View v)
				{
					score_add(150);
					// the URL of the Khan Academy video
					Intent i=new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("http://youtu.be/Mioqyv_IW3E"));
					startActivity(i);
				}// end innermost function
			});// end onClick function
	        
	        Button intro_acc_pay=(Button)V.findViewById(R.id.account_payable);
	        intro_acc_pay.setOnClickListener(new View.OnClickListener(){
				
				public void onClick(View v)
				{
					score_add(150);
					// the URL of the Khan Academy video
					Intent i=new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("http://youtu.be/57gz50uTPqM"));
					startActivity(i);
				}// end innermost function
			});// end onClick function
	        
	        
	        // Inflate the layout for this fragment
	        return V;
	        
	    }// end onCreateView function 
	 
	 // Standard Methods now
	 


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
