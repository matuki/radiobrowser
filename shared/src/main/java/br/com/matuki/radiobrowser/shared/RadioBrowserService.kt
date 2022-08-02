package br.com.matuki.radiobrowser.shared

import android.app.PendingIntent
import android.net.Uri
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat.MediaItem
import android.support.v4.media.MediaDescriptionCompat
import android.support.v4.media.MediaMetadataCompat
import androidx.media.MediaBrowserServiceCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.media.utils.MediaConstants
import androidx.media.utils.MediaConstants.DESCRIPTION_EXTRAS_KEY_CONTENT_STYLE_BROWSABLE
import androidx.media.utils.MediaConstants.DESCRIPTION_EXTRAS_KEY_CONTENT_STYLE_PLAYABLE
import androidx.media.utils.MediaConstants.DESCRIPTION_EXTRAS_VALUE_CONTENT_STYLE_GRID_ITEM
import br.com.matuki.radiobrowser.shared.api.RadioBrowserApi
import br.com.matuki.radiobrowser.shared.api.RadioRepositoryImpl
import br.com.matuki.radiobrowser.shared.extension.from
import br.com.matuki.radiobrowser.shared.model.MyPlaybackPreparer
import br.com.matuki.radiobrowser.shared.model.toMediaItem
import br.com.matuki.radiobrowser.shared.provider.AlbumArtContentProvider
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

/**
 * This class provides a MediaBrowser through a service. It exposes the media library to a browsing
 * client, through the onGetRoot and onLoadChildren methods. It also creates a MediaSession and
 * exposes it through its MediaSession.Token, which allows the client to create a MediaController
 * that connects to and send control commands to the MediaSession remotely. This is useful for
 * user interfaces that need to interact with your media session, like Android Auto. You can
 * (should) also use the same service from your app"s UI, which gives a seamless playback
 * experience to the user.
 *
 *
 * To implement a MediaBrowserService, you need to:
 *
 *  *  Extend [MediaBrowserServiceCompat], implementing the media browsing
 * related methods [MediaBrowserServiceCompat.onGetRoot] and
 * [MediaBrowserServiceCompat.onLoadChildren];
 *
 *  *  In onCreate, start a new [MediaSessionCompat] and notify its parent
 * with the session"s token [MediaBrowserServiceCompat.setSessionToken];
 *
 *  *  Set a callback on the [MediaSessionCompat.setCallback].
 * The callback will receive all the user"s actions, like play, pause, etc;
 *
 *  *  Handle all the actual music playing using any method your app prefers (for example,
 * [android.media.MediaPlayer])
 *
 *  *  Update playbackState, "now playing" metadata and queue, using MediaSession proper methods
 * [MediaSessionCompat.setPlaybackState]
 * [MediaSessionCompat.setMetadata] and
 * [MediaSessionCompat.setQueue])
 *
 *  *  Declare and export the service in AndroidManifest with an intent receiver for the action
 * android.media.browse.MediaBrowserService
 *
 * To make your app compatible with Android Auto, you also need to:
 *
 *  *  Declare a meta-data tag in AndroidManifest.xml linking to a xml resource
 * with a &lt;automotiveApp&gt; root element. For a media app, this must include
 * an &lt;uses name="media"/&gt; element as a child.
 * For example, in AndroidManifest.xml:
 * &lt;meta-data android:name="com.google.android.gms.car.application"
 * android:resource="@xml/automotive_app_desc"/&gt;
 * And in res/values/automotive_app_desc.xml:
 * &lt;automotiveApp&gt;
 * &lt;uses name="media"/&gt;
 * &lt;/automotiveApp&gt;
 *
 */
