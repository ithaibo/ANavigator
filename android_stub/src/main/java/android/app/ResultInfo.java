package android.app;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

public class ResultInfo implements Parcelable {
    public final String mResultWho;
    public final int mRequestCode;
    public final int mResultCode;
    public final Intent mData;

    public ResultInfo(String resultWho, int requestCode, int resultCode,
                      Intent data) {
        mResultWho = resultWho;
        mRequestCode = requestCode;
        mResultCode = resultCode;
        mData = data;
    }

    public String toString() {
        return "ResultInfo{who=" + mResultWho + ", request=" + mRequestCode
                + ", result=" + mResultCode + ", data=" + mData + "}";
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        throw new RuntimeException("stub");
    }

    public static final Parcelable.Creator<ResultInfo> CREATOR
            = new Parcelable.Creator<ResultInfo>() {
        public ResultInfo createFromParcel(Parcel in) {
            return new ResultInfo(in);
        }

        public ResultInfo[] newArray(int size) {
            return new ResultInfo[size];
        }
    };

    public ResultInfo(Parcel in) {
        throw new RuntimeException("stub");
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }
}

