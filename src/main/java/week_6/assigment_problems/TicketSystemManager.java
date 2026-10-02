package week_6.assigment_problems;

public class TicketSystemManager {

    public static String registerBatch(String[] attendeeIds, double basePrice) {
        int registered = 0;
        int rejected = 0;

        if (attendeeIds != null) {
            for (String id : attendeeIds) {
                try {
                    new EventTicket(id, basePrice);
                    registered++;
                } catch (IllegalArgumentException e) {
                    rejected++;
                }
            }
        }
        return "Registered: " + registered + " | Rejected: " + rejected;
    }

    public static String classifyGeneration(EventTicket ticket) {
        if (ticket instanceof PremiumWorkshopTicket) {
            return "Multilevel descendant (3 generations deep)";
        } else if (ticket instanceof HackathonTicket) {
            return "Hierarchical sibling (independent branch)";
        } else if (ticket instanceof WorkshopTicket) {
            return "Direct descendant (2 generations deep)";
        } else {
            return "Base class";
        }
    }

    public static double getTotalBalanceDue(EventTicket[] tickets) {
        double total = 0.0;
        if (tickets != null) {
            for (EventTicket t : tickets) {
                if (t != null) {
                    total += t.getBalanceDue();
                }
            }
        }
        return total;
    }

    public static String batchPrint(EventTicket[] tickets) {
        StringBuilder sb = new StringBuilder();
        if (tickets != null) {
            for (EventTicket ticket : tickets) {
                if (ticket == null) continue;

                sb.append(ticket.printTicket());
                if (ticket instanceof WorkshopTicket) {
                    WorkshopTicket wt = (WorkshopTicket) ticket;
                    sb.append(" [Track via downcast: ").append(wt.getTrack()).append("]");
                }
                sb.append(" | ");
            }
        }
        return sb.toString();
    }

    public static String processNightlySettlement(EventTicket[] tickets) {
        int processed = 0;
        int nullSkipped = 0;
        int groupCount = 0;
        int individualCount = 0;

        if (tickets != null) {
            for (EventTicket ticket : tickets) {
                if (ticket == null) {
                    nullSkipped++;
                } else {
                    processed++;
                    if (ticket instanceof GroupTicket) {
                        groupCount++;
                    } else {
                        individualCount++;
                    }
                }
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " +
               groupCount + " group | " + individualCount + " individual";
    }
}
