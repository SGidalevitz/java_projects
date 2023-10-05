public class Car {
    //make car class with constructor with parameters int mileage, int fuel, int doors and set private ints to these values, also add a drive method to add 100 to mileage and subtract 5 from fuel.
    private int mileage;
    private int fuel;
    private int doors;
    public Car(int mileage, int fuel, int doors){
        this.mileage = mileage;
        this.fuel = fuel;
        this.doors = doors;
    }
    public void drive(){
        this.mileage += 100;
        this.fuel -= 5;
    }
    public int getMileage(){
        return this.mileage;
    }
    public int getFuel(){
        return this.fuel;
    }
    public int getDoors(){
        return this.doors;
    }
    public void setMileage(int mileage){
        this.mileage = mileage;
    }
    public void setFuel(int fuel){
        this.fuel = fuel;
    }
    public void setDoors(int doors){
        this.doors = doors;
    }
    public String toString(){
        return "Mileage: " + this.mileage + " Fuel: " + this.fuel + " Doors: " + this.doors;
    }
    
}
