package SupportVectorMachine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Shape;
import java.awt.Stroke;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import org.jfree.chart.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.event.ChartChangeEvent;
import org.jfree.chart.event.ChartChangeListener;
import org.jfree.chart.event.ChartProgressEvent;
import org.jfree.chart.event.ChartProgressListener;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * Helper class for plotting scatter graphs for Support Vector Machine (SVM)
 * 
 * Modified from:
 * http://stackoverflow.com/questions/13792917/jfreechart-scatter-plot-moving-data-between-series
 * 
 * @see http://stackoverflow.com/a/13794076/230513
 * @see http://stackoverflow.com/questions/8430747
 * @see http://stackoverflow.com/questions/8048652
 * @see http://stackoverflow.com/questions/7231824
 * @see http://stackoverflow.com/questions/7205742
 * @see http://stackoverflow.com/questions/7208657
 * @see http://stackoverflow.com/questions/7071057
 * @see http://stackoverflow.com/questions/8736553
 */
public class svm_plot_helper extends JFrame {

	public static final String SV = "SVECTOR";
	public static final boolean AREA = false;
	public static final boolean DATA = true;
    public static final int DEFAULT_CHART_WIDTH = 800;
    public static final int DEFAULT_CHART_HEIGHT = 600;


    private static final String title = "Scatter Add Demo";
	private XYPlot xyPlot;
	private final XYSeries[] series;
	
	private ChartPanel chartPanel;
	private JFreeChart jfreechart;
	private XYSeriesCollection xySeriesCollection;
	private String[] str_labels;

    /**Construct a new scatter chart
     * 
     * @param title
     * Chart title
     * @param str_labels
     * Labels for the series
     */
    public svm_plot_helper(String title, String[] str_labels, int chart_width, int chart_height) {
        super(title);
        this.str_labels = str_labels;

        
        series = new XYSeries[str_labels.length*2+1];
        series[0] = new XYSeries("SV");
        
        for(int i=0; i<str_labels.length; i++){
        	series[i+1] = new XYSeries(str_labels[i]+" data");
        	series[str_labels.length+i+1] = new XYSeries(str_labels[i]+" area");
        }
        
        chartPanel = createDemoPanel(title);
        chartPanel.setPreferredSize(new Dimension(chart_width, chart_height));
    }

    public svm_plot_helper(String title, String[] str_labels) {
        this(title, str_labels, DEFAULT_CHART_WIDTH, DEFAULT_CHART_HEIGHT);
//        chartPanel.setSize(arg0, arg1)
    }
    /**Show an external dialog container the graph if you don't want to create one yourself
     * 
     * @param close_option 
     * Close option for the dialog e.g. JFrame.EXIT_ON_CLOSE
     */
    public void showDialog(int close_option){
        
        setDefaultCloseOperation(close_option);
        setLayout(new BorderLayout());
        add(chartPanel, BorderLayout.CENTER);
        

        jfreechart.addChangeListener(new ChartChangeListener(){

			public void chartChanged(ChartChangeEvent arg0) {
				// TODO Auto-generated method stub
			
				String title = arg0.getChart().getTitle().getText();
				if(title!=null)
					setTitle(title);
			}
        	
        });
        
        chartPanel.addPropertyChangeListener(new PropertyChangeListener(){
        	public void propertyChange(PropertyChangeEvent e) {
                String propertyName = e.getPropertyName();
                System.out.println(propertyName);
        	}
        });
        
    
        pack();
//        chartPanel.setVisible(false);
        setVisible(true);
        
    }
    /** Method for getting chart panel if you want to integrate the chart
     *  into your own dialog
     */
    public ChartPanel getChartPanel(){
    	return chartPanel;
    }
    private ChartPanel createDemoPanel(final String title) {
 
        jfreechart = ChartFactory.createScatterPlot(
            title, "X1", "X2", createSeries(),
            PlotOrientation.VERTICAL, true, true, false);
        xyPlot = (XYPlot) jfreechart.getPlot();
        xyPlot.setDomainCrosshairVisible(true);
        xyPlot.setRangeCrosshairVisible(true);
//        XYItemRenderer renderer = xyPlot.getRenderer();
//       renderer.setSeriesPaint(0, new Color(132,188,255));
//        adjustAxis((NumberAxis) xyPlot.getDomainAxis(), false);
//        adjustAxis((NumberAxis) xyPlot.getRangeAxis(), false);
        xyPlot.setBackgroundPaint(Color.white);

        return new ChartPanel(jfreechart);
    }

