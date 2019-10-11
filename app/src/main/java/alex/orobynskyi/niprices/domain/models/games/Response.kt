package alex.orobynskyi.niprices.domain.models.games

data class Response(
    val docs: List<GameDoc>,
    val numFound: Int,
    val start: Int
)