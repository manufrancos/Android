package com.manui.myapplication.rest

import com.manui.myapplication.rest.networkadapter.NetworkResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface RestInterface {

//    @POST("api/Auth")
//    suspend fun login(
//        @Body access: Access
//    ): NetworkResponse<String, ApiError>
//
//
//    @GET("api/Cargo/GetCargoByDateWithClaimsAsync/{year}/{month}/{day}")
//    suspend fun getMain(
//        @Path("year") year: Int,
//        @Path("month") month: Int,
//        @Path("day") day: Int
//    ): NetworkResponse<ArrayList<Position>, ApiError>
//
//
//    @GET("api/CargoDetail/GetCargoDetailByIdCargoAppControllerAsync/{id}")
//    suspend fun getDetail(
//        @Path("id") id: Int,
//        @Query("showStatusCargada") showStatusCargada: Boolean = false
//    ): NetworkResponse<ArrayList<Detail>, ApiError>
//
//    @GET("api/CargoDetail/GetContainersTypes")
//    suspend fun getContainersType(
//    ): NetworkResponse<ArrayList<ContainerType>, ApiError>
//
//    @GET("api/Blob/GetImagesToAppController/{id}")
//    suspend fun getImages(
//        @Path("id") id: Int,
//        ): NetworkResponse<ListImage, ApiError>
//
//    @POST("api/CargoDetail/CreateCargoDetailAppController/{id}")
//    suspend fun createCharge(
//        @Path("id") id: Int,
//        @Body detail: Detail
//    ): NetworkResponse<Detail, ApiError>
//
//    @POST("api/CargoDetail/EditCargoDetailAppController/{id}")
//    suspend fun editCargo(
//        @Path("id") id: Int,
//        @Body detail: Detail
//    ): NetworkResponse<Detail, ApiError>
//
//    @DELETE("api/CargoDetail/deleteCargoDetail/{id}")
//    suspend fun deleteCharge(
//        @Path("id") id: Int,
//    ): NetworkResponse<Any, ApiError>
//
//    @POST("Vgm/api/FunctionSendVgm")
//    suspend fun sendVGM(
//        @Query("numdoc") numdoc: Int,
//        @Query("X3Id") X3Id: String,
//        @Query("containernumber") containernumber: String,
//        @Query("sealnumber") sealnumber: String,
//        @Query("booking") booking: String,
//        @Query("vgm") vgm: Double,
//        @Query("tipcont") tipcont: Int,
//        @Query("isAppController") isAppController: Boolean = true,
//    ): NetworkResponse<Any, ApiError>
//
//
//    @POST("api/Blob/SaveMultiPartImages/{id}")
//    @Multipart
//    suspend fun addImages(
//        @Path("id") id: Int,
//        @Part photos: ArrayList<MultipartBody.Part>
//    ): NetworkResponse<Unit, ApiError>
}