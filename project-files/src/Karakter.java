//***************************************************************************************
//**************************************************************************************
//****************************KARAKTER CLASSI*********************************************
//******************************************************************************************
//************************************************************************************
public abstract class Karakter extends Lokasyon{
    private int OyuncuID;
    private String OyuncuAdi;
    private String OyuncuTur;
    
    private String resim_yolu;
    private int hiz;
    
    public Karakter(){
        
    }
    public Karakter(int OyuncuID, String OyuncuAdi, String OyuncuTur, int x, int y, String resim_yolu,int hiz) {
        this.OyuncuID = OyuncuID;
        this.OyuncuAdi = OyuncuAdi;
        this.OyuncuTur = OyuncuTur;
        setX(x);
        setY(y);
        this.resim_yolu=resim_yolu;
        this.hiz=hiz;
    }

    public int getOyuncuID() {
        return OyuncuID;
    }

    public void setOyuncuID(int OyuncuID) {
        this.OyuncuID = OyuncuID;
    }

    public String getOyuncuAdi() {
        return OyuncuAdi;
    }

    public void setOyuncuAdi(String OyuncuAdi) {
        this.OyuncuAdi = OyuncuAdi;
    }

    public String getOyuncuTur() {
        return OyuncuTur;
    }

    public void setOyuncuTur(String OyuncuTur) {
        this.OyuncuTur = OyuncuTur;
    }

    

    public String getResim_yolu() {
        return resim_yolu;
    }

    public void setResim_yolu(String resim_yolu) {
        this.resim_yolu = resim_yolu;
    }

    public int getHiz() {
        return hiz;
    }

    public void setHiz(int hiz) {
        this.hiz = hiz;
    }
    
    public  abstract void enKisaYol();
}
//***************************************************************************************
//**************************************************************************************
//****************************KARAKTER CLASSI*********************************************
//******************************************************************************************
//************************************************************************************