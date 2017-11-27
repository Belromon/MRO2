import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class One extends JFrame {
    String imageName = new String("A2.png");
    String[] arrImage ={"A1.png", "A2.png", "A3.png", "A4.png", "A5.png"};
    File file = new File(imageName);
    BufferedImage image = ImageIO.read(file);
    int endPoints;
    boolean symmetry;
    int zond1;
    int zond2;
    int zond3;
    int zond4;
    int jMi;
    int jMa;
    int jMin = 30;
    int jMax = 0;
    int iMin = 30;
    int iMax = 0;

    String[][] rData;
    public void process() throws IOException{
        int endP = 0;
        boolean sym;
        for (int i = 0; i < arrImage.length; i++) {
            File file = new File(arrImage[i]);
            BufferedImage image = ImageIO.read(file);
           endP = FendPoints(image);
            FcreateZond(image);
           sym = FVSymmetry(image);
            FcheckZond(image);
            createR();



        }
    }


    public One() throws IOException {
        endPoints();
        createZond();
        draw();
        VSymmetry();
        checkZond();
        createR();
    }


    public int FendPoints(BufferedImage image) throws IOException {
        int count;
        int count2 = 0;
        for (int j = 0; j < image.getHeight(); j++) {
            for (int i = 0; i < image.getWidth(); i++) {
                count = 0;
                if (image.getRGB(i, j) != -1) {
                    for (int k = j-1; k<=j+1; k++) {
                        for (int l = i - 1; l <= i+1 ; l++) {
                            if(image.getRGB(l,k) == -16777216)
                                count++;
                        }
                    }
                    if(count == 2)
                        count2++;

                }
            }
        }
        System.out.println("End points: " + count2);
        return count2;
    }
    public void FcreateZond(BufferedImage image) throws IOException{
        for(int i = 0; i < image.getHeight(); ++i){
            for(int j = 0; j < image.getWidth();++j){
                if(i == j){
                    image.setRGB(i, j, 65280);
                }
            }
        }

        FcreateAnotherZond(image);
        FcreateThirdZond(image);
    }
    public void FcheckZond(BufferedImage image) throws IOException {
        int count1 = 0;
        int local  = 0;
        int local1 = 0;
        int local3 = 0;
        int local2 = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;

        int width = image.getWidth();
        int height = image.getHeight();

        File f2 = new File(imageName);
        BufferedImage originalImage = ImageIO.read(f2);

        for (int i = 0; i < height ; i++) {
            for (int j = 0; j < width; j++) {
                if (originalImage.getRGB(i, j) == -16777216) {
                    if(iMin > j)
                        iMin = j;

                    if(iMax < j)
                        iMax = j;

                }
            }
        }

        for (int i = 0; i < height ; i++) {
            for (int j = 0; j < width; j++) {
                if (originalImage.getRGB(i, j) == -16777216) {
                    if(jMin > i)
                        jMin = i;

                    if(jMax < i)
                        jMax = i;

                }
            }
        }

        jMi = jMin;
        jMa = jMax;
        for (int i = 0; i < originalImage.getHeight(); i++) {
            if(originalImage.getRGB(i,i) == -16777216)
                local++;
            if(originalImage.getRGB(i,i) == -1){
                if(local > 0){
                    count1++;
                    local = 0;
                }
            }
        }

        for (int i = 0; i < originalImage.getWidth(); i++) {
            if(originalImage.getRGB(i,iMax-(iMax-iMin)/3) == -16777216)
                local1++;
            if(originalImage.getRGB(i,iMax-(iMax-iMin)/3) == -1){
                if(local1 > 0){
                    count2++;
                    local1 = 0;
                }
            }


        }
        for (int i = 0; i < originalImage.getWidth(); i++) {
            if(originalImage.getRGB(i,iMin+(iMax-iMin)/4) == -16777216)
                local3++;
            if(originalImage.getRGB(i,iMin+(iMax-iMin)/4) == -1){
                if(local3 > 0){
                    count4++;
                    local3 = 0;
                }
            }


        }

        for (int i = 0; i < originalImage.getHeight(); i++) {
            if(originalImage.getRGB(jMin+(jMax-jMin)/4,i) == -16777216)
                local2++;
            if(originalImage.getRGB(jMin+(jMax-jMin)/4,i) == -1){
                if(local2 > 0){
                    count3++;
                    local2 = 0;
                }
            }
        }
        zond1=count2;
        zond2=count1;
        zond3=count3;
        zond4 = count4;
        System.out.println("Peresechenie d diagonal : " + count1 + " snizy: " +count2 + " vertical: " + count3 + "top" + count4);
    }
    public void FcreateAnotherZond(BufferedImage image) throws IOException{
        for (int i = 0; i < image.getWidth(); i++){
            image.setRGB(i,20,-255);
            image.setRGB(i,10,65535);

        }
    }
    public void FcreateThirdZond(BufferedImage image){
        for (int i = 0; i < image.getHeight() ; i++) {
            image.setRGB(13, i, 16711680);
        }

    }
    public boolean FVSymmetry(BufferedImage image) throws IOException{

        int count1 = 0;
        int count2 = 0;
        int width = image.getWidth();
        int height = image.getHeight();
        int jMin = 30;
        int jMax = 0;
        int mid ;

        for (int i = 0; i < height ; i++) {
            for (int j = 0; j < width; j++) {
                if (image.getRGB(i, j) == -16777216) {
                    if(jMin > i)
                        jMin = i;

                    if(jMax < i)
                        jMax = i;

                }
            }
        }
        mid = (jMax + jMin)/2;

        for (int i = jMin; i < mid; i++) {
            for (int j = 0; j < height; j++) {
                if(image.getRGB(i,j) == -16777216)
                    count1++;
                //System.out.println("First I:" +i );
            }
        }

        for (int i = mid; i < jMax; i++) {
            for (int j = 0; j < height; j++) {
                if (image.getRGB(i,j) == -16777216)
                    count2++;

            }
        }

        if(7> Math.abs(count2-count1)) {
            System.out.println("Symmetry true");
            return true;
        }
        else {
            System.out.println("Symmetry false");
            return false;
        }
    }



    public void endPoints() throws IOException {
        int count;
        int count2 = 0;
        for (int j = 0; j < image.getHeight(); j++) {
            for (int i = 0; i < image.getWidth(); i++) {
                count = 0;
                if (image.getRGB(i, j) != -1) {
                    for (int k = j-1; k<=j+1; k++) {
                        for (int l = i - 1; l <= i+1 ; l++) {
                            if(image.getRGB(l,k) == -16777216)
                                count++;
                        }
                    }
                    if(count == 2)
                        count2++;

                }
            }
        }
        System.out.println("End points: " + count2);
        endPoints=count2;
    }
    public void createZond() throws IOException{
        for(int i = 0; i < image.getHeight(); ++i){
            for(int j = 0; j < image.getWidth();++j){
                if(i == j){
                    image.setRGB(i, j, 65280);
                }
            }
        }

        createAnotherZond();
        createThirdZond();
    }
    public void checkZond() throws IOException {
        int count1 = 0;
        int local  = 0;
        int local1 = 0;
        int local3 = 0;
        int local2 = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;

        int width = image.getWidth();
        int height = image.getHeight();

        File f2 = new File(imageName);
        BufferedImage originalImage = ImageIO.read(f2);

        for (int i = 0; i < height ; i++) {
            for (int j = 0; j < width; j++) {
                if (originalImage.getRGB(i, j) == -16777216) {
                    if(iMin > j)
                        iMin = j;

                    if(iMax < j)
                        iMax = j;

                }
            }
        }

        for (int i = 0; i < height ; i++) {
            for (int j = 0; j < width; j++) {
                if (originalImage.getRGB(i, j) == -16777216) {
                    if(jMin > i)
                        jMin = i;

                    if(jMax < i)
                        jMax = i;

                }
            }
        }

        jMi = jMin;
        jMa = jMax;
        for (int i = 0; i < originalImage.getHeight(); i++) {
            if(originalImage.getRGB(i,i) == -16777216)
                local++;
            if(originalImage.getRGB(i,i) == -1){
                if(local > 0){
                    count1++;
                    local = 0;
                }
            }
        }

        for (int i = 0; i < originalImage.getWidth(); i++) {
            if(originalImage.getRGB(i,iMax-(iMax-iMin)/3) == -16777216)
                local1++;
            if(originalImage.getRGB(i,iMax-(iMax-iMin)/3) == -1){
                if(local1 > 0){
                    count2++;
                    local1 = 0;
                }
            }


        }
        for (int i = 0; i < originalImage.getWidth(); i++) {
            if(originalImage.getRGB(i,iMin+(iMax-iMin)/4) == -16777216)
                local3++;
            if(originalImage.getRGB(i,iMin+(iMax-iMin)/4) == -1){
                if(local3 > 0){
                    count4++;
                    local3 = 0;
                }
            }


        }

        for (int i = 0; i < originalImage.getHeight(); i++) {
            if(originalImage.getRGB(jMin+(jMax-jMin)/4,i) == -16777216)
                local2++;
            if(originalImage.getRGB(jMin+(jMax-jMin)/4,i) == -1){
                if(local2 > 0){
                    count3++;
                    local2 = 0;
                }
            }
        }
        zond1=count2;
        zond2=count1;
        zond3=count3;
        zond4 = count4;
        System.out.println("Peresechenie d diagonal : " + count1 + " snizy: " +count2 + " vertical: " + count3 + "top" + count4);
    }
    public void createAnotherZond() throws IOException{
        for (int i = 0; i < image.getWidth(); i++){
            image.setRGB(i,20,-255);
            image.setRGB(i,10,65535);

        }
    }
    public void createThirdZond(){
        for (int i = 0; i < image.getHeight() ; i++) {
            image.setRGB(13, i, 16711680);
        }

    }
    public void VSymmetry() throws IOException{
        File file = new File(imageName);
        BufferedImage image = ImageIO.read(file);
        int count1 = 0;
        int count2 = 0;
        int width = image.getWidth();
        int height = image.getHeight();
        int jMin = 30;
        int jMax = 0;
        int mid ;

        for (int i = 0; i < height ; i++) {
            for (int j = 0; j < width; j++) {
                if (image.getRGB(i, j) == -16777216) {
                    if(jMin > i)
                        jMin = i;

                    if(jMax < i)
                        jMax = i;

                }
            }
        }
        mid = (jMax + jMin)/2;

        for (int i = jMin; i < mid; i++) {
            for (int j = 0; j < height; j++) {
                if(image.getRGB(i,j) == -16777216)
                    count1++;
                //System.out.println("First I:" +i );
            }
        }

        for (int i = mid; i < jMax; i++) {
            for (int j = 0; j < height; j++) {
                if (image.getRGB(i,j) == -16777216)
                    count2++;

            }
        }

        if(7> Math.abs(count2-count1)) {
            System.out.println("Symmetry true");
            symmetry=true;
        }
        else {
            System.out.println("Symmetry false");
            symmetry=false;
        }
    }

    public String[][] data = {
            {"1-Alef", "4", "true", "1", "2", "2","2",""},
            {"2-Bet", "3", "false", "3", "1", "2","1",""},
            {"3-Gimel", "3", "false", "2", "2", "2","1",""},
            {"4-Dalet", "3", "false", "2", "1", "1","1",""},
            {"5-Het", "2", "true", "2", "2", "1","2",""},
            {"6-Vav", "2", "true", "1", "1", "1","1",""},
            {"7-Lamed", "1", "true", "3", "1", "1","1",""},
            {"8-Samex", "0", "true", "2", "2", "2","2",""},
            {"9-Resg", "2", "false", "2", "1", "1","1",""},
            {"10-Shin", "3", "true", "3", "2", "2","3",""}
    };
    public int R[] = new int[10];

    //﻿Корень из( (признак картинки - признак 1) в квадрате  + (тоже  самое с другим признаком) в квадрате и т. д.)
    public void createR(){
        int one = 0 , two = 0, three = 0, thour = 0, five = 0, six = 0;
        double[] R = new double[10];
        //narko code starts here
        int sym;
        if(symmetry == true)
            sym = 1;
        else
            sym = 0;

        for (int i = 0; i <10 ; i++) {
            one = Integer.parseInt(data[i][1]) - endPoints;
            if(data[i][2] == "true"){
                two = 1 - sym;
            }

            three = Integer.parseInt(data[i][3]) - zond1;
            thour = Integer.parseInt(data[i][4]) - zond2;
            five = Integer.parseInt(data[i][5]) - zond3;
            six = Integer.parseInt(data[i][6]) - zond4;
            R[i] = Math.sqrt(Math.pow(one,2) + Math.pow(two,2) + Math.pow(three,2) + Math.pow(thour,2) + Math.pow(five,2) + Math.pow(six,2));
        }


        for (int i = 0; i < 10; i++) {
            data[i][7] = String.valueOf(R[i]);

        }
    }

    public void draw() {
        //Image scaled = image.getScaledInstance(image.getWidth()*10, image.getHeight()*10,Image.SCALE_SMOOTH);
        //Icon icon = new ImageIcon(scaled);
        //JLabel label1 = new JLabel(icon);
        JFrame frame = new JFrame("Lab 1");
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
       // frame.add(label1);
        frame.show();

        JFrame frame1 = new JFrame("Test frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] columnNames = {
                "Class",
                "End points",
                "Symmetry",
                "Diagonal Zond",
                "Bottom Zond",
                "Vertical Zond",
                "Top zond",
                "R"

        };
        String s = new String("q");


        JTable table = new JTable(data, columnNames);

        JScrollPane scrollPane = new JScrollPane(table);

        frame1.getContentPane().add(scrollPane);
        frame1.setPreferredSize(new Dimension(450, 400));
        frame1.pack();
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);

    }

}
