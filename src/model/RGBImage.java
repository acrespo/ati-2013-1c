package model;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;

import model.mask.Mask;
import app.Utils;

public class RGBImage implements Image, Cloneable {
	
	private ImageType type;
	private ImageFormat format;
	private SingleChannel red;
	private SingleChannel green;
	private SingleChannel blue;
	
	public RGBImage(int height, int width, ImageFormat format, ImageType type) {
		if( format == null ){
			throw new IllegalArgumentException("ImageFormat can't be null");
		}
		this.red = new SingleChannel(width, height);
		this.green = new SingleChannel(width, height);
		this.blue = new SingleChannel(width, height);
		
		this.format = format;
		this.type = type;
	}
	
	public RGBImage(BufferedImage bi, ImageFormat format, ImageType type){
		this(bi.getHeight(), bi.getWidth(), format, type);
		for(int x = 0 ; x < bi.getWidth() ; x++){
			for(int y = 0 ; y < bi.getHeight() ; y++ ){
				Color c = new Color(bi.getRGB(x, y));
				red.setPixel(x, y, c.getRed());
				green.setPixel(x, y, c.getGreen());
				blue.setPixel(x, y, c.getBlue());
			}
		}
	}
	
	private RGBImage(SingleChannel red, SingleChannel green, SingleChannel blue, 
			ImageFormat format, ImageType type){
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.format = format;
		this.type = type;
	}
	
	public void setPixel(int x, int y, Channel channel, double color) {

		if( !red.validPixel(x, y)){
			throw new IllegalArgumentException("Invalid pixels on setPixel");
		}
		
		if( channel == Channel.RED ){
			red.setPixel(x, y, color);
			return;
		}
		if( channel == Channel.GREEN ){
			green.setPixel(x, y, color);
			return;
		}
		if( channel == Channel.BLUE ){
			blue.setPixel(x, y, color);
			return;
		}
		throw new IllegalStateException();
	}
	
	@Override
	public void setRGBPixel(int x, int y, int rgb) {
		this.setPixel(x, y, Channel.RED, Utils.getRedFromRGB(rgb));
		this.setPixel(x, y, Channel.GREEN, Utils.getGreenFromRGB(rgb));
		this.setPixel(x, y, Channel.BLUE, Utils.getBlueFromRGB(rgb));
	}

	@Override
	public int getHeight() {
		return red.getHeight();
	}

	@Override
	public int getWidth() {
		return red.getWidth();
	}

	@Override
	public double getPixelFromChannel(int x, int y, Channel channel) {
		if( channel == Channel.RED ){
			return red.getPixel(x, y);
		}
		if( channel == Channel.GREEN ){
			return green.getPixel(x, y);
		}
		if( channel == Channel.BLUE ){
			return blue.getPixel(x, y);
		}
		throw new IllegalStateException();
	}

	@Override
	public int getRGBPixel(int x, int y) {
		int red = this.red.truncatePixel(getPixelFromChannel(x, y, Channel.RED));
		int green = this.green.truncatePixel(getPixelFromChannel(x, y, Channel.GREEN));
		int blue = this.blue.truncatePixel(getPixelFromChannel(x, y, Channel.BLUE));
		return new Color(red, green, blue).getRGB();
	}
	
	@Override
	public ImageType getType() {
		return type;
	}

	@Override
	public ImageFormat getImageFormat() {
		return format;
	}

	@Override
	public Image cropImage(int x1, int y1, int x2, int y2)
			throws InvalidImageException {
		SingleChannel red = this.red.cropImage(x1, y1, x2, y2);
		SingleChannel green = this.green.cropImage(x1, y1, x2, y2);
		SingleChannel blue = this.blue.cropImage(x1, y1, x2, y2);
		
		return new RGBImage(red, green, blue, format, type);
	}

	@Override
	public Image add(Image img) {
		RGBImage ci = (RGBImage)img;
		
		this.red.add(ci.red);
		this.green.add(ci.green);
		this.blue.add(ci.blue);
		return this;
	}

	@Override
	public Image multiply(Image img) {
		RGBImage ci = (RGBImage)img;
		
		this.red.multiply(ci.red);
		this.green.multiply(ci.green);
		this.blue.multiply(ci.blue);
		return this;
	}

	@Override
	public Image substract(Image img) {
		RGBImage ci = (RGBImage)img;
		
		this.red.substract(ci.red);
		this.green.substract(ci.green);
		this.blue.substract(ci.blue);
		return this;
	}

	@Override
	public void dynamicRangeCompression() {
		double max = -Double.MAX_VALUE;
		double min = Double.MAX_VALUE;
		for(int i = 0; i < this.getWidth(); i++) {
			for(int j = 0; j < this.getHeight(); j++) {
				double redPixel = red.getPixel(i, j);
				double greenPixel = green.getPixel(i, j);
				double bluePixel = blue.getPixel(i, j);
				
				min = Math.min(Math.min(min, redPixel), Math.min(greenPixel, bluePixel));
				max = Math.max(Math.max(max, redPixel), Math.max(greenPixel, bluePixel));
			}
		}

		this.red.dynamicRangeCompression(min, max);
		this.green.dynamicRangeCompression(min, max);
		this.blue.dynamicRangeCompression(min, max);
	}

