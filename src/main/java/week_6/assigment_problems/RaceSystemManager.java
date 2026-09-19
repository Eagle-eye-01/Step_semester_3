package week_6.assigment_problems;

public class RaceSystemManager {

    public static String registerBatch(String[] bibNumbers, double entryFee) {
        int registered = 0;
        int rejected = 0;

        for (String bib : bibNumbers) {
            try {
                new RaceEntry(bib, entryFee);
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }
        return "Registered: " + registered + " | Rejected: " + rejected;
    }

    public static String classifyGeneration(RaceEntry entry) {
        if (entry instanceof EliteRunnerEntry) {
            return "Multilevel descendant (3 generations deep)";
        } else if (entry instanceof RunnerEntry) {
            return "Direct descendant (2 generations deep)";
        } else if (entry instanceof RelayTeamEntry) {
            return "Hierarchical sibling (independent branch)";
        } else {
            return "Base class";
        }
    }

    public static double getTotalBalanceDue(RaceEntry[] entries) {
        double total = 0;
        for (RaceEntry entry : entries) {
            if (entry != null) {
                total += entry.getBalanceDue();
            }
        }
        return total;
    }

    public static String announceAll(RaceEntry[] entries) {
        StringBuilder sb = new StringBuilder();
        for (RaceEntry entry : entries) {
            if (entry == null) continue;
            
            sb.append(entry.announce());
            
            if (entry instanceof RelayTeamEntry) {
                RelayTeamEntry relay = (RelayTeamEntry) entry;
                sb.append(" [Team size via downcast: ").append(relay.getTeamSize()).append("]");
            }
            sb.append(" | ");
        }
        return sb.toString();
    }

    public static String settleNight(RaceEntry[] entries) {
        int processed = 0;
        int skipped = 0;
        int relay = 0;
        int individual = 0;

        for (RaceEntry entry : entries) {
            if (entry == null) {
                skipped++;
            } else {
                processed++;
                if (entry instanceof RelayTeamEntry) {
                    relay++;
                } else {
                    individual++;
                }
            }
        }
        return processed + " processed | " + skipped + " null skipped | " + relay + " relay | " + individual + " individual";
    }
}
