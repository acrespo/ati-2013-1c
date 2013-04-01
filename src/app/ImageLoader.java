package app;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import model.RGBImage;
import model.Image;
import model.Image.ImageType;

import org.apache.sanselan.ImageFormat;
import org.apache.sanselan.ImageInfo;
import org.apache.sanselan.ImageReadException;
import org.apache.sanselan.Sanselan;

public class ImageLoader {
	
	
	//TODO: remove this wire
	public static File currentFile;
	
	public static Image loadImage(File arch) throws ImageReadException, IOException{
		
		currentFile = arch;
		
		BufferedImage bi = Sanselan.getBufferedImage(arch);
		ImageInfo info = Sanselan.getImageInfo(arch);
		model.Image.ImageFormat format;
		
		if( info.getFormat() == ImageFormat.IMAGE_FORMAT_BMP ){
			format = model.Image.ImageFormat.BMP;
		} else if( info.getFormat() == ImageFormat.IMAGE_FORMAT_PGM ){
			format = model.Image.ImageFormat.PGM;
		} else if( info.getFormat() == ImageFormat.IMAGE_FORMAT_PPM ){
			format = model.Image.ImageFormat.PPM;
		} else if( info.getFormat() == ImageFormat.IMAGE_FORMAT_UNKNOWN ){
			//TODO: fix this
			format = model.Image.ImageFormat.RAW;
			throw new IllegalStateException("Unsupported image format");
		} else {
			throw new IllegalStateException("Unsupported image format");
		}
		
		if(bi.getType() == BufferedImage.TYPE_INT_RGB){
			return new RGBImage(bi, format, ImageType.RGB);
		} else if(bi.getType() == BufferedImage.TYPE_BYTE_GRAY){			
			return new RGBImage(bi, format, ImageType.GRAYSCALE);
		} else {
			throw new IllegalStateException("Image wasn't RGB nor Grayscale");
		}
		
	}

	public static Image loadRaw(File file, int width, int height) throws IOException{

        BufferedImage ret;
        byte[] data = getBytesFromFile(file);
        ret = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster raster = ret.getRaster();
        int k = 0;
        for (int j = 0; j < height; j++) {
        	for (int i = 0; i < width; i++) {
                        raster.setSample(i, j, 0, data[k]);
                        k=k+1;
                }
        }
        Image image = new RGBImage(height, width, Image.ImageFormat.RAW, ImageType.GRAYSCALE);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
            	image.setRGBPixel(i, j, ret.getRGB(i, j));
            }
        }
        return image;

	}

	public static byte[] getBytesFromFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);
        long length = file.length();
        byte[] bytes = new byte[(int)length];
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
               && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }
        if (offset < bytes.length) {
            throw new IOException();
        }
        is.close();
        return bytes;
    }

}
