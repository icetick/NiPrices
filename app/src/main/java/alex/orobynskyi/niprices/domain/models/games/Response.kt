package alex.orobynskyi.niprices.domain.models.games

data class Response(
    val docs: List<Doc>,
    val numFound: Int,
    val start: Int
)