/**
 * Filter that flips the image horizontally.
 */
public class DarkenBy10Filter implements Filter
{
    public void filter(PixelImage pi) {
        Pixel[][] data = pi.getData();
        Pixel[][] newData = new Pixel[data.length][data[0].length];

        // FILL IN METHOD HERE
        for (int row = 0; row < data.length; row++) {
			for (int col = 0; col < data[0].length; col++) {
				Pixel old = data[row][col];
				newData[row][col] = new Pixel(clamp(old.getRed()-10), clamp(old.getGreen()-10), clamp(old.getBlue()-10));
			}
		}


        pi.setData(newData);
    }

    private int clamp(int val) {
        return Math.max(Math.min(255, val), 0);
    }
}
