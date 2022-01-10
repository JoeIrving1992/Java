
/**
 * Write a description of class Controller here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Controller
{
   private ArrayList<Model> modelList;

    public Controller(){
        modelList = new ArrayList<>();
        getData();
    }
    public void showMenu() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Loan item system for local library");
        char c = 'n';
        while(c!= 113 || c!= 81 ){
            System.out.println("A. Add Item into library\nB. Delete Item\n" +
                    "C. Update Item\nD. Give on loan\nE. Display All\n" +
                    "S. Sort Data (By Date)\nT. Sort Data (By ID)\nQ. quit");
            c = scanner.next().charAt(0);
            if(c == 'A' || c == 'a'){
                addItemToLibrary();
            }else if(c == 'B' || c == 'b'){
                deleteItem();
            }else if(c == 'C' || c == 'c'){
                updateItem();
            }else if(c == 'D' || c == 'd'){
                giveOnLoan();
            }else if (c == 'E' || c == 'e'){
                displayAll();
            }
            else if(c == 'S' || c == 's') {
                sortData();
                storeData(modelList);
                System.out.println("Data Sorted");
                displayAll();
            }
            else if(c == 'T' || c == 't'){
                sortDataByID();
                storeData(modelList);
                System.out.println("Data Sorted");
                displayAll();
            }
            else if(c == 'Q' || c == 'q'){
                storeData(modelList);
                System.exit(0);
            }

        }
        storeData(modelList);
    }
    private void sortData(){
        modelList.sort(Comparator.comparing(Model::getDate));
        modelList.sort((o1,o2)->o2.getDate().compareTo(o1.getDate()));
    }
    private void sortDataByID(){
        modelList.sort(Comparator.comparingInt(Model::getLibraryId));
    }
    public void displayAll(){
        for (Model model:
             modelList) {
            System.out.println(model.toString());
        }
    }
    private void giveOnLoan() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Item ID:");
        int choice = scanner.nextInt();
        for (Model model:
                modelList) {
            if (model.getLibraryId() == choice){
                System.out.println(model.toString());
                if(model.isFlag()){
                    System.out.println("Item Already on Loan");
                    return;
                }
                System.out.println("1. Give this on Loan 2. Cancel");
                if(scanner.nextInt() == 1){
                    System.out.println("Enter Return Date Format(YYMMDD)");
                    String date = scanner.next();
                    model.setDate(date);
                    model.setFlag(true);
                    storeData(modelList);
                }
                break;
            }
        }
    }
    private void updateItem() throws IOException {
        deleteItem();
        addItemToLibrary();
    }
    private void deleteItem() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Item ID:");
        int choice = scanner.nextInt();
        for (Model model:
             modelList) {
            if (model.getLibraryId() == choice){
                System.out.println(model.toString());
                modelList.remove(model);
                break;
            }
        }
        storeData(modelList);
    }
    private void addItemToLibrary() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose Item Type:");
        System.out.println("1. Book 2. CD 3. Magazine 4. DVD");
        int choice = scanner.nextInt();
        if(choice == 1){
            System.out.print("Enter Title of the Book:");
            String title = scanner.next();
            System.out.print("Enter Author of the Book:");
            String author = scanner.next();
            Model id = new Model();
            if(modelList.size() >= 1)
                id = modelList.get(modelList.size()-1);
            modelList.add(new Model(id.getLibraryId()+1,title, author,"Book",false,""));
            storeData(modelList);
        }else if(choice == 2){
            System.out.print("Enter Title of the CD:");
            String title = scanner.next();
            System.out.print("Enter Artist of the CD:");
            String author = scanner.next();
            Model id = new Model();
            if(modelList.size() >= 1)
                id = modelList.get(modelList.size()-1);
            modelList.add(new Model(id.getLibraryId()+1,title, author,"CD",false,""));
            storeData(modelList);
        }else if(choice == 3){
            System.out.print("Enter Title of the Magazine:");
            String title = scanner.next();
            System.out.print("Enter Publisher of the Magazine:");
            String author = scanner.next();
            Model id = new Model();
            if(modelList.size() >= 1)
                id = modelList.get(modelList.size()-1);
            modelList.add(new Model(id.getLibraryId()+1, title,author,"Magazine",false,""));
            storeData(modelList);
        }else if(choice == 4){
            System.out.print("Enter Title of the DVD:");
            String title = scanner.next();
            System.out.print("Enter Publisher of the DVD:");
            String author = scanner.next();
            Model id = new Model();
            if(modelList.size() >= 1)
                id = modelList.get(modelList.size()-1);
            modelList.add(new Model(id.getLibraryId()+1, title,author,"DVD",false,""));
            storeData(modelList);
        }else{
            System.out.println("Invalid Choice!");
        }
    }

    private void storeData(ArrayList<Model> models) throws IOException {
        FileWriter fileWriter = new FileWriter("database.txt");
        for (Model model:
             models) {
            fileWriter.write(model.toString());
        }
        fileWriter.close();
    }
    private void getData(){
        try{
            FileReader fileReader = new FileReader("database.txt");
            Scanner scanner = new Scanner(fileReader);
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                String[] lineArray = line.split(",");
                if(lineArray[4].equalsIgnoreCase("no")) {
                    modelList.add(new Model(Integer.parseInt(lineArray[0].substring(5)
                    ), lineArray[1], lineArray[2],lineArray[3], false, ""));
                }else{
                    modelList.add(new Model(Integer.parseInt(lineArray[0].substring(5)
                    ), lineArray[1], lineArray[2],lineArray[3], true, lineArray[5]));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Model> getModelList() {
        return modelList;
    }

    public void setModelList(ArrayList<Model> modelList) {
        this.modelList = modelList;
    }
}