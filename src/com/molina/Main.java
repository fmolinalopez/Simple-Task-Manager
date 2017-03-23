package com.molina;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ArrayList<String> taskList = new ArrayList<String>();

        taskList.add("Echar sopa en el Halcón Milenario");
        taskList.add("Entrenar con Obi Wan");
        taskList.add("Quedar con Amidala");
        taskList.add("Darle una paliza a Jabba");

        int option;

        while ((option = showMenu(taskList)) != 0){
            switch (option){
                case 1:
                    showTasks(taskList);
                    System.out.println();
                    break;
                case 2:
                    addTask(taskList);
                    System.out.println();
                    break;
                case 3:
                    removeTask(taskList);
                    System.out.println();
                    break;
                case 4:
                    editTask(taskList);
                    break;
                case 5:
                    moveTask(taskList);
                    break;
                default:
            }
        }
    }

    public static int showMenu(ArrayList<String> taskList){
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> options = new ArrayList<Integer>();
        int option;

        System.out.println("***********************");
        System.out.println("* 1 - Ver tareas      *");
        options.add(1);
        System.out.println("* 2 - Añadir tareas   *");
        options.add(2);

        if (taskList.size() > 0){
            System.out.println("* 3 - Eliminar tareas *");
            options.add(3);
            System.out.println("* 4 - Editar tarea    *");
            options.add(4);
        }if (taskList.size() > 1){
            System.out.println("* 5 - Mover tarea     *");
            options.add(5);
        }
        System.out.println("* 0 - Salir           *");
        options.add(0);
        System.out.println("***********************");

        do {
            System.out.println("Opción: ");

            option = input.nextInt();
        }while (option < 0 || option >= options.size());

        return option;
    }

    public static void showTasks(ArrayList<String> taskList){
        int index = 0;
        for (String tasks: taskList){
            System.out.println((index++) + " - " + tasks);
        }
    }

    public static void addTask(ArrayList<String> taskList){
        Scanner input = new Scanner(System.in);
        String task;

        do {

            System.out.println("Introducir nueva tarea:");
            task = input.nextLine().trim().replaceAll("\\s+"," ");

        }while (task.length() == 0);

        taskList.add(task);


    }

    public static void removeTask(ArrayList<String> taskList){
        Scanner input = new Scanner(System.in);
        int index;

        showTasks(taskList);

        do {
            System.out.println("Introduzca el indice de la tarea a borrar;");
            index = input.nextInt();
        }while (!correctIndex(index,taskList));

        taskList.remove(index);

    }

    public static boolean correctIndex(int index, ArrayList<String> taskList){
        if (index >= 0 && index < taskList.size()){
            return true;
        }else {
            return false;
        }
    }

    public static void editTask(ArrayList<String> taskList){
        Scanner input = new Scanner(System.in);
        int option;
        String task;

        showTasks(taskList);

        do {
            System.out.println("Introduzca el indice de la tarea a editar");
            option = input.nextInt();
        }while (option >= taskList.size());

        input.nextLine(); //Para limpiar el scanner

        do {
            System.out.println("Introducir la nueva tarea:");
            task = input.nextLine().trim().replaceAll("\\s+"," ");
        }while (task.length() == 0 || taskList.contains( task ));

        taskList.set(option, task);
    }

    public static void moveTask(ArrayList<String> taskList) {
        Scanner input = new Scanner(System.in);
        int option;
        int moveTo;

        showTasks(taskList);

        do{
            System.out.println("Introduzca el indice de la tarea a mover");
            option = input.nextInt();
        }while (option < 0 && option >= taskList.size());

        do {
            System.out.println("Introduzca la posicion a la que desea mover la tarea");
            moveTo = input.nextInt();
        }while (moveTo < 0 || moveTo >= taskList.size() || moveTo == option);

        if (option > moveTo) {
            taskList.add(moveTo, taskList.get(option));
            taskList.remove(option + 1);

        }else {
            taskList.add(moveTo+1, taskList.get(option));
            taskList.remove(option);
        }
    }
}
