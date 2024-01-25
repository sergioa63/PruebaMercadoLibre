package om.example.core.apiservice

import com.example.core.model.data.local.ResponceProductos
import retrofit2.http.GET
import retrofit2.http.Url

interface ProductosList {
    /**
     * Servicio GET obtener listado de Productos
     *
     * @param url url con Query para busqueda
     *
     * @return POJO ResponceProductos (responce json)

     */
    @GET
    suspend fun getProductos(
        @Url url: String,
    ): ResponceProductos
}
