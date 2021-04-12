package com.myapp.retrofit_with_rxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myapp.retrofit_with_rxjava.adapter.FoodAdapter
import com.myapp.retrofit_with_rxjava.model.Food
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.SchedulerSupport.IO
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var foodAdapter: FoodAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView= findViewById(R.id.recyclerView)
        foodAdapter= FoodAdapter(this, ArrayList<Food>())
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager =LinearLayoutManager(this@MainActivity)
            adapter=foodAdapter
        }
        val compositeDisposable= CompositeDisposable()
        compositeDisposable.add(getObservable().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                    response-> getObserver(response as ArrayList<Food>)
            },{
                t->onFailure(t)
            }))
    }
    private fun getObservable():Observable<List<Food>>{
        return com.myapp.retrofit_with_rxjava.network.Retrofit.apis.getData()
    }
    private fun getObserver(foodList: ArrayList<Food>){
        if(foodList!=null && foodList.size>0)
        {
            foodAdapter.setData(foodList)
        }
    }
    private fun onFailure(t:Throwable){
        Toast.makeText(applicationContext, "$t", Toast.LENGTH_SHORT).show()
    }
}