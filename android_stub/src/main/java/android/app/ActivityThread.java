package android.app;

public class ActivityThread {
    private static volatile ActivityThread sCurrentActivityThread;
    final Object mH = new Object();

    public static ActivityThread currentActivityThread() {
        return sCurrentActivityThread;
    }
}
