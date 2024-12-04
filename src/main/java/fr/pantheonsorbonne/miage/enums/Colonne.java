package fr.pantheonsorbonne.miage.enums;

public enum Colonne {
    A(0),
    B(1),
    C(2),
    D(3),
    E(4),
    F(5),
    G(6),
    H(7),
    I(8),
    J(9),
    K(10),
    L(11),
    M(12),
    N(13);

    @SuppressWarnings("unused")
    private final int code;

    Colonne (int i){
        this.code=i;
    }
}