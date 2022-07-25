package br.com.matuki.radiobrowser.shared.api;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bJ\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lbr/com/matuki/radiobrowser/shared/api/RadioBrowserApi;", "", "()V", "radioStations", "", "Lbr/com/matuki/radiobrowser/shared/model/RadioStation;", "getRadioStation", "stationId", "", "listRadioStations", "shared_debug"})
public final class RadioBrowserApi {
    private java.util.List<br.com.matuki.radiobrowser.shared.model.RadioStation> radioStations;
    
    @javax.inject.Inject()
    public RadioBrowserApi() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<br.com.matuki.radiobrowser.shared.model.RadioStation> listRadioStations() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final br.com.matuki.radiobrowser.shared.model.RadioStation getRadioStation(@org.jetbrains.annotations.NotNull()
    java.lang.String stationId) {
        return null;
    }
}