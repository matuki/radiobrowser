package br.com.matuki.radiobrowser.shared.model;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J,\u0010\t\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\nH\u0016J\"\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\"\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\"\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0014\u001a\u00020\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lbr/com/matuki/radiobrowser/shared/model/MyPlaybackPreparer;", "Lcom/google/android/exoplayer2/ext/mediasession/MediaSessionConnector$PlaybackPreparer;", "player", "Lcom/google/android/exoplayer2/ExoPlayer;", "repository", "Lbr/com/matuki/radiobrowser/shared/RadioRepository;", "(Lcom/google/android/exoplayer2/ExoPlayer;Lbr/com/matuki/radiobrowser/shared/RadioRepository;)V", "getSupportedPrepareActions", "", "onCommand", "", "Lcom/google/android/exoplayer2/Player;", "command", "", "extras", "Landroid/os/Bundle;", "cb", "Landroid/os/ResultReceiver;", "onPrepare", "", "playWhenReady", "onPrepareFromMediaId", "mediaId", "onPrepareFromSearch", "query", "onPrepareFromUri", "uri", "Landroid/net/Uri;", "shared_debug"})
public final class MyPlaybackPreparer implements com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector.PlaybackPreparer {
    private final com.google.android.exoplayer2.ExoPlayer player = null;
    private final br.com.matuki.radiobrowser.shared.RadioRepository repository = null;
    
    public MyPlaybackPreparer(@org.jetbrains.annotations.NotNull()
    com.google.android.exoplayer2.ExoPlayer player, @org.jetbrains.annotations.NotNull()
    br.com.matuki.radiobrowser.shared.RadioRepository repository) {
        super();
    }
    
    @java.lang.Override()
    public boolean onCommand(@org.jetbrains.annotations.NotNull()
    com.google.android.exoplayer2.Player player, @org.jetbrains.annotations.NotNull()
    java.lang.String command, @org.jetbrains.annotations.Nullable()
    android.os.Bundle extras, @org.jetbrains.annotations.Nullable()
    android.os.ResultReceiver cb) {
        return false;
    }
    
    @java.lang.Override()
    public long getSupportedPrepareActions() {
        return 0L;
    }
    
    @java.lang.Override()
    public void onPrepare(boolean playWhenReady) {
    }
    
    @java.lang.Override()
    public void onPrepareFromMediaId(@org.jetbrains.annotations.NotNull()
    java.lang.String mediaId, boolean playWhenReady, @org.jetbrains.annotations.Nullable()
    android.os.Bundle extras) {
    }
    
    @java.lang.Override()
    public void onPrepareFromSearch(@org.jetbrains.annotations.NotNull()
    java.lang.String query, boolean playWhenReady, @org.jetbrains.annotations.Nullable()
    android.os.Bundle extras) {
    }
    
    @java.lang.Override()
    public void onPrepareFromUri(@org.jetbrains.annotations.NotNull()
    android.net.Uri uri, boolean playWhenReady, @org.jetbrains.annotations.Nullable()
    android.os.Bundle extras) {
    }
}