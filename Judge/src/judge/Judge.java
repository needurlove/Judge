package judge;

import java.io.*;
import java.util.*;

/**
 *
 * @author mingleee
 */
public class Judge {
    int field_size = 10;
    Random random;
    int flag = 1;
    String history = "New game \n";
    File field1 = new File("C:\\Users\\mingleee\\Desktop\\battles\\Field1.txt");
    File field2 = new File("C:\\Users\\mingleee\\Desktop\\battles\\Field2.txt");
    File game = new File("C:\\Users\\mingleee\\Desktop\\battles\\game.txt");
    boolean r1 = false;
    boolean r2 = false;
            
    ArrayList<Cell> cellsAI1 = new ArrayList<Cell>();
    ArrayList<Cell> cellsAI2 = new ArrayList<Cell>();    
    
    Judge() throws IOException, FileNotFoundException, InterruptedException {
        System.out.println(history);
        checkFiles();
//            if(!field1.exists()) {
//                System.out.println("Первый игрок не предоставил свое поле");
//            } else {
//                System.out.println("Reading Field of the first player:");
//                br = new BufferedReader(new FileReader(field1));
//                while((line = br.readLine()) != null) {
//                    char data[] = line.toCharArray();
//                    if(data[0] == '[') {
//                        System.out.println(data[1] + ", " + data[4]);
//                        int a = Character.getNumericValue(data[1]);
//                        int b = Character.getNumericValue(data[4]);
//                        cellsAI1.add(new Cell(a, b));   
//                    } else {
//                        System.out.println(data);
//                    }
//                }
//                br.close();
//            }
//            
//            if(!field2.exists()) {
//                System.out.println("Второй игрок не предоставил свое поле");
//            } else {
//                System.out.println("Reading Field of the second player:");
//                br = new BufferedReader(new FileReader(field2));
//                while((line = br.readLine()) != null) {
//                    char data[] = line.toCharArray();
//                    if(data[0] == '[') {
//                        System.out.println(data[1] + ", " + data[4]);
//                        int a = Character.getNumericValue(data[1]);
//                        int b = Character.getNumericValue(data[4]);
//                        cellsAI2.add(new Cell(a, b));   
//                    } else {
//                        System.out.println(data);
//                    }
//                }
//                br.close();
//            }
//            
//            if(field1.delete() && field2.delete()) {
//                System.out.println("файлы с полями были удалены");
//                if(!game.exists()){
//                    game.createNewFile();
//                    System.out.println("game file was successfully created");
//                }
//            } else {
//                System.out.println("deleting files was unsuccessfully");
//            }
//            shot();
    }
    
    void checkFiles() throws FileNotFoundException, IOException, InterruptedException {
        BufferedReader br;
        String line;
        
        if(!field1.exists() && !r1) {
                System.out.println("Первый игрок не предоставил свое поле");
                Thread.sleep(1000);
            } else if(!r1){
                System.out.println("Reading Field of the first player:");
                br = new BufferedReader(new FileReader(field1));
                while((line = br.readLine()) != null) {
                    char data[] = line.toCharArray();
                    if(data[0] == '[') {
                        System.out.println(data[1] + ", " + data[4]);
                        int a = Character.getNumericValue(data[1]);
                        int b = Character.getNumericValue(data[4]);
                        cellsAI1.add(new Cell(a, b));   
                    } else {
                        System.out.println(data);
                    }
                }
                br.close();
                if(field1.delete()) {
                    System.out.println("Файл с координатами кораблей первого игрока был прочитан и удалён");
                    r1 = true;
                }
            }
            
            if(!field2.exists() && !r2) {
                System.out.println("Второй игрок не предоставил свое поле");
                Thread.sleep(1000);
            } else if(!r2){
                System.out.println("Reading Field of the second player:");
                br = new BufferedReader(new FileReader(field2));
                while((line = br.readLine()) != null) {
                    char data[] = line.toCharArray();
                    if(data[0] == '[') {
                        System.out.println(data[1] + ", " + data[4]);
                        int a = Character.getNumericValue(data[1]);
                        int b = Character.getNumericValue(data[4]);
                        cellsAI2.add(new Cell(a, b));   
                    } else {
                        System.out.println(data);
                    }
                }
                br.close();
                if(field2.delete()) {
                    System.out.println("Файл с координатами кораблей второго игрока был прочитан и удалён");
                    r2 = true;
                } else {
                    System.out.println("Файл не был удалён");
                }
            }
            
            if(r1 && r2) {
                if(!game.exists()){
                    game.createNewFile();
                    System.out.println("game file was successfully created");   
                }
            shot();
            } else {
                checkFiles();
            }
    }
    