    public void addProgressListener(ChartProgressListener chartprogress){
    	jfreechart.addProgressListener(chartprogress);
    }

    /** Method for setting dot color:
     *  For setting dot color for support vector, use one of the following:
     *  	addData(svm_plot_helper.SV, false, Color color)
     *  	addData(svm_plot_helper.SV, Color color)
     *  
     *  @param label
     *  Label for the series you want to set dot color
     *  @param data_Or_Area
     *  True for adding data. False for adding area
     *  @param color Color
     */
    public void setDotColor(String label, boolean data_Or_Area, Color color){
    	XYItemRenderer renderer = xyPlot.getRenderer();
    	if(label.equals(SV)){
    		renderer.setSeriesPaint(0, color);
    		return;
    	}
    	for(int i=0; i<str_labels.length; i++){
    		if(str_labels[i].equals(label)){
    			if(data_Or_Area == DATA)	//true
    				renderer.setSeriesPaint(i+1, color);
    			else if (data_Or_Area == AREA)	//false
    				renderer.setSeriesPaint(str_labels.length+i+1, color);
    			return;
    		}
    	}
    }
    
    /** Method for setting dot color:
     *  For setting dot color for support vector, use one of the following:
     *  	addData(svm_plot_helper.SV, false, Color color)
     *  	addData(svm_plot_helper.SV, Color color)
     *  
     *  @param label
     *  Label for the series you want set dot color
     *  @param color Color
     */
    public void setDotColor(String label, Color color){
    	XYItemRenderer renderer = xyPlot.getRenderer();
    	if(label.equals(SV)){
    		renderer.setSeriesPaint(0, color);
    		return;
    	}
    }
    
    /** Method for setting dot shape:
     *  For setting dot shape for support vector, use one of the following:
     *  	addData(svm_plot_helper.SV, false, double x, double y)
     *  	addData(svm_plot_helper.SV, double x, double y)
     *  
     *  @param label
     *  Label for the series you want to set dot shape
     *  @param data_Or_Area
     *  True for data series. False for area series
     *  @param shape Shape
     */
    public void setDotShape(String label, boolean data_Or_Area, Shape shape){
    	XYItemRenderer renderer = xyPlot.getRenderer();
    	if(label.equals(SV)){
    		renderer.setSeriesShape(0, shape);
    		return;
    	}
    	
    	for(int i=0; i<str_labels.length; i++){
    		if(str_labels[i].equals(label)){
    			if(data_Or_Area == DATA)	//true
    				renderer.setSeriesShape(i+1, shape);
    			else if (data_Or_Area == AREA)	//false
    				renderer.setSeriesShape(str_labels.length+i+1, shape);
    			return;
    		}
    	}
    }
    
    /** Method for setting dot shape for SV by using:
     *  setDotShape(svm_plot_helper.SV, Shape shape)
     *  
     *  For setting dot shape to other series, use:
     *  setDotShape(String label, boolean data_Or_Area, Shape shape)
     */
    public void setDotShape(String label, Shape shape){
    	XYItemRenderer renderer = xyPlot.getRenderer();
    	if(label.equals(SV)){
    		renderer.setSeriesShape(0, shape);
    		return;
    	}
    }
 
