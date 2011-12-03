package edu.rit.se.sse.rapdevx.gui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Text implements Drawable{
	
	private final File fontFile = new File("assets/FontPage.png");
	private final File otherFile = new File("assets/FontPageWhite.png");
	private String toConvert;
	private int xCord;
	private int yCord;
	private int textScale;
	
	public Text(String toConvert, int xCord, int yCord) {
		
		this.toConvert = toConvert.toUpperCase();
		this.xCord = xCord;
		this.yCord = yCord;
		this.textScale = 2;
		
	}
	
	public Text(String toConvert, int xCord, int yCord, int textSize) {
		
		this.toConvert = toConvert.toUpperCase();
		this.xCord = xCord;
		this.yCord = yCord;
		this.textScale = textSize;
		
	}

	public void update() {
		
		
		
	}

	public void draw(Graphics2D gPen) {
		
		char[] toConvertArray = toConvert.toCharArray();
		int tempX = this.xCord;
		
		for ( char character : toConvertArray ) {
			
			int xIndex;
			BufferedImage largeImage;
			BufferedImage smallImage = null;
			
			if (character >= 65 && character <= 90) {
				
				xIndex = (int) character - 65;
				xIndex *= 6;
				
				try {
					
					largeImage = ImageIO.read(fontFile);
					smallImage = largeImage.getSubimage(xIndex, 0, 7, 7);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			
			} else if (character >= 97 && character <= 122) {
				
				xIndex = (int) character - 97;
				xIndex *= 6;
				
				try {
					
					largeImage = ImageIO.read(fontFile);
					smallImage = largeImage.getSubimage(xIndex, 12, 7, 7);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} else if (character >= 33 && character <= 58) {
				
				xIndex = (int) character - 33;
				xIndex *= 6;
				
				try {
					
					largeImage = ImageIO.read(fontFile);
					smallImage = largeImage.getSubimage(xIndex, 24, 7, 7);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} else if (character >= 59 && character <= 63) {
				
				xIndex = (int) character - 59;
				xIndex *= 6;
				
				try {
					
					largeImage = ImageIO.read(fontFile);
					smallImage = largeImage.getSubimage(xIndex, 36, 7, 7);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} else if (character >= 91 && character <= 96) {
				
				xIndex = (int) character - 91;
				xIndex *= 6;
				xIndex += 30;
				
				try {
					
					largeImage = ImageIO.read(fontFile);
					smallImage = largeImage.getSubimage(xIndex, 36, 7, 7);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} else if (character == 124) {
				
				xIndex = 66;
				
				try {
					
					largeImage = ImageIO.read(fontFile);
					smallImage = largeImage.getSubimage(xIndex, 36, 7, 7);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} else if (character == 32) {
				
				try {
					
					largeImage = ImageIO.read(fontFile);
					smallImage = largeImage.getSubimage(0, 100, 7, 7);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}else {
				
				try {
					
					largeImage = ImageIO.read(fontFile);
					smallImage = largeImage.getSubimage(0, 48, 7, 7);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
			gPen.drawImage(smallImage, tempX, yCord, smallImage.getWidth()*this.textScale, 
					smallImage.getHeight()*this.textScale, null);
			
			tempX += 6 * this.textScale;
			
		}
		
	}
	
	public void drawColor(Graphics2D gPen, int toReplace) {
		
		int replaceWith = 0x000000;
		char[] toConvertArray = toConvert.toCharArray();
		int tempX = this.xCord;
			
		for ( char character : toConvertArray ) {
				
			int xIndex;
			BufferedImage largeImage;
			BufferedImage smallImage = null;
			ImageColorizer colorize = null;
				
			if (character >= 65 && character <= 90) {
					
				xIndex = (int) character - 65;
				xIndex *= 6;
					
				try {
						
					largeImage = ImageIO.read(fontFile);
					smallImage = largeImage.getSubimage(xIndex, 0, 7, 7);
					colorize = new ImageColorizer(smallImage);
					colorize.recolorStrong(replaceWith, toReplace);
						
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} else if (character >= 97 && character <= 122) {
					
				xIndex = (int) character - 97;
				xIndex *= 6;
					
				try {
						
					largeImage = ImageIO.read(fontFile);
					smallImage = largeImage.getSubimage(xIndex, 12, 7, 7);
					colorize = new ImageColorizer(smallImage);
					colorize.recolorStrong(replaceWith, toReplace);
						
				} catch (IOException e) {
					e.printStackTrace();
				}
					
			} else if (character >= 33 && character <= 58) {
					
				xIndex = (int) character - 33;
				xIndex *= 6;
					
				try {
						
					largeImage = ImageIO.read(fontFile);
					smallImage = largeImage.getSubimage(xIndex, 24, 7, 7);
					colorize = new ImageColorizer(smallImage);
					colorize.recolorStrong(replaceWith, toReplace);
						
				} catch (IOException e) {
					e.printStackTrace();
				}
					
			} else if (character >= 59 && character <= 63) {
				
				xIndex = (int) character - 59;
				xIndex *= 6;
					
				try {
						
					largeImage = ImageIO.read(fontFile);
					smallImage = largeImage.getSubimage(xIndex, 36, 7, 7);
					colorize = new ImageColorizer(smallImage);
					colorize.recolorStrong(replaceWith, toReplace);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
					
			} else if (character >= 91 && character <= 96) {
					
				xIndex = (int) character - 91;
				xIndex *= 6;
				xIndex += 30;
					
				try {
					
					largeImage = ImageIO.read(fontFile);
					smallImage = largeImage.getSubimage(xIndex, 36, 7, 7);
					colorize = new ImageColorizer(smallImage);
					colorize.recolorStrong(replaceWith, toReplace);
						
				} catch (IOException e) {
					e.printStackTrace();
				}
					
			} else if (character == 124) {
					
				xIndex = 66;
				
				try {
						
					largeImage = ImageIO.read(fontFile);
					smallImage = largeImage.getSubimage(xIndex, 36, 7, 7);
					colorize = new ImageColorizer(smallImage);
					colorize.recolorStrong(replaceWith, toReplace);
						
				} catch (IOException e) {
					e.printStackTrace();
				}
					
			} else if (character == 32) {
					
				try {
						
					largeImage = ImageIO.read(fontFile);
					smallImage = largeImage.getSubimage(0, 100, 7, 7);
					colorize = new ImageColorizer(smallImage);
					colorize.recolorStrong(replaceWith, toReplace);
						
				} catch (IOException e) {
					e.printStackTrace();
				}
					
			}else {
					
				try {
						
					largeImage = ImageIO.read(fontFile);
					smallImage = largeImage.getSubimage(0, 48, 7, 7);
					colorize = new ImageColorizer(smallImage);
					colorize.recolorStrong(replaceWith, toReplace);
						
				} catch (IOException e) {
					e.printStackTrace();
				}
					
			}
				
			gPen.drawImage(colorize.copyAndRecolor(replaceWith, toReplace), tempX, yCord, smallImage.getWidth()*this.textScale, 
				smallImage.getHeight()*this.textScale, null);
				
			tempX += 6 * this.textScale;
				
	}
		
}
	

	
	public void drawWhite(Graphics2D gPen) {
		
		char[] toConvertArray = toConvert.toCharArray();
		int tempX = this.xCord;
		
		for ( char character : toConvertArray ) {
			
			int xIndex;
			BufferedImage largeImage;
			BufferedImage smallImage = null;
			
			if (character >= 65 && character <= 90) {
				
				xIndex = (int) character - 65;
				xIndex *= 6;
				
				try {
					
					largeImage = ImageIO.read(otherFile);
					smallImage = largeImage.getSubimage(xIndex, 0, 7, 7);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			
			} else if (character >= 97 && character <= 122) {
				
				xIndex = (int) character - 97;
				xIndex *= 6;
				
				try {
					
					largeImage = ImageIO.read(fontFile);
					smallImage = largeImage.getSubimage(xIndex, 12, 7, 7);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} else if (character >= 33 && character <= 58) {
				
				xIndex = (int) character - 33;
				xIndex *= 6;
				
				try {
					
					largeImage = ImageIO.read(fontFile);
					smallImage = largeImage.getSubimage(xIndex, 24, 7, 7);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} else if (character >= 59 && character <= 63) {
				
				xIndex = (int) character - 59;
				xIndex *= 6;
				
				try {
					
					largeImage = ImageIO.read(fontFile);
					smallImage = largeImage.getSubimage(xIndex, 36, 7, 7);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} else if (character >= 91 && character <= 96) {
				
				xIndex = (int) character - 91;
				xIndex *= 6;
				xIndex += 30;
				
				try {
					
					largeImage = ImageIO.read(fontFile);
					smallImage = largeImage.getSubimage(xIndex, 36, 7, 7);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} else if (character == 124) {
				
				xIndex = 66;
				
				try {
					
					largeImage = ImageIO.read(fontFile);
					smallImage = largeImage.getSubimage(xIndex, 36, 7, 7);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} else if (character == 32) {
				
				try {
					
					largeImage = ImageIO.read(fontFile);
					smallImage = largeImage.getSubimage(0, 100, 7, 7);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}else {
				
				try {
					
					largeImage = ImageIO.read(fontFile);
					smallImage = largeImage.getSubimage(0, 48, 7, 7);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
			gPen.drawImage(smallImage, tempX, yCord, smallImage.getWidth()*this.textScale, 
					smallImage.getHeight()*this.textScale, null);
			
			tempX += 6 * this.textScale;
			
		}
		
	}
	
	public int getSizeOnScreen() {
		
		int size = toConvert.length() * (7 * this.textScale);
		
		System.out.println(xCord);
		
		return size;
		
	}

}
