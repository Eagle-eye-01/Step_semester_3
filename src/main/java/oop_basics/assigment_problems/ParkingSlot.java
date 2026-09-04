package oop_basics.assigment_problems;

class ParkingSlot {
    String slotNo;
    int capacity;
    int occupiedCount;

    public ParkingSlot(String slotNo, int capacity, int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    public void allot(String vehicleNo) {
        if (occupiedCount < capacity) {
            occupiedCount++;
            System.out.println(vehicleNo + " allotted to slot " + slotNo);
        }
    }

    public static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {
        for (ParkingSlot slot : slots) {
            if (slot.occupiedCount < slot.capacity) {
                return slot;
            }
        }
        return null;
    }

    public static void safeAllot(ParkingSlot[] slots, String vehicleNo) {
        ParkingSlot availableSlot = findAvailableSlot(slots);
        if (availableSlot != null) {
            availableSlot.allot(vehicleNo);
        } else {
            System.out.println("No slots available for " + vehicleNo);
        }
    }
    
    /*
     * Passing the ParkingSlot array into these methods does not copy the slots themselves because 
     * Java uses "pass by value" for references. The array holds references (memory addresses) to 
     * the actual ParkingSlot objects. We pass a copy of the reference to the array, which allows 
     * the methods to modify the exact same objects that were created in main().
     */

    public static void main(String[] args) {
        ParkingSlot[] slotsAvailable = {
                new ParkingSlot("A1", 4, 3),
                new ParkingSlot("A2", 5, 5)
        };
        System.out.println("Slots: A1 (3/4), A2 (5/5)");
        safeAllot(slotsAvailable, "TN09AB1234");

        ParkingSlot[] slotsFull = {
                new ParkingSlot("A1", 4, 4),
                new ParkingSlot("A2", 5, 5)
        };
        System.out.println("Slots: A1 (4/4), A2 (5/5)");
        safeAllot(slotsFull, "TN09AB1234");
    }
}
