/**
 * Filter that flips the image horizontally.
 */
public class FlipHorizontalFilter implements Filter
{
    public void filter(PixelImage pi) {
        Pixel[][] data = pi.getData();
        Pixel[][] newData = new Pixel[data.length][data[0].length];

        // FILL IN METHOD HERE
        for (int row = 0; row < data.length; row++) {
			for (int col = 0; col < data[0].length; col++) {
				newData[row][col] = data[row][data[0].length-col-1];
			}
		}


        pi.setData(newData);
    }
}
