package enis.backend.utils;

public enum OperationType {
    EDIT("Úprava"), ADD("Přidání"), DELETE("Odebrání");

    private String name;

    OperationType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
