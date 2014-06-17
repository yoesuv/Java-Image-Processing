package proc;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.jhlabs.image.GrayscaleFilter;
import com.jhlabs.image.InvertFilter;
import com.jhlabs.image.PointFilter;

public class NegativeProcess extends ProcessingImg{

	private BufferedImage bfrOut;
	
	public void Negative(File f){
		try {
			bfrImg = ImageIO.read(f);
			
			PointFilter pf = new GrayscaleFilter();
			bfrProcess = new BufferedImage(bfrImg.getWidth(), bfrImg.getHeight(), bfrImg.getType());
			pf.filter(bfrImg, bfrProcess);
			
			PointFilter pf2 = new InvertFilter();
			bfrOut = new BufferedImage(bfrImg.getWidth(), bfrImg.getHeight(), bfrImg.getType());
			pf2.filter(bfrProcess, bfrOut);
			
			imgIco = new ImageIcon(bfrOut);
			System.out.println("Proses Negative Selesai\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void Tampilkan(JLabel jl){
		jl.setIcon(imgIco);
	}
}
