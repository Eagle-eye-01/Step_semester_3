package week_7.class_problems;

public interface Playable {
    String play();
    String play(int fromSecond);
    String pause();

    static void launchAll(Playable[] items) {
        if (items == null) return;
        for (Playable item : items) {
            if (item != null) {
                System.out.println(item.play());
            }
        }
    }
}
