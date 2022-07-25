package br.com.matuki.radiobrowser.shared;

import java.lang.System;

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
 * *  Extend [MediaBrowserServiceCompat], implementing the media browsing
 * related methods [MediaBrowserServiceCompat.onGetRoot] and
 * [MediaBrowserServiceCompat.onLoadChildren];
 *
 * *  In onCreate, start a new [MediaSessionCompat] and notify its parent
 * with the session"s token [MediaBrowserServiceCompat.setSessionToken];
 *
 * *  Set a callback on the [MediaSessionCompat.setCallback].
 * The callback will receive all the user"s actions, like play, pause, etc;
 *
 * *  Handle all the actual music playing using any method your app prefers (for example,
 * [android.media.MediaPlayer])
 *
 * *  Update playbackState, "now playing" metadata and queue, using MediaSession proper methods
 * [MediaSessionCompat.setPlaybackState]
 * [MediaSessionCompat.setMetadata] and
 * [MediaSessionCompat.setQueue])
 *
 * *  Declare and export the service in AndroidManifest with an intent receiver for the action
 * android.media.browse.MediaBrowserService
 *
 * To make your app compatible with Android Auto, you also need to:
 *
 * *  Declare a meta-data tag in AndroidManifest.xml linking to a xml resource
 * with a &lt;automotiveApp&gt; root element. For a media app, this must include
 * an &lt;uses name="media"/&gt; element as a child.
 * For example, in AndroidManifest.xml:
 * &lt;meta-data android:name="com.google.android.gms.car.application"
 * android:resource="@xml/automotive_app_desc"/&gt;
 * And in res/values/automotive_app_desc.xml:
 * &lt;automotiveApp&gt;
 * &lt;uses name="media"/&gt;
 * &lt;/automotiveApp&gt;
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \'2\u00020\u0001:\u0001\'B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0002J\u000e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u001aH\u0016J\"\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00142\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J$\u0010#\u001a\u00020\u001a2\u0006\u0010$\u001a\u00020\u001f2\u0012\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00160&H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u00020\u000e8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006("}, d2 = {"Lbr/com/matuki/radiobrowser/shared/RadioBrowserService;", "Landroidx/media/MediaBrowserServiceCompat;", "()V", "mediaSession", "Landroid/support/v4/media/session/MediaSessionCompat;", "mediaSessionConnector", "Lcom/google/android/exoplayer2/ext/mediasession/MediaSessionConnector;", "metadataBuilder", "Landroid/support/v4/media/MediaMetadataCompat$Builder;", "playbackStateBuilder", "Landroid/support/v4/media/session/PlaybackStateCompat$Builder;", "player", "Lcom/google/android/exoplayer2/ExoPlayer;", "radioRepository", "Lbr/com/matuki/radiobrowser/shared/RadioRepository;", "getRadioRepository", "()Lbr/com/matuki/radiobrowser/shared/RadioRepository;", "setRadioRepository", "(Lbr/com/matuki/radiobrowser/shared/RadioRepository;)V", "rootMode", "", "buildRadioStationList", "", "Landroid/support/v4/media/MediaBrowserCompat$MediaItem;", "buildRadioStationMenuList", "onCreate", "", "onDestroy", "onGetRoot", "Landroidx/media/MediaBrowserServiceCompat$BrowserRoot;", "clientPackageName", "", "clientUid", "rootHints", "Landroid/os/Bundle;", "onLoadChildren", "parentId", "result", "Landroidx/media/MediaBrowserServiceCompat$Result;", "Companion", "shared_debug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class RadioBrowserService extends androidx.media.MediaBrowserServiceCompat {
    private android.support.v4.media.session.MediaSessionCompat mediaSession;
    private com.google.android.exoplayer2.ExoPlayer player;
    private com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector mediaSessionConnector;
    private final android.support.v4.media.session.PlaybackStateCompat.Builder playbackStateBuilder = null;
    private final android.support.v4.media.MediaMetadataCompat.Builder metadataBuilder = null;
    @javax.inject.Inject()
    public br.com.matuki.radiobrowser.shared.RadioRepository radioRepository;
    private int rootMode = -1;
    @org.jetbrains.annotations.NotNull()
    public static final br.com.matuki.radiobrowser.shared.RadioBrowserService.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ROOT_ID = "root";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SINGLE_MENU = "single_menu";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CONTENT_LIST = "content_list";
    
    public RadioBrowserService() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final br.com.matuki.radiobrowser.shared.RadioRepository getRadioRepository() {
        return null;
    }
    
    public final void setRadioRepository(@org.jetbrains.annotations.NotNull()
    br.com.matuki.radiobrowser.shared.RadioRepository p0) {
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public androidx.media.MediaBrowserServiceCompat.BrowserRoot onGetRoot(@org.jetbrains.annotations.NotNull()
    java.lang.String clientPackageName, int clientUid, @org.jetbrains.annotations.Nullable()
    android.os.Bundle rootHints) {
        return null;
    }
    
    @java.lang.Override()
    public void onLoadChildren(@org.jetbrains.annotations.NotNull()
    java.lang.String parentId, @org.jetbrains.annotations.NotNull()
    androidx.media.MediaBrowserServiceCompat.Result<java.util.List<android.support.v4.media.MediaBrowserCompat.MediaItem>> result) {
    }
    
    private final java.util.List<android.support.v4.media.MediaBrowserCompat.MediaItem> buildRadioStationList() {
        return null;
    }
    
    private final java.util.List<android.support.v4.media.MediaBrowserCompat.MediaItem> buildRadioStationMenuList() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lbr/com/matuki/radiobrowser/shared/RadioBrowserService$Companion;", "", "()V", "CONTENT_LIST", "", "ROOT_ID", "SINGLE_MENU", "shared_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}