package edu.rit.se.sse.rapdevx;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.rit.se.sse.rapdevx.gui.ImageColorizer;

public class ImageColorizerTest {

	
	protected ImageColorizer ic;
	protected BufferedImage baseImage;
	@Before
	public void setUp() throws Exception {
		baseImage = ImageIO.read(new File("assets/ship.png"));
		ic = new ImageColorizer(baseImage);
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testImageColorizer() {
	}

	@Test
	public void testRecolor() {
		ic.recolor(0xFF00FF00, 0x00D6007F);
		try {
			ImageIO.write(ic, "png",new File("assets/_test_shipChanged.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageColorizer count= null;
		try {
			count = new ImageColorizer(ImageIO.read(new File("assets/_test_shipChanged.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assert(count.findRecolorMarks(0xFF00FF00).size()>0);
		
		try {
			baseImage = ImageIO.read(new File("assets/ship.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ic = new ImageColorizer(baseImage);
		ic.recolor(0x70FFFF00,0x00D6007F);
		try {
			ImageIO.write(ic, "png",new File("assets/_test_shipChangedAlpha.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			count = new ImageColorizer(ImageIO.read(new File("assets/_test_shipChangedAlpha.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		assert(count.findRecolorMarks(0xFFFFFF00).size()>0);
	}

	
	@Test
	public void testCopyAndRecolor(){
		BufferedImage kit = ic.copyAndRecolor(0xFF00FF00, 0x00D6007F);
		try {
			ImageIO.write(kit, "png",new File("assets/_test_shipBLA.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedImage kat;
		kat =ic.copyAndRecolor(0x70FFFF00, 0x00D6007F);
		try {
			ImageIO.write(kat, "png",new File("assets/_test_shipBLB.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testRecolorStrong() {
		ic.recolor(0xFF00FF00, 0x00D6007F);
		try {
			ImageIO.write(ic, "png",new File("assets/_test_shipChanged.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageColorizer count= null;
		ImageColorizer strongCount = null;
		try {
			count = new ImageColorizer(ImageIO.read(new File("assets/_test_shipChanged.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			baseImage = ImageIO.read(new File("assets/ship.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ic = new ImageColorizer(baseImage);
		ic.recolorStrong(0x00FF00, 0x00D6007F);
		try {
			ImageIO.write(ic, "png",new File("assets/_test_shipChangedStrong.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			strongCount = new ImageColorizer(ImageIO.read(new File("assets/_test_shipChangedStrong.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertTrue(strongCount.findRecolorMarks(0x00FF00).size() == count.findRecolorMarks(0x00FF00).size());
		
	}

	@Test
	public void testFindRecolorMarks() {
		assert(ic.findRecolorMarks(0x00D6007F).size() > 0);
		assert(ic.findRecolorMarks(0x00FF00).size() ==0);
	}

}
