package android.os;

public interface Parcelable {
    interface Creator<T> {
        T createFromParcel(Parcel in);
        T[] newArray(int size);
    }
}
