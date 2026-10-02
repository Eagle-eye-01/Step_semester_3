package week_7.assigment_problems;

import java.util.concurrent.atomic.AtomicInteger;

public interface Exportable {
    AtomicInteger exportCounter = new AtomicInteger(0);

    String exportData();

    static void recordExport() {
        exportCounter.incrementAndGet();
    }

    static int getTotalExports() {
        return exportCounter.get();
    }

    static void exportAll(Exportable[] items) {
        if (items == null) return;
        for (Exportable item : items) {
            if (item != null) {
                System.out.println(item.exportData());
            }
        }
    }
}
