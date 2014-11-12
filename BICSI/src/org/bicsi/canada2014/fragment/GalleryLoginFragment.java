package org.bicsi.canada2014.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.content.Intent;

import org.bicsi.winter2015.R;
import org.bicsi.canada2014.activities.MainActivity;
import org.bicsi.canada2014.activities.Welcome;

import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import org.bicsi.canada2014.common.MizeUtil.NavigateToTabFragmentListener;

public class GalleryLoginFragment extends Fragment {
	
private NavigateToTabFragmentListener mCallback;
	
	private Fragment newFragment = new GallerySignupFragment();
	
	// Declare Variables
		Button loginbutton;
		Button signup;
		String usernametxt;
		String passwordtxt;
		String emailtxt;
		EditText password;
		EditText username;
		EditText email;
		
		public void onAttach(Activity activity) {
			super.onAttach(activity);

			try {
				mCallback = (NavigateToTabFragmentListener) activity;
			} catch (ClassCastException e) {
				throw new ClassCastException(activity.toString()
						+ " must implement NavigateToListener");
			}
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			super.onCreateView(inflater, container, savedInstanceState);
			View v = inflater.inflate(R.layout.loginsignup, container, false);
			
			
			username = (EditText) v.findViewById(R.id.username);
			password = (EditText) v.findViewById(R.id.password);
			email = (EditText) v.findViewById(R.id.email);

			// Locate Buttons in loginsignup.xml
			loginbutton = (Button) v.findViewById(R.id.login);
			signup = (Button) v.findViewById(R.id.signup);
			
			// Login Button Click Listener
			loginbutton.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					// Retrieve the text entered from the EditText
					usernametxt = username.getText().toString();
					passwordtxt = password.getText().toString();

					// Send data to Parse.com for verification
					ParseUser.logInInBackground(usernametxt, passwordtxt,
							new LogInCallback() {
								public void done(ParseUser user, ParseException e) {
									if (user != null) {
										// If user exist and authenticated, send user to Welcome.class
										/*Intent intent = new Intent(
												getActivity(),
												Welcome.class);
										startActivity(intent);*/
										Toast.makeText(getActivity(),
												"Successfully Logged in",
												Toast.LENGTH_LONG).show();
										//finish();
									} else {
										Toast.makeText(
												getActivity(),
												"No such user exist, please signup",
												Toast.LENGTH_LONG).show();
									}
								}
							});
				}
			});
			
			signup.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					
				     //when a user selects an event from your list

				mCallback.navigateToTabFragment(newFragment, null);

				
			}
				
			});
			
			return v;
		}
		
}