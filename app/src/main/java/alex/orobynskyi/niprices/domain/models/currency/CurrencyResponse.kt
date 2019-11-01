package alex.orobynskyi.niprices.domain.models.currency
import com.google.gson.annotations.SerializedName


data class CurrencyResponse(
    @SerializedName("note")
    val note: String,
    @SerializedName("results")
    val results: HashMap<String, Currency>
)

data class Currency(
    @SerializedName("alpha3")
    val alpha3: String,
    @SerializedName("currencyId")
    val currencyId: String,
    @SerializedName("currencyName")
    val currencyName: String,
    @SerializedName("currencySymbol")
    val currencySymbol: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)

data class CurrencyRate(
    @SerializedName("query")
    val query: Query,
    @SerializedName("results")
    val results: HashMap<String, Rate>
)

data class Query(
    @SerializedName("count")
    val count: Int
)

data class Rate(
    @SerializedName("fr")
    val fr: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("to")
    val to: String,
    @SerializedName("val")
    val valX: Double
)