class RadioBrowserService:
    MediaBrowserServiceCompat() {

    private lateinit var mediaSession: MediaSessionCompat

    private lateinit var player: ExoPlayer

    private lateinit var mediaSessionConnector: MediaSessionConnector

    private val playbackStateBuilder = PlaybackStateCompat.Builder()

    private val metadataBuilder = MediaMetadataCompat.Builder()

    // TODO: Inject this instead
    var radioRepository = RadioRepositoryImpl(RadioBrowserApi())

    private var rootMode: Int = -1

    override fun onCreate() {
        super.onCreate()
        Timber.d("MyMusicService onCreate()")

        // Build a PendingIntent that can be used to launch the UI.
        val sessionActivityPendingIntent =
            packageManager?.getLaunchIntentForPackage(packageName)?.let { sessionIntent ->
                PendingIntent.getActivity(this, 0, sessionIntent, 0)
            }

        Timber.d("Creating MediaSession")
        mediaSession = MediaSessionCompat(this, "MyMusicService").apply {
            setSessionActivity(sessionActivityPendingIntent)
            isActive = true

            setFlags(
                MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS or
                        MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS
            )

            setPlaybackState(
                playbackStateBuilder
                    .setActions(PlaybackStateCompat.ACTION_PLAY or PlaybackStateCompat.ACTION_PAUSE)
                    .build()
            )
        }

        sessionToken = mediaSession.sessionToken


        mediaSession.setMetadata(metadataBuilder.build())

        Timber.d("MyMusicService building ExoPlayer")
        player = ExoPlayer.Builder(this).build()

        Timber.d("Creating MediaSessionConnector")
        mediaSessionConnector = MediaSessionConnector(mediaSession)
        mediaSessionConnector.setPlayer(player)
        mediaSessionConnector.setPlaybackPreparer(MyPlaybackPreparer(player, radioRepository))
        mediaSessionConnector.setMediaMetadataProvider { player -> metadataBuilder.from(player.mediaMetadata) }
    }

    override fun onDestroy() {
        mediaSession.run {
            isActive = false
            release()
        }
        player.release()
    }

    override fun onGetRoot(
        clientPackageName: String,
        clientUid: Int,
        rootHints: Bundle?
    ): BrowserRoot {
        Timber.d("MyMusicService onGetRoot()")
        val maximumRootChildLimit = rootHints?.getInt(
            MediaConstants.BROWSER_ROOT_HINTS_KEY_ROOT_CHILDREN_LIMIT,
            /* defaultValue= */ 4
        )
        val supportedRootChildFlags = rootHints?.getInt(
            MediaConstants.BROWSER_ROOT_HINTS_KEY_ROOT_CHILDREN_SUPPORTED_FLAGS,
            /* defaultValue= */ MediaItem.FLAG_BROWSABLE
        )

        rootMode = supportedRootChildFlags ?: MediaItem.FLAG_BROWSABLE
        Timber.d("MyMusicService onGetRoot() rootMode: $rootMode")

        val extras = Bundle().apply {
            // Show browsable and content as grid
            putInt(
                DESCRIPTION_EXTRAS_KEY_CONTENT_STYLE_BROWSABLE,
                DESCRIPTION_EXTRAS_VALUE_CONTENT_STYLE_GRID_ITEM
            )
            putInt(
                DESCRIPTION_EXTRAS_KEY_CONTENT_STYLE_PLAYABLE,
                DESCRIPTION_EXTRAS_VALUE_CONTENT_STYLE_GRID_ITEM
            )
        }

        return BrowserRoot(ROOT_ID, extras)
    }

    override fun onLoadChildren(parentId: String, result: Result<MutableList<MediaItem>>) {
        Timber.d("MyMusicService onLoadChildren(). parentId = $parentId")

        when (parentId) {
            ROOT_ID -> if (rootMode == MediaItem.FLAG_PLAYABLE) {
                result.sendResult(buildRadioStationList())
            } else {
                result.sendResult(buildRadioStationMenuList())
            }
            SINGLE_MENU -> result.sendResult(buildRadioStationList())
            else -> result.sendResult(null)
        }
    }

    private fun buildRadioStationList(): MutableList<MediaItem> {
        Timber.d("MyMusicService buildRadioStationList")
        // TODO: Do on background when a real API is used
        val radioStations = radioRepository.listRadioStations()

        return radioStations.map { station ->
            // Add station image to content provider
            AlbumArtContentProvider.mapUri(Uri.parse(station.iconUrl))
            // Convert from RadioStation to MediaBrowserCompat.MediaItem
            toMediaItem(station)
        }.toMutableList()
    }

    private fun buildRadioStationMenuList(): MutableList<MediaItem> {
        Timber.d("MyMusicService buildRadioStationMenuList")
        val description = MediaDescriptionCompat.Builder().setMediaId(SINGLE_MENU)
            .setTitle(resources.getString(R.string.single_menu_name)).build()
        return mutableListOf(MediaItem(description, MediaItem.FLAG_BROWSABLE))
    }

    companion object {
        const val ROOT_ID = "root"
        const val SINGLE_MENU = "single_menu"
        const val CONTENT_LIST = "content_list"
    }
}