	@Override
	public void negative() {
		this.red.negative();
		this.blue.negative();
		this.green.negative();		
	}

	@Override
	public void threshold(double thresholdLimit) {
		this.red.threshold(thresholdLimit);
		this.blue.threshold(thresholdLimit);
		this.green.threshold(thresholdLimit);
	}

	@Override
	public void incrementContrast(double x1, double x2, double y1, double y2) {
		this.red.incrementContrast(x1, x2, y1, y2);
		this.blue.incrementContrast(x1, x2, y1, y2);
		this.green.incrementContrast(x1, x2, y1, y2);
	}

	@Override
	public void equalizeGrays() {
		this.red.equalize();
		this.green.equalize();
		this.blue.equalize();
	}

	@Override
	public double getGraylevelFromPixel(int x, int y) {
		double red = this.red.getPixel(x, y);
		double green = this.green.getPixel(x, y);
		double blue = this.blue.getPixel(x, y);
		
		return (red + green + blue)/3.0;
	}
	
	@Override
	public double[] getHistogramPixels() {
 		double[] result = new double[this.getHeight()*this.getWidth()];
 		
 		for(int i = 0 ; i < result.length ; i++){
 			result[i] = getGraylevelFromPixel((int)Math.floor(i/this.getWidth()), i % this.getWidth());
 		}
 		
		return result;
	}

	@Override
	public void multiply(double scalar) {
		this.red.multiply(scalar);
		this.green.multiply(scalar);
		this.blue.multiply(scalar);
	}
	
	
	@Override
	public void whiteNoise(double stdDev) {
		SingleChannel noisyChannel = new SingleChannel(this.getWidth(), this.getHeight());
		for(int x = 0; x < noisyChannel.getWidth(); x++) {
			for(int y = 0; y < noisyChannel.getHeight() ; y++) {
				double noiseLevel = RandomGenerator.getGaussian(0, stdDev);
				noisyChannel.setPixel(x, y, noiseLevel);
			}
		}
		this.red.add(noisyChannel);
		this.green.add(noisyChannel);
		this.blue.add(noisyChannel);
	}

	@Override
	public void rayleighNoise(double mean) {
		SingleChannel noisyChannel = new SingleChannel(this.getWidth(), this.getHeight());
		for(int x = 0; x < noisyChannel.getWidth(); x++) {
			for(int y = 0; y < noisyChannel.getHeight() ; y++) {
				double noiseLevel = RandomGenerator.getRayleigh(mean);
				noisyChannel.setPixel(x, y, noiseLevel);
			}
		}
		this.red.multiply(noisyChannel);
		this.green.multiply(noisyChannel);
		this.blue.multiply(noisyChannel);
	}

	@Override
	public void exponentialNoise(double mean) {
		SingleChannel noisyChannel = new SingleChannel(this.getWidth(), this.getHeight());
		for(int x = 0; x < noisyChannel.getWidth(); x++) {
			for(int y = 0; y < noisyChannel.getHeight() ; y++) {
				double noiseLevel = RandomGenerator.getExponential(mean);
				noisyChannel.setPixel(x, y, noiseLevel);
			}
		}
		this.red.multiply(noisyChannel);
		this.green.multiply(noisyChannel);
		this.blue.multiply(noisyChannel);
	}
	
	@Override
	public void saltAndPepperNoise(double minLimit, double maxLimit) {
		for(int x = 0; x < this.getWidth(); x++) {
			for(int y = 0; y < this.getHeight() ; y++) {
				double random = RandomGenerator.getUniform(0, 1);
				if(random < minLimit) {
					double noiseLevel = SingleChannel.MIN_CHANNEL_COLOR;
					this.red.setPixel(x, y, noiseLevel);
					this.green.setPixel(x, y, noiseLevel);
					this.blue.setPixel(x, y, noiseLevel);
				} else if(random > maxLimit) {
					double noiseLevel = SingleChannel.MAX_CHANNEL_COLOR;
					this.red.setPixel(x, y, noiseLevel);
					this.green.setPixel(x, y, noiseLevel);
					this.blue.setPixel(x, y, noiseLevel);
				}
			}
		}
	}

	@Override
	public void applyMask(Mask mask) {
		this.red.applyMask(mask);
		this.green.applyMask(mask);
		this.blue.applyMask(mask);
	}

	@Override
	public void applyMedianMask(Point maskSize) {
		this.red.applyMedianMask(maskSize);
		this.green.applyMedianMask(maskSize);
		this.blue.applyMedianMask(maskSize);
	}
	
	public Point getCenter(){
		
		int x = (int) Math.floor(getWidth()/2);
		int y = (int) Math.floor(getHeight()/2);
		
		return new Point(x, y);
	}
	
	@Override
	public Image clone() {

		BufferedImage bi = new BufferedImage(this.getWidth(), this.getHeight(), 
				Utils.toBufferedImageType(this.getType()));
		Utils.populateEmptyBufferedImage(bi, this);

		return new RGBImage(bi, format, type);
	}
}
