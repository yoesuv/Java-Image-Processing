package proc;


import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ProcessingImg {
	
	protected BufferedImage bfrImg,bfrProcess;
	protected ImageIcon imgIco;
	
	public ProcessingImg(){
		super();
	}
	
	public void Tampilkan(JLabel jl){
		jl.setIcon(imgIco);
	}
}
