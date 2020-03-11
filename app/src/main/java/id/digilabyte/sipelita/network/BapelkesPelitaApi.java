package id.digilabyte.sipelita.network;

import java.util.ArrayList;

import id.digilabyte.sipelita.model.Contact;
import id.digilabyte.sipelita.model.request.BangunanRequest;
import id.digilabyte.sipelita.model.request.ChangePasswordRequest;
import id.digilabyte.sipelita.model.request.FormRequest;
import id.digilabyte.sipelita.model.request.LoginRequest;
import id.digilabyte.sipelita.model.request.RegisterRequest;
import id.digilabyte.sipelita.model.request.RuangRequest;
import id.digilabyte.sipelita.model.request.UpdateProfileRequest;
import id.digilabyte.sipelita.model.response.BangunanResponse;
import id.digilabyte.sipelita.model.response.BannerResponse;
import id.digilabyte.sipelita.model.response.ChangePasswordResponse;
import id.digilabyte.sipelita.model.response.CitiesResponse;
import id.digilabyte.sipelita.model.response.DetailPelatihan;
import id.digilabyte.sipelita.model.response.DistrictsResponse;
import id.digilabyte.sipelita.model.response.EducationResponse;
import id.digilabyte.sipelita.model.response.FormResponse;
import id.digilabyte.sipelita.model.response.GroupResponse;
import id.digilabyte.sipelita.model.response.HistoryRoomResponse;
import id.digilabyte.sipelita.model.response.HistoryTrainingResponse;
import id.digilabyte.sipelita.model.response.KamarResponse;
import id.digilabyte.sipelita.model.response.LoginResponse;
import id.digilabyte.sipelita.model.response.LogoutResponse;
import id.digilabyte.sipelita.model.response.PelatihanResponse;
import id.digilabyte.sipelita.model.response.PositionResponse;
import id.digilabyte.sipelita.model.response.ProvinciesResponse;
import id.digilabyte.sipelita.model.response.RegisterResponse;
import id.digilabyte.sipelita.model.response.RegistrasiPelatihanResponse;
import id.digilabyte.sipelita.model.response.RuangResponse;
import id.digilabyte.sipelita.model.response.TransaksiResponse;
import id.digilabyte.sipelita.model.response.UpdateProfileResponse;
import id.digilabyte.sipelita.model.response.UploadFileResponse;
import id.digilabyte.sipelita.model.response.UserResponse;
import id.digilabyte.sipelita.model.response.VerifyResponse;
import id.digilabyte.sipelita.model.response.VillagesResponse;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BapelkesPelitaApi {

    // login
    @Headers({"Accept:application/json","Content-Type:application/json"})
    @POST("api/v1/auth/login")
    Call<LoginResponse> loginResponseCall(@Body LoginRequest login);

    // check token
    @Headers({"Accept:application/json","Content-Type:application/json"})
    @GET("api/v1/auth/verify")
    Call <VerifyResponse> validateTokenResponse(@Header("Authorization") String authorizen);

    // kontak
    @Headers({"Accept:application/json","Content-Type:application/json"})
    @GET("contact.json")
    Call<Contact> contactResponseCall();

    // register
    @Headers({"Accept:application/json","Content-Type:application/json"})
    @POST("api/v1/auth")
    Call <RegisterResponse> registerResponseCall(@Body RegisterRequest register);

    // banner
    @Headers({"Accept:application/json","Content-Type:application/json"})
    @GET("api/v2/banner/{type}")
    Call <BannerResponse> bannerResponseCall(@Path("type") String type);

    // pelatihan listing
    @Headers({"Accept:application/json","Content-Type:application/json"})
    @GET("api/v1/event")
    Call<PelatihanResponse> pelatihanResponse();

    // registrasi pelatihan
    @Headers({"Accept:application/json","Content-Type:application/json"})
    @POST("api/v1/event/{id}")
    Call<RegistrasiPelatihanResponse> registrasiPelatihanResponseCall(@Header("Authorization") String authorizen, @Path("id") String id);

    // profile
    @Headers({"Accept:application/json","Content-Type:application/json"})
    @GET("api/v1/auth/profile")
    Call <UserResponse> userResponseCall(@Header("Authorization") String authorizen);

    //listing building
    @Headers({"Accept:application/json","Content-Type:application/json"})
    @GET("api/v2/building/")
    Call <BangunanResponse> bangunanResponseCall(@Query("type") String type);

    //listing kamar
    @Headers({"Accept:application/json","Content-Type:application/json"})
    @POST("api/v2/room/available")
    Call<KamarResponse> kamarResponseCall(@Header("Authorization") String authorizen, @Body BangunanRequest bangunanRequest);

    // change password
    @Headers({"Accept:application/json","Content-Type:application/json"})
    @PATCH("api/v1/auth/change_password")
    Call<ChangePasswordResponse> changePasswordResponseCall(@Header("Authorization") String authorizen, @Body ChangePasswordRequest changePasswordRequest);

    // provinsi
    @Headers({"Accept:application/json","Content-Type:application/json"})
    @GET("api/v2/region/provinces")
    Call <ProvinciesResponse> provinciesResponseCall();

    // city
    @Headers({"Accept:application/json","Content-Type:application/json"})
    @GET("api/v2/region/cities")
    Call <CitiesResponse> citiesResponseCall(@Query("province") String province);

    // district
    @Headers({"Accept:application/json","Content-Type:application/json"})
    @GET("api/v2/region/districts")
    Call <DistrictsResponse> districtsResponseCall(@Query("city") String city);

    // village
    @Headers({"Accept:application/json","Content-Type:application/json"})
    @GET("api/v2/region/villages")
    Call <VillagesResponse> villagesResponseCall(@Query("district") String district);

    // group
    @Headers({"Accept:application/json","Content-Type:application/json"})
    @GET("api/v2/group")
    Call <GroupResponse> groupsResponseCall();

    // position
    @Headers({"Accept:application/json","Content-Type:application/json"})
    @GET("api/v2/position")
    Call <PositionResponse> positionsResponseCall();

    // education
    @Headers({"Accept:application/json","Content-Type:application/json"})
    @GET("api/v2/education")
    Call <EducationResponse> educationResponseCall();

    // registrasi peserta pelatihan
    @Headers({"Accept:application/json","Content-Type:application/json"})
    @POST("api/v1/event/{id}")
    Call<FormResponse> formRegisterPelatihanResponseCall(@Header("Authorization") String authorizen, @Body FormRequest fr, @Path("id") String id);

    // logout
    @Headers({"Accept:application/json","Content-Type:application/json"})
    @GET("api/v1/auth/logout")
    Call<LogoutResponse> logoutResponseCall(@Header("Authorization") String authorizen);

    // update profile
    @Headers({"Accept:application/json","Content-Type:application/json"})
    @PATCH("api/v1/auth")
    Call<UpdateProfileResponse> updateProfileResponseCall(@Header("Authorization") String authorizen, @Body UpdateProfileRequest updateProfileRequest);

    // history event
    @Headers({"Accept:application/json","Content-Type:application/json"})
    @GET("api/v2/history/event")
    Call<HistoryTrainingResponse> historyTrainingResponseCall(@Header("Authorization") String authorizen, @Query("status") String status);

    // history event
    @Headers({"Accept:application/json","Content-Type:application/json"})
    @GET("api/v2/history/event")
    Call<HistoryTrainingResponse> historyTrainingScrollResponseCall(@Header("Authorization") String authorizen, @Query("status") String status, @Query("page") String page);

    // history room
    @Headers({"Accept:application/json","Content-Type:application/json"})
    @GET("api/v2/history/booking")
    Call<HistoryRoomResponse> historyRoomReponseCall(@Header("Authorization") String authorizen, @Query("status") String status);

    // history room
    @Headers({"Accept:application/json","Content-Type:application/json"})
    @GET("api/v2/history/booking")
    Call<HistoryRoomResponse> historyRoomScrollReponseCall(@Header("Authorization") String authorizen, @Query("status") String status, @Query("page") String page);

    // booking room
    @Headers({"Accept:application/json","Content-Type:application/json"})
    @POST("api/v2/booking")
    Call<RuangResponse> ruangResponseCall(@Header("Authorization") String authorizen, @Body RuangRequest ruangRequest);

    // transaksi list
    @Headers({"Accept:application/json","Content-Type:application/json"})
    @GET("api/v2/transaction")
    Call<TransaksiResponse> transaksiResponseCall(@Header("Authorization") String authorizen);

    // detail pelatihan
    @Headers({"Accept:application/json","Content-Type:application/json"})
    @GET("api/v1/event/{id}")
    Call<DetailPelatihan> detailPelatihanResponseCall(@Header("Authorization") String authorizen, @Path("id") String s);

    // update photo profile
    @Headers({"Accept:application/json"})
    @Multipart
    @POST("api/v1/auth/upload_profile")
    Call<UpdateProfileResponse> uploadProfilePicture(@Header("Authorization") String authorizen, @Part MultipartBody.Part photo);

    // upload file
    @Headers({"Accept:application/json"})
    @Multipart
    @POST("api/v1/event/{id}/document")
    Call<UploadFileResponse> uploadFilePelatihan(@Header("Authorization") String authorizen, @Path("id") String id, @Part ArrayList<MultipartBody.Part> photo, @Part ArrayList<MultipartBody.Part> file);

    // harga
    @Headers({"Accept:application/json","Content-Type:application/json"})
    @GET("api/v2/building/")
    Call<BangunanResponse> hargaResponseCall();
}