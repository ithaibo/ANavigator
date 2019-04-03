package android.app;

import android.content.Context;
import android.content.Intent;

public class Activity extends Context {
    public FragmentManager getFragmentManager() {
        return null;
    }
    public void startActivityForResult(Intent intent, int reqCode) {}
    public void startActivity(Intent intent) {}
}
