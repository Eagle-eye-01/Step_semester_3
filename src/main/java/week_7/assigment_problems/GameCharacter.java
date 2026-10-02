package week_7.assigment_problems;

public abstract class GameCharacter {
    private static int counter = 1000;
    private final String characterId;

    public GameCharacter() {
        this.characterId = "CHAR-" + (++counter);
    }

    public abstract String getSpecialMove();

    public String getCharacterId() {
        return characterId;
    }
}
