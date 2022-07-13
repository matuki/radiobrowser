package br.com.matuki.radiobrowser.shared

import br.com.matuki.radiobrowser.shared.model.RadioStation

interface RadioRepository {
    fun listRadioStations() : List<RadioStation>
}
