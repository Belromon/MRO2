import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Lab2 {

    String[] arrImage ={"A1.png", "A2.png", "A3.png", "A4.png", "A5.png",
                        "B1.png", "B2.png", "B3.png", "B4.png", "B5.png",
                        "C1.png", "C2.png", "C3.png", "C4.png", "C5.png",
                        "E1.png", "E2.png", "E3.png", "E4.png", "E5.png",
                        "F1.png", "F2.png", "F3.png", "F4.png", "F5.png",
                        "G1.png", "G2.png", "G3.png", "G4.png", "G5.png",
                        "H1.png", "H2.png", "H3.png", "H4.png", "H5.png",
                        "K1.png", "K2.png", "K3.png", "K4.png", "K5.png",
                        "L1.png", "L2.png", "L3.png", "L4.png", "L5.png",
                        "M1.png", "M2.png", "M3.png", "M4.png", "M5.png",
                        "test.png"
    };
    String test = new String("test.png");
    String [] testArray = new String[7];
    int m = 51, k = 7;
    String[][] data = new String[m][k];
    String[][] secondTable = new String[m][k];
    String[][] therdTable = new String[11][8];
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


    public Lab2() throws IOException {
        createFirstTable();
        //output();
        drawTable();
        createSecondTable();
        createTherdTable();
        processTest();
    }

    public void processTest() throws IOException{
        int endPoints = -1;
        int symmetry = -1;
        File file = new File(test);
        BufferedImage image = ImageIO.read(file);
        endPoints = endPoints(image);
        symmetry = VSymmetry(image);
        testArray[0] = test;
        testArray[1] = String.valueOf(endPoints);
        testArray[2] = String.valueOf(symmetry);
        testArray[3] = String.valueOf(checkZond1(image));
        testArray[4] = String.valueOf(checkZond2(image));
        testArray[5] = String.valueOf(checkZond3(image));
        testArray[6] = String.valueOf(checkZond4(image));

    }
    public void drawTable(){
        String[] columnNames = {
                "Class",
                "End points",
                "Symmetry",
                "Diagonal Zond",
                "Bottom Zond",
                "Vertical Zond",
                "Top zond"
        };
        String[] columnNames1 = {
                "Symmetry",
                "Diagonal Zond",
                "Bottom Zond",
                "Vertical Zond",
                "Top zond"
        };
        String[] columnNames2 = {
                "Class",
                "End points",
                "Symmetry",
                "Diagonal Zond",
                "Bottom Zond",
                "Vertical Zond",
                "Top zond",
                "R"
        };
        JFrame frame1 = new JFrame("Firts");
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        frame1.getContentPane().add(scrollPane);
        frame1.setPreferredSize(new Dimension(450, 400));
        frame1.pack();
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);

        JFrame frame2 = new JFrame("Second");
        JTable table1 = new JTable(secondTable, columnNames);
        JScrollPane scrollPane1 = new JScrollPane(table1);
        frame2.getContentPane().add(scrollPane1);
        frame2.setPreferredSize(new Dimension(450, 400));
        frame2.pack();
        frame2.setLocationRelativeTo(null);
        frame2.setVisible(true);

        JFrame frame3 = new JFrame("Third");
        JTable table2 = new JTable(therdTable, columnNames2);
        JScrollPane scrollPane2 = new JScrollPane(table2);
        frame3.getContentPane().add(scrollPane2);
        frame3.setPreferredSize(new Dimension(450, 400));
        frame3.pack();
        frame3.setLocationRelativeTo(null);
        frame3.setVisible(true);
    }
    public void createFirstTable() throws IOException{
        int endPoints = -1;
        int symmetry = -1;
        for (int i = 0; i < arrImage.length; i++) {
            File file = new File(arrImage[i]);
            BufferedImage image = ImageIO.read(file);
            endPoints = endPoints(image);
            symmetry = VSymmetry(image);
            data[i][0] = arrImage[i];
            data[i][1] = String.valueOf(endPoints);
            data[i][2] = String.valueOf(symmetry);
            data[i][3] = String.valueOf(checkZond1(image));
            data[i][4] = String.valueOf(checkZond2(image));
            data[i][5] = String.valueOf(checkZond3(image));
            data[i][6] = String.valueOf(checkZond4(image));
        }
    }
    public void createSecondTable() {
        int n = 0, l = 51;
        double prMin = 5, prMax = -1, pr;


        for (int e = 1; e < 7; e++) {

            for (int j = n; j < l; j++) {
                if (new Integer(data[j][1]) < prMin)
                    prMin = new Integer(data[j][e]);

                if (prMax < new Integer(data[j][e]))
                    prMax = new Integer(data[j][e]);
            }

            for (int i = 0; i < l; i++) {
                pr = new Integer(data[i][e]);


                secondTable[i][0] = data[i][0];
                secondTable[i][e] = String.valueOf((pr - prMin) / (prMax - prMin));

            }
        }
    }
    public void createTherdTable(){
        int count=0; int n = 0, l = 5;
        double res =0, exit;
        for (int w = 1; w < 7; w++) {


        for (int i = 0; i < 10; i++) {

            for (int j = n; j < l; j++) {
                res += new Double(secondTable[j][w]);
            }

            exit = res / 5;
            therdTable[i][w] = String.valueOf(exit);

            n += 5;
            l += 5;
            therdTable[i][0] = secondTable[i*5][0];
            res = 0;
        }
        therdTable[10][w-1]=secondTable[50][w-1];

        n=0; l=5;
        }
        //Pasxalochka
        therdTable[10][6]=secondTable[50][6];
        countR();
    }
    public  void countR(){
        double R=0, min;
        for (int i = 0; i < 10; i++) {
            therdTable[i][7] =String.valueOf( Math.sqrt(Math.pow((Double.valueOf( therdTable[10][1])-Double.valueOf(therdTable[i][1])), 2) +
                    Math.pow((Double.valueOf( therdTable[10][2])-Double.valueOf(therdTable[i][2])), 2) +
                    Math.pow((Double.valueOf( therdTable[10][3])-Double.valueOf(therdTable[i][3])), 2) +
                    Math.pow((Double.valueOf( therdTable[10][4])-Double.valueOf(therdTable[i][4])), 2) +
                    Math.pow((Double.valueOf( therdTable[10][5])-Double.valueOf(therdTable[i][5])), 2) +
                    Math.pow((Double.valueOf( therdTable[10][6])-Double.valueOf(therdTable[i][6])), 2)));
        }

        for (int i = 0; i < 10; i++) {
            min = Double.valueOf(therdTable[0][7]);
            if( min > Double.valueOf(therdTable[i][7])){
                char arr[] = new char[1];
                therdTable[i][0].getChars(0,1,arr,0);
                therdTable[10][7] = String.valueOf(arr,0,1);


            }

        }
        }





    public int endPoints(BufferedImage image) throws IOException {
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
      //  System.out.println("End points: " + count2);
        return count2;
    }
    public int VSymmetry(BufferedImage image) throws IOException{

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
           // System.out.println("Symmetry true");
            return 1;
        }
        else {
           // System.out.println("Symmetry false");
            return 0;
        }
    }
    public void checkZond(BufferedImage image) throws IOException {
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



        for (int i = 0; i < height ; i++) {
            for (int j = 0; j < width; j++) {
                if (image.getRGB(i, j) == -16777216) {
                    if(iMin > j)
                        iMin = j;

                    if(iMax < j)
                        iMax = j;

                }
            }
        }

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

        jMi = jMin;
        jMa = jMax;
        for (int i = 0; i < image.getHeight(); i++) {
            if(image.getRGB(i,i) == -16777216)
                local++;
            if(image.getRGB(i,i) == -1){
                if(local > 0){
                    count1++;
                    local = 0;
                }
            }
        }

        for (int i = 0; i < image.getWidth(); i++) {
            if(image.getRGB(i,iMax-(iMax-iMin)/3) == -16777216)
                local1++;
            if(image.getRGB(i,iMax-(iMax-iMin)/3) == -1){
                if(local1 > 0){
                    count2++;
                    local1 = 0;
                }
            }


        }
        for (int i = 0; i < image.getWidth(); i++) {
            if(image.getRGB(i,iMin+(iMax-iMin)/4) == -16777216)
                local3++;
            if(image.getRGB(i,iMin+(iMax-iMin)/4) == -1){
                if(local3 > 0){
                    count4++;
                    local3 = 0;
                }
            }


        }

        for (int i = 0; i < image.getHeight(); i++) {
            if(image.getRGB(jMin+(jMax-jMin)/4,i) == -16777216)
                local2++;
            if(image.getRGB(jMin+(jMax-jMin)/4,i) == -1){
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
       // System.out.println("Peresechenie d diagonal : " + count1 + " snizy: " +count2 + " vertical: " + count3 + "top" + count4);
    }
    public int checkZond1(BufferedImage image) throws IOException {
        checkZond(image);
        return zond1;
    }
    public int checkZond2(BufferedImage image) throws IOException {
        checkZond(image);
        return zond2;
    }
    public int checkZond3(BufferedImage image) throws IOException {
        checkZond(image);
        return zond3;
    }
    public int checkZond4(BufferedImage image) throws IOException {
        checkZond(image);
        return zond4;
    }




    public void output(){
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < k; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
    }

}
