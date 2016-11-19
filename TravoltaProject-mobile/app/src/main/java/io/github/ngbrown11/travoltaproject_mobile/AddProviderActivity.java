package io.github.ngbrown11.travoltaproject_mobile;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class AddProviderActivity extends AppCompatActivity {

    private LinearLayout mLayout;
    private RelativeLayout mLayout2;
    private EditText mEditText;
    private EditText mProviderID;
    private Button mButton;
    private static ArrayList<String> nicknames = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_provider);

        mLayout = (LinearLayout) findViewById(R.id.linearLayoutSettings);
        mLayout2 = (RelativeLayout) findViewById(R.id.relLayout);
        mEditText = (EditText) findViewById(R.id.editText);
        mProviderID = (EditText) findViewById(R.id.providerID) ;
        mButton = (Button) findViewById(R.id.addProvider);
        mButton.setOnClickListener(onClick());
        TextView textView = new TextView(this);
        textView.setText("Add");

    }

    private View.OnClickListener onClick() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final RelativeLayout.LayoutParams lparams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                mLayout.addView(createNewTextView(mEditText.getText().toString(), mProviderID.getText().toString()), lparams);
                nicknames.add(createNewTextView(mEditText.getText().toString(), mProviderID.getText().toString()).toString());
            }
        };
    }

    private TextView createNewTextView(String text, String text2) {
        final TextView textView = new TextView(this);
        final TextView textView2 = new TextView(this);
        //textView.setLayoutParams(lparams);
        textView.setText(text);
        textView2.setText(text + "    " + text2);
        return textView2;
    }

    public static ArrayList<String> getNicknames() {
        return nicknames;
    }
}
