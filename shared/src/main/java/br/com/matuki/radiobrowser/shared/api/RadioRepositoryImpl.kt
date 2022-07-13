package br.com.matuki.radiobrowser.shared.api

import br.com.matuki.radiobrowser.shared.RadioRepository

class RadioRepositoryImpl : RadioRepository {

    // TODO: Inject instead
    private val serviceApi = RadioBrowserApi()

    override fun listRadioStations() = serviceApi.listRadioStations()
}
