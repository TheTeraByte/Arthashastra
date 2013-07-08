package org.aakashlabs.arthashastra;

import android.app.Activity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.view.View;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.LinearLayout.LayoutParams;


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
 * This Activity presents questions to test the understanding of users.
 * It layout consists of TextView that serves as a place holder for the question.
 * The layout also consists of a group of radio buttons which provide the options.
 * @author Tushar Bhargava
 *
 */


public class Challenge2_Activity extends Activity
{

	// Global variables
	public static final String file_name="score_file";
	int question_number=1;
	TextView question;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_challenge2);
		
		// The TextView that serves as the question 
		question=new TextView(getBaseContext());
		
		RelativeLayout layout=(RelativeLayout)findViewById(R.id.challenge2_layout);
		layout.addView(question);
		
		// Call the q_and_a function to set the first question
		q_and_a_order(1);
		
		// The radio group is important as it provides us the answer
	    final RadioGroup group=(RadioGroup)findViewById(R.id.option_frame_challenge2);
		
		// Sets the submit button to not only check whether the answer is correct and add 
		// the score but also to call the next question
		
		Button submit=(Button)findViewById(R.id.sub_button_challenge2);
		submit.setOnClickListener(new View.OnClickListener(){
			
			public void onClick(View v)
			{
				// To understand this button's action refer to the function descriptions of
				// each function 
				
				// We have to ensure that the correct answer is chosen
			    if(answers(question_number)==group.getCheckedRadioButtonId())
			    {
			    	score_add(50);
			    	// Immediate feedback for the user
			    	Toast.makeText(getBaseContext(), "That is correct !", Toast.LENGTH_SHORT).show();
			    	Log.d(STORAGE_SERVICE, "That is correct !");

			    	// Calls the next question
			    	
			    	question_number++;
					q_and_a_order(question_number);
			    }// end if statement 	
			    
			    else
			    {
			    	
			    // A Toast is a floating message that I'm using to provide unobtrusive
			    // feedback to the user	
			    Toast.makeText(getBaseContext(), "That is incorrect, try again", Toast.LENGTH_SHORT).show();
			    score_add(-10);
			    }// end else statement
			    
			}// end innermost function
		});// end inner function

		
	}// end onCreate function
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
			
     // Inflate the menu; this adds items to the action bar if it is present.
     getMenuInflater().inflate(R.menu.home_, menu);
	   
	 // Updating score and making sure it's shown in the action bar
     MenuItem score_keeper=menu.add(Integer.toString(get_score()));
     score_keeper.setShowAsAction(2);  
    
	 
	return true;
	}
    

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
	
	// ------------------------------Standard Methods now ----------------------------------
	
	// You provide the questions(string type) as argument and the function sets the TextView
	// to display the question
	public void quest_generator(String quest)
    {
    
    question.setTextColor(Color.parseColor("#FFFFFF"));
    question.setTextSize(40);
    question.setText(quest);
    			
    }// end quest_generator function
    
	// Pass the text of the option and it's number to set the radio button text in the layout
    public void options_generator(String opt_text, int opt_no)
    {
    	switch(opt_no)
    	{
    	case 1:
    		RadioButton opt1=(RadioButton)findViewById(R.id.opt1_challenge2);
    		opt1.setText(opt_text);
    		break;
    		
    	case 2:
    		RadioButton opt2=(RadioButton)findViewById(R.id.opt2_challenge2);
    		opt2.setText(opt_text);
    		break;
    		
    	case 3:
    		RadioButton opt3=(RadioButton)findViewById(R.id.opt3_challenge2);
    		opt3.setText(opt_text);
    		break;
    		
    	case 4:
    		RadioButton opt4=(RadioButton)findViewById(R.id.opt4_challenge2);
    		opt4.setText(opt_text);
    		break;
    	}// end switch statement
    }// end options_generator function 
    
    
    // Brings together quest_generator and options_generator to centralize question 
    // management. This function also allows switching to the next question by simple
    // incrementing as the the questions are numbered serially
    
    public void q_and_a_order(int q_no)
    {
    	switch(q_no)
    	{
    	
    	case 1:
    		quest_generator("Q: Why do open-ended mutual fund managers need to keep some cash in hand ?");
    		options_generator("In case someone wants their money back",1);
            options_generator("So they can take their fee",2);
            options_generator("To convince more people to join",3);
            options_generator("None of the above",4);
    		break;
    		
    	case 2:
    		quest_generator("Q: What is the full form of 'NAV' ?");
    		options_generator("Net average value",1);
    		options_generator("Net allocation value",2);
    		options_generator("Net asset value",3);
    		options_generator("None of the above",4);
    		break;
    		
    	case 3:
    		quest_generator("Q: Disadvantage of closed mutual fund ?");
    		options_generator("Less liquidity",1);
    		options_generator("Less flexibility",2);
    		options_generator("Both of the above",3);
    		options_generator("None of the above",4);
    		break;
    		
    	case 4:
    		quest_generator("Q: Advantage of closed mutual fund ?");
    		options_generator("More flexibility",1);
    		options_generator("More likely to make money",2);
    		options_generator("No liquidity",3);
    		options_generator("All of the above",4);
    		break;
    		
    	case 5:
    		quest_generator("Q: What can be roughly thought of as a combination of open-ended and closed mutual funds ? ");
    	    options_generator("VTF",1);
    	    options_generator("ITF",2);
    	    options_generator("LTF",3);
    	    options_generator("ETF",4);
    		break;
    	    
    	case 6:
    		quest_generator("Q: What does CPI stand for ?");
    		options_generator("Consumer Price Index",1);
    		options_generator("Careful Performance Insurance",2);
    		options_generator("Car Price Inflation",3);
    		options_generator("None of the above",4);
    		break;
    		
    	case 7:
    		quest_generator("Q: What are the 2 types of inflation ?");
    		options_generator("Price and stock inflation",1);
    		options_generator("Stock and monetary inflation",2);
    		options_generator("Stock and technology inflation",3);
    		options_generator("Price and monetary inflation",4);
    		break;
    		
    	case 8:
    		quest_generator("Q: When you buy a share you ");
    		options_generator("Become part lender to the company",1);
    		options_generator("Become part owner of the company",2);
    		options_generator("Both of the above",3);
    		options_generator("None of the above",4);
    		break;
    		
    	case 9:
    		quest_generator("Q: When you buy a bond you");
    		options_generator("Become part owner of the company",1);
    		options_generator("Have to pay it back",2);
    		options_generator("Become part lender to the company",3);
    		options_generator("None of the above",4);
    		break;
    		
    		default:
    			Toast chal_over=Toast.makeText(getBaseContext(), "This challenge is over. Your score has been updated. Press Back button to exit.", Toast.LENGTH_LONG);
    			chal_over.show();
    	
    	}// end switch statement
    }// end function q_and_a_order
    
    // This function returns which option is the correct answer, it takes the question number
    // as an argument
    public int answers(int index)
    {
    	switch(index)
    	{
    	
    	// No need for break statement as 'return' will hand the control back to the calling
    	// function
    	
    	case 1:
    		return R.id.opt1_challenge2;
    		
    	case 2:
    		return R.id.opt3_challenge2;
    		
    	case 3:
    		return R.id.opt2_challenge2;
    		
    	case 4:
    		return R.id.opt3_challenge2;
    		
    	case 5:
    		return R.id.opt4_challenge2;
    		
    	case 6:
    		return R.id.opt1_challenge2;
    		
    	case 7:
    		return R.id.opt4_challenge2;
    		
    	case 8:
    		return R.id.opt2_challenge2;
    		
    	case 9:
    		return R.id.opt3_challenge2;
    		
    		default:
    			return 0;
    	
    	}// end switch statement
    	
    }// end answers function
    	
	public void view_PDF(String file_name)
	{
		// I'm assuming all my files will be stored on the SD card directly ; using bluetooth
		// folder for tablet testing

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
			Toast no_file=Toast.makeText(getBaseContext(), "Resource file missing !", Toast.LENGTH_SHORT);
			no_file.show();
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
