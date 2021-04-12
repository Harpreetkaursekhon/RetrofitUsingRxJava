package com.myapp.retrofit_with_rxjava.network

import com.myapp.retrofit_with_rxjava.model.Food
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface Api {

    @GET("food.php")
    fun getData():Observable<List<Food>>
}