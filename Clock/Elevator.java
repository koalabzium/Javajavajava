package Clock;

import static java.lang.Thread.sleep;

public class Elevator {
    static ElevatorCar car = new ElevatorCar();
    static ExternalPanelsAgent externalPanelAgent = new ExternalPanelsAgent(car);
    static InternalPanelsAgent internalPanelAgent = new InternalPanelsAgent(car);

    // symulacja przywołania windy z panelu zewnętrznego
    static void makeExternalCall(int atFloor,boolean directionUp){
        try {
            externalPanelAgent.input.put(new ExternalPanelsAgent.ExternalCall(atFloor,directionUp));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // symulacja wyboru pietra w panelu wewnętrznym
    static void makeInternalCall(int toFloor){
        try {
            internalPanelAgent.input.put(new InternalPanelsAgent.InternalCall(toFloor));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // uruchomienie wątków
    static void init(){
        car.start();
        externalPanelAgent.start();
        internalPanelAgent.start();
    }

    public static void main(String[] args) throws InterruptedException {
        init();
        opcja5();

    }

    public static void opcja1() throws InterruptedException {
        Elevator.makeExternalCall(5,true);
        sleep(1000);
        Elevator.makeInternalCall(6);
        Elevator.makeExternalCall(3,true);
    }

    public static void opcja2() throws InterruptedException {
        Elevator.makeInternalCall(3);
        Elevator.makeInternalCall(8);
        Elevator.makeInternalCall(6);
    }

    public static void opcja3() throws InterruptedException {
        Elevator.makeExternalCall(3,true);
        Elevator.makeExternalCall(5,false);
        Thread.currentThread().sleep(6000);

        Elevator.makeInternalCall(6);
        sleep(1000);
        Elevator.makeInternalCall(2);
    }

    public static void opcja4() throws InterruptedException {
        Elevator.makeExternalCall(8, false);
        Elevator.makeInternalCall(1);
        Elevator.makeExternalCall(9, false);
        Elevator.makeExternalCall(2, false);
    }

    public static void opcja5() throws InterruptedException {
        Elevator.makeInternalCall(2);
        Elevator.makeInternalCall(8);
        sleep(9000);
        Elevator.makeExternalCall(6,false);
        Elevator.makeInternalCall(4);
    }
}
