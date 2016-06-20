package com.example.ekok.canidrink;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    public static final int AGE_REQUEST_CODE = 55;
    //public int age = -1;

    public User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        user = new User();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.miRequest:
                Toast.makeText(this, "Clicked on action bar!", Toast.LENGTH_SHORT).show();

                launchSecondActivity();

                //dialPhone();

                //launchSite();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void launchSite() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.codepath.com"));
        if (browserIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(browserIntent);
        }
    }

    private void dialPhone() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:3868480287"));
        if (callIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(callIntent);
        }
    }

    private void launchSecondActivity() {
        Intent intent = new Intent(this, SecondActivity.class);

        // Pass in previous age
        intent.putExtra("user", user);

        startActivityForResult(intent, AGE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == AGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                user = (User)data.getSerializableExtra("user");

                String message;
                if (user.age >= 21) {
                    message = "Drink Up!";
                }
                else {
                    message = "None for you!";
                }
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            } else {
                // Handle failure case
            }
        }
    }
}
