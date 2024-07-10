package com.uam.scheduleapk.repository

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.uam.scheduleapk.remote.ApiAdapter
import com.uam.scheduleapk.remote.ApiUsuario
import org.json.JSONObject
import retrofit2.Response

class RepositoryUsuario {
    val apiUsuario : ApiUsuario by lazy {
        ApiAdapter.getInstance().create(ApiUsuario :: class.java)
    }

    suspend fun login(email:String,password : String) : Result<Int> {
        val retorno : Int
        return try {
            val response : Response<Int> = apiUsuario.login(email,password)
            //val var1 = response?.errorBody()?.charStream()?.readText()
            //val var2 = response.errorBody()?.string()
            //val jObjError = response.errorBody()?.string();
           // val jsonObject = jObjError.getJSONObject("error").getString("message");
            Log.d("RESPONSE", "${response}")
            Log.d("BODY","${response.body()}")
            retorno = response.body() as Int
            Log.d("BODY", "${retorno}")
            Result.success(retorno)
        } catch (e : Exception) {
            Log.d("error", "${e.message}")
            Result.failure(e)
        }
    }

}