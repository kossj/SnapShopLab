/**
 * Filter that flips the image horizontally.
 */
public class GrayScaleFilter implements Filter
{
    public void filter(PixelImage pi) {
        Pixel[][] data = pi.getData();
        Pixel[][] newData = new Pixel[data.length][data[0].length];

        // FILL IN METHOD HERE
        for (int row = 0; row < data.length; row++) {
			for (int col = 0; col < data[0].length; col++) {
				Pixel old = data[row][col];
				int avg = (old.getRed()+old.getGreen()+old.getBlue()) / 3;
				newData[row][col] = new Pixel(avg, avg, avg);
			}
		}


        pi.setData(newData);
    }
}
