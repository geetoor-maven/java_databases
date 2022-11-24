package id.co.indivara.crudproj;

import java.util.ArrayList;
import java.util.Scanner;


public class CRUDApp {

    public static void main(String[] args) {
        System.out.println("------ GEETOOR MAVEN DATABASES ------");
        System.out.println("============================================");

        Scanner input = new Scanner(System.in);
        Database db = null;


        try {
            db = new Database();
            System.out.println("----- WELCOME VLOG TO MY GAIS -----");
            System.out.println("=== MENU : ");
            System.out.println("1.) MELIHAT SEMUA DATA EMPLOYE");
            System.out.println("2.) MENCARI EMPLOYE BERDASARKAN NIP");
            System.out.println("3.) INPUT DATA EMPLOYE BARU");
            System.out.println("4.) UPDATE DATA EMPLOYE");
            System.out.println("5.) HAPUS DATA EMPLOYE");

            System.out.print("SILAHKAN PILIH GAISS : ");
            String pilihan = input.nextLine();

            if(pilihan.equals("1")){
                
                EmployeeDAO dao = new EmployeeDAO(db);
                ArrayList<Employee> allEmployee = dao.getAllEmployees();

                for(int a = 0; a < allEmployee.size(); a++ ){
                    Employee e = allEmployee.get(a);
                    System.out.println("NIP : " + e.getId());
                    System.out.println("NAME : " + e.getName());
                    System.out.println("EMAIL : " + e.getEmail());
                    System.out.println("JABATAN : " + e.getDesignation());
                    System.out.println("=========================");
                }
            }else if(pilihan.equals("2")){
                System.out.println("====== MENCARI DATA EMPLOYEE ======");
                System.out.print(" -- MASUKAN NIP EMPLOYE : ");
                String nip = input.nextLine();

                EmployeeDAO dao =  new EmployeeDAO(db);
                Employee findEmploye = dao.findEmploye(nip);
                
                if(findEmploye != null){
                    System.out.println("NIP : " + findEmploye.getId());
                    System.out.println("NAME : " + findEmploye.getName());
                    System.out.println("EMAIL : " + findEmploye.getEmail());
                    System.out.println("JABATAN : " + findEmploye.getDesignation());
                    System.out.println("=========================");
                }else{
                    System.out.println("SORRY..... EMPLOYEE NOT FOUND");
                }
            }else if(pilihan.equals("3")){
                System.out.println("====== CREATE DATA EMPLOYEE ======");

                System.out.print("NIP : ");
                String nip = input.nextLine();

                System.out.print("NAMA : ");
                String nama = input.nextLine();

                System.out.print("EMAIL : ");
                String email = input.nextLine();

                System.out.print("JABATAN : ");
                String jabatan = input.nextLine();

                System.out.print("CREATE AT : ");
                String create = input.nextLine();

                Employee employee = new Employee();
                employee.setId(nip);
                employee.setName(nama);
                employee.setEmail(email);
                employee.setDesignation(jabatan);
                employee.setCreated(create);

                EmployeeDAO dao = new EmployeeDAO(db);
                if(dao.createEmploye(employee)){
                    System.out.println("SUKSESSSSSSSS CREATE EMPLOYE....");
                }else{
                    System.out.println("FAILED CREATE EMPLOYEE....");
                }

            }else if(pilihan.equals("4")){
                System.out.println("====== UPDATE DATA EMPLOYEE ======");
                System.out.print(" -- MASUKAN NIP EMPLOYE YANG INGIN DI UPDATE : ");
                String nip = input.nextLine();

                EmployeeDAO dao =  new EmployeeDAO(db);
                Employee findEmploye = dao.findEmploye(nip);
                
                if(findEmploye != null){

                    System.out.print("NAMA : " + findEmploye.getName() + " : NEW DATA : ");
                    String nama = input.nextLine();

                    if(!nama.isEmpty()){
                        findEmploye.setName(nama);
                    }
                 

                    System.out.print("EMAIL : " + findEmploye.getEmail() + " : NEW DATA : ");
                    String email = input.nextLine();
                    if(!email.isEmpty()){
                        findEmploye.setEmail(email);
                    }
                  

                    System.out.print("JABATAN : " + findEmploye.getDesignation() + " : NEW DATA : ");
                    String jabatan = input.nextLine();
                    if(!jabatan.isEmpty()){
                        findEmploye.setDesignation(jabatan);
                    }
                 
                    if(dao.updateEmploye(findEmploye)){
                        
                        System.out.println("SUCCESSS UPDATED....");
                    }else{
                        System.out.println("FAILED....");
                    }

                }else{
                    System.out.println("NIP IS NOT FOUND");
                }
            }else if(pilihan.equals("5")){
                System.out.println("====== DELETE DATA EMPLOYEE ======");
                System.out.print("DATA YANG INGIN DI HAPUS : ");
                String nip = input.nextLine();
                EmployeeDAO dao = new EmployeeDAO(db);

                if(dao.deleteEmploye(nip)){
                    System.out.println("SUKSESS");
                }else{
                    System.out.println("FAILED");
                }
            }

        } catch (Exception e) {
           e.printStackTrace();
        }
    }
    
}
