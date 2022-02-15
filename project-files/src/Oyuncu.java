//***************************************************************************************
//**************************************************************************************
//****************************OYUNCU CLASSI*********************************************
//******************************************************************************************
//************************************************************************************
public abstract class Oyuncu extends Karakter{
    private int puan;
    public Oyuncu() {      
    }
    
    public Oyuncu(int puan, int OyuncuID, String OyuncuAdi, String OyuncuTur, int x, int y, String resim_yolu, int hiz) {
        super(OyuncuID, OyuncuAdi, OyuncuTur, x, y, resim_yolu, hiz);
        this.puan = puan;
    }
    public int getPuan() {
        return puan;
    }

    public void setPuan(int puan) {
        this.puan = puan;
    }
    
    public String puani_goster(Oyuncu oyuncu) {
        return "Oyuncu Puanı : "+puan;  
    }
    
    
    @Override
    public void enKisaYol() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
//***************************************************************************************
//**************************************************************************************
//****************************OYUNCU CLASSI*********************************************
//******************************************************************************************
//************************************************************************************