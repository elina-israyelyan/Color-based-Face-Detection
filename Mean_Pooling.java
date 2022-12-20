import ij.ImagePlus;
import ij.plugin.PlugIn;
import ij.process.ImageProcessor;
import ij.process.ColorProcessor;
import java.awt.Color;
public class Mean_Pooling implements PlugIn {

    public void run(String arg) {
        // Get the current image
        ImagePlus imp = ij.WindowManager.getCurrentImage();
        ImageProcessor ip = imp.getProcessor();
	String inputTitle = imp.getShortTitle();
        // Get the width and height of the image
        int width = ip.getWidth();
        int height = ip.getHeight();
	int outputWidth = width/5, outputHeight = height/5;
	int r, g, b;
	int rMean;
	int gMean;
	int bMean;
	int output_col_cursor=0;
	int output_row_cursor=0;
	Color color;
        // Create a new image processor for the output image
        ImageProcessor output = new ColorProcessor(outputWidth, outputHeight);

        // Set the kernel size for the mean pooling operation
     	for (int row = 0; row < height; row+=5){
		for (int col = 0; col < width; col+=5) {
			int output_pixel[]=new int[3];
			r = 0;
			g = 0;
			b = 0;
			for (int j=0; j<=4; j++)
				for (int i=0; i<=4; i++){
					color = new Color(ip.getPixel(col+i, row+j));
					r += color.getRed();
					g += color.getGreen();
					b += color.getBlue();
				}
			rMean = r/5;
			gMean = g/5;
			bMean = b/5;
			output_pixel[0] = rMean;
			output_pixel[1] = gMean;
			output_pixel[2] = bMean;
			output.putPixel(col/5, row/5, output_pixel);
		        

			}



	}


        // Set the output image as the current image
        ImagePlus outputImage = new ImagePlus("mean_pool_5" + "_" + inputTitle, output);
        outputImage.show();
    }
}

