package gui;

import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

import proc.GrayscaleProcess;
import proc.NegativeProcess;
import proc.PencilProcess;
import proc.TresholdProcess;

public class GUI_ImgProcessing {

	private JFrame frameGUI;
	private JLabel lblImg;
	private JButton btnOpen,btnGrayscale,btnTreshold,btnNegative;
	private JFileChooser jfcOpen,jfcSave;
	
	private File fileSumber,fileToSave;
	private BufferedImage bfrImage;
	private ImageIcon imgIco;
	private JButton btnPencil;
	private JButton btnSave;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_ImgProcessing window = new GUI_ImgProcessing();
					window.frameGUI.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI_ImgProcessing() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameGUI = new JFrame();
		frameGUI.setTitle("Java Image Processing");
		frameGUI.setResizable(false);
		frameGUI.setBounds(100, 100, 754, 465);
		frameGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameGUI.getContentPane().setLayout(null);
		
		btnOpen = new JButton("OPEN");
		btnOpen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				jfcOpen = new JFileChooser();
				jfcOpen.setDialogTitle("Open an Image");
				jfcOpen.setMultiSelectionEnabled(false);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Gambar Only (*.jpg | *.png)", "jpg","JPG","jpeg","png");
				jfcOpen.setFileFilter(filter);
				if(jfcOpen.showOpenDialog(frameGUI)==JFileChooser.APPROVE_OPTION){
					fileSumber = jfcOpen.getSelectedFile();
					try {
						bfrImage = ImageIO.read(fileSumber);
						imgIco = new ImageIcon(bfrImage);
						lblImg.setIcon(imgIco);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		btnOpen.setBounds(10, 11, 93, 30);
		frameGUI.getContentPane().add(btnOpen);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 52, 722, 368);
		frameGUI.getContentPane().add(scrollPane);
		
		lblImg = new JLabel("");
		scrollPane.setViewportView(lblImg);
		
		btnGrayscale = new JButton("GRAYSCALE");
		btnGrayscale.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(fileSumber!=null){
					GrayscaleProcess gr = new GrayscaleProcess();
					gr.Gray(fileSumber);
					gr.Tampilkan(lblImg);
				}
			}
		});
		btnGrayscale.setBounds(113, 11, 93, 30);
		frameGUI.getContentPane().add(btnGrayscale);
		
		btnTreshold = new JButton("TRESHOLD");
		btnTreshold.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(fileSumber!=null){
					TresholdProcess tp = new TresholdProcess();
					tp.TresholdImg(fileSumber);
					tp.Tampilkan(lblImg);
				}
			}
		});
		btnTreshold.setBounds(213, 11, 93, 30);
		frameGUI.getContentPane().add(btnTreshold);
		
		btnNegative = new JButton("NEGATIVE");
		btnNegative.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(fileSumber!=null){
					NegativeProcess np = new NegativeProcess();
					np.Negative(fileSumber);
					np.Tampilkan(lblImg);
				}
			}
		});
		btnNegative.setBounds(314, 11, 93, 30);
		frameGUI.getContentPane().add(btnNegative);
		
		btnPencil = new JButton("PENCIL");
		btnPencil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(fileSumber!=null){
					PencilProcess pp = new PencilProcess();
					pp.Pencil(fileSumber);
					pp.Tampilkan(lblImg);
				}
			}
		});
		btnPencil.setBounds(414, 11, 93, 30);
		frameGUI.getContentPane().add(btnPencil);
		
		btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jfcSave = new JFileChooser();
				jfcSave.setDialogTitle("Save Image ");
				FileNameExtensionFilter fil = new FileNameExtensionFilter("JPG only (*.jpg)", "jpg");
				jfcSave.setFileFilter(fil);
				
				if(jfcSave.showSaveDialog(frameGUI)==JFileChooser.APPROVE_OPTION){
					fileToSave = new File(jfcSave.getSelectedFile()+".jpg");
					
					Image ico = iconToImage(lblImg.getIcon());
					bfrImage = (BufferedImage) ico;
					try {
						ImageIO.write(bfrImage, "jpg",fileToSave);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		btnSave.setBounds(639, 11, 93, 30);
		frameGUI.getContentPane().add(btnSave);
	}
	
	static Image iconToImage(Icon icon) {
        if (icon instanceof ImageIcon) {
            return ((ImageIcon)icon).getImage();
        } else {
            int w = icon.getIconWidth();
            int h = icon.getIconHeight();
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gd = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gd.getDefaultConfiguration();
            BufferedImage image = gc.createCompatibleImage(w, h);
            Graphics2D g = image.createGraphics();
            icon.paintIcon(null, g, 0, 0);
            g.dispose();
            return image;
        }
    }
}
