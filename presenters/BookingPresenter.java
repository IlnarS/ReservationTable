package ru.geekbrains.oop.lesson5.presenters;

import java.util.Date;

public class BookingPresenter implements ViewObserver {

    private Model model;
    private  View view;

    public BookingPresenter(Model model, View view){
        this.model = model;
        this.view = view;
        view.registerObserver(this);
    }

    public void updateTablesView(){
        view.showTables(model.loadTables());
    }

    private void updateReservationTableView(int reservationNo){
        view.showReservationTableResult(reservationNo);
    }

    private void updateChangeReservationTableView(int oldReservation){
        view.showChangeReservationTableResult(oldReservation);
    }

    @Override
    public void onReservationTable(Date orderDate, int tableNo, String name) {
        try {
            int reservationNo = model.reservationTable(orderDate, tableNo, name);
            updateReservationTableView(reservationNo);
        }
        catch (Exception e){
            updateReservationTableView(-1);
        }

    }

    public void changeReservationTable(Date reservationDate, int tableNo, String name){
        try {
            int oldReservation = model.reservationTable(reservationDate, tableNo, name);
            updateChangeReservationTableView(oldReservation);
        } catch (Exception e) {
            updateChangeReservationTableView(-1);
        }
    }

    @Override
    public void onChangeReservationTable(int oldReservation, Date orderDate, int tableNo, String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onChangeReservationTable'");
    }

}