    public void checkHit() throws FileNotFoundException, IOException, InterruptedException {
        BufferedReader br = new BufferedReader(new FileReader(game));
        String line;
            if(flag == 1) {
                Thread.sleep(20);
                while((line = br.readLine()) != null && flag != 3) {  
                char check[] = line.toCharArray();
                if(check[0] != '[') {
                    checkHit();
                } else {
                    int x = Character.getNumericValue(check[1]);
                    int y = Character.getNumericValue(check[4]);
                    if(!check(cellsAI2, x, y)) {
                        System.out.println("AI1 missed at " + x + " | " + y);
                        history += "AI1 missed at " + x + " | " + y + "\n";
                        flag = 2;
                        FileWriter fw = new FileWriter(game);
                        fw.write("-");
                        fw.flush();
                        fw.close();
                    } else {
                        System.out.println("AI1 hit at " + x + " | " + y);
                        history += "AI1 hit at " + x + " | " + y + "\n";
                        FileWriter fw = new FileWriter(game);
                        fw.write("+");
                        fw.flush();
                        fw.close();
                        if(!checkSurvivors(cellsAI2)) {
                            System.out.println("Game over \nAI1 WON");
                            history += "AI1 WON \n";
                            for(Cell cell : cellsAI1)
                                if(cell.isAlive()) {
                                    history += "x:" + cell.getX() + " y:" + cell.getY() + " = alive\n";
                                    System.out.println("x:" + cell.getX() + " y:" + cell.getY() + " = alive"); 
                                }
                            history += "Game over";
                            flag = 3;
                        }
                    }
                    Thread.sleep(30);
                    shot();
                    }
                }
            } else if(flag == 2) {
                Thread.sleep(20);
                while((line = br.readLine()) != null && flag != 3) {     
                char check[] = line.toCharArray();
                if(check[0] != '[') {
                    checkHit();
                } else {
                    int x = Character.getNumericValue(check[1]);
                    int y = Character.getNumericValue(check[4]);
                    if(!check(cellsAI1, x, y)) {
                        System.out.println("AI2 missed at " + x + " | " + y);
                        history += "AI2 missed at " + x + " | " + y + "\n";
                        flag = 1;
                        FileWriter fw = new FileWriter(game);
                        fw.write("-");
                        fw.flush();
                        fw.close();
                    } else {
                        System.out.println("AI2 hit at " + x + " | " + y);
                        history += "AI2 hit at " + x + " | " + y + "\n";
                        FileWriter fw = new FileWriter(game);
                        fw.write("+");
                        fw.flush();
                        fw.close();
                        if(!checkSurvivors(cellsAI1)) {
                            System.out.println("Game over \nAI2 WON");
                            history += "AI2 WON \n";
                            for(Cell cell : cellsAI2)
                                if(cell.isAlive()) {
                                    history += "x:" + cell.getX() + " y:" + cell.getY() + " = alive\n";
                                    System.out.println("x:" + cell.getX() + " y:" + cell.getY() + " = alive");
                                }
                            history += "Game over";
                            flag = 3;  
                        }
                    }
                    Thread.sleep(30);
                    shot(); 
                }
            }
        } else if(flag == 3) {
                FileWriter fw = new FileWriter(game);
                fw.write(history);
                fw.flush();
                fw.close();
            }       
    }
    
    boolean check(ArrayList<Cell> cells, int x, int y) {
        for(Cell cell : cells) {
            if(cell.checkHit(x, y)) {
                cell.alive = false;
                return true;
            }  
        }
        return false;
    }
    
    public boolean checkSurvivors(ArrayList<Cell> cells) {
        for(Cell cell : cells)
            if(cell.isAlive())
                return true;                 
            return false;          
    }
    
    public void shot() throws IOException, FileNotFoundException, InterruptedException {
        FileWriter fw = new FileWriter(game);
        if(flag == 1) {
            String ai1turn = "AI1:";
            fw.write(ai1turn);
            fw.flush();
            fw.close();
        } else {
            String ai2turn = "AI2:";
            fw.write(ai2turn);
            fw.flush();
            fw.close();
        }
        checkHit();     
    }
    
    
    public static void main(String[] args) throws IOException, FileNotFoundException, InterruptedException {
        new Judge();
    }
    
}
