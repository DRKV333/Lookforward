package io.github.drkv333.lookforward.network

import io.github.drkv333.lookforward.network.dao.MyHoliday
import retrofit2.Call
import retrofit2.http.*

interface LookforwardService {
    /**
     *
     * Get a list of every holiday resource.
     * @return Call&lt;List&lt;MyHoliday&gt;&gt;
     */
    @GET("myholidays")
    fun myholidaysGet(): Call<List<MyHoliday>>

    /**
     *
     * Delete the specified holiday resource.
     * @param id ID of the holiday resource to delete. (required)
     * @return Call&lt;Void&gt;
     */
    @DELETE("myholidays/{id}")
    fun myholidaysIdDelete(@Path("id") id: String): Call<Void>

    /**
     *
     * Get details of a specific holiday resource.
     * @param id ID of the holiday resource to get. (required)
     * @return Call&lt;MyHoliday&gt;
     */
    @GET("myholidays/{id}")
    fun myholidaysIdGet(@Path("id") id: String): Call<MyHoliday>

    /**
     *
     * Modify or create a new holiday resource with the specified id.
     * @param id ID of the holiday resource to modify or create. (required)
     * @param body  (optional)
     * @return Call&lt;Void&gt;
     */
    @PUT("myholidays/{id}")
    fun myholidaysIdPut(@Path("id") id: String, @Body body: MyHoliday): Call<Void>

    /**
     *
     * Create a new holiday resource.
     * @param body  (optional)
     * @return Call&lt;MyHoliday&gt;
     */
    @POST("myholidays")
    fun myholidaysPost(@Body body: MyHoliday): Call<MyHoliday>
}