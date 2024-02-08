/**
 * Filter that flips the image horizontally.
 */
public class LaplacianFilter implements Filter
{
    public void filter(PixelImage pi) {
        Pixel[][] data = pi.getData();
        Pixel[][] newData = new Pixel[data.length][data[0].length];
        
        // rowPermutation colPermutation weight
        int[][] matrixPermutations = new int[][] {
        		{-1,-1,-1},
        		{0,-1,-1},
        		{1,-1,-1},
        		{-1,0,-1},
        		{0,0,8},
        		{1,0,-1},
        		{-1,1,-1},
        		{0,1,-1},
        		{1,1,-1}
        };
        
        // FILL IN METHOD HERE
        for (int row = 0; row < data.length; row++) {
			for (int col = 0; col < data[0].length; col++) {				
				int r_sum=0, g_sum=0, b_sum=0;
				
				for (int[] permutation : matrixPermutations) {
					int rowPermutation = permutation[0]; int colPermutation = permutation[1]; int weight = permutation[2];
					
					if (row+rowPermutation<0 || row+rowPermutation>=data.length || col+colPermutation<0 || col+colPermutation>=data[row].length) continue;
					Pixel p = data[row+rowPermutation][col+colPermutation];
					
					r_sum += p.getRed()*weight;
					b_sum += p.getBlue()*weight;
					g_sum += p.getGreen()*weight;
					
				}
				Pixel c = data[row][col];
				newData[row][col] = new Pixel(clamp(c.getRed()+r_sum), clamp(c.getGreen()+g_sum), clamp(c.getBlue()+b_sum));
			}
		}


        pi.setData(newData);
    }
    
    private int clamp(int val) {
    	return Math.max(Math.min(255, val), 0);
    }
}
