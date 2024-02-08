/**
 * Filter that flips the image horizontally.
 */
public class GaussianBlurFilter implements Filter
{
    public void filter(PixelImage pi) {
        Pixel[][] data = pi.getData();
        Pixel[][] newData = new Pixel[data.length][data[0].length];
        
        // rowPermutation colPermutation weight
        int[][] matrixPermutations = new int[][] {
        		{-1,-1,1},
        		{0,-1,2},
        		{1,-1,1},
        		{-1,0,2},
        		{0,0,4},
        		{1,0,2},
        		{-1,1,1},
        		{0,1,2},
        		{1,1,1}
        };
        
        // FILL IN METHOD HERE
        for (int row = 0; row < data.length; row++) {
			for (int col = 0; col < data[0].length; col++) {				
				int r_sum=0, g_sum=0, b_sum=0;
				int divSum=0;
				
				for (int[] permutation : matrixPermutations) {
					int rowPermutation = permutation[0]; int colPermutation = permutation[1]; int weight = permutation[2];
					
					if (row+rowPermutation<0 || row+rowPermutation>=data.length || col+colPermutation<0 || col+colPermutation>=data[row].length) continue;
					Pixel p = data[row+rowPermutation][col+colPermutation];
					
					r_sum += p.getRed()*weight;
					b_sum += p.getBlue()*weight;
					g_sum += p.getGreen()*weight;
					
					divSum+=weight;
				}
				
				newData[row][col] = new Pixel(r_sum/divSum, g_sum/divSum, b_sum/divSum);
			}
		}


        pi.setData(newData);
    }
}
