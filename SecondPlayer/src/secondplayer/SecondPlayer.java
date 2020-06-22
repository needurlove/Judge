package secondplayer;

import java.io.*;
import java.util.Random;

/**
 *
 * @author mingleee
 */
public class SecondPlayer {
    String ai1 = "AI1:";
    String ai2 = "AI2:";   
    String gameover = "Game over";
    Shots shots = new Shots();
    int fieldSize = 10;
    int x,y;
    int q = 1;
    Random random;
    boolean end = false;
    BufferedReader br;
    String allCells = "AI2: \n";
    File game = new File("C:\\Users\\mingleee\\Desktop\\battles\\game.txt");
    File field = new File("C:\\Users\\mingleee\\Desktop\\battles\\Field2.txt");
    Ships myships = new Ships(10);
    SecondPlayer() throws IOException, FileNotFoundException, InterruptedException {
        if(!field.exists()){
            field.createNewFile();
        }
        PrintWriter pw = new PrintWriter(field);
        
        int count = 0;
        for (int i = 0; i < 10; i++) {            
            if (count == 19) {
                for(int k = 0; k < myships.ships.get(i).cells.size(); k++) {
                      allCells += "[" + myships.ships.get(i).cells.get(k).getX() + ", " +  myships.ships.get(i).cells.get(k).getY() + "].  \n";
                    }
            } else {
                for(int k = 0; k < myships.ships.get(i).cells.size(); k++) {
                      allCells += "[" + myships.ships.get(i).cells.get(k).getX() + ", " +  myships.ships.get(i).cells.get(k).getY() + "],  \n";
                      count++;
                    }
                }
            }
        pw.append(allCells);
        pw.close();
        
        if(!game.exists()) {
            gaming(game);
        }
    }
    
    void status() throws FileNotFoundException, IOException, InterruptedException {
        Thread.sleep(20);
        String line;
        BufferedReader br = new BufferedReader(new FileReader(game));
        while((line = br.readLine()) != null) {
            if(line.equals("+")) {
                shots.add(x, y, true, false);
                System.out.println("hit");
            } else if(line.equals("-")) {
                shots.add(x, y, true, true);
                System.out.println("miss");
            } else 
                status();
        }
    }
    
    void gaming(File game) throws FileNotFoundException, IOException, InterruptedException {
        if(!game.exists()) {
            Thread.sleep(1000);
            gaming(game);            
        } else if(end == true) {
            System.out.println(gameover);
        } else {
            String line;
            BufferedReader br = new BufferedReader(new FileReader(game));
            while((line = br.readLine()) != null) {
                Thread.sleep(10);
                if(line.equals(gameover)) {
                    end = true;
                } else if(line.equals(ai2)) {
                    random = new Random();
                    do {
                        x = random.nextInt(fieldSize);
                        y = random.nextInt(fieldSize);
                    } while(shots.hitSamePlace(x, y));
                    String coord = "[" + x + ", " + y + "]";
                    System.out.println(q + ". " + coord);
                    q++;
                    FileWriter fw = new FileWriter(game);
                    fw.write(coord);
                    fw.flush();
                    fw.close();
                    status();
                }           
            }
        br.close();
        Thread.sleep(10);
        gaming(game);
        }
    }
    
    public static void main(String[] args) throws IOException, FileNotFoundException, InterruptedException {
        new SecondPlayer();
    }
    
}
