package Ui;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import model.Doctor;
public class UIPatientMenu {

    public static void showPatientMenu(){
        int response=0;
        do{
            System.out.println("\n\n");
            System.out.println("Patient");
            System.out.println("Welcome: "+ UiMenu.patientLogged.getName());
            System.out.println("1. Book an appointment");
            System.out.println("2. My appointments");
            System.out.println("0. Logout");

            Scanner sc= new Scanner(System.in);
            response= Integer.parseInt(sc.nextLine());

            switch (response){
                case 1:
                    showBookAppointmentMenu();
                    break;
                case 2:
                    showPatientMyAppointments();
                    break;
                case 0:
                    UiMenu.showMenu();
                    break;
            }

        }while(response!=0);
    }

    private static void showBookAppointmentMenu(){
        int response=0;
        do {
            System.out.println("::Book an appointment");
            System.out.println(":: Select date: ");
            //Primer integer númeración de la lista de fechas
            //Segundo integer: Indice de la fecha seleccionada
            Map <Integer,Map<Integer,Doctor>> doctors=new TreeMap<>();
            int k=0;
            for (int i = 0; i < UiDoctorMenu.doctorsAvailableAppointments.size(); i++) {
                ArrayList<Doctor.AvailableAppointment> availableAppointments=UiDoctorMenu.doctorsAvailableAppointments.get(i).getAvailableAppointements();
                Map<Integer,Doctor> doctorAppointments= new TreeMap<>();
                for (int j = 0; j < availableAppointments.size(); j++) {
                    k++;
                    System.out.println(k+". "+availableAppointments.get(j).getDate());
                    doctorAppointments.put(j,UiDoctorMenu.doctorsAvailableAppointments.get(i));
                    doctors.put(k,doctorAppointments);
                    
                }
            }
            Scanner sc=new Scanner(System.in);
            int responseDateSelected= Integer.parseInt(sc.nextLine());
            Map<Integer,Doctor> doctorAvailableSelected=doctors.get(responseDateSelected);
            Integer indexDate=0;
            Doctor doctorSelected=new Doctor("","");
            for (Map.Entry<Integer,Doctor> doc:doctorAvailableSelected.entrySet()) {
                indexDate=doc.getKey();
                doctorSelected=doc.getValue();
            }
            System.out.println(doctorSelected.getName()+
                            ". Date"+doctorSelected.getAvailableAppointements().get(indexDate).getDate()+
                            ". Time"+doctorSelected.getAvailableAppointements().get(indexDate).getTime()
                    );
            System.out.println("Confirm your appointment: \n1. Yes \n2. Change Date");
            response= Integer.parseInt(sc.nextLine());
            if(response==1){
                UiMenu.patientLogged.addAppointmentDoctors(
                        doctorSelected,
                        doctorSelected.getAvailableAppointements().get(indexDate).getDate(null),
                        doctorSelected.getAvailableAppointements().get(indexDate).getTime()
                );
                showPatientMenu();
            }

        }while(response!=0);


    }

    private static  void showPatientMyAppointments(){
        int response=0;
        do {
            System.out.println(":: My appointments");
            if (UiMenu.patientLogged.getAppointmentDoctors().size()==0){
                System.out.println("Don´t have appointments");
                break;
            }
            for (int i = 0; i < UiMenu.patientLogged.getAppointmentDoctors().size(); i++) {
                int j=i+1;
                System.out.println(j+" ."+"Date: "+UiMenu.patientLogged.getAppointmentDoctors().get(i).getDate()+"  Time: "
                        +UiMenu.patientLogged.getAppointmentDoctors().get(i).getTime()+
                        "\nDoctor: "+UiMenu.patientLogged.getAppointmentDoctors().get(i).getDoctor().getName()

                );
            }
            System.out.println("0. Return");
        }while(response!=0);
    }

}
