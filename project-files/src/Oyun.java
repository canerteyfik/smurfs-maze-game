//***************************************************************************************
//**************************************************************************************
//****************************OYUN CLASSI*********************************************
//******************************************************************************************
//************************************************************************************

import java.awt.Color;
import java.awt.Graphics;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Oyun extends JLabel implements KeyListener, ActionListener {

    private static Metin metin = new Metin();
    private static String[][] dusmanlar = metin.dusman_dondur();
    private int[][] labirent = metin.harita_dondur();
    Puan puan = new Puan();

    private static int[][] yol_azman;
    private int aKapiX = 3;
    private int aKapiY = 0;
    private int bKapiX = 10;
    private int bKapiY = 0;
    private int cKapiX = 0;
    private int cKapiY = 5;
    private int dKapiX = 3;
    private int dKapiY = 10;
    private int cikisX = 12;
    private int cikisY = 7;
    private String azmanKapi;
    private String gargamelKapi;
    private Timer timer = new Timer(5, this);

    private boolean oyun_bittimi = false;
    private static Dusman dusman = new Dusman();
    private Oyuncu secilen_karakter;
    private Gargamel gargamel;
    private Azman azman;
    private int sirin_x = 360;
    private int sirin_y = 300;
    private int hareket_x = 60;
    private int hareket_y = 60;
    private BufferedImage oyuncu_image;
    private BufferedImage gargamel_image = null;
    private BufferedImage azman_image = null;
    private BufferedImage sirine=ImageIO.read(new FileImageInputStream(new File("sirine.png")));;
    private int gargamelSecilenX = Integer.MAX_VALUE;
    private int azmanSecilenX = Integer.MAX_VALUE;
    private int gargamelSecilenY = Integer.MAX_VALUE;
    private int azmanSecilenY = Integer.MAX_VALUE;
    private BufferedImage altin_resim = ImageIO.read(new FileImageInputStream(new File("coin.png")));
    private BufferedImage mantar_resim = ImageIO.read(new FileImageInputStream(new File("mantar.png")));
    long altinBaslangic = System.currentTimeMillis();
    long mantarBaslangic = System.currentTimeMillis();
    int atlanan_y = -1;
    int atlanan_x = -1;

    public Oyun() throws IOException {

        timer.start();
        Scanner scanner = new Scanner(System.in);
        String sayi = JOptionPane.showInputDialog(this, "Gozluklu İçin 1'i(Gözlüklü Şirin Hızı:2)\n"
                + "Tembel İçin 2'yi(Tembel Şirin Hızı:1)\n"
                + "Tuşlayınız...");
        Gozluklu gozluklu = new Gozluklu(20, 1234, "gozluklu", "kullanici", (sirin_x / 60), (sirin_y / 60), "gozluk.png", 2);
        Tembel tembel = new Tembel(20, 1235, "tembel", "kullanici", (sirin_x / 60), (sirin_y / 60), "tembel.png", 1);

        //--------------------------------------
        //KAPI BELIRLEME VE KAPI ATAMA ISLEMLERI
        //--------------------------------------
        if (dusmanlar[0][0].equals("Gargamel")) {
            gargamelKapi = dusmanlar[0][1];
            if (dusmanlar[0][1].equals("A")) {
                gargamelSecilenX = aKapiX;
                gargamelSecilenY = aKapiY;
            } else if (dusmanlar[0][1].equals("B")) {
                gargamelSecilenX = bKapiX;
                gargamelSecilenY = bKapiY;
            } else if (dusmanlar[0][1].equals("C")) {
                gargamelSecilenX = cKapiX;
                gargamelSecilenY = cKapiY;
            } else {
                gargamelSecilenX = dKapiX;
                gargamelSecilenY = dKapiY;
            }
        } else if (dusmanlar[0][0].equals("Azman")) {
            azmanKapi = dusmanlar[0][1];
            if (dusmanlar[0][1].equals("A")) {
                azmanSecilenX = aKapiX;
                azmanSecilenY = aKapiY;
            } else if (dusmanlar[0][1].equals("B")) {
                azmanSecilenX = bKapiX;
                azmanSecilenY = bKapiY;
            } else if (dusmanlar[0][1].equals("C")) {
                azmanSecilenX = cKapiX;
                azmanSecilenY = cKapiY;
            } else {
                azmanSecilenX = dKapiX;
                azmanSecilenY = dKapiY;
            }
        }
        if (dusmanlar[1][0] != null) {
            if (dusmanlar[1][0].equals("Gargamel")) {
                gargamelKapi = dusmanlar[1][1];
                if (dusmanlar[1][1].equals("A")) {
                    gargamelSecilenX = aKapiX;
                    gargamelSecilenY = aKapiY;
                } else if (dusmanlar[1][1].equals("B")) {
                    gargamelSecilenX = bKapiX;
                    gargamelSecilenY = bKapiY;
                } else if (dusmanlar[1][1].equals("C")) {
                    gargamelSecilenX = cKapiX;
                    gargamelSecilenY = cKapiY;
                } else {
                    gargamelSecilenX = dKapiX;
                    gargamelSecilenY = dKapiY;
                }
            } else if (dusmanlar[1][0].equals("Azman")) {
                azmanKapi = dusmanlar[1][1];
                if (dusmanlar[1][1].equals("A")) {
                    azmanSecilenX = aKapiX;
                    azmanSecilenY = aKapiY;
                } else if (dusmanlar[1][1].equals("B")) {
                    azmanSecilenX = bKapiX;
                    azmanSecilenY = bKapiY;
                } else if (dusmanlar[1][1].equals("C")) {
                    azmanSecilenX = cKapiX;
                    azmanSecilenY = cKapiY;
                } else {
                    azmanSecilenX = dKapiX;
                    azmanSecilenY = dKapiY;
                }
            }
        }
        Gargamel gargamel_olusum = new Gargamel(12345, "Gargamel", "Dusman", gargamelSecilenX, gargamelSecilenY, "gargamel.png", 2, gargamelKapi);
        Azman azman_olusum = new Azman(1456, "Azman", "Dusman", azmanSecilenX, azmanSecilenY, "azman.png", 1, azmanKapi);
        //----------------------------
        //RESIM ATAMA ISLEMLERI
        //---------------------------
        if (dusmanlar[0][0].equals("Gargamel")) {
            gargamel_image = ImageIO.read(new FileImageInputStream(new File(gargamel_olusum.getResim_yolu())));
        } else if (dusmanlar[0][0].equals("Azman")) {
            azman_image = ImageIO.read(new FileImageInputStream(new File(azman_olusum.getResim_yolu())));
        }

        if (dusmanlar[1][0].equals("Gargamel")) {
            gargamel_image = ImageIO.read(new FileImageInputStream(new File(gargamel_olusum.getResim_yolu())));
        } else if (dusmanlar[1][0].equals("Azman")) {
            azman_image = ImageIO.read(new FileImageInputStream(new File(azman_olusum.getResim_yolu())));
        }
        //-------------------------------
        //Dusman atamalari
        //-------------------------------
        azman = azman_olusum;
        gargamel = gargamel_olusum;
        //-----------------------
        //karakter secimi
        //----------------------
        int secim = Integer.parseInt(sayi);
        if (secim == 1) {
            secilen_karakter = gozluklu;
        } else if (secim == 2) {
            secilen_karakter = tembel;
        }

        if (secim == 1 || secim == 2) // secim yapildiginda sureyi baslatir.
        {
            altinBaslangic = System.currentTimeMillis();
            mantarBaslangic = System.currentTimeMillis();
        }

        secilen_karakter.setX((labirent[0].length / 2));
        secilen_karakter.setY((labirent.length / 2));
        oyuncu_image = ImageIO.read(new FileImageInputStream(new File(secilen_karakter.getResim_yolu())));

    }

    int kaldirilanAltin = 0;
    int kaldirilanMantar = 0;
    int altinSure = (int) (1 + Math.random() * 10);
    int mantarSure = (int) (1 + Math.random() * 20);

    ArrayList<Altin> altinlar = new ArrayList<Altin>();
    ArrayList<Mantar> mantarlar = new ArrayList<Mantar>();

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        
        for (int i = 0; i < labirent.length; i++) {
            for (int j = 0; j < labirent[0].length; j++) {
                Color color;
                if (labirent[i][j] == 0) {
                    color = Color.GRAY;
                } else if (labirent[i][j] == 1) {
                    color = Color.WHITE;
                } else {
                    color = Color.BLUE;
                }
                g.setColor(color);
                g.fillRect(60 * j, 60 * i, 60, 60);
                g.setColor(Color.BLACK);
                g.drawRect(60 * j, 60 * i, 60, 60);
            }
        }

        if (azman_image != null) {
            azman.yol_ciz(azman.getY() * 13 + azman.getX(), secilen_karakter.getY() * 13 + secilen_karakter.getX());

            for (int i = 0; i < azman.getTakipYolu().size(); i++) {
                g.setColor(Color.GREEN);
                int x = azman.getTakipYolu().get(i) % 13;
                int y = azman.getTakipYolu().get(i) / 13;
                g.fillRect(x * 60, y * 60, 60, 60);
                g.setColor(Color.BLACK);
                g.drawRect(x * 60, y * 60, 60, 60);
            }

        }
        if (gargamel_image != null) {

            gargamel.yol_ciz(gargamel.getY() * 13 + gargamel.getX(), secilen_karakter.getY() * 13 + secilen_karakter.getX());

            for (int i = 0; i < gargamel.getTakipYolu().size(); i++) {
                g.setColor(Color.blue);
                int x = gargamel.getTakipYolu().get(i) % 13;
                int y = gargamel.getTakipYolu().get(i) / 13;
                g.fillRect(x * 60, y * 60, 60, 60);
                g.setColor(Color.BLACK);
                g.drawRect(x * 60, y * 60, 60, 60);
            }

            long altinBitis = System.currentTimeMillis();
            double altinSayac = (double) (altinBitis - altinBaslangic) / 1000;
            long mantarBitis = System.currentTimeMillis();
            double mantarSayac = (double) (mantarBitis - mantarBaslangic) / 1000;

            if (altinSayac >= altinSure && altinSayac <= altinSure + 5) { // altinin aktif oldugu zaman araligi

                while (altinlar.size() < 5 && kaldirilanAltin == 0) // altinlari olusturma
                {
                    int random_y = (int) (Math.random() * 11);
                    int random_x = (int) (Math.random() * 13);

                    if (labirent[random_y][random_x] == 1 && varMi(altinlar, random_x, random_y) == false && varMi(altinlar, mantarlar, random_x, random_y) == false && (60 * random_x != sirin_x && 60 * random_y != sirin_y)) {
                        Altin altin = new Altin();
                        altin.setX(random_x);
                        altin.setY(random_y);
                        altinlar.add(altin);
                    }
                }

                if (altinlar.size() + kaldirilanAltin == 5) // altinlarin ekranda gösterilmesi
                {
                    for (int i = 0; i < altinlar.size(); i++) {
                        g.drawImage(altin_resim, altinlar.get(i).getX() * 60, altinlar.get(i).getY() * 60, 60, 60, this);
                    }
                }

                if (varMi(altinlar, sirin_x / 60, sirin_y / 60)) // oyuncunun altini yemesi
                {
                    int iptal = altinIptal(altinlar, sirin_x / 60, sirin_y / 60);
                    secilen_karakter.setPuan(secilen_karakter.getPuan() + altinlar.get(iptal).getPuan());
                    altinlar.remove(iptal);
                    kaldirilanAltin++;
                }

                if (varMi(altinlar, atlanan_x / 60, atlanan_y / 60)) // gozluklunun ustunden atladigi altini yemesi
                {
                    int iptal = altinIptal(altinlar, atlanan_x / 60, atlanan_y / 60);
                    secilen_karakter.setPuan(secilen_karakter.getPuan() + altinlar.get(iptal).getPuan());
                    altinlar.remove(iptal);
                    kaldirilanAltin++;
                    atlanan_y = -1;
                    atlanan_x = -1;
                }

            }

            if (altinSayac >= altinSure + 5) // altinlarin suresi dolduktan sonra ekrandan kaybolup yeni olusacagi zamanin belirlenmesi
            {
                kaldirilanAltin = 0;

                for (int i = 0; i < altinlar.size(); i++) {
                    altinlar.remove(0);
                }

                altinBaslangic = System.currentTimeMillis();

                altinSure = (int) (1 + Math.random() * 10);
                //System.out.println("\naltinSure: "+altinSure);
            }

            if (mantarSayac >= mantarSure && mantarSayac <= mantarSure + 7) { // mantarin aktif oldugu zaman araligi

                while (mantarlar.size() == 0 && kaldirilanMantar == 0) // mantar olusturma
                {
                    int random_y = (int) (Math.random() * 11);
                    int random_x = (int) (Math.random() * 13);

                    if (labirent[random_y][random_x] == 1 && varMi(altinlar, random_x, random_y) == false && (60 * random_x != sirin_x && 60 * random_y != sirin_y)) {
                        Mantar mantar = new Mantar();
                        mantar.setX(random_x);
                        mantar.setY(random_y);
                        mantarlar.add(mantar);
                    }
                }

                if (kaldirilanMantar == 0) // mantarin ekranda gosterilmesi
                {
                    g.drawImage(mantar_resim, mantarlar.get(0).getX() * 60, mantarlar.get(0).getY() * 60, 60, 60, this);
                }

                if (mantarlar.size() == 1) {
                    if (mantarlar.get(0).getY() == sirin_y / 60 && mantarlar.get(0).getX() == sirin_x / 60) // oyuncunun mantari yemesi
                    {
                        secilen_karakter.setPuan(secilen_karakter.getPuan() + mantarlar.get(0).getPuan());
                        mantarlar.remove(0);
                        kaldirilanMantar++;
                    }
                }

                if (mantarlar.size() == 1) {
                    if (mantarlar.get(0).getX() == atlanan_x / 60 && mantarlar.get(0).getY() == atlanan_y / 60) // gozluklunun ustunden atladigi mantari yemesi
                    {
                        secilen_karakter.setPuan(secilen_karakter.getPuan() + mantarlar.get(0).getPuan());
                        mantarlar.remove(0);
                        kaldirilanMantar++;
                        atlanan_y = -1;
                        atlanan_x = -1;
                    }
                }

            }

            if (mantarSayac >= mantarSure + 7) // mantarin suresi dolduktan sonra ekrandan kaybolmasi ve yeni olusacagi zamanin belirlenmesi
            {
                kaldirilanMantar = 0;

                if (mantarlar.size() == 1) {
                    mantarlar.remove(0);
                }

                mantarBaslangic = System.currentTimeMillis();

                mantarSure = (int) (1 + Math.random() * 20);
                //System.out.println("\naltinSure: "+altinSure);
            }

        }
        if (azman_image != null) {
            g.drawImage(azman_image, azman.getX() * 60, azman.getY() * 60, azman_image.getWidth() / 5, azman_image.getHeight() / 5, this);
        }
        if (gargamel != null) {
            g.drawImage(gargamel_image, gargamel.getX() * 60, gargamel.getY() * 60, gargamel_image.getWidth() / 5, gargamel_image.getHeight() / 5, this);
        }
        g.drawImage(oyuncu_image, sirin_x, sirin_y, oyuncu_image.getWidth() / 5, oyuncu_image.getHeight() / 5, this);
        //Puan Gösterme yeri
        g.setColor(Color.WHITE);
        g.fillRect(850, 100, 200, 100);
        g.setColor(Color.BLACK);
        g.drawString(puan.puani_goster(secilen_karakter), 890, 155);
        g.drawImage(sirine,13*60,7*60, sirine.getWidth()/5,sirine.getHeight()/5,this);
    }

    @Override
    public void repaint() {
        super.repaint(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        if (c == KeyEvent.VK_LEFT) {
            if (sirin_x <= 0) {
                sirin_x = 0;
                secilen_karakter.setX(0);
            } else {
                if (labirent[secilen_karakter.getY()][secilen_karakter.getX() - 1] == 1) {
                    if (secilen_karakter instanceof Gozluklu) {
                        if (labirent[secilen_karakter.getY()][secilen_karakter.getX() - 2] == 1) {
                            atlanan_x = sirin_x - 60;
                            atlanan_y = sirin_y;
                            sirin_x -= 2 * hareket_x;
                            secilen_karakter.setX(secilen_karakter.getX() - 2);
                            //**************************
                            //dusman hareket
                            //***************************
                            kazandi_mi();
                            if (azman_image != null) {
                                dusman_hareket(azman, "sol");
                            }
                            if (gargamel_image != null) {
                                dusman_hareket(gargamel, "sol");
                            }
                            //**************************
                            //dusman hareket
                            //***************************
                        }
                    } else {
                        sirin_x -= hareket_x;
                        secilen_karakter.setX(secilen_karakter.getX() - 1);
                        //**************************
                        //dusman hareket
                        //***************************
                        kazandi_mi();
                        if (azman_image != null) {
                            dusman_hareket(azman, "sol");
                        }
                        if (gargamel_image != null) {
                            dusman_hareket(gargamel, "sol");
                        }
                        //**************************
                        //dusman hareket
                        //***************************
                    }

                }
            }
        } else if (c == KeyEvent.VK_RIGHT) {
            if (sirin_x >= 720) {
                sirin_x = 720;
                secilen_karakter.setX(12);

            } else {
                if (labirent[secilen_karakter.getY()][secilen_karakter.getX() + 1] == 1) {
                    if (secilen_karakter instanceof Gozluklu) {
                        if (labirent[secilen_karakter.getY()][secilen_karakter.getX() + 2] == 1) {
                            atlanan_x = sirin_x + 60; //
                            atlanan_y = sirin_y;   //
                            sirin_x += 2 * hareket_x;
                            secilen_karakter.setX(secilen_karakter.getX() + 2);
                            //**************************
                            //dusman hareket
                            //***************************
                            kazandi_mi();
                            if (azman_image != null) {
                                dusman_hareket(azman, "sag");
                            }
                            if (gargamel_image != null) {
                                dusman_hareket(gargamel, "sag");
                            }
                            //**************************
                            //dusman hareket
                            //***************************
                        }
                    } else {

                        sirin_x += hareket_x;
                        secilen_karakter.setX(secilen_karakter.getX() + 1);
                        //**************************
                        //dusman hareket
                        //***************************
                        kazandi_mi();
                        if (azman_image != null) {
                            dusman_hareket(azman, "sag");
                        }
                        if (gargamel_image != null) {
                            dusman_hareket(gargamel, "sag");
                        }
                        //**************************
                        //dusman hareket
                        //***************************
                    }

                }

            }
        } else if (c == KeyEvent.VK_UP) {
            if (sirin_y <= 0) {
                sirin_y = 0;
                secilen_karakter.setY(0);
            } else {
                if (labirent[secilen_karakter.getY() - 1][secilen_karakter.getX()] == 1) {
                    if (secilen_karakter instanceof Gozluklu) {
                        if (secilen_karakter.getY() - 2 > 0) {
                            if (labirent[secilen_karakter.getY() - 2][secilen_karakter.getX()] == 1) {

                                atlanan_x = sirin_x;
                                atlanan_y = sirin_y - 60;
                                sirin_y -= 2 * hareket_x;
                                secilen_karakter.setY(secilen_karakter.getY() - 2);
                                //**************************
                                //dusman hareket
                                //***************************
                                kazandi_mi();
                                if (azman_image != null) {
                                    dusman_hareket(azman, "yuk");
                                }
                                if (gargamel_image != null) {
                                    dusman_hareket(gargamel, "yuk");
                                }
                                //**************************
                                //dusman hareket
                                //***************************
                            }
                        }
                    } else {
                        sirin_y -= hareket_y;
                        secilen_karakter.setY(secilen_karakter.getY() - 1);
                        //**************************
                        //dusman hareket
                        //***************************
                        kazandi_mi();
                        if (azman_image != null) {
                            dusman_hareket(azman, "yuk");
                        }
                        if (gargamel_image != null) {
                            dusman_hareket(gargamel, "yuk");
                        }
                        //**************************
                        //dusman hareket
                        //***************************
                    }

                }
            }
        } else if (c == KeyEvent.VK_DOWN) {
            if (sirin_y >= 600) {
                sirin_y = 600;
                secilen_karakter.setY(10);
            } else {
                if (labirent[secilen_karakter.getY() + 1][secilen_karakter.getX()] == 1) {
                    if (secilen_karakter instanceof Gozluklu) {
                        if (labirent[secilen_karakter.getY() + 2][secilen_karakter.getX()] == 1) {
                            atlanan_x = sirin_x;
                            atlanan_y = sirin_y + 60;
                            sirin_y += 2 * hareket_x;
                            secilen_karakter.setY(secilen_karakter.getY() + 2);
                            //**************************
                            //dusman hareket
                            //***************************
                            kazandi_mi();
                            if (azman_image != null) {
                                dusman_hareket(azman, "asa");
                            }
                            if (gargamel_image != null) {
                                dusman_hareket(gargamel, "asa");
                            }
                            //**************************
                            //dusman hareket
                            //***************************
                        }
                    } else {
                        sirin_y += hareket_y;
                        secilen_karakter.setY(secilen_karakter.getY() + 1);
                        //**************************
                        //dusman hareket
                        //***************************
                        kazandi_mi();
                        if (azman_image != null) {
                            dusman_hareket(azman, "asa");
                        }
                        if (gargamel_image != null) {
                            dusman_hareket(gargamel, "asa");
                        }
                        //**************************
                        //dusman hareket
                        //***************************
                    }

                }
            }
        }

    }

    public void dusman_hareket(Dusman dusman, String yon) {
        
        if (oyun_bittimi != true) {
            if (dusman instanceof Gargamel) {
                //***********************************************************************
                //****************************GARGAMEL HAREKETLERİ***********************
                //***********************************************************************
                dusman.yol_ciz(dusman.getY() * 13 + dusman.getX(), secilen_karakter.getY() * 13 + secilen_karakter.getX());
                if (dusman.getX() == secilen_karakter.getX() && dusman.getY() == secilen_karakter.getY()) {
                    dusman_degdi(dusman);
                } else {
                    if (dusman.getTakipYolu().size() >= 3) {
                        int x = dusman.getTakipYolu().get(2) % 13;
                        int y = dusman.getTakipYolu().get(2) / 13;
                        dusman.setX(x);
                        dusman.setY(y);
                        if (dusman.getX() == secilen_karakter.getX() && dusman.getY() == secilen_karakter.getY()) {
                            dusman_degdi(dusman);
                        }
                    } else {
                        if (secilen_karakter instanceof Tembel) {
                            dusman.setX(secilen_karakter.getX());
                            dusman.setY(secilen_karakter.getY());
                            dusman_degdi(dusman);
                        }
                    }
                }
                //***********************************************************************
                //****************************GARGAMEL HAREKETLERİ***********************
                //***********************************************************************
            } else {
                //***********************************************************************
                //******************************AZMAN HAREKETLERİ************************
                //***********************************************************************
                dusman.yol_ciz(dusman.getY() * 13 + dusman.getX(), secilen_karakter.getY() * 13 + secilen_karakter.getX());
                if (dusman.getX() == secilen_karakter.getX() && dusman.getY() == secilen_karakter.getY()) {                   
                        dusman_degdi(dusman);
                } else {
                    if (dusman.getTakipYolu().size() > 0) {
                        int x = dusman.getTakipYolu().get(1) % 13;
                        int y = dusman.getTakipYolu().get(1) / 13;
                        dusman.setX(x);
                        dusman.setY(y);
                        if (dusman.getX() == secilen_karakter.getX() && dusman.getY() == secilen_karakter.getY()) {
                            dusman_degdi(dusman);
                        }
                    }
                }

                //***********************************************************************
                //******************************AZMAN HAREKETLERİ************************
                //***********************************************************************
            }
        }
    }

    public void oyun_bittimi() {
        if (secilen_karakter.getPuan() <= 0) {
            JOptionPane.showMessageDialog(this, "Oyun Bitti\n"
                    + "Kaybettiniz......");
            System.exit(0);
        }
    }

    public void dusman_degdi(Dusman dusman) {
        if (dusman instanceof Gargamel) {
            secilen_karakter.setPuan(secilen_karakter.getPuan() - 15);
        } else {
            secilen_karakter.setPuan(secilen_karakter.getPuan() - 5);
        }
        oyun_bittimi();
        if (dusman.getKapi().equals("A")) {
            dusman.setX(aKapiX);
            dusman.setY(aKapiY);
        } else if (dusman.getKapi().equals("B")) {
            dusman.setX(bKapiX);
            dusman.setY(bKapiY);
        } else if (dusman.getKapi().equals("C")) {
            dusman.setX(cKapiX);
            dusman.setY(cKapiY);
        } else {
            dusman.setX(dKapiX);
            dusman.setY(dKapiY);
        }
    }

    public void kazandi_mi() {
        if (secilen_karakter.getX() == cikisX && secilen_karakter.getY() == cikisY) {
            JOptionPane.showMessageDialog(this, "TEBRİKLER KAZANDINIZ");
            oyun_bittimi = true;
            System.exit(0);
        }
    }

    public boolean azman_hareket(int x, int y) {
        if (gargamel.getX() == x && gargamel.getY() == y) {
            return false;
        }
        return true;
    }

    boolean varMi(ArrayList<Altin> dizi, int x, int y) {
        for (int i = 0; i < dizi.size(); i++) {
            if (dizi.get(i).getX() == x && dizi.get(i).getY() == y) {
                return true;
            }
        }
        return false;
    }

    boolean varMi(ArrayList<Altin> dizi1, ArrayList<Mantar> dizi2, int x, int y) { // altinin mantar uzerinde olusmasini engellemek amaciyla olusturulmustur.

        for (int i = 0; i < dizi1.size() && dizi2.size() == 1; i++) {
            if (dizi1.get(i).getX() == dizi2.get(0).getX() && dizi1.get(i).getY() == dizi2.get(0).getY()) {
                return true;
            }
        }

        return false;
    }

    public int altinIptal(ArrayList<Altin> dizi, int x, int y) // iptal edilecek altinin indisini dondurur.
    {
        for (int i = 0; i < dizi.size(); i++) {
            if (dizi.get(i).getX() == x && dizi.get(i).getY() == y) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
//***************************************************************************************
//**************************************************************************************
//****************************OYUN CLASSI*********************************************
//******************************************************************************************
//************************************************************************************