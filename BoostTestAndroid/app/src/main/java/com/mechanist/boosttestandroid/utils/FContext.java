package com.mechanist.boosttestandroid.utils;

import android.content.Context;

public class FContext
{
    private static Context sContext;

    protected FContext()
    {
    }

    public static final void set(Context context)
    {
        if (sContext == null)
        {
            sContext = context.getApplicationContext();
        }
    }

    public static final Context get()
    {
        return sContext;
    }
}
