package net.cathienova.havenalchemy.events;

public enum AcidDeathMessages {
    M1("was corroded by acid!"),
    M2("dissolved into nothingness from the acid!"),
    M3("succumbed to the corrosive power of acid!"),
    M4("melted away in a pool of acid!"),
    M5("was eaten away by acid!"),
    M6("disintegrated due to acid exposure!"),
    M7("became one with the acid!"),
    M8("suffered a fatal acid bath!"),
    M9("lost the battle against acid corrosion!"),
    M10("became acidic waste!"),
    M11("disappeared into acid oblivion!"),
    M12("met a corrosive end!");

    private final String message;

    AcidDeathMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
