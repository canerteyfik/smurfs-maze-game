//***************************************************************************************
//**************************************************************************************
//****************************DUSMAN CLASSI*********************************************
//******************************************************************************************
//************************************************************************************
import java.util.ArrayList;

public class Dusman extends Karakter {

    private String kapi;

    public Dusman() {

    }

    public Dusman(int OyuncuID, String OyuncuAdi, String OyuncuTur, int x, int y, String resim_yolu, int hiz, String kapi) {
        super(OyuncuID, OyuncuAdi, OyuncuTur, x, y, resim_yolu, hiz);
        this.kapi = kapi;
    }

    @Override
    public void enKisaYol() {
    }
    private int[][] labirent;
    private int[][] komsuluk_matrisi_kopya;
    private ArrayList<Yol> yol_liste = new ArrayList<Yol>();
    private ArrayList<Integer> takipYolu = new ArrayList<>();

    public void dijkstra(int[][] komsuluk_matrisi, int kaynak_dugum) {
        int dugum_sayisi = komsuluk_matrisi.length;
        boolean[] dugum_ziyaret_edildi_mi = new boolean[dugum_sayisi];
        int[] mesafe = new int[dugum_sayisi];
        for (int i = 0; i < dugum_sayisi; i++) {
            dugum_ziyaret_edildi_mi[i] = false;
            mesafe[i] = Integer.MAX_VALUE;
        }
        mesafe[kaynak_dugum] = 0;
        //KAYNAK KÖŞEYE MESAFE KENDİSİNE MESAVEDİR VE BU SIFIRDIR
        for (int i = 0; i < dugum_sayisi; i++) {
            //KAYNAK KÖŞEYE KOMŞU OLAN ZİYARET EDİLMEMİŞ EN AZ MESAFEDE OLAN KÖŞELERİ BULUN      
            //İLK DEFA U KAYNAK KÖŞE OLACAK VE MESAFE DİZİSİ KAYNAK KÖŞEYE KOMŞU OLAN KÖŞENİN MESAFESİYLE GÜNCELLENECEKTİR
            int u = min_uzaklık_bul(mesafe, dugum_ziyaret_edildi_mi);
            //U SATIR VE V  SÜTUNDUR
            dugum_ziyaret_edildi_mi[u] = true;
            //ŞİMDİ TÜMK KOMŞU KÖŞE UZAKLIKLARINI GÜNCELLİYORUZ
            for (int v = 0; v < dugum_sayisi; v++) {
                //graph[u][v] != 0 -> DOGRUDAN BİR KENAR OLMALI
                if (!dugum_ziyaret_edildi_mi[v] && komsuluk_matrisi[u][v] != 0 && (mesafe[u] + komsuluk_matrisi[u][v] < mesafe[v])) {

                    mesafe[v] = mesafe[u] + komsuluk_matrisi[u][v];
                    yol_liste.add(new Yol(u, v));
                }
            }
        }
    }

    private int min_uzaklık_bul(int[] mesafe, boolean[] dugum_ziyaret_edildi_mi) {
        int min_uzaklik = Integer.MAX_VALUE;
        int dugume_min_mesafe = -1;
        for (int i = 0; i < mesafe.length; i++) {
            //KÖŞE ZİYARET EDİLMEMİŞ OLMALI VE KÖŞEYE YOL EN KÜÇÜK OLMALI
            //BU BİR DİZİNİN MİNİMUM ELEMANINI BULMAYA BENZER
            if (!dugum_ziyaret_edildi_mi[i] && mesafe[i] <= min_uzaklik) {
                min_uzaklik = mesafe[i];
                dugume_min_mesafe = i;

            }
        }
        return dugume_min_mesafe;
    }

    public ArrayList<Integer> yol_dondur(int varilacak_yer) {

        ArrayList<Integer> yol = new ArrayList<>();
        for (int i = 0; i < yol_liste.size(); i++) {
            if (yol_liste.get(i).getSon_yol() == varilacak_yer) {
                yol.add(0, yol_liste.get(i).getSon_yol());
                varilacak_yer = yol_liste.get(i).getIlk_yol();
                i = -1;
            }
        }
        yol.add(0, varilacak_yer);
        return yol;
    }

    public void yol_ciz(int merkez_dugum, int varilacak_yer) {
        int[][] komsuluk_matrisi = new int[143][143];
        Metin metin = new Metin();
        labirent = metin.harita_dondur();
        int k = 0;

        for (int i = 0; i < labirent.length; i++) {
            for (int j = 0; j < labirent[0].length; j++) {
                komsuluk_matrisi[k][0] = labirent[i][j];
                k++;
            }
        }
        for (int i = 0; i < komsuluk_matrisi.length; i++) {
            if (komsuluk_matrisi[i][0] == 1) {
                if (i + 1 < komsuluk_matrisi.length) {
                    if (komsuluk_matrisi[i + 1][0] == 1) {
                        komsuluk_matrisi[i][i + 1] = 1;
                    }
                }
                if (i - 1 > 0) {
                    if (komsuluk_matrisi[i - 1][0] == 1) {
                        komsuluk_matrisi[i][i - 1] = 1;
                    }
                }
                if (i + labirent[0].length < komsuluk_matrisi.length) {
                    if (komsuluk_matrisi[i + labirent[0].length][0] == 1) {
                        komsuluk_matrisi[i][i + labirent[0].length] = 1;
                    }
                }
                if (i - labirent[0].length > 0) {
                    if (komsuluk_matrisi[i - labirent[0].length][0] == 1) {
                        komsuluk_matrisi[i][i - labirent[0].length] = 1;
                    }
                }

            }
        }
        k = 0;
        for (int i = 0; i < labirent.length; i++) {
            for (int j = 0; j < labirent[0].length; j++) {
                komsuluk_matrisi[k][0] = 0;
                k++;
            }
        }
        Dusman t = new Dusman();
        t.dijkstra(komsuluk_matrisi, merkez_dugum);
        takipYolu = t.yol_dondur(varilacak_yer);

    }

    public ArrayList<Integer> getTakipYolu() {
        return takipYolu;
    }

    public void setTakipYolu(ArrayList<Integer> takipYolu) {
        this.takipYolu = takipYolu;
    }

    public String getKapi() {
        return kapi;
    }

    public void setKapi(String kapi) {
        this.kapi = kapi;
    }

}
//***************************************************************************************
//**************************************************************************************
//****************************DUSMAN CLASSI*********************************************
//******************************************************************************************
//************************************************************************************