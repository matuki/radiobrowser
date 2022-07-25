package br.com.matuki.radiobrowser.shared.api;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lbr/com/matuki/radiobrowser/shared/api/RadioRepositoryImpl;", "Lbr/com/matuki/radiobrowser/shared/RadioRepository;", "serviceApi", "Lbr/com/matuki/radiobrowser/shared/api/RadioBrowserApi;", "(Lbr/com/matuki/radiobrowser/shared/api/RadioBrowserApi;)V", "getRadioStation", "Lbr/com/matuki/radiobrowser/shared/model/RadioStation;", "stationId", "", "listRadioStations", "", "shared_debug"})
public final class RadioRepositoryImpl implements br.com.matuki.radiobrowser.shared.RadioRepository {
    private final br.com.matuki.radiobrowser.shared.api.RadioBrowserApi serviceApi = null;
    
    @javax.inject.Inject()
    public RadioRepositoryImpl(@org.jetbrains.annotations.NotNull()
    br.com.matuki.radiobrowser.shared.api.RadioBrowserApi serviceApi) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.util.List<br.com.matuki.radiobrowser.shared.model.RadioStation> listRadioStations() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public br.com.matuki.radiobrowser.shared.model.RadioStation getRadioStation(@org.jetbrains.annotations.NotNull()
    java.lang.String stationId) {
        return null;
    }
}