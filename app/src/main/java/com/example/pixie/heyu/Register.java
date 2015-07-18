package com.example.pixie.heyu;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pixie.heyu.custom.CustomActivity;
import com.example.pixie.heyu.utils.Const;
import com.example.pixie.heyu.utils.Utils;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class Register extends CustomActivity {


    /** The username EditText. */
    private EditText user;

    /** The password EditText. */
    private EditText pwd;

    /** The email EditText. */
    private EditText email;

    /* (non-Javadoc)
     * @see com.chatt.custom.CustomActivity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        setTouchNClick(R.id.btnReg);

        user = (EditText) findViewById(R.id.user);
        pwd = (EditText) findViewById(R.id.pwd);
        email = (EditText) findViewById(R.id.email);
    }

    /* (non-Javadoc)
     * @see com.chatt.custom.CustomActivity#onClick(android.view.View)
     */
    @Override
    public void onClick(View v)
    {
        super.onClick(v);

        String u = user.getText().toString();
        String p = pwd.getText().toString();
        String e = email.getText().toString();
        if (u.length() == 0 || p.length() == 0 || e.length() == 0)
        {
            Utils.showDialog(this, R.string.err_fields_empty);
            return;
        }
        final ProgressDialog dia = ProgressDialog.show(this, null,
                getString(R.string.alert_wait));

        final ParseUser pu = new ParseUser();
        pu.setEmail(e);
        pu.setPassword(p);
        pu.setUsername(u);
        pu.signUpInBackground(new SignUpCallback() {

            @Override
            public void done(ParseException e)
            {
                dia.dismiss();
                if (e == null)
                {
                    UserList.user = pu;
                    startActivity(new Intent(Register.this, UserList.class));
                    setResult(RESULT_OK);
                    finish();
                }
                else
                {
                    Utils.showDialog(
                            Register.this,
                            getString(R.string.err_singup) + " "
                                    + e.getMessage());
                    e.printStackTrace();
                }
            }
        });

    }
}
