package io.github.ngbrown11.travoltaproject_mobile;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1888;
    private ImageView image;
    private static final String TAG = "MessageActivity";
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        this.image = (ImageView) this.findViewById(R.id.imageView);
        Button photo = (Button) this.findViewById(R.id.shootImage);
        photo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

        // Grab the intent
        Intent intent = getIntent();

        // Create the spinner for the severity section of the activity
        spinner = (Spinner) findViewById(R.id.spinner);
        // Create an array of the values in the spinner
        final String[] levels = new String[] {"Not Important", "Medium", "Urgent"};
        // Create an array adapter
        ArrayAdapter<CharSequence> adapter = ArrayAdapter
                .createFromResource(this, R.array.levels,
                        android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        /*spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.v("", levels[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            // Add the code for the addProvider activity
            Intent intent = new Intent(MessageActivity.this, AddProviderActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.logout) {
            // Add the code for logging out here
            Intent intent = new Intent(MessageActivity.this, LoginActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public boolean validateProvider(EditText text) {

        String provider = text.toString();
        ArrayList<String> names = new ArrayList<String>();
        names = AddProviderActivity.getNicknames();

        for(int i = 0; i < AddProviderActivity.getNicknames().size(); i++) {
            if(provider.equalsIgnoreCase(names.get(i))) {
                return true;
            }
        }
        return false;
    }

    public void send() {

        // Add code to send message to provider
        //Intent toProvider = new Intent(this, .......);
        EditText recipient = (EditText) findViewById(R.id.textViewRecipient);
        Boolean checkName = validateProvider(recipient);

        if(!checkName) {
            AlertDialog alertDialog = new AlertDialog.Builder(MessageActivity.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("That is not a valid provider");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
            return;
        }
        Spinner severity = (Spinner) findViewById(R.id.spinner);
        EditText subject = (EditText) findViewById(R.id.editText);
        EditText description = (EditText) findViewById(R.id.description);
        // Send the image as well
    }

    /*
    * Code for slecting an image from the gallery and
    * adding it to the message
    * */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            image.setImageBitmap(photo);
        }
    }
}
