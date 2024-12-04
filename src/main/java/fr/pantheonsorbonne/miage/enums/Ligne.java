package fr.pantheonsorbonne.miage.enums;

public enum Ligne {
    ONE(13,1),
    TWO(12,2),
    THREE(11,3),
    FOUR(10,4),
    FIVE(9,5),
    SIX(8,6),
    SEVEN(7,7),
    EIGHT(6,8),
    NINE(5,9),
    TEN(4,10),
    ELEVEN(3,11),
    TWELVE(2,12),
    THIRTEEN(1,13),
    FOURTEEN(0,14);
    @SuppressWarnings("unused")
    private final int coordPlateau;
    private final int coordAlgebrique;

    Ligne (int i, int j) {
        this.coordPlateau=i;
        this.coordAlgebrique=j;
    }
}
