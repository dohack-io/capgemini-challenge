package io.dohack.challenge.domain;

public enum CommuteType {
    WALKING(0),
    BIKE(0),
    CAR(143),
    CAR_POOL(100), // TODO wer prüfen
    BUS(80),
    TRAM(55),
    PLANE(214),
    E_BIKE(20), // TODO wer prüfen
    E_SCOOTER(40), // TODO wer prüfen
    E_CAR(60), // TODO wer prüfen
    HYBRID_CAR(90);

    public final Integer value;

    private CommuteType(Integer value) {
        this.value = value;
    }
}
