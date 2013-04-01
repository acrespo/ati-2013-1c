package model;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.TreeSet;

import model.mask.Mask;


public class SingleChannel implements Cloneable {
	
	static final int MIN_CHANNEL_COLOR = 0;
	static final int MAX_CHANNEL_COLOR = 255;
	
	private int width;
	private int height;
	
	private double[] channel;
	
	public SingleChannel(int width, int height){
		if(width <= 0 || height <= 0) {
			throw new IllegalArgumentException("Images must have at least 1x1 pixel size");
		}
		
		this.width = width;
		this.height = height;
		this.channel = new double[width*height];
	}
	
	public SingleChannel(int width, int height, BufferedImage bi, int chnl){
		this(width, height);
		
		if(chnl < 0 || chnl > 2 ) {
			throw new IllegalArgumentException("Channel must be between 0 and 2");
		}

		for(int x = 0 ; x < width ; x++ ) {
			for(int y = 0 ; y < height ; y++) {
				setPixel(x, y, bi.getData().getPixel(x, y, new double[3])[chnl]);
			}
		}
	}
	
	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public double getPixel(int x, int y) {
		if (!validPixel(x, y)) {
			throw new IndexOutOfBoundsException();
		}
	
		return  channel[y * this.getWidth() + x];
	}
	
	public void setPixel(int x, int y, double color){
		if (!validPixel(x, y)) {
			throw new IndexOutOfBoundsException();
		}
		
		channel[y * this.getWidth() + x] = color;
	}

	public SingleChannel cropImage(int x1, int y1, int x2,	int y2) throws InvalidImageException {
		if(!validSquare(x1,y1, x2, y2)){
			throw new InvalidImageException();
		}
		
		SingleChannel newImage = new SingleChannel(y2 - y1, x2 - x1);	
		int i = 0;
		for(int y = y1; y < y2; y++) {
			for(int x = x1; x < x2 ; x++) {
				newImage.channel[i++] = this.getPixel(x, y);				
			}
		}
		
		return newImage;
	}

	public boolean validPixel(int x, int y) {
		boolean validX = x >= 0 && x < this.getWidth();
		boolean validY = y >= 0 && y < this.getHeight(); 
		return validX && validY;
	}
	
	public boolean validSquare(int x1, int y1, int x2, int y2){
		boolean validX = this.validPixel(x1, y1);
		boolean validY = this.validPixel(x2, y2);
		boolean validSquare = (x1 < x2) && (y1 < y2);
		return validX && validY && validSquare;
	}
	
	public double[] getPixels() {
		return this.channel;
	}

	public void add(SingleChannel channel2) {
		for( int x = 0 ; x < width ; x++ ) {
			for( int y = 0 ; y < height ; y++) {
				double color = this.getPixel(x, y) + channel2.getPixel(x, y);
				this.setPixel(x, y, color);
			}
		}
	}
	
	public void substract(SingleChannel channel2) {
		for( int x = 0 ; x < width ; x++ ) {
			for( int y = 0 ; y < height ; y++) {
				double color = this.getPixel(x, y) - channel2.getPixel(x, y);
				this.setPixel(x, y, color);
			}
		}
	}
	
	public void multiply(SingleChannel channel2) {
		for( int x = 0 ; x < width ; x++ ) {
			for( int y = 0 ; y < height ; y++) {
				double color = this.getPixel(x, y) * channel2.getPixel(x, y);
				this.setPixel(x, y, color);
			}
		}
	}

	public void dynamicRangeCompression(double min, double max) {
		double c = (MAX_CHANNEL_COLOR - 1) / Math.log(1 + max - min);		
		for( int x = 0 ; x < width ; x++ ) {
			for( int y = 0 ; y < height ; y++) {
				double color = (double) (c * Math.log(1 + this.getPixel(x, y) - min));
				this.setPixel(x, y, color);
			}
		}
	}

	public void negative() {
		for( int x = 0 ; x < width ; x++ ) {
			for( int y = 0 ; y < height ; y++) {
				double color = this.getPixel(x, y);
				this.setPixel(x, y, MAX_CHANNEL_COLOR - color);
			}
		}
	}
	
	public void threshold(double thresholdLimit) {
		double blackColor = MIN_CHANNEL_COLOR;
		double whiteColor = MAX_CHANNEL_COLOR;
		
		for( int x = 0 ; x < width ; x++ ) {
			for( int y = 0 ; y < height ; y++) {
				double colorToApply = this.getPixel(x, y) > thresholdLimit? whiteColor : blackColor;
				this.setPixel(x, y, colorToApply);
			}
		}
	}
	
