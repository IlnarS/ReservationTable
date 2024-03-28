package ru.geekbrains.oop.lesson5.views;

import ru.geekbrains.oop.lesson5.models.Table;
import ru.geekbrains.oop.lesson5.presenters.View;
import ru.geekbrains.oop.lesson5.presenters.ViewObserver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class BookingView implements View {


    private Collection<ViewObserver> observers;
    

    public void showTables(Collection<Table> tables){
        for (Table table: tables) {
            System.out.println(table);
        }
    }

    @Override
    public void registerObserver(ViewObserver observer) {
        if (observers == null)
            observers = new ArrayList<>();
        observers.add(observer);
    }

    
    public void showReservationTableResult(int reservationNo, int oldReservation) {
        if (reservationNo > 0){
            System.out.printf("Столик успешно забронирован. Номер брони: #%d\n", reservationNo);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Желаете забронировать другой столик?\n y/n");
            String sc = scanner.nextLine();
            if (sc == "y") {
                System.out.printf("Другой столик успешно забронирован. Номер брони: #%d\n", oldReservation);
            }
            scanner.close();
        }
        else {
            System.out.println("Произошла ошибка при попытке забронировать столик.");
        }
    }


    public void reservationTable(Date orderDate, int tableNo, String name){
        if (observers != null)
        {
            for (ViewObserver observer : observers){
                observer.onReservationTable(orderDate, tableNo, name);
            }
        }
    }

    public void changeReservationTable(int oldReservation, Date reservationDate, int tableNo, String name){
        if (observers != null) {
            for (ViewObserver observer : observers){
                observer.onChangeReservationTable(tableNo, reservationDate, tableNo, name);
            }
        }
    }

    @Override
    public void showChangeReservationTableResult(int oldReservation) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showChangeReservationTableResult'");
    }

    @Override
    public void showReservationTableResult(int reservationNo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showReservationTableResult'");
    }

}
