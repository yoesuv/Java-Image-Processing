package proc;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.jhlabs.image.GrayscaleFilter;
import com.jhlabs.image.PointFilter;

public class GrayscaleProcess extends ProcessingImg {
	
	public void Gray(File f){
		try {
			bfrImg = ImageIO.read(f);
			PointFilter pf = new GrayscaleFilter();
			bfrProcess = new BufferedImage(bfrImg.getWidth(), bfrImg.getHeight(), bfrImg.getType());
			pf.filter(bfrImg, bfrProcess);
			imgIco = new ImageIcon(bfrProcess);
			System.out.println("Proses Grayscale Selesai\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void Tampilkan(JLabel l){
		l.setIcon(imgIco);
	}
}
