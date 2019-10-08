package alex.orobynskyi.niprices.domain.models.games

data class ResponseHeader(
    val QTime: Int,
    val params: Params,
    val status: Int
)