package week_7.class_problems;

public abstract class LibraryItem {
    private static int counter = 1000;
    private final String itemId;

    public LibraryItem() {
        this.itemId = "ITEM-" + (++counter);
    }

    public abstract int getLoanPeriodDays();

    public String getItemId() {
        return itemId;
    }

    public static void processCheckouts(LibraryItem[] items) {
        if (items == null) return;
        for (LibraryItem item : items) {
            if (item != null) {
                System.out.println(item.getLoanPeriodDays());
            }
        }
    }

    public static String reserveIfSupported(Object o) {
        if (o instanceof Reservable) {
            Reservable reservable = (Reservable) o;
            return reservable.reserve();
        }
        return "Reservation not supported";
    }
}
