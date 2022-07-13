package br.com.matuki.radiobrowser

import android.content.ComponentName
import android.media.AudioManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.PlaybackStateCompat
import android.widget.ImageView
import androidx.core.content.ContextCompat
import br.com.matuki.radiobrowser.shared.RadioBrowserService
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var mediaBrowser: MediaBrowserCompat

    private lateinit var playPauseButton: ImageView

    private val connectionCallbacks = object : MediaBrowserCompat.ConnectionCallback() {
        override fun onConnected() {

            // Get the token for the MediaSession
            mediaBrowser.sessionToken.also { token ->

                // Create a MediaControllerCompat
                val mediaController = MediaControllerCompat(
                    this@MainActivity, // Context
                    token
                )

                // Save the controller
                MediaControllerCompat.setMediaController(this@MainActivity, mediaController)
            }

            // Finish building the UI
            buildTransportControls()
        }

        override fun onConnectionSuspended() {
            // The Service has crashed. Disable transport controls until it automatically reconnects
        }

        override fun onConnectionFailed() {
            // The Service has refused our connection
        }
    }

    private var controllerCallback = object : MediaControllerCompat.Callback() {

        override fun onMetadataChanged(metadata: MediaMetadataCompat?) {}

        override fun onPlaybackStateChanged(playbackState: PlaybackStateCompat?) {
            Timber.d("MainActivity playback state changed to $playbackState")

            val playPauseDrawable =
                if (playbackState?.state == PlaybackStateCompat.STATE_PLAYING) {
                    Timber.d("MainActivity setting button icon to pause")
                    R.drawable.baseline_pause_circle_outline_black_48
                } else {
                    Timber.d("MainActivity setting button icon to play")
                    R.drawable.baseline_play_circle_outline_black_48
                }
            playPauseButton.setImageDrawable(
                ContextCompat.getDrawable(this@MainActivity, playPauseDrawable)
            )
        }

        override fun onSessionDestroyed() {
            mediaBrowser.disconnect()
            // maybe schedule a reconnection using a new MediaBrowser instance
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mediaBrowser = MediaBrowserCompat(
            this,
            ComponentName(this, RadioBrowserService::class.java),
            connectionCallbacks,
            null
        )
    }

    override fun onStart() {
        super.onStart()
        mediaBrowser.connect()
    }

    override fun onResume() {
        super.onResume()
        volumeControlStream = AudioManager.STREAM_MUSIC
    }

    override fun onStop() {
        super.onStop()
        MediaControllerCompat.getMediaController(this)?.unregisterCallback(controllerCallback)
        mediaBrowser.disconnect()
    }

    fun buildTransportControls() {
        val mediaController = MediaControllerCompat.getMediaController(this@MainActivity)
        // Grab the view for the play/pause button
        playPauseButton = findViewById<ImageView>(R.id.play_pause).apply {
            setOnClickListener {
                // Since this is a play/pause button, you'll need to test the current state
                // and choose the action accordingly

                val pbState = mediaController.playbackState.state

                Timber.d("MainActivity play clicked. Current state: $pbState")

                if (pbState == PlaybackStateCompat.STATE_PLAYING) {
                    mediaController.transportControls.pause()
                } else {
                    Timber.d("MainActivity start playing")
//                    mediaController.transportControls.play()
                    mediaController.transportControls.prepareFromMediaId("1", null)
                    mediaController.transportControls.playFromMediaId("1", null)
                }
            }
        }

        // Display the initial state
        val metadata = mediaController.metadata
        val pbState = mediaController.playbackState

        // Register a Callback to stay in sync
        mediaController.registerCallback(controllerCallback)
    }
}
