package android.app;

public abstract class FragmentTransaction {
    public abstract FragmentTransaction add(Fragment fragment, String tag);
    public abstract FragmentTransaction add(int containerViewId, Fragment fragment);
    public abstract FragmentTransaction add(int containerViewId, Fragment fragment, String tag);

    public abstract FragmentTransaction replace(int containerViewId, Fragment fragment);
    public abstract FragmentTransaction replace(int containerViewId, Fragment fragment, String tag);
    public abstract FragmentTransaction remove(Fragment fragment);
    public abstract FragmentTransaction hide(Fragment fragment);
    public abstract FragmentTransaction show(Fragment fragment);
    public abstract FragmentTransaction detach(Fragment fragment);
    public abstract FragmentTransaction attach(Fragment fragment);
    public abstract FragmentTransaction setPrimaryNavigationFragment(Fragment fragment);
    public abstract int commit();
    public abstract boolean isEmpty();
}
