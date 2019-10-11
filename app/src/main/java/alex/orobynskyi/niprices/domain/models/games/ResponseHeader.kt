package alex.orobynskyi.niprices.domain.models.games

data class ResponseHeader(val QTime: Int, val params: Params?, val status: Int) {
    companion object {
        fun empty(): ResponseHeader {
            return ResponseHeader(0, null, 200)
        }
    }
}