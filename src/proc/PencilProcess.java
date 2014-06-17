package proc;

import java.awt.CompositeContext;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.jhlabs.composite.ColorDodgeComposite;
import com.jhlabs.image.GaussianFilter;
import com.jhlabs.image.GrayscaleFilter;
import com.jhlabs.image.InvertFilter;
import com.jhlabs.image.PointFilter;

public class PencilProcess extends ProcessingImg {

	private BufferedImage bfrInv,bfrG,bfrSketch;
	
	public void Pencil(File f){
		try {
			bfrImg = ImageIO.read(f);
			
			PointFilter pfGray = new GrayscaleFilter();
			bfrProcess = new BufferedImage(bfrImg.getWidth(), bfrImg.getHeight(), bfrImg.getType());
			pfGray.filter(bfrImg, bfrProcess);
			
			PointFilter pfInv = new InvertFilter();
			bfrInv = new BufferedImage(bfrImg.getWidth(), bfrImg.getHeight(), bfrImg.getType());
			pfInv.filter(bfrProcess, bfrInv);
			
			GaussianFilter gf = new GaussianFilter(20);
			bfrG = new BufferedImage(bfrImg.getWidth(), bfrImg.getHeight(), bfrImg.getType());
			gf.filter(bfrInv,bfrG);
			
			ColorDodgeComposite cdc = new ColorDodgeComposite(1.0f);
			CompositeContext cc = cdc.createContext(bfrInv.getColorModel(), bfrG.getColorModel(), null);
			Raster invR = bfrG.getRaster();
			Raster grayR = bfrProcess.getRaster();
			
			bfrSketch = new BufferedImage(bfrImg.getWidth(), bfrImg.getHeight(), bfrImg.getType());
			WritableRaster wr = bfrSketch.getRaster();
			cc.compose(invR, grayR, wr);
			
			imgIco = new ImageIcon(bfrSketch);
			System.out.println("Proses Pensil Selesai\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void Tampilkan(JLabel jl){
		jl.setIcon(imgIco);
	}
}
