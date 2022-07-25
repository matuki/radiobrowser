package br.com.matuki.radiobrowser.shared.api

import br.com.matuki.radiobrowser.shared.RadioRepository
import br.com.matuki.radiobrowser.shared.model.RadioStation
import javax.inject.Inject

class RadioRepositoryImpl @Inject constructor(
    private val serviceApi: RadioBrowserApi
) : RadioRepository {
    override fun listRadioStations() = serviceApi.listRadioStations()
    override fun getRadioStation(stationId: String) = serviceApi.getRadioStation(stationId)
}
