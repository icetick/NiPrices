package alex.orobynskyi.niprices.domain.repository

import alex.orobynskyi.niprices.networking.EshopService
import javax.inject.Inject

class EuApiRepository @Inject constructor(var apiService: EshopService): ApiRepository {
    override fun getGames() = apiService.getEuGames()
}