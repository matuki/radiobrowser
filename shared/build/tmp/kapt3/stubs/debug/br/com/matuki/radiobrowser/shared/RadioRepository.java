package br.com.matuki.radiobrowser.shared;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007H&\u00a8\u0006\b"}, d2 = {"Lbr/com/matuki/radiobrowser/shared/RadioRepository;", "", "getRadioStation", "Lbr/com/matuki/radiobrowser/shared/model/RadioStation;", "stationId", "", "listRadioStations", "", "shared_debug"})
public abstract interface RadioRepository {
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.List<br.com.matuki.radiobrowser.shared.model.RadioStation> listRadioStations();
    
    @org.jetbrains.annotations.NotNull()
    public abstract br.com.matuki.radiobrowser.shared.model.RadioStation getRadioStation(@org.jetbrains.annotations.NotNull()
    java.lang.String stationId);
}