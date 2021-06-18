package com.unicomg.baghdadmunicipality.data.ApisClient;
import com.unicomg.baghdadmunicipality.data.models.Login.AccessTokenModel;
import com.unicomg.baghdadmunicipality.data.models.Login.LoginModel;
import com.unicomg.baghdadmunicipality.data.models.category.CategoriesResponse;

import com.unicomg.baghdadmunicipality.data.models.forgetpass.ForgetPassResponse;
import com.unicomg.baghdadmunicipality.data.models.forgetpass.ForgetPasswordModel;
import com.unicomg.baghdadmunicipality.data.models.scheduled_works.ScheduledWorksResponse;
import com.unicomg.baghdadmunicipality.data.models.serverViolations.ServerViolationsResponse;
import com.unicomg.baghdadmunicipality.data.models.shops.ShopModel;
import com.unicomg.baghdadmunicipality.data.models.shops.AddShopResponse;
import com.unicomg.baghdadmunicipality.data.models.shops.ShopsResponse;
import com.unicomg.baghdadmunicipality.data.models.billboard.BillboardModel;
import com.unicomg.baghdadmunicipality.data.models.billboard.BillboardResponse;
import com.unicomg.baghdadmunicipality.data.models.shops_activities.ShopsActivitiesResponse;
import com.unicomg.baghdadmunicipality.data.models.violation.VilationImageResponse;
import com.unicomg.baghdadmunicipality.data.models.violation.ViolatImage2;
import com.unicomg.baghdadmunicipality.data.models.violation.ViolationImage;
import com.unicomg.baghdadmunicipality.data.models.violation.ViolationModel;
import com.unicomg.baghdadmunicipality.data.models.violation.ViolationResponse;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Completable;
import rx.Observable;

public interface ApiInterface {
    @FormUrlEncoded
    @POST(EndPoints.LOGIN)
    Observable<AccessTokenModel> getLogin(
            @Field("email") String email,
            @Field("password") String pw
    );


    @GET(EndPoints.GET_LOGin_Data)
    Observable<LoginModel> getLoginData(
            @Header("Authorization") String access_token
    );
    // By Elamary
    //////////////////////////////////

    @GET(EndPoints.All_SHOPS_DATA)
    Observable<ShopsResponse> getShopsData();
    @GET(EndPoints.GET_Shops_ctivities)
    Observable<ShopsActivitiesResponse> getShopsActivities();
    @GET(EndPoints.ALL_CATEGORIES)
    Observable<CategoriesResponse> getCategories();
    @GET(EndPoints.ALL_VIOLATIONS)
    Observable<ServerViolationsResponse> getViolations();


    @POST(EndPoints.ADD_BILLBORAD)
    Observable<BillboardResponse> saveBillboard(@Header("Authorization") String token  , @Body BillboardModel billboardModel);


    @POST(EndPoints.ADD_VILATION_MONITORING)
    Observable<ViolationResponse> saveViolation(@Header("authorization") String token  , @Body ViolationModel violationModel);
    @POST(EndPoints.ADD_IMAGE)
    Observable<VilationImageResponse> saveViolationImage(@Header("authorization") String token  , @Body ViolatImage2 violationModel);
    @GET(EndPoints.All_SHOPS_DATA)
    Observable<ShopsResponse> getAllShopsData(@Header("authorization") String token  );

    /////////////////////////////////

    @POST(EndPoints.Post_Add_Shop_Data)
    Observable<AddShopResponse> addShopData2(@Header("authorization") String token  , @Body ShopModel shopModel);


    @POST(EndPoints.forget_pass)
    Observable<ForgetPassResponse> getNewPassword(@Body ForgetPasswordModel  forgetPasswordModel);

    @GET(EndPoints.allScheduling)
    Observable<ScheduledWorksResponse> getScheduledWorksResponseObservable(@Header("authorization") String token  );

}
