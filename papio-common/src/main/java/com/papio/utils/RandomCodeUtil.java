package com.papio.utils;




import java.awt.*;
import java.util.Random;

public  class RandomCodeUtil {
	private static Random random = new Random();

	private static Color getRandColor(int fc, int bc) {
		if (fc > 255){
			fc = 255;
		}

		if (bc > 255){
			bc = 255;
		}

		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	private static int getRandomIntColor() {
		int[] rgb = getRandomRgb();
		int color = 0;
		for (int c : rgb) {
			color = color << 8;
			color = color | c;
		}
		return color;
	}

	private static int[] getRandomRgb() {
		int[] rgb = new int[3];
		for (int i = 0; i < 3; i++) {
			rgb[i] = random.nextInt(255);
		}
		return rgb;
	}

	private static void shear(Graphics g, int w1, int h1, Color color) {
		shearX(g, w1, h1, color);
		shearY(g, w1, h1, color);
	}

	private static void shearX(Graphics g, int w1, int h1, Color color) {

		int period = random.nextInt(2);

		boolean borderGap = true;
		int frames = 1;
		int phase = random.nextInt(2);

		for (int i = 0; i < h1; i++) {
			double d = (double) (period >> 1)
					* Math.sin((double) i / (double) period + (6.2831853071795862D * (double) phase) / (double) frames);
			g.copyArea(0, i, w1, 1, (int) d, 0);
			if (borderGap) {
				g.setColor(color);
				g.drawLine((int) d, i, 0, i);
				g.drawLine((int) d + w1, i, w1, i);
			}
		}

	}

	private static void shearY(Graphics g, int w1, int h1, Color color) {

		int period = random.nextInt(40) + 10; // 50;

		boolean borderGap = true;
		int frames = 20;
		int phase = 7;
		for (int i = 0; i < w1; i++) {
			double d = (double) (period >> 1)
					* Math.sin((double) i / (double) period + (6.2831853071795862D * (double) phase) / (double) frames);
			g.copyArea(i, 0, 1, h1, 0, (int) d);
			if (borderGap) {
				g.setColor(color);
				g.drawLine(i, (int) d, i, 0);
				g.drawLine(i, (int) d + h1, i, h1);
			}

		}
	}

	/**
	 * 生成指定长度的随机数字和字母
	 * @param length
	 * @return
	 */
	public static String getStringRandom(int length) {
		String val = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			switch (charOrNum) {
				case "char":
					int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
					val += (char) (random.nextInt(26) + temp);
					break;
				case "num":
					val += String.valueOf(random.nextInt(10));
					break;
			}
		}
		return val;
	}

	/**
	 * 生成昵称
	 * @param length
	 * @param type
	 * @return
	 */
	public static String getStringRandom(int length ,String type) {
		String val = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {

			switch (type) {
				case "char":
					int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
					val += (char) (random.nextInt(26) + temp);
					break;
				case "num":
					val += String.valueOf(random.nextInt(10));
					break;
			}
		}
		return val;
	}

	/**
	 * Base64编码的验证码图片
	 * @param w
	 * @param h
	 * @param code
	 * @return
	 * @throws Exception
	 */
	/*public static ImageCode imageToBase64(int w, int h, String code) throws Exception {
		int verifySize = code.length();
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Random rand = new Random();
		Graphics2D g2 = image.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Color[] colors = new Color[5];
		Color[] colorSpaces = new Color[] { Color.WHITE, Color.CYAN, Color.GRAY, Color.LIGHT_GRAY, Color.MAGENTA,
				Color.ORANGE, Color.PINK, Color.YELLOW };
		float[] fractions = new float[colors.length];
		for (int i = 0; i < colors.length; i++) {
			colors[i] = colorSpaces[rand.nextInt(colorSpaces.length)];
			fractions[i] = rand.nextFloat();
		}
		Arrays.sort(fractions);

		g2.setColor(Color.GRAY);// 设置边框色
		g2.fillRect(0, 0, w, h);

		Color c = getRandColor(200, 250);
		g2.setColor(c);// 设置背景色
		g2.fillRect(0, 2, w, h - 4);

		// 绘制干扰线
		Random random = new Random();
		g2.setColor(getRandColor(160, 200));// 设置线条的颜色
		for (int i = 0; i < 20; i++) {
			int x = random.nextInt(w - 1);
			int y = random.nextInt(h - 1);
			int xl = random.nextInt(6) + 1;
			int yl = random.nextInt(12) + 1;
			g2.drawLine(x, y, x + xl + 40, y + yl + 20);
		}

		// 添加噪点
		float yawpRate = 0.05f;// 噪声率
		int area = (int) (yawpRate * w * h);
		for (int i = 0; i < area; i++) {
			int x = random.nextInt(w);
			int y = random.nextInt(h);
			int rgb = getRandomIntColor();
			image.setRGB(x, y, rgb);
		}

		shear(g2, w, h, c);// 使图片扭曲

		g2.setColor(getRandColor(100, 160));
		int fontSize = h - 4;
		Font font = new Font("Arial", Font.ITALIC, fontSize);
		g2.setFont(font);
		char[] chars = code.toCharArray();
		for (int i = 0; i < verifySize; i++) {
			AffineTransform affine = new AffineTransform();
			affine.setToRotation(Math.PI / 4 * rand.nextDouble() * (rand.nextBoolean() ? 1 : -1),
					(w / verifySize) * i + fontSize / 2, h / 2);
			g2.setTransform(affine);
			g2.drawChars(chars, i, 1, ((w - 10) / verifySize) * i + 5, h / 2 + fontSize / 2 - 10);
		}
		g2.dispose();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(image, "jpg", baos);
		return  new ImageCode(image,code,60);
	}
*/

	/**
	 * 随机获得四位验证码
	 * @return
	 */
	public static String msgValidatecode(){
		return String.valueOf(new Random().nextInt(8999) + 1000);
	}

}