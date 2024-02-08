/**
 * A class to configure the SnapShop application
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SnapShopConfiguration
{
    /**
     * Method to configure the SnapShop.  Call methods like addFilter
     * and setDefaultFilename here.
     * @param theShop A pointer to the application
     */
    public static void configure(SnapShop theShop)
    {   theShop.setDefaultFilename("C:/Users/jkoss/eclipse-workspace/ImageManipulation/src/monet.jpg");
        theShop.addFilter(new FlipHorizontalFilter(), "Flip Horizontal");
        theShop.addFilter(new FlipVerticalFilter(), "Flip Vertical");
        theShop.addFilter(new LightenBy10Filter(), "Lighten by 10 Image" );
        theShop.addFilter(new DarkenBy10Filter(), "Darken by 10 Image" );
        theShop.addFilter(new NegativeImageFilter(), "Reverse Image");
        theShop.addFilter(new GrayScaleFilter(), "Gray Scale Image" );

        theShop.addFilter(new GaussianBlurFilter(), "Gaussian Blur Image");
        theShop.addFilter(new LaplacianFilter(), "Laplacian Image");
        theShop.addFilter(new UnsharpFilter(), "Unsharpen Image" );
        theShop.addFilter(new EdgyFilter(), "Edge Image" );
    }
}