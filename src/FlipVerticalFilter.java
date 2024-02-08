public class FlipVerticalFilter implements Filter
{
   public void filter(PixelImage pi) {
       Pixel[][] data = pi.getData();
       Pixel[][] newData = new Pixel[data.length][data[0].length];

       // FILL IN METHOD HERE
       for (int row = 0; row < data.length; row++) {
    	   newData[row] = data[data.length-row-1];
	}


       pi.setData(newData);
    }
}
