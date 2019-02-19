package in.skivvy.app.retrofit;

import in.skivvy.app.bean.AdvertisementDataClass;
import in.skivvy.app.bean.AllUsersRatingDataClass;
import in.skivvy.app.bean.CategoryDataClass;
import in.skivvy.app.bean.Login;
import in.skivvy.app.bean.MaidDataClass;
import in.skivvy.app.bean.MaidReviewDataClass;
import in.skivvy.app.bean.OTPDataClass;
import in.skivvy.app.bean.SelfRatingDataClass;
import in.skivvy.app.bean.Splash;
import in.skivvy.app.bean.Status;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @Headers("Content-Type: application/json")
    @POST("login.php")
    Call<Login> loginValidation(@Body String body);

    @GET("sendotp.php")  //http://smstxt.riteshinfotech.com/api/sendotp.php?authkey=166915ApVqGijYWj5977380d&mobile=9971227563&message=Your%20otp%20is%201232&sender=signup&otp=1232
    Call<OTPDataClass> OTPSend(@Query("authkey") String authkey, @Query("mobile") String mobile, @Query("message") String message, @Query("sender") String sender, @Query("otp") String otp);

    @GET("verifyRequestOTP.php")  //http://smstxt.riteshinfotech.com/api/verifyRequestOTP.php?authkey=166915ApVqGijYWj5977380d&mobile=9971227563&otp=1232
    Call<OTPDataClass> OTPVerify(@Query("authkey") String authkey, @Query("mobile") String mobile, @Query("otp") String otp);

    @Headers("Content-Type: application/json")
    @POST("registration.php")
    Call<Login> getRegistration(@Body String body);

    @Headers("Content-Type: application/json")
    @POST("userMobileDetails.php")
    Call<Splash> getDeviceDetails(@Body String body);

    @Headers("Content-Type: application/json")
    @POST("categoryDetails.php")
    Call<CategoryDataClass> getCategories();

    @Headers("Content-Type: application/json")
    @POST("maidList.php")
    Call<MaidDataClass> getMaidsList(@Body String body);

    @Headers("Content-Type: application/json")
    @POST("getFavourate.php")
    Call<Status> addFav(@Body String body);

    @Headers("Content-Type: application/json")
    @POST("userFavList.php")
    Call<MaidDataClass> getFavMaidsList(@Body String body);

    @Headers("Content-Type: application/json")
    @POST("maidRatingReview.php")
    Call<MaidReviewDataClass> maidRating(@Body String body);

    @Headers("Content-Type: application/json")
    @POST("getSelfRating.php")
    Call<SelfRatingDataClass> getMaidSelfRating(@Body String body);

    @Headers("Content-Type: application/json")
    @POST("maidReviewList.php")
    Call<AllUsersRatingDataClass> getAllUsersRating(@Body String body);

    @Headers("Content-Type: application/json")
    @POST("advertiseImage.php")
    Call<AdvertisementDataClass> getAdvertisementURLs();

}
