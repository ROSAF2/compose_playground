package op.mobile.app.dev.rosaf2.navigationcompose.api

import op.mobile.app.dev.rosaf2.navigationcompose.model.Robot
import retrofit2.http.GET

interface IRobot {
    @GET("raw")
    suspend fun getResponse(): List<Robot>
}
