package br.com.matuki.radiobrowser.shared.extension

import android.net.Uri
import android.support.v4.media.MediaMetadataCompat
import br.com.matuki.radiobrowser.shared.provider.AlbumArtContentProvider

fun MediaMetadataCompat.Builder.from(metadata: com.google.android.exoplayer2.MediaMetadata): MediaMetadataCompat =
    this.putString(MediaMetadataCompat.METADATA_KEY_TITLE, metadata.title.toString())
        .putString(
            MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON_URI,
            metadata.artworkUri?.let {
                AlbumArtContentProvider.mapUri(metadata.artworkUri ?: Uri.parse("no_image")).toString()
            }
        )
        .build()
