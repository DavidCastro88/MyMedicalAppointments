package Ui;

import model.Doctor;
import model.Patient;

import java.util.ArrayList;
import java.util.Scanner;

public class UiMenu {
    public static final String[] MONTHS={"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

    public static Doctor doctorLogged;
    public static Patient patientLogged;
    public static void showMenu(){
        System.out.println("Welcome to My Appointments");
        System.out.println("Selecciona la opción deseada");

        int response = 0;
        do {
            System.out.println("1. Doctor");
            System.out.println("2. Patient");
            System.out.println("0. Salir");

            Scanner sc = new Scanner(System.in);
            response = Integer.parseInt(sc.nextLine());

            switch (response) {
                case 1 -> {
                    response=0;
                    System.out.println("Doctor");
                    authUser(1);
                }
                case 2 -> {
                    System.out.println("Patient");
                    authUser(2);
                    response = 0;
                }
                case 0 -> System.out.println("Thank you for you visit");
                default -> System.out.println("Please select a correct answer");
            }
        }while (response != 0);
    }

    private static void authUser(int userType){
        //userType = 1 Doctor
        //userType = 2 Patient
        ArrayList<Doctor> doctors= new ArrayList<>();
        doctors.add(new Doctor("David Castro","devi@gmail.com"));
        doctors.add(new Doctor("Maria Gutierrez","mari@gmail.com"));
        doctors.add(new Doctor("Fernanado Sanchez","ferza@gmail.com"));

        ArrayList<Patient> patients= new ArrayList<>();
        patients.add(new Patient("Juan Zuluaga","juazu@gmail.com"));
        patients.add(new Patient("Marta Zuluaga","marzu@gmail.com"));
        patients.add(new Patient("Fredy Lozano","frelo@gmail.com"));

        boolean emailCorrect=false;
        do {
            System.out.println("Insert your Email");
            Scanner sc= new Scanner(System.in);
            String email=sc.nextLine();
            if(userType==1){
                for (Doctor d:doctors) {
                    if (d.getEmail().equals(email)) {
                        emailCorrect = true;
                        doctorLogged=d;
                        //Show Menu Doctor
                        UiDoctorMenu.showDoctorMenu();
                    }
                }
                if(!emailCorrect){
                    System.out.println("Correo invalido, por favor intente de nuevo");
                }
            } else if (userType==2) {
                for (Patient p:patients) {
                    if(p.getEmail().equals(email)){
                        emailCorrect=true;
                        patientLogged=p;
                        UIPatientMenu.showPatientMenu();
                    }
                }
                if(!emailCorrect){
                    System.out.println("Correo invalido, por favor intente de nuevo");
                }
            }

        }while(!emailCorrect);
    }

    static void showPatientMenu(){
        int response = 0;
        do {
            System.out.println("\n\n");
            System.out.println("model.Patient");
            System.out.println("1. Book an appointment");
            System.out.println("2. My appointments");
            System.out.println("0. Return");

            Scanner sc = new Scanner(System.in);
            response = Integer.parseInt(sc.nextLine());

            switch (response) {
                case 1 -> {
                    System.out.println("::Book an appointment");
                    for (int i = 1; i < 4 ; i++) {
                        System.out.println(i+". "+MONTHS[i]);
                    }
                }
                case 2 -> System.out.println("::My appointments");
                case 0 -> showMenu();
            }
        }while (response != 0);
    }
}
