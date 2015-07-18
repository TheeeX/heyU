package com.example.pixie.heyu;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by pixie on 7/17/15.
 */

public class heyU  extends Application{

    public void onCreate()
    {
        Parse.initialize(this, "1mmikdGZLCqwu8Anz6cr5KvWk5z2FnKRAQ3jGXXZ", "cSiHMTytuAfSjqOLuque3ajTTo88YEzcyCUydnqF");
    }

}
