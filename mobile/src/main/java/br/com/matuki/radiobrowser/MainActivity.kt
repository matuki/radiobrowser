package br.com.matuki.radiobrowser

import android.content.ComponentName
import android.media.AudioManager
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.PlaybackStateCompat
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.matuki.radiobrowser.shared.RadioBrowserService
import br.com.matuki.radiobrowser.view.PlayerScreen
import br.com.matuki.radiobrowser.view.PlayerScreenViewModel
import br.com.matuki.radiobrowser.view.RadioListScreen
import br.com.matuki.radiobrowser.view.RadioListViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mediaBrowser: MediaBrowserCompat

    private val radioListViewModel: RadioListViewModel by viewModels()

    private val playerViewModel: PlayerScreenViewModel by viewModels()

    inner class MediaBridge : MediaControllerCompat.Callback() {

        fun onPlayPauseClick(mediaId: String) {
            val pbState = mediaController.playbackState?.state
            if (pbState == PlaybackStateCompat.STATE_PLAYING) {
                mediaController.transportControls.pause()
            } else {
                Log.d("MainActivity", "onPlayPauseClick starting playFromMediaId")
                mediaController.transportControls.playFromMediaId(mediaId, null)
            }
        }

        override fun onMetadataChanged(metadata: MediaMetadataCompat?) {}

        override fun onPlaybackStateChanged(playbackState: PlaybackStateCompat?) {
            Timber.d("MainActivity MediaBridge MediaControllerCompat.Callback playback state changed to $playbackState")
            if (playbackState?.state == PlaybackStateCompat.STATE_PLAYING) {
                playerViewModel.sendIntent(PlayerScreenViewModel.Intent.StreamPlayed)
            } else {
                playerViewModel.sendIntent(PlayerScreenViewModel.Intent.StreamPaused)
            }

        }

        override fun onSessionDestroyed() {
            mediaBrowser.disconnect()
            // maybe schedule a reconnection using a new MediaBrowser instance
        }

    }

    private var mediaBridge = MediaBridge()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "radioList") {
                composable("radioList") {
                    RadioListScreen(
                        viewModel = radioListViewModel,
                        navController = navController
                    )
                }
                composable("player/{stationId}") { navBackStackEntry ->
                    PlayerScreen(
                        viewModel = playerViewModel,
                        stationId = navBackStackEntry.arguments?.getString("stationId") ?: "",
                        mediaBridge = mediaBridge
                    )
                }
            }
        }

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
        MediaControllerCompat.getMediaController(this)?.unregisterCallback(mediaBridge)
        mediaBrowser.disconnect()
    }

    private val connectionCallbacks = object : MediaBrowserCompat.ConnectionCallback() {
        override fun onConnected() {
            Log.d("MainActivity", "ConnectionCallback onConnected")

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
            Log.d("MainActivity", "ConnectionCallback onConnectionSuspended")
            // The Service has crashed. Disable transport controls until it automatically reconnects
        }

        override fun onConnectionFailed() {
            Log.d("MainActivity", "ConnectionCallback onConnectionFailed")
            // The Service has refused our connection
        }
    }

    fun buildTransportControls() {
        val mediaController = MediaControllerCompat.getMediaController(this@MainActivity)

        // Display the initial state
        val metadata = mediaController.metadata
        val pbState = mediaController.playbackState

        // Register a Callback to stay in sync
        mediaController.registerCallback(mediaBridge)
    }
}
