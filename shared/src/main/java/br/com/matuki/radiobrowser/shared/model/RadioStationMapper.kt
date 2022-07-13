package br.com.matuki.radiobrowser.shared.model

import android.content.ContentResolver
import android.net.Uri
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaBrowserCompat.MediaItem.FLAG_PLAYABLE
import android.support.v4.media.MediaDescriptionCompat
import br.com.matuki.radiobrowser.shared.provider.AlbumArtContentProvider
import br.com.matuki.radiobrowser.shared.provider.RADIOBROWSER_AUTHORITY
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.MediaMetadata
import com.google.android.exoplayer2.util.MimeTypes
import timber.log.Timber

fun toExoPlayerMediaItem(station: RadioStation): MediaItem {
    return with(MediaItem.Builder()) {
        setMediaId(station.id)
        setUri(station.streamUrl)
        setMimeType(MimeTypes.AUDIO_MPEG)
        setMediaMetadata(
            MediaMetadata.Builder().setTitle(station.name).setArtworkUri(Uri.parse(station.iconUrl)).build()
        )
        setMediaMetadata(toMediaItemMetadata(station))
    }.build()
}

fun toMediaItem(station: RadioStation): MediaBrowserCompat.MediaItem {
    Timber.d("toMediaItem from station: $station")
    return MediaBrowserCompat.MediaItem(station.toMediaDescription(), FLAG_PLAYABLE)
}

fun RadioStation.toMediaDescription(): MediaDescriptionCompat {

    Timber.d("toMediaDescription URI built: ${AlbumArtContentProvider.mapUri(Uri.parse(iconUrl))}")

    return MediaDescriptionCompat.Builder()
    .setMediaId(id)
    .setTitle(name)
    .setIconUri(
        AlbumArtContentProvider.mapUri(Uri.parse(iconUrl))
    )
    .setMediaUri(Uri.parse(streamUrl))
    .build()
}

fun toMediaItemMetadata(station: RadioStation): com.google.android.exoplayer2.MediaMetadata {
    return with(MediaMetadata.Builder()) {
        setTitle(station.name)
        setDisplayTitle(station.name)
        setAlbumTitle(station.name)
        setArtworkUri(Uri.parse(station.iconUrl))
    }.build()
}

