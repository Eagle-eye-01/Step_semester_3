package week_7.assigment_problems;

public class Warrior extends GameCharacter implements Attackable, Defendable {
    private final String name;

    public Warrior(String name) {
        super();
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("name cannot be blank");
        }
        this.name = name;
    }

    @Override
    public String attack() {
        return name + " strikes with a blade";
    }

    @Override
    public String attack(String weaponName) {
        char first = Character.toUpperCase(weaponName.charAt(0));
        String article = (first == 'A' || first == 'E' || first == 'I' || first == 'O' || first == 'U') ? "an " : "a ";
        return name + " strikes with " + article + weaponName;
    }

    @Override
    public String defend() {
        return name + " raises a shield";
    }

    @Override
    public String getSpecialMove() {
        return name + " unleashes Whirlwind Slash";
    }

    public String getName() {
        return name;
    }
}
