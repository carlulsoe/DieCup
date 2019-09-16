import java.util.*;

/**
 * This class implements a number of filters, i.e. methods that can be used to
 * manipulate Image objects, e.g. make the image darker or mirrored.
 * The filter methods operates on the image in the field (feltvariabel) image.
 * The filter methods change the original image and return the new image.
 *
 * @author Kurt Jensen
 * @version 2017-08-04
 **/
public class Filters
{

    private Image image;     // Image on which the filter methods operate
    
    /**
     * The constructor takes as input an instance of Image.
     * 
     * @param image   Image to apply filters to.
     */
    public Filters(Image image) {
    }
    
    /**
     * The constructor  creates an Image object from the file picture.jpg (in the project folder).
     * 
     * @param image   Image to apply filters to.
     */
    public Filters() {
    }
    
    /**
     * This method brightens an image by adding some amount to the
     * value of all pixels in the image.
     * The title of the new image is prefixed 'brightenX-',
     * where X is the parametervalue.
     *
     * @param amount   Increase in value for each pixel.
     * @return   Brightened image.
     */
    public Image brighten(int amount) {
        return null;
    }

    /**
     * This method darkens an image by subtracting some amount from the
     * value of all pixels in the image.
     * The title of the new image is prefixed 'darkenX-',
     * where X is the parametervalue.
     *
     * @param amount   Decrease in value for each pixel.
     * @return   Darkened image.
     */
    public Image darken(int amount) {
        return null;
    }  

    /**
     * This method inverts an image by mapping each pixel value 'v' to '255-v'
     * such that white turns black and vice-versa.
     * The title of the new image is prefixed 'invert-'.
     *
     * @return   Inverted image.
     */
    public Image invert() {
        return null;
    }

    /**
     * This method mirrors an image across the vertical axis.
     * The value of pixel (i,j) in the new image is set to the value of pixel
     * (width-i-1, j) in the old image, where width is the width of the image.
     * The title of the new image is prefixed 'mirror-'.
     *
     * @return   Mirrored image.
     */
    public Image mirror() {
        return null;
    }

    /**
     * This method flips an image across the horizontal axis.
     * The value of pixel (i,j) in the new image is set to the value of pixel
     * ?????????? in the old image, where height is the height of the image.
     * The title of the new image is prefixed 'flip-'.
     *
     * @return   Flipped image.
     */
    public Image flip() {
        return null;
    }

    /**
     * This method rotates an image 90 degrees clockwise.
     * The value of pixel (i,j) in the new image is set to the value of pixel
     * (j,width-i-1) in the old image, where width is the width of the new image.
     * The title of the new image is prefixed 'rotate-'.
     *
     * @return   Rotated image.
     */
    public Image rotate() {
        return null;
    }

    /**
     * Auxillary method for blur.
     * This method computes the average value of the (up to nine) neighbouring pixels
     * of position (i,j) -- including pixel (i,j).
     *
     * @param i   Horizontal index.
     * @param j   Vertical index.
     * @return    Average pixel value.
     */
    private int average(int i, int j) {
        return 0;
    }

    /**
     * This method blurs an image.
     * Each pixel (x,y) is mapped to the average value of the neighbouring pixels. 
     * The title of the new image is prefixed 'blur-'.
     *
     * @return   Blurred image.
     */
    public Image blur() {
        return null;
    }
   
    /**
     * This method adds noise to an image.
     * The value of pixel (i,j) is set to a random value in the interval
     * [v-amount, v+amount], where v is the old value and amount the parameter.
     * The title of the new image is prefixed 'noiseX-'.
     *
     * @param amount   Maximal amount of noise to add.
     * @return  Noisy image. 
     */  
    public Image noise(int amount) {
        return null;
    }

    /**
     * This method resizes an image by some factor.
     * The size of the new image becomes with*factor x hiehgt*factor, where
     * width and heigt are the width and height of the old image.
     * The value of pixel (i,j) in the new image is set to the value of pixel
     * (i/factor,j/factor) in the old image, where factor is the parameter.
     * This produces a new image of size (width*factor, height*factor).
     * The title of the new image is prefixed 'factorX-'.
     *
     * @param factor   Resize factor.
     * @return   Resized image.
     */   
    public Image resize(double factor) {
        return null;
    }
    
    /**
     * This method rotates an image 90 degrees anti-clockwise.
     * The value of pixel (i,j) in the new image is set to the value of pixel
     * ???????? in the old image, where width is the width of the new image.
     * The title of the new image is prefixed 'rotateAC-'.
     *
     * @return   Rotated image.
     */
    public Image rotateAC() {
        return null;
    }
    
    /**
     * This image increases the contrast of an image by some amount.
     * 
     * @param amount    The amount by which to increase contrast
     */
    public Image increaseContrast(double amount) {
        return null;
    }

}
