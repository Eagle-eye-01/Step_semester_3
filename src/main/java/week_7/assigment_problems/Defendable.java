package week_7.assigment_problems;

public interface Defendable {
    String defend();

    static void resolveDefense(Defendable[] combatants) {
        if (combatants == null) return;
        for (Defendable combatant : combatants) {
            if (combatant != null) {
                System.out.println(combatant.defend());
            }
        }
    }
}
