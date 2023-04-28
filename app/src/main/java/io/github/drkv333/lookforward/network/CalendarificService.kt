package io.github.drkv333.lookforward.network

import io.github.drkv333.lookforward.network.dao.CountryResponse
import io.github.drkv333.lookforward.network.dao.HolidayResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CalendarificService {
    /**
     *
     * Get list of supported countries.
     * @return Call&lt;CountryResponse&gt;
     */
    @GET("countries")
    suspend fun countriesGet(): Response<CountryResponse>

    /**
     *
     * Get a list of holidays.
     * @param country Which country to get holidays for. This should be a supported country, specifies in ISO-3166 format. (required)
     * @param year Get holidays for this year, up to 2049. (required)
     * @return Call&lt;HolidayResponse&gt;
     */
    @GET("holidays")
    suspend fun holidaysGet(@Query("country") country: String, @Query("year") year: Int): Response<HolidayResponse>
}