    /** Method for setting dot stroke:
     *  For setting dot stroke for support vector, use one of the following:
     *  	addData(svm_plot_helper.SV, false, double x, double y)
     *  	addData(svm_plot_helper.SV, double x, double y)
     *  
     *  @param label
     *  Label for the series you want to set dot stroke
     *  @param data_Or_Area
     *  True for data series. False for area series
     *  @param stroke Stroke
     */
    public void setDotStroke(String label, boolean data_Or_Area, Stroke stroke){
    	XYItemRenderer renderer = xyPlot.getRenderer();
    	if(label.equals(SV)){
    		renderer.setSeriesStroke(0, stroke);
    		return;
    	}
    	
    	for(int i=0; i<str_labels.length; i++){
    		if(str_labels[i].equals(label)){
    			if(data_Or_Area == DATA)	//true
    				renderer.setSeriesStroke(i+1, stroke);
    			else if (data_Or_Area == AREA)	//false
    				renderer.setSeriesStroke(str_labels.length+i+1, stroke);
    			return;
    		}
    	}
    }
    
    /** Method for setting dot stroke for SV by using:
     *  setDotShape(svm_plot_helper.SV, Stroke stroke)
     *  
     *  For setting dot stroke to other series, use:
     *  setDotShape(String label, boolean data_Or_Area, Stroke stroke)
     */
    public void setDotStroke(String label, Stroke stroke){
    	XYItemRenderer renderer = xyPlot.getRenderer();
    	if(label.equals(SV)){
    		renderer.setSeriesStroke(0, stroke);
    		return;
    	}
    }
    
    /** Method for adjusting axis / bounds
     *   
     *  @param minX
     *  X minimum
     *  @param maxX
     *  X maximum
     *  @param minY 
     *  Y minimum
     *  @param maxY 
     *  Y maximum
     */
    public void adjustAxis(double minX, double maxX, double minY, double maxY){
    	xyPlot.getDomainAxis().setRange(minX, maxX);
    	xyPlot.getRangeAxis().setRange(minY, maxY);
    }

    @SuppressWarnings("unused")
	private void adjustAxis(NumberAxis axis, boolean vertical) {
        axis.setRange(-6.0, 6.0);
        axis.setTickUnit(new NumberTickUnit(0.5));
        axis.setVerticalTickLabels(vertical);
    }

    private XYDataset createSeries() {
        xySeriesCollection = new XYSeriesCollection();
        for(int i=0; i<series.length; i++)
        	xySeriesCollection.addSeries(series[i]);
		return xySeriesCollection;
    }
   
    /** Method for adding data
     *  For adding data for support vector, use one of the following:
     *  	addData(svm_plot_helper.SV, false, double x, double y)
     *  	addData(svm_plot_helper.SV, double x, double y)
     *  
     *  @param label
     *  Label for the series you want to add data
     *  @param data_Or_Area
     *  True for adding data. False for adding area
     *  @param x x coordinate
     *  @param y y coordinate
     */
    public void addData(String label, boolean data_Or_Area, double x, double y){
    	if(label.equals(SV)){
    		this.series[0].add(x,y);
    		return;
    	}
    	for(int i=0; i<str_labels.length; i++){
    		if(str_labels[i].equals(label)){
    			if(data_Or_Area == DATA)	//true
    				this.series[i+1].add(x,y);
    			else if (data_Or_Area == AREA)	//false
    				this.series[str_labels.length+i+1].add(x,y);
    			return;
    		}
    	}
    }
    
    /** Method for adding data for SV by using:
     *  addData(svm_plot_helper.SV, double x, double y)
     *  
     *  For adding data to other series, use:
     *  addData(String label, boolean data_Or_Area, double x, double y)
     */
    public void addData(String label, double x, double y){
    	if(label.equals(SV)){
    		this.series[0].add(x,y);
    		return;
    	}    	
    }
    

    /** This main method is intended for experimenting svm_plot_helper class directly only
     *  Not intended to be used with other classes
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {

            public void run() {
                svm_plot_helper demo = new svm_plot_helper(title, new String[]{"-1","1"});

                demo.addProgressListener(new ChartProgressListener(){
					public void chartProgress(ChartProgressEvent arg0) {
						// TODO Auto-generated method stub
						if(arg0.getType() == ChartProgressEvent.DRAWING_FINISHED)
							System.out.println("Ploting finished");
					}
                });
                demo.setLocationRelativeTo(null);
                demo.showDialog(JFrame.EXIT_ON_CLOSE);
            }
        });
    }
}
