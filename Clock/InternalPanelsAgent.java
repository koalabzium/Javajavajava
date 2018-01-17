package Clock;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class InternalPanelsAgent extends Thread {

    static class InternalCall{
            private final int toFloor;
            InternalCall(int toFloor){
                this.toFloor = toFloor;
            }

            public InternalCall() {
                toFloor = 0;
            }
        }

        InternalPanelsAgent(ElevatorCar elevatorCar){
            this.elevatorCar = elevatorCar;
        }

        BlockingQueue<InternalCall> input = new ArrayBlockingQueue<>(100);
        ElevatorCar elevatorCar;

        public void run(){
            for(;;){
                InternalCall ic = null;
                try {
                    ic = input.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(ic.toFloor==elevatorCar.getFloor())continue;
                if(ic.toFloor<elevatorCar.getFloor() && elevatorCar.tour== ElevatorCar.Tour.DOWN) {
                    ElevatorStops.get().setLiftStopDown(ic.toFloor);
                }if(ic.toFloor<elevatorCar.getFloor() && elevatorCar.tour== ElevatorCar.Tour.UP){
                    ElevatorStops.get().whileMovingDownShouldStopAt(ic.toFloor);
                }
                if(ic.toFloor>elevatorCar.getFloor() && elevatorCar.tour== ElevatorCar.Tour.UP) {
                    ElevatorStops.get().setLiftStopUp(ic.toFloor);
                }if(ic.toFloor>elevatorCar.getFloor() && elevatorCar.tour== ElevatorCar.Tour.DOWN){
                    ElevatorStops.get().whileMovingDownShouldStopAt(ic.toFloor);
                }
                // w zależności od aktualnego piętra, na którym jest winda,
                // umieść przystanek w odpowiedniej tablicy ''EleveatorStops''
            }
        }

    }

