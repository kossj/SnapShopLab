x`Edited and adapted with permission by Kyle Gillette from the original web page.
Image Representation
A digital image is a rectangular array of pixel objects. Each pixel contains three integer values that range from 0 to 255, one integer each for the red, green, and blue components of the pixel, in that order. The larger a number, the brighter that color appears in the pixel. So a pixel with values (0,0,0) is black, (255,255,255) is white, (255,0,0) is pure red, (0,0,255) is pure blue, and so forth.  A constructor, accessors and mutators are provided in the Pixel class.

An  image is represented by an instance of class PixelImage. This class contains the methods getData() and setData() to retrieve 2-dimensional arrays of Pixel objects representing the pixels of the image. You can also use the methods getHeight() and getWidth() to get the height and width of the image. 
The Application
We have implemented a PhotoShop-like Java application, called SnapShop. The application knows how to load image files, and provides all the user interface objects you'll need to apply your filters to the image. You will need not implement anything in the SnapShop class, but create a few new classes. The file loader of the SnapShop class expects filenames to be fully specified, that is, you must say something like c:\directory\image.jpg. Forward slashes also seem to work: c:/directory/image.jpg.   It also seems to find unspecified names if they are in a directory that contains the class files. To save you some work, we've provided a shortcut so you don't need to always retype the file name; details below. [In BlueJ] To run the application, either create a new SnapShop object, or right click on the SnapShop class and run the test() method.  [Jcreator] Requires a main method to launch the test() method found in SnapShot.

We have also provided an interface class Filter. Remember that an interface simply specifies the methods another class must implement, and cannot be used to make objects itself. You will be writing classes that implement this Filter interface, one for each transformation you write. Each class implementing the Filter interface must have a method called filter(), which takes a PixelImage as an argument. The method then applies a transformation to the data in the image. As an example, we have included the FlipHorizontalFilter class, which flips an image horizontally.

You will need some way to tell our SnapShop class which filters you have implemented. So we've provided a class called SnapShopConfiguration, with a single method, configure(). In this method, you can call methods for the SnapShop object. The two methods you'll be interested in are addFilter(), which creates a button in the application to apply your filter, and setDefaultFilename(), which lets you specify a default path or filename for the file loader, to aid you in testing. For each filter that you create, there should be a call to addFilter() in the configure() method.
(A note on setDefaultFilename(). Windows path names have backslashes (\) in them. To specify this in a string in a Java source program, you need to put two backslashes. For example, the Java string for c:\directory\image.jpg would be "c:\\directory\\image.jpg".)

The files to get started are SnapShop.java, SnapShopConfiguration.java, Pixel.java, PixelImage.java, Filter.java, FlipHorizontalFilter.java. Use a small jpeg image.  Larger images can take some time to process.
Simple Transformations
There are two kinds of transformations that you are required to implement. The simple transformations can be implemented by replacing each Pixel in the existing image with the updated one. The more complex 3x3 transformations require creating a new array of Pixels with the transformed image, then updating the image instance variable to refer to the new array once it is completely initialized.
We have implemented flipHorizontalFilter for you as an example.  It swaps pixels, but does not change the values of the pixels.  
Four transformations you should implement:  flipVerticalFilter, flipHorizontalFilter, negativeImageFilter, and GreyScaleFilter.

negativeImageFilter  is done by replacing each Pixel in the image with a new Pixel whose rgb values are calculated by subtracting the original rgb values from 255. These subtractions are done individually for each of the red, green, and blue colors.  Each pixel is changed as follows:  if a particular pixel has a value of 100 for red, 240 for blue, and 0 for green, its corresponding negative image pixel will be 155 for red, 15 for blue, and 255 for green.

greyScaleFilter replaces the red, blue, and green values with the average of the red, green, and blue values in the original pixel.  So red, green, and blue will contain the same numeric value.  If a particular pixel has a value of 100 for red, 260 for blue, and 0 for green, then the resulting grey pixel will have 120 for red, 120 for green, and 120 for blue. The monet.jpg image that is included with the project is called impression sunrise.  Note what happens to the sun when the grey scale is applied.  How did Monet “see” this?

3x3 Transformations
Once you’ve got the simple transformations working, you should implement Gaussian blur and Edgy.  All of these transformations are based on the following idea: each pixel in the transformed image is calculated from the values of the original pixel and its immediate neighbors, i.e., the 3x3 array of pixels centered on the old pixel whose new value we are trying to calculate. The new rgb values can be obtained by calculating a weighted average; the median, minimum, or maximum; or something else. As with the negate transformation, the calculations are carried out independently for each color, i.e., the new red value for a pixel is obtained from the old red values, and similarly for red and blue.

Here are the weights for the 3x3 transformations you should implement.

Gaussian
1 2 1
2 4 2
1 2 1

After computing the weighted sum, the result must be divided by 16 to scale the numbers back down to the range 0 to 255. The effect is to blur the image.

Laplacian
-1 -1 -1
-1 8 -1
-1 -1 -1

The neighboring pixel values are subtracted from 8 times the center one, so no scaling is needed. However, you do need to check that the weighted average is between 0 and 255. If it is less than 0, replace the calculated value with 0 (i.e., the new value is the maximum of 0 and the calculated value). If it is greater than 255, then replace the calculated value with 255. This transformation detects and highlights edges.

Unsharp masking
-1 -2 -1
-2 28 -2
-1 -2 -1

This transformation is created by multiplying the center pixel and subtracting the Gaussian weighted average. The result must be divided by 16 to scale it back down to the range 0 to 255. As with the Laplacian transformation, check for negative weighted averages or weighted averages greater than 255 (and do the same thing as in the Laplacian case to fix it).

Edgy
-1 -1 -1
-1 9 -1
-1 -1 -1
	
This adds the Laplacian weighted average to the original pixel, which sharpens the edges in the image. It does not need scaling, but you need to watch for weighted averages less than 0 or greater than 255.

Notes: 
    • The complication with these transformations is that the new value of each pixel depends on the neighboring ones, as well as itself. That means we cannot replace the original pixels with new values before the old values have been used to compute the new values of their neighbors. The simplest way to handle this is to create a new 2D Pixel array the same size as the old image, compute Pixels for the new image and store them in the new array, then change the image instance variable to refer to the new array once it is completed. 
    • You should assume the image has at least three rows and columns and you do not need to worry about updating the first and last rows and columns. In other words, only update the interior pixels that have neighbors on all four sides. However, every position in the array of Pixels must have refer to a Pixel object; you can't just leave a position in the array uninitialized. 
    • Debugging hint: From past experience, we’ve noticed that bugs in the implementation of these transformations tend to produce more spectacular visible effects with the Laplacian weights. You might start with this set of weights when testing your code for the 3x3 transformations. 
    • Be sure that your monitor is set to “thousands” or “millions” of colors, which is normally the case on most modern PCs. If you set the monitor to such a high resolution that the color display is set to 256, the colors will be rendered only approximately and it will be hard to see the effects of most of these transformations. 