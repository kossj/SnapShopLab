/**
 * Filter that flips the image horizontally.
 */
public class LightenBy10Filter implements Filter
{
    public void filter(PixelImage pi) {
        Pixel[][] data = pi.getData();
        Pixel[][] newData = new Pixel[data.length][data[0].length];

        // FILL IN METHOD HERE
        for (int row = 0; row < data.length; row++) {
			for (int col = 0; col < data[0].length; col++) {
				Pixel old = data[row][col];
				newData[row][col] = new Pixel(Math.min(255, old.getRed()+10), Math.min(255, old.getGreen()+10), Math.min(255, old.getBlue()+10));
			}
		}


        pi.setData(newData);
    }
}
