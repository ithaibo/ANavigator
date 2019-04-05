package android.content;

import android.android.net.Uri;

public class Intent {
    public Intent() {
    }

    /**
     * Copy constructor.
     */
    public Intent(Intent o) {
    }

    private Intent(Intent o, int copyMode) {
    }

    public Intent(String action) {
    }

    public Intent(String action, Uri uri) {
    }

    public Intent(Context packageContext, Class<?> cls) {
    }

    public Intent(String action, Uri uri, Context packageContext, Class<?> cls) {
    }
}
