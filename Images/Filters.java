
import java.util.*;
import java.lang.Math;
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
        this.image = image;
    }

    /**
     * The constructor  creates an Image object from the file picture.jpg (in the project folder).
     * 
     * @param image   Image to apply filters to.
     */
    public Filters() {
        image = new Image("picture.png");
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
        for (Pixel p : image.getPixels()) {
            p.setValue(p.getValue() + amount);
        }
        image.setTitle("brighten" + amount + "-" + image.getTitle());
        image.updateCanvas();
        return image;
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
        for (Pixel p : image.getPixels()) {
            p.setValue(p.getValue() - amount);
        }
        image.setTitle("darken" + amount + "-" + image.getTitle());
        image.updateCanvas();
        return image;
    }  

    /**
     * This method inverts an image by mapping each pixel value 'v' to '255-v'
     * such that white turns black and vice-versa.
     * The title of the new image is prefixed 'invert-'.
     *
     * @return   Inverted image.
     */
    public Image invert() {
        for (Pixel p : image.getPixels()) {
            p.setValue(255 - p.getValue());
        }
        image.setTitle("invert-" + image.getTitle());
        image.updateCanvas();
        return image;
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
        int width = image.getWidth();
        int height = image.getHeight();
        Image mirror = new Image(width, height, "sort");
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {            
                mirror.getPixel(i, j).setValue(image.getPixel(width - i - 1, j).getValue()); 
            }                
        }
        mirror.setTitle("mirror-" + image.getTitle());        
        mirror.updateCanvas();
        return mirror;
    }

    /**
     * This method flips an image across the horizontal axis.
     * The value of pixel (i,j) in the new image is set to the value of pixel
     * (i, height-j-1) in the old image, where height is the height of the image.
     * The title of the new image is prefixed 'flip-'.
     *
     * @return   Flipped image.
     */
    public Image flip() {
        int width = image.getWidth();
        int height = image.getHeight();
        Image flip = new Image(width, height, "sort");
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {            
                flip.getPixel(i, j).setValue(image.getPixel(i, height - j - 1).getValue()); 
            }                
        }
        flip.setTitle("flip-" + image.getTitle());        
        flip.updateCanvas();
        return flip;
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
        int width = image.getWidth();
        int height = image.getHeight();
        Image rotate = new Image(height, width, "sort");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {            
                rotate.getPixel(i, j).setValue(image.getPixel(j, height - i - 1).getValue()); 
            }                
        }
        rotate.setTitle("rotate-" + image.getTitle());        
        rotate.updateCanvas();
        return rotate;
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
        int average = 0;
        for (Pixel p : image.getNeighbours(i, j)) {
            average += p.getValue();
        }
        average /= image.getNeighbours(i, j).size();
        return average;
    }

    /**
     * This method blurs an image.
     * Each pixel (x,y) is mapped to the average value of the neighbouring pixels. 
     * The title of the new image is prefixed 'blur-'.
     *
     * @return   Blurred image.
     */
    public Image blur() {
        int width = image.getWidth();
        int height = image.getHeight();
        Image blur = new Image(width, height, "sort");
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                blur.getPixel(i, j).setValue(average(i, j));
            }
        }
        blur.setTitle("blur-" + image.getTitle());        
        blur.updateCanvas();
        return blur;
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
        Random r = new Random();
        for (Pixel p : image.getPixels()) {
            p.setValue(p.getValue() + (r.nextInt(amount*2+1) - amount));
        }
        image.setTitle("noise" + amount + "-" + image.getTitle());        
        image.updateCanvas();
        return image;
    }

    /**
     * This method resizes an image by some factor.
     * The size of the new image becomes Width*factor x Height*factor, where
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
        int newWidth = (int) (image.getWidth() * factor);
        int newHeight = (int) (image.getHeight() * factor);
        Image resize = new Image(newWidth, newHeight, "sort");
        for (int i=0; i <newWidth; i++) {
            for (int j=0; j <newHeight; j++) {
                resize.getPixel(i, j).setValue(image.getPixel((int)(i/factor),
                        (int)(j/factor)).getValue());
            }
        }
        resize.setTitle("resize" + factor + "-" + image.getTitle());        
        resize.updateCanvas();
        return resize;
    }

    /**
     * This method rotates an image 90 degrees anti-clockwise.
     * The value of pixel (i,j) in the new image is set to the value of pixel
     * (width-j-1, i) in the old image, where width is the width of the new image.
     * The title of the new image is prefixed 'rotateAC-'.
     *
     * @return   Rotated image.
     */
    public Image rotateAC() {
        int width = image.getWidth();
        int height = image.getHeight();
        Image rotate = new Image(height, width, "sort");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {            
                rotate.getPixel(i, j).setValue(image.getPixel(width-j-1, i).getValue()); 
            }                
        }
        rotate.setTitle("rotateAC-" + image.getTitle());        
        rotate.updateCanvas();
        return rotate;
    }

    /**
     * This image increases the contrast of an image by some amount.
     * 
     * @param amount    The amount by which to increase contrast
     */
    public Image increaseContrast(double amount) {
        double power = Math.exp(-amount);
        for (Pixel p : image.getPixels()) {
            // calculates x as a double 
            double x = (p.getValue() * 2)/255.0 -1;
            // find the signum
            double sig = Math.signum(x);
            // calculates the new value
            double y = ((sig * Math.pow(Math.abs(x), power) + 1)/2.0) * 255.0;
            p.setValue((int)(y));
        }
        image.setTitle("contrast" + amount + "-" + image.getTitle());        
        image.updateCanvas();
        return image;
    }
}
