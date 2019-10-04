package com.bhupen.dailynews.shared.api

/**
 * Created by Bhupen
 */

import com.bhupen.dailynews.dataType.model.Git_commit
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * The interface which provides methods to get result of webservices
 */
interface PostApi {
    /**
     * Get the list of the pots from the API
     */

    @GET("commits")
    fun getCommits(): Observable<List<Git_commit>>
}