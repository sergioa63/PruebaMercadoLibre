package om.example.core.apiservice

import com.example.core.model.data.local.ResponceProductos
import retrofit2.http.GET
import retrofit2.http.Url

interface ProductosList {
    @GET
    suspend fun getProductos(
        @Url url: String,
    ): ResponceProductos
}
