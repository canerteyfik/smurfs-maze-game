//***************************************************************************************
//**************************************************************************************
//****************************METİN CLASSI*********************************************
//******************************************************************************************
//************************************************************************************
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Metin {

    static String dusman_1 = "";
    static String dusman_2 = "";
    static String dusman_1_kapi = "";
    static String dusman_2_kapi = "";

    public int[][] harita_dondur() {
        String harita = "";

        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader("harita.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Metin.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (scanner.hasNextLine()) {
            harita += scanner.nextLine();
            harita += "\n";
        }

        harita = harita.toLowerCase(Locale.ENGLISH);
        boolean sayim_bitti = false;
        //SATIR ADET HESABİ
        int satir_adet = 0;
        int sutun_adet = 0;

        for (int i = 0; i < harita.length(); i++) {
            if (harita.charAt(i) == '0') {
                for (int j = i; j < harita.length(); j++) {
                    if (harita.charAt(j) != '\n') {
                        sutun_adet++;
                    }
                    if (harita.charAt(j) == '\n') {
                        satir_adet++;
                    }
                }
                sayim_bitti = true;
            }
            if (sayim_bitti == true) {
                break;
            }
        }
        //SUTUNDA BOŞLUKLARI SAYDIĞINDAN
        sutun_adet = ((sutun_adet / 11) + 1) / 2;
        int k = 0;
        int m = 0;
        int[][] harita_matris = new int[satir_adet][sutun_adet];

        for (int i = 0; i < harita.length(); i++) {
            String sayi = "";
            sayi += harita.charAt(i);
            if (sayi.equals("1") || sayi.equals("0")) {
                harita_matris[k][m] = Integer.valueOf(sayi);
                m++;
                if (m == sutun_adet) {
                    m = 0;
                    k++;
                }
                if (k == satir_adet) {
                    break;
                }
            }
        }
        return harita_matris;
    }

    public String[][] dusman_dondur() {
        try {
            Scanner scanner = new Scanner(new FileReader("harita.txt"));
            int i = 0;
            String harita = "";
            String[] Array;
            while (scanner.hasNextLine()) {
                harita = scanner.nextLine();
                if (harita.charAt(0) != '0') {
                    if (i == 0) {
                        Array = harita.split(":");
                        dusman_1_kapi = Array[2];
                        Array = Array[1].split(",");
                        dusman_1 = Array[0];
                    } else if (i == 1) {
                        Array = harita.split(":");
                        dusman_2_kapi = Array[2];
                        Array = Array[1].split(",");
                        dusman_2 = Array[0];
                        break;
                    }
                }
                i++;

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Metin.class.getName()).log(Level.SEVERE, null, ex);
        }
        String dusman_dizi[][] = new String[2][2];
        dusman_dizi[0][0] = dusman_1;
        dusman_dizi[0][1] = dusman_1_kapi;
        dusman_dizi[1][0] = dusman_2;
        dusman_dizi[1][1] = dusman_2_kapi;
        return dusman_dizi;
    }
}
//***************************************************************************************
//**************************************************************************************
//****************************METİN CLASSI*********************************************
//******************************************************************************************
//************************************************************************************