package br.com.matuki.radiobrowser.shared.model

import android.net.Uri
import android.os.Bundle
import android.os.ResultReceiver
import android.support.v4.media.session.PlaybackStateCompat
import br.com.matuki.radiobrowser.shared.RadioRepository
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector
import timber.log.Timber

class MyPlaybackPreparer(
    // TODO: Inject instead?
    private val player: ExoPlayer,
    private val repository: RadioRepository) : MediaSessionConnector.PlaybackPreparer {

    override fun onCommand(player: Player, command: String, extras: Bundle?, cb: ResultReceiver?): Boolean {
        Timber.d("MyPlaybackPreparer onCommand: $command")
        return true
    }

    override fun getSupportedPrepareActions(): Long {
        return PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID or
                PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID or
                PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH or
                PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH
    }

    override fun onPrepare(playWhenReady: Boolean) {
        Timber.d("MyPlaybackPreparer onPrepare playWhenReady: $playWhenReady")
    }

    override fun onPrepareFromMediaId(mediaId: String, playWhenReady: Boolean, extras: Bundle?) {
        Timber.d("MyPlaybackPreparer onPrepareFromMediaId mediaId: $mediaId playWhenReady: $playWhenReady")
        val station = repository.listRadioStations().firstOrNull { it.id == mediaId }

        station?.let {
            player.playWhenReady = playWhenReady
            player.setMediaItem(toExoPlayerMediaItem(it))
            player.prepare()
        }
    }

    override fun onPrepareFromSearch(query: String, playWhenReady: Boolean, extras: Bundle?) {
        Timber.d("MyPlaybackPreparer onPrepareFromSearch: $query")
    }

    override fun onPrepareFromUri(uri: Uri, playWhenReady: Boolean, extras: Bundle?) {
        Timber.d("MyPlaybackPreparer onPrepareFromUri: $uri")
    }
}
