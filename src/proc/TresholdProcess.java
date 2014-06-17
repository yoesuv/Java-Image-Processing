package proc;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.jhlabs.image.PointFilter;
import com.jhlabs.image.ThresholdFilter;

public class TresholdProcess extends ProcessingImg {
	
	public void TresholdImg(File f){
		try {
			bfrImg = ImageIO.read(f);
			PointFilter pf = new ThresholdFilter();
			bfrProcess = new BufferedImage(bfrImg.getWidth(), bfrImg.getHeight(), bfrImg.getType());
			pf.filter(bfrImg, bfrProcess);
			imgIco = new ImageIcon(bfrProcess);
			System.out.println("Proses Tresholding Selesai\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void Tampilkan(JLabel jl){
		jl.setIcon(imgIco);
	}

}
