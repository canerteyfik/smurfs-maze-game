//***************************************************************************************
//**************************************************************************************
//****************************OYUN EKRANI CLASSI*********************************************
//******************************************************************************************
//************************************************************************************
import java.io.IOException;
import javax.swing.JFrame;


public class OyunEkrani extends JFrame{
    public static void main(String[] args) throws IOException {
        
        OyunEkrani ekran=new OyunEkrani();
        ekran.setTitle("SİRİNLER");
        ekran.setResizable(false);
        ekran.setFocusable(false);
        ekran.setSize(1080,760);
        ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Oyun oyun=new Oyun();
        oyun.requestFocus();
        oyun.addKeyListener(oyun);
        oyun.setFocusable(true);
        oyun.setFocusTraversalKeysEnabled(false);
        ekran.add(oyun);
        ekran.setVisible(true);          
    }
}
//***************************************************************************************
//**************************************************************************************
//****************************OYUN EKRANI CLASSI*********************************************
//******************************************************************************************
//************************************************************************************