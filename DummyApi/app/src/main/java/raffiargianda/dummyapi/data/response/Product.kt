package raffiargianda.dummyapi.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

data class ProductResponse(
    val products: List<Product>
)
@Parcelize
data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val thumbnail: String
) : Parcelable

