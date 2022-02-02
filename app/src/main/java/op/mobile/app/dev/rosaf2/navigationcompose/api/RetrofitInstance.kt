package op.mobile.app.dev.rosaf2.navigationcompose.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL =
    "https://gist.github.com/ROSAF2/a1e6f9fca91465b3b4db4b122312abc8/"

object ServiceInstance {
    val retrofitService: IRobot by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IRobot::class.java)
    }
}
