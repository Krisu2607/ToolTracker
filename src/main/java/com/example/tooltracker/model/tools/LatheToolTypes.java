package com.example.tooltracker.model.tools;

public enum LatheToolTypes {

    ZGRUBNA("ZGRUBNA"),
    WYKANCZAJACA("WYKANCZAJACA"),
    GWINTY("GWINTY"),
    ROWKI("ROWKI"),
    ODCINANIE("ODCINANIE"),
    BRAK_DANYCH("BRAK DANYCH");  // Dodanie opcji "brak danych"

    private final String displayName;

    LatheToolTypes(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return this.displayName;
    }

    // Metoda do pobierania enum z wartości string, obsługująca null i nieprawidłowe wartości
    public static LatheToolTypes fromString(String type) {
        if (type == null || type.isEmpty()) {
            return BRAK_DANYCH;
        }
        try {
            return LatheToolTypes.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            return BRAK_DANYCH;
        }
    }
}