	public void incrementContrast(double x1, double x2, double y1, double y2) {
		if(!(x1 < x2) || !(y1 < y2)) {
			throw new IllegalArgumentException("The params are incorrect because they have no order");
		}
		
		for( int x = 0 ; x < width ; x++ ){
			for( int y = 0 ; y < height ; y++){
				double thisPixel = this.getPixel(x, y);
				
				double m = 0;
				double b = 0;
				if(thisPixel < x1) {
					m = y1 / x1;
					b = 0;
				} else if(thisPixel > x2) {
					m = (255 - y2) / (255 - x2);
					b = y2 - m * x2;
				} else {
					m = (y2 - y1) / (x2 - x1);
					b = y1 - m * x1;
				}
				double newPixel = m * thisPixel + b;
				
				this.setPixel(x, y, newPixel);
			}
		}
	}
	
	public void equalize() {
		int[] histData = this.getColorOccurrences();
		double[] newChannel = new double[this.channel.length];
		
		for( int i = 0 ; i < newChannel.length ; i++ ){
				int grayLevel = truncatePixel((int)Math.floor(this.channel[i]));
				
				double newValue = 0;
				for( int k = 0 ; k < grayLevel ; k++){
					newValue += histData[k];
				}
				
				newValue = newValue * (255.0/newChannel.length);
				newChannel[i] = newValue;
		}
		
		this.channel = newChannel;
	}
	
	private int[] getColorOccurrences() {
		int[] dataset = new int[Image.GRAY_LEVEL_AMOUNT];
		
		for( int i = 0 ; i < this.channel.length ; i++ ){
				int grayLevel = truncatePixel((int)Math.floor(this.channel[i]));
				dataset[grayLevel] += 1;
		}
		
		return dataset;
	}
	
	public void multiply(double scalar) {
		for(int i = 0 ; i < this.channel.length ; i++){
			double newValue = this.channel[i] * scalar;
			this.channel[i] = newValue;
		}
	}
	
	int truncatePixel(double notTruncatedValue) {
//		notTruncatedValue = Math.abs(notTruncatedValue);
		if(notTruncatedValue > SingleChannel.MAX_CHANNEL_COLOR) {
			return SingleChannel.MAX_CHANNEL_COLOR;
		} else if(notTruncatedValue < SingleChannel.MIN_CHANNEL_COLOR) {
			return SingleChannel.MIN_CHANNEL_COLOR;
		}
		return (int)notTruncatedValue;
	}
	
	@Override
	public SingleChannel clone() {
		SingleChannel newChannel = new SingleChannel(width, height);
		
		for(int i = 0 ; i < width ; i++ ){
			for(int j = 0 ; j < height ; j++){
				newChannel.setPixel(i, j, this.getPixel(i, j));
			}
		}
		return newChannel;
	}
	
	public void applyMask(Mask mask){
		SingleChannel newChannel = new SingleChannel(this.width, this.height);
		for( int x = 0 ; x < width ; x++ ){
			for( int y = 0 ; y < height ; y++){
				double newPixel = applyPixelMask(x, y, mask);
				newChannel.setPixel(x, y, newPixel);
			}
		}
		this.channel = newChannel.channel;
	}

	private double applyPixelMask(int x, int y, Mask mask) {
		boolean ignoreByX = x < mask.getWidth() / 2 || x > this.getWidth() - mask.getWidth() / 2;
		boolean ignoreByY = y < mask.getHeight() / 2 || y > this.getHeight() - mask.getHeight() / 2;
		if(ignoreByX || ignoreByY) {
			return this.getPixel(x, y);
		}
		
		double newColor = 0;
		for(int i = - mask.getWidth() / 2 ; i <= mask.getWidth() / 2; i++) {
			for(int j = - mask.getHeight() / 2; j <= mask.getHeight() / 2; j++) {
				if(this.validPixel(x + i, y + j)) {
					double oldColor = this.getPixel(x + i, y + j);
					newColor += oldColor * mask.getValue(i, j);
				}
			}
		}
		return newColor;
	}
	
	public void applyMedianMask(Point maskSize) {
		SingleChannel newChannel = new SingleChannel(this.width, this.height);
		for( int x = 0 ; x < width ; x++ ){
			for( int y = 0 ; y < height ; y++){
				double newPixel = applyMedianPixelMask(x, y, maskSize);
				newChannel.setPixel(x, y, newPixel);
			}
		}
		this.channel = newChannel.channel;
	}

	private double applyMedianPixelMask(int x, int y, Point maskSize) {
		TreeSet<Double> pixelsAffected = new TreeSet<Double>(); 
		for(int i = - maskSize.x / 2 ; i <= maskSize.x / 2; i++) {
			for(int j = - maskSize.y / 2; j <= maskSize.y / 2; j++) {
				if(this.validPixel(x + i, y + j)) {
					double oldColor = this.getPixel(x + i, y + j); 
					pixelsAffected.add(oldColor);
				}
			}
		}
		
		double valueToReturn = 0;
		int indexToReturn = pixelsAffected.size() / 2;
		Iterator<Double> iterator = pixelsAffected.iterator();
		for(int i = 0; iterator.hasNext(); i++) {
			double each = iterator.next();
			if(i == indexToReturn) {
				valueToReturn = each;
			}
		}
		return valueToReturn;
	}
	
}
