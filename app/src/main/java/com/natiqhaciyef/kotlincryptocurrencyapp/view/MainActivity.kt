package com.natiqhaciyef.kotlincryptocurrencyapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.natiqhaciyef.kotlincryptocurrencyapp.adapter.CryptoAdapter
import com.natiqhaciyef.kotlincryptocurrencyapp.databinding.ActivityMainBinding
import com.natiqhaciyef.kotlincryptocurrencyapp.model.CryptoModel
import com.natiqhaciyef.kotlincryptocurrencyapp.service.CryptoAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.disposables.ArrayCompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val BASE_URL = "https://raw.githubusercontent.com/"
    private var cryptoModels: ArrayList<CryptoModel>? = null
    private lateinit var cryptoAdapter: CryptoAdapter
    //Disposable
    private lateinit var compositeDisposable: CompositeDisposable


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        compositeDisposable = CompositeDisposable()
        //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json
        //Recyclerview data gelmezden evvel qosulmalidi
        binding.recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
        loadData()

    }

    private fun loadData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(CryptoAPI::class.java)

        compositeDisposable.add(
            retrofit.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponsible)
        )

        /*
        val service = retrofit.create(CryptoAPI::class.java)
        val call = service.getData()
        call.enqueue(object : Callback<List<CryptoModel>> {
            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        cryptoModels = ArrayList(it)
                        cryptoModels?.let {
                            cryptoAdapter = CryptoAdapter(it)
                            recyclerview.adapter = cryptoAdapter
                        }

//                        for (cryptoModel: CryptoModel in cryptoModels!!){
//                            println(cryptoModel.currency)
//                            println(cryptoModel.price)
//                        }

                    }
                }
            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                t.printStackTrace()
            }
        })
        */
    }

    private fun handleResponsible(cryptoList: List<CryptoModel>){
        cryptoModels = ArrayList(cryptoList)
        cryptoModels?.let {
            cryptoAdapter = CryptoAdapter(it)
            recyclerview.adapter = cryptoAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

}