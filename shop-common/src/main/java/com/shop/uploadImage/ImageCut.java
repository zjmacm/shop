package com.shop.uploadImage;


import java.io.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.Graphics;
import java.awt.color.ColorSpace;
import java.io.File;
import javax.imageio.ImageIO;

public class ImageCut {
    public static void abscut(String srcImageFile,String dirImageFile, int x, int y, int destWidth,
            int destHeight) {
        try {
            Image img;
            ImageFilter cropFilter;

            BufferedImage bi = ImageIO.read(new java.io.File(srcImageFile));
            int srcWidth = bi.getWidth(); 
            int srcHeight = bi.getHeight();          
            if (srcWidth >= destWidth && srcHeight >= destHeight) {
                Image image = bi.getScaledInstance(srcWidth, srcHeight,
                        Image.SCALE_DEFAULT);
                
                cropFilter = new CropImageFilter(x, y, destWidth, destHeight);
                img = Toolkit.getDefaultToolkit().createImage(
                        new FilteredImageSource(image.getSource(), cropFilter));
                BufferedImage tag = new BufferedImage(destWidth, destHeight,
                        BufferedImage.TYPE_INT_RGB);
                Graphics g = tag.getGraphics();
                g.drawImage(img, 0, 0, null); 
                g.dispose();
                
                ImageIO.write(tag, "JPEG", new java.io.File(dirImageFile));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

	public static void scale(String srcImageFile, String result, int scale,
			boolean flag) {
		try {
			BufferedImage src = ImageIO.read(new java.io.File(srcImageFile)); // �����ļ�
			int width = src.getWidth(); 
			int height = src.getHeight(); 
			if (flag) {
				
				width = width * scale;
				height = height * scale;
			} else {
				
				width = width / scale;
				height = height / scale;
			}
			Image image = src.getScaledInstance(width, height,Image.SCALE_DEFAULT);
			BufferedImage tag = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null); 
			g.dispose();
			ImageIO.write(tag, "JPEG", new java.io.File(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void scale(String srcImageFile, String result, int _width,int _height) {		
		scale(srcImageFile,result,_width,_height,0,0);
	}
	
	public static void scale(String srcImageFile, String result, int _width,int _height,int x,int y) {
		try {
			
			BufferedImage src = ImageIO.read(new java.io.File(srcImageFile)); // �����ļ�
			
			int width = src.getWidth(); 
			int height = src.getHeight(); 
			
			if (width > _width) {
				 width = _width;
			}
			if (height > _height) {
				height = _height;
			}			
			Image image = src.getScaledInstance(width, height,Image.SCALE_DEFAULT);
			BufferedImage tag = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, x, y, null); 
			g.dispose();			
			ImageIO.write(tag, "JPEG", new java.io.File(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 */
	public static void convert(String source, String result) {
		try {
			java.io.File f = new java.io.File(source);
			f.canRead();
			f.canWrite();
			BufferedImage src = ImageIO.read(f);
			ImageIO.write(src, "JPG", new java.io.File(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public static void gray(String source, String result) {
		try {
			BufferedImage src = ImageIO.read(new java.io.File(source));
			ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
			ColorConvertOp op = new ColorConvertOp(cs, null);
			src = op.filter(src, null);
			ImageIO.write(src, "JPEG", new File(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//cut("c:/images/ipomoea.jpg", "c:/images/t/ipomoea.jpg", 200, 150);
		//ok
		//gray("c:/images/ipomoea.jpg", "c:/images/t/ipomoea.jpg");
		//convert("c:/images/ipomoea.jpg", "c:/images/t/ipomoea.gif");
		//scale("c:/images/5105049910001020110718648723.jpg", "c:/images/t/5105049910001020110718648725.jpg",154,166,157,208);
		//scale("c:/images/rose1.jpg", "c:/images/t/rose1.jpg",154,166,157,208);
		scale("c:/images/rose1.jpg", "c:/images/t/rose2.jpg",154,166,10,10);
		
	}
}