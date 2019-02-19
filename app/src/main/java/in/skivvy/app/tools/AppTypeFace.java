package in.skivvy.app.tools;

import android.content.Context;
import android.graphics.Typeface;



public class AppTypeFace {

    private static AppTypeFace instance ;
    private Context mContext ;
    private Typeface mPoppinsMediumTypeface;
    private Typeface mPoppinsSemiBoldTypeface;
    private Typeface mPacificoTypeface;

    private AppTypeFace(Context mContext){

        this.mContext = mContext;

    }

    public static AppTypeFace getInstance(Context context){

        if (instance == null) {
            instance = new AppTypeFace(context);
        }
        return instance;
    }

    public Typeface getPoppinsMediumTypeface() {
        if (mPoppinsMediumTypeface == null)
            mPoppinsMediumTypeface = Typeface.createFromAsset(
                    mContext.getAssets(), "poppinsmedium.ttf");
        return mPoppinsMediumTypeface;
    }

    public Typeface getPoppinsSemiBoldTypeface() {
        if (mPoppinsSemiBoldTypeface == null)
            mPoppinsSemiBoldTypeface = Typeface.createFromAsset(
                    mContext.getAssets(), "poppinssemibold.ttf");
        return mPoppinsSemiBoldTypeface;
    }

    public Typeface getPacificoTypeface() {
        if (mPacificoTypeface == null)
            mPacificoTypeface = Typeface.createFromAsset(
                    mContext.getAssets(), "pacifico.ttf");
        return mPacificoTypeface;
    }
}