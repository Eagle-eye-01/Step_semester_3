package oop_basics.class_problems;

public class HostelRoom {
    private String roomNo;
    private int beds;
    private int occupied;

    public HostelRoom(String roomNo, int beds, int occupied) {
        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = occupied;
    }

    public HostelRoom(String roomNo, int beds) {
        this(roomNo, beds, 0);
    }

    public boolean allot(String name) {
        if (occupied < beds) {
            occupied++;
            return true;
        }
        return false;
    }

    public static HostelRoom findAvailableRoom(HostelRoom[] rooms) {
        if (rooms == null) return null;
        for (HostelRoom room : rooms) {
            if (room != null && room.occupied < room.beds) {
                return room;
            }
        }
        return null;
    }

    /*
     * Explanation: Passing the HostelRoom array into safeAllot does NOT copy the rooms themselves.
     * In Java, object variables store references (memory addresses) to objects on the heap.
     * Passing the array passes a copy of the reference to the array, and each array element holds
     * a reference to the shared HostelRoom object. Mutating an element directly modifies the underlying object.
     */
    public static void safeAllot(HostelRoom[] rooms, String studentName) {
        HostelRoom available = findAvailableRoom(rooms);
        if (available != null) {
            available.allot(studentName);
            System.out.println(studentName + " allotted to room " + available.getRoomNo());
        } else {
            System.out.println("No rooms available for " + studentName);
        }
    }

    public String getRoomNo() {
        return roomNo;
    }

    public int getBeds() {
        return beds;
    }

    public int getOccupied() {
        return occupied;
    }
}
