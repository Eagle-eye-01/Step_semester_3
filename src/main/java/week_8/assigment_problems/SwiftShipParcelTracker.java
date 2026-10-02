package week_8.assigment_problems;

import java.util.*;

public class SwiftShipParcelTracker {

    public enum ParcelStatus {
        BOOKED, PICKED_UP, IN_TRANSIT, OUT_FOR_DELIVERY, DELIVERED, CANCELLED
    }

    public interface ShippingType {
        double calculateCharge(double weightKg);
        String getTypeName();
    }

    public static class StandardShipping implements ShippingType {
        @Override
        public double calculateCharge(double weightKg) {
            return 40.0 + (10.0 * weightKg);
        }
        @Override
        public String getTypeName() { return "Standard"; }
    }

    public static class ExpressShipping implements ShippingType {
        @Override
        public double calculateCharge(double weightKg) {
            return 80.0 + (15.0 * weightKg);
        }
        @Override
        public String getTypeName() { return "Express"; }
    }

    public static class FragileShipping implements ShippingType {
        private final StandardShipping standard = new StandardShipping();
        @Override
        public double calculateCharge(double weightKg) {
            return standard.calculateCharge(weightKg) + 50.0;
        }
        @Override
        public String getTypeName() { return "Fragile"; }
    }

    public interface NotificationChannel {
        void notifyStatus(String parcelId, ParcelStatus status);
    }

    public static class SmsChannel implements NotificationChannel {
        @Override
        public void notifyStatus(String parcelId, ParcelStatus status) {
            System.out.println("[SMS] " + parcelId + " is now " + status + ".");
        }
    }

    public static class EmailChannel implements NotificationChannel {
        @Override
        public void notifyStatus(String parcelId, ParcelStatus status) {
            System.out.println("[Email] " + parcelId + " is now " + status + ".");
        }
    }

    public static class Parcel {
        private final String parcelId;
        private final double weightKg;
        private final ShippingType shippingType;
        private ParcelStatus status;
        private final List<NotificationChannel> channels = new ArrayList<>();

        public Parcel(String parcelId, double weightKg, ShippingType shippingType) {
            this.parcelId = parcelId;
            this.weightKg = weightKg;
            this.shippingType = shippingType;
            this.status = ParcelStatus.BOOKED;
        }

        public void subscribe(NotificationChannel channel) {
            channels.add(channel);
        }

        public void notifySubscribers() {
            for (NotificationChannel ch : channels) {
                ch.notifyStatus(parcelId, status);
            }
        }

        public boolean advanceTo(ParcelStatus targetStatus) {
            if (this.status == ParcelStatus.CANCELLED) {
                System.out.println("Invalid transition: Parcel is already CANCELLED.");
                return false;
            }
            if (isValidTransition(this.status, targetStatus)) {
                this.status = targetStatus;
                notifySubscribers();
                return true;
            } else {
                System.out.println("Invalid transition: " + this.status + " → " + targetStatus + " is not allowed.");
                return false;
            }
        }

        private boolean isValidTransition(ParcelStatus current, ParcelStatus next) {
            if (current == ParcelStatus.BOOKED && next == ParcelStatus.PICKED_UP) return true;
            if (current == ParcelStatus.PICKED_UP && next == ParcelStatus.IN_TRANSIT) return true;
            if (current == ParcelStatus.IN_TRANSIT && next == ParcelStatus.OUT_FOR_DELIVERY) return true;
            if (current == ParcelStatus.OUT_FOR_DELIVERY && next == ParcelStatus.DELIVERED) return true;
            return false;
        }

        public boolean cancel() {
            if (status == ParcelStatus.BOOKED) {
                status = ParcelStatus.CANCELLED;
                System.out.println("Parcel " + parcelId + " cancelled successfully.");
                notifySubscribers();
                return true;
            } else {
                System.out.println("Cancellation failed: " + parcelId + " can be cancelled only while BOOKED.");
                return false;
            }
        }

        public double getCharge() {
            return shippingType.calculateCharge(weightKg);
        }

        public String getParcelId() { return parcelId; }
        public ParcelStatus getStatus() { return status; }
        public ShippingType getShippingType() { return shippingType; }
    }

    public static void main(String[] args) {
        Parcel p = new Parcel("P101", 2.0, new ExpressShipping());
        System.out.printf(Locale.US, "Parcel %s booked (%s, %.0f kg). Charge: ₹%.2f. ",
                p.getParcelId(), p.getShippingType().getTypeName(), 2.0, p.getCharge());
        p.subscribe(new SmsChannel());
        p.subscribe(new EmailChannel());
        p.notifySubscribers();

        p.advanceTo(ParcelStatus.PICKED_UP);
        p.cancel();
        p.advanceTo(ParcelStatus.IN_TRANSIT);
        p.advanceTo(ParcelStatus.DELIVERED);
    }
}
