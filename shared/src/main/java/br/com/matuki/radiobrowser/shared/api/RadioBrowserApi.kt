package br.com.matuki.radiobrowser.shared.api

import br.com.matuki.radiobrowser.shared.model.RadioStation

class RadioBrowserApi {

    // "Fake" API for now
    fun listRadioStations() : List<RadioStation> {

        val cnbc = RadioStation(
            id = "0",
            name = "CNBC",
            streamUrl = "https://tunein.streamguys1.com/cnbc",
            iconUrl = "https://play-lh.googleusercontent.com/UFseqpgRgK38lavCq46JkCidIMkdaXsKi6II4nBwyIggaeiAiWWT8QgEEzxZDVuWMZM=w240-h480"
        )

        val wprIdeasNetwork = RadioStation(
            id = "1",
            name = "WPR Ideas Network",
            streamUrl = "https://wpr-ice.streamguys1.com/wpr-ideas-mp3-64",
            iconUrl = "https://play-lh.googleusercontent.com/98WxCExgZSb4p41nR288T-b1VyBbeAljV0DNlgU6hDysg_VUH0vxwpo0n-TUGCY8Rgg7=w240-h480"
        )

        val wnycNewYork = RadioStation(
            id = "2",
            name = "WNYC - Public Radio",
            streamUrl = "https://fm939.wnyc.org/wnycfm-mobile.aac",
            iconUrl = "https://play-lh.googleusercontent.com/6zRobz37BWHNxa2iIMtKg8SlRKmAaGpk0aLKZ_BDO5ArPxttprUidt_3UJWieucq4vwR=w240-h480"
        )

        val bbcWorldService = RadioStation(
            id = "3",
            name = "BBC from VPR",
            streamUrl = "https://vprbbc.streamguys1.com/vprbbc24.mp3",
            iconUrl = "https://play-lh.googleusercontent.com/Iip-8Yn3PLAzecCMb4ZaHTvFObl3ETUWZmd5zLflhbB6BXKyNc5aM4hrGAA9NXSs7i0=w240-h480"
        )

        val kiisFmLA = RadioStation(
            id = "4",
            name = "KIIS FM LA",
            streamUrl = "https://n27b-e2.revma.ihrhls.com/zc185",
            iconUrl = "https://play-lh.googleusercontent.com/ku1jq93F8wMW9LwQZaoqJFD40gYQJSpIoHdnY-8Co66J-X-a5ot9a8GLO7G8jGplSKU=w240-h480"
        )

        val wTopFm = RadioStation(
            id = "5",
            name = "NPR News",
            streamUrl = "https://npr-ice.streamguys1.com/live.mp3",
            iconUrl = "https://play-lh.googleusercontent.com/XqcEtsosmgfU3bh3gwgrrZ9_QZFE9DcFgFS6qaMYt-IWyQX0z1hiU_FRlQGXSmkA93CH=w240-h480"
        )

        return listOf(cnbc, wprIdeasNetwork, wnycNewYork, bbcWorldService, kiisFmLA, wTopFm)
    }
}
