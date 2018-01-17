package Clock;

public class ElevatorCar extends Thread{
        int floor=0;

        public int getFloor() {
            return floor;
        }

        enum Tour {UP, DOWN};
        Tour tour = Tour.UP;
        enum Movement {STOP,MOVING};
        Movement movementState = Movement.STOP;

    public void run(){
        while (true) {
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (this.movementState == Movement.STOP && this.tour == Tour.DOWN) {
                if (!ElevatorStops.get().hasStopBelow(this.floor)) {
                    this.tour = Tour.UP;
                } else {
                    this.movementState = Movement.MOVING;
                }
            } else if (this.movementState == Movement.STOP && this.tour == Tour.UP) {
                if (!ElevatorStops.get().hasStopAbove(this.floor)) {
                    this.tour = Tour.DOWN;
                } else {
                    this.movementState = Movement.MOVING;
                }
            } else if (this.movementState == Movement.MOVING && this.tour == Tour.DOWN) {
                if (this.floor > ElevatorStops.get().getMinSetFloor()) {
                    this.floor--;
                    System.out.println("Floor" + this.floor);
                } else {
                    this.movementState = Movement.STOP;
                    this.tour = Tour.UP;
                }

                if (ElevatorStops.get().whileMovingDownShouldStopAt(this.floor) || this.floor == ElevatorStops.get().getMinSetFloor()) {
                    this.movementState = Movement.STOP;
                    if (this.floor == ElevatorStops.get().getMinSetFloor()) {
                        ElevatorStops.get().clearStopUp(this.floor);
                    }
                    ElevatorStops.get().clearStopDown(this.floor);
                    System.out.println("STOP");

                }
            } else if (this.movementState == Movement.MOVING && this.tour == Tour.UP) {
                if (this.floor < ElevatorStops.get().getMaxSetFloor()) {
                    this.floor++;
                    System.out.println("Floor" + this.floor);
                } else {
                    this.movementState = Movement.STOP;
                    this.tour = Tour.DOWN;
                }

                if (ElevatorStops.get().whileMovingUpShouldStopAt(this.floor) || this.floor == ElevatorStops.get().getMaxSetFloor()) {
                    this.movementState = Movement.STOP;
                    if (this.floor == ElevatorStops.get().getMaxSetFloor()) {
                        ElevatorStops.get().clearStopDown(this.floor);
                    }
                    ElevatorStops.get().clearStopUp(this.floor);
                    System.out.println("STOP");
                }
            }
        }
    }

}
