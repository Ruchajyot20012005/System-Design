package com.systemdesign;

import com.systemdesign.model.Ticket;
import com.systemdesign.model.gate.EntryGate;
import com.systemdesign.model.gate.ExitGate;
import com.systemdesign.model.vehicle.Bike;
import com.systemdesign.model.vehicle.Car;
import com.systemdesign.model.vehicle.Truck;
import com.systemdesign.model.vehicle.Vehicle;
import com.systemdesign.strategy.paymentStrategy.CardPayment;
import com.systemdesign.strategy.paymentStrategy.CashPayment;
import com.systemdesign.strategy.paymentStrategy.PaymentStrategy;
import com.systemdesign.strategy.paymentStrategy.UPIPayment;

import java.util.Scanner;

public class ParkingLotOrchestrator {

    private final EntryGate entryGate;
    private final ExitGate exitGate;

    public ParkingLotOrchestrator(EntryGate entryGate, ExitGate exitGate) {
        this.entryGate = entryGate;
        this.exitGate = exitGate;
    }

    public void start() {

        System.out.println("\n-----------------------------------------------------------------");
        System.out.println("Welcome to Parking Lot!!!!");
        System.out.println("-----------------------------------------------------------------\n");

        Scanner sc = new Scanner(System.in);

        String vehicleType;

        while (true) {
            System.out.println("Select vehicle type - BIKE | CAR | TRUCK: ");
            vehicleType = sc.nextLine().toUpperCase();

            if(!"BIKE#CAR#TRUCK".contains(vehicleType)){
                System.out.println("Invalid vehicle type...Please try again");
                continue;
            }
            break;
        }

        System.out.println("Enter Vehicle Number: ");
        String vehicleNumber =  sc.nextLine().toUpperCase();

        Vehicle vehicle = createVehicle(vehicleType, vehicleNumber);

        Ticket ticket = entryGate.park(vehicle);

        System.out.println("\n------------------------------------");
        System.out.println("----- TICKET -----");
        System.out.println("Ticket ID: " + ticket.getTicketId());
        System.out.println("Vehicle: " + vehicle.getNumber());
        System.out.println("Spot: " + ticket.getSpot().getSpotId());
        System.out.println("Entry Time: " + ticket.getEntryTime());
        System.out.println("-----------------------------------------\n");

        System.out.println("Do you want to Exit? YES | NO");
        if(sc.nextLine().equalsIgnoreCase("YES")){

            double amount = exitGate.getAmount(ticket);

            System.out.println("Amount to be Paid: " + amount);

            String paymentMode;
            while(true){
                System.out.println("Select Payment Mode - UPI | CASH | CARD: ");
                paymentMode = sc.nextLine().toUpperCase();

                if(!"UPI#CASH#CARD".contains(paymentMode)){
                    System.out.println("Invalid Payment Mode...Please try again");
                    continue;
                }
                break;
            }

            PaymentStrategy strategy = getPayment(paymentMode);

            exitGate.makePayment(ticket, amount, strategy);

            System.out.println("Vehicle Exited Successfully!!");
        }

    }

    private Vehicle createVehicle(String vehicleType, String vehicleNumber) {
        return switch (vehicleType) {
            case "CAR" -> new Car(vehicleNumber);
            case "BIKE" -> new Bike(vehicleNumber);
            case "TRUCK" -> new Truck(vehicleNumber);
            default -> throw new RuntimeException("Invalid vehicle type");
        };
    }

    private PaymentStrategy getPayment(String mode) {
        return switch (mode) {
            case "UPI" -> new UPIPayment();
            case "CARD" -> new CardPayment();
            case "CASH" -> new CashPayment();
            default -> throw new RuntimeException("Invalid Payment Mode");
        };
    }
}
