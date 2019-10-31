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