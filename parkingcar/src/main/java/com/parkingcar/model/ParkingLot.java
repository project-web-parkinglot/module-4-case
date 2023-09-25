//package model;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import java.time.LocalDate;
//
//@Entity
//public class ParkingLot {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    private LocalDate dueDate;
//    private int position;
//    private int baseLevel;
//    private int status;
//
//    public ParkingLot() {
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public LocalDate getDueDate() {
//        return dueDate;
//    }
//
//    public void setDueDate(LocalDate dueDate) {
//        this.dueDate = dueDate;
//    }
//
//    public int getPosition() {
//        return position;
//    }
//
//    public void setPosition(int position) {
//        this.position = position;
//    }
//
//    public int getBaseLevel() {
//        return baseLevel;
//    }
//
//    public void setBaseLevel(int baseLevel) {
//        this.baseLevel = baseLevel;
//    }
//
//    public int getStatus() {
//        return status;
//    }
//
//    public void setStatus(int status) {
//        this.status = status;
//    }
//}
