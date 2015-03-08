package svm.advance;

import graphing.PlotHelper;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.event.ChartProgressEvent;
import org.jfree.chart.event.ChartProgressListener;

import svm.XDATA;
import svm.libsvm.svm;
import svm.libsvm.svm_model;
import svm.libsvm.svm_node;
import svm.libsvm.svm_problem;

public class STrainHelper extends SParam{

	private double[][] train;
	private String model_file_name = "train.model";
	private svm_problem prob;
	private svm_model model;
	
	private double[] maxbounds = null;
	private double[] minbounds = null;
	private int total_dots = 0;
	private int dots_drawn = 0;
	private String[] str_labels;
	private double[] num_labels;
	
	// 
	private ArrayList<String> str_labels_map = new ArrayList<String>();
	
	public STrainHelper(String[] str_labels, double[][] train){
		super();
		this.str_labels = str_labels;
		num_labels = new double[str_labels.length];
		for(int i = 0; i < str_labels.length; i++){
			int j = 0;
			while(j < str_labels_map.size()){
				if(str_labels[i].equals(str_labels_map.get(j))){
					num_labels[i] = j;
					break;
				}

				j++;
			}
			if(j == str_labels_map.size()){
				str_labels_map.add(str_labels[i]);
				num_labels[i] = str_labels_map.size()-1;
			}
		}

        this.train = train;	
	}
	
	public String[] getLabels(){
		return str_labels_map.toArray(new String[str_labels_map.size()]);
	}
	
	public svm_model getModel(){
		return model;
	}
	public svm_model svmTrain() throws IOException {
	    prob = new svm_problem();
	    int dataCount = train.length;
	    prob.y = new double[dataCount];
	    prob.l = dataCount;
	    prob.x = new svm_node[dataCount][];     

	    maxbounds = new double[train[0].length];
	    minbounds = new double[train[0].length];
	    

	    for (int i = 0; i < dataCount; i++){            
	        double[] features = train[i];
	        prob.x[i] = new svm_node[features.length];
	        for (int j = 0; j < features.length; j++){
	            svm_node node = new svm_node();
	            node.index = j;
	            node.value = features[j];
	            prob.x[i][j] = node;
	            
	            // Set the maximum and minimum bounds
	    	    if(i == 0){
	    	    	maxbounds[j] = minbounds[j] = node.value;
	    	    } else {
	    	    	// Find Bounds
	    			if(node.value > maxbounds[j])
	    				maxbounds[j] = node.value;
	    			else if(node.value < minbounds[j])
	    				minbounds[j] = node.value;
	    	    }
	        }
	        prob.y[i] = num_labels[i];
	        
			
	    }                

	    System.out.println("Training...");
	    model = svm.svm_train(prob, super.getParam());
	    svm.svm_save_model(model_file_name,model);

	    return model;
	}
	
	public double[][] getTrainData(){
		// Total dots to draw
		total_dots += train.length;
		double[][] result = new double[train.length][2];
		for(int i=0; i<result.length; i++){
			result[i][0] = train[i][0];
			result[i][1] = train[i][1];
		}
		return result;
	}
	public double[][] getBounds(double percent_ext){
		
		
		if(maxbounds == null)
			throw new NullPointerException("Read training data first using svm_train");

		double[] extension = {percent_ext/100*(maxbounds[0]-minbounds[0]), percent_ext/100*(maxbounds[1]-minbounds[1])};
		
		
		// Adjust the bounds
		for(int i = 0; i<2; i++){
		maxbounds[i] += extension[i];
		minbounds[i] -= extension[i];
		}
		
		return new double[][]{
				{minbounds[0], maxbounds[0]},
				{minbounds[1], maxbounds[1]}
		};
	}
	/** Generate dots
	 * 
	 * @param x_dimen Number of dots in the x axis
	 * @param y_dimen Number of dots in the y axis
	 * @return
	 */
	public double[][] generateDots(int x_dimen, int y_dimen){
		if(maxbounds == null)
			throw new NullPointerException("Read training data first, see readTrainData(String input_file_name)");

		double xmax = maxbounds[0];
		double xmin = minbounds[0];
		double ymax = maxbounds[1];
		double ymin = minbounds[1];
		
		double xinc = (xmax-xmin)/(double)x_dimen;
		double yinc = (ymax-ymin)/(double)y_dimen;
		
		double xcur = xmin;
		double ycur = ymax;
		
		double[][] result = new double[x_dimen*y_dimen][2];
		for(int i=0; i<x_dimen; i++){
			for(int j=0; j<y_dimen; j++){
				result[i*y_dimen +j ][0] = xcur;
				result[i*y_dimen +j ][1] = ycur;
				ycur-=yinc;	//increment y
			}
			xcur+=xinc;	//increment x
			ycur = ymax;	//reset y
		}
		
		// Total dots to draw
		total_dots += x_dimen*y_dimen;
		
		return result;
	}
	public double[][] getSV(){
		svm_node[][] node = model.SV;
		
		int m=2;
		double[][] resultSV = new double[node.length][m];
		for(int i=0;i<node.length;i++)
		{
			for(int j=0;j<m;j++)
				resultSV[i][j] = node[i][j].value;
			
		}
		
		total_dots += node.length;
		return resultSV;
	}
	
	public void plot_graph(final String axis_name1, final String axis_name2, final CustomChartProgressListener progress_listener){
		if(train[0].length != 2)
			throw new IllegalArgumentException("This plot graph only works with data with 2 axis");
		
		final Thread b = new Thread(){
			public void run(){
				// For the loading dialog
				final JFrame load_frame = new JFrame("Plotting graph...");
				load_frame.setLayout(new FlowLayout());
				final JProgressBar pbar = new JProgressBar();
				pbar.setStringPainted(true);
				pbar.setString("Loading ...");
				final JButton load_button = new JButton("Cancel");
				load_frame.add(pbar);
				load_frame.add(load_button);
				load_frame.pack();
				load_frame.setVisible(true);
				load_frame.setAlwaysOnTop(true);
				load_frame.setLocationRelativeTo(null);
				
				TimerTask timertask = new TimerTask(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if(dots_drawn < total_dots && total_dots!=0){
							int value = total_dots == 0 ? 0 : 100*dots_drawn/total_dots;
							pbar.setValue(value);
							
						}
					}
					
				};
				
				
				final JFrame dia2 = new JFrame();
				dia2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dia2.setLayout(new BorderLayout());
				
				
				final Timer timer = new Timer();
				timer.scheduleAtFixedRate(timertask, 0, 100);
				load_button.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						timer.cancel();
						load_frame.dispose();
						dia2.dispose();
						if(progress_listener != null)
							progress_listener.onAbort();
						System.err.println("User aborted plotting graph");
					}
				});
				
								
				final SPredictHelper pp = new SPredictHelper(model);
				
				double[][] xymm = getBounds(2);
				
//				System.out.println("x max: "+xymm[0]+"\nx min: "+xymm[1]+"\ny max: "+xymm[2]+"\ny min: "+xymm[3]);
				

				
				int[] num_label = model.label;
				String[] s_label = new String[num_label.length*2+1];
				for(int i=0; i<num_label.length; i++){
					s_label[i] = str_labels_map.get(i)+" points";
					s_label[i+num_label.length] = str_labels_map.get(i)+" area";
				}
				
				s_label[s_label.length-1] = "Support Vectors";
				
				final PlotHelper demo = new PlotHelper("Sample Plot", axis_name1, axis_name2, s_label);
				
				
				
				final ChartPanel cp = demo.getChartPanel();
				
				
//				dia2.add(load_frame, BorderLayout.NORTH);
				dia2.add(cp, BorderLayout.CENTER);
				

				
				

				dia2.pack();
				dia2.setVisible(true);
				
				cp.setVisible(false);
				
				
				
				demo.setAxisRange(xymm[0][0],xymm[0][1],xymm[1][0],xymm[1][1]);
				Shape shapeCir  = new Ellipse2D.Double(-2.5,-2.5,5,5);
				    				
			    Shape shapeCirStroke = new BasicStroke(0.1f).createStrokedShape(shapeCir);

				Shape shapeRect  = new Rectangle2D.Double(-1.5,-1.5,3,3);
				
				demo.setSeriesPaint(s_label[s_label.length-1], new Color(0,0,0));
				demo.setSeriesShape(s_label[s_label.length-1], shapeCirStroke);
				
				Color color[][] = new Color[5][2];
				color[0][0] = new Color(0,0,255);
				color[0][1] = new Color(0,0,255,20);
				color[1][0] = new Color(255,0,0);
				color[1][1] = new Color(255,0,0,20);
				color[2][0] = new Color(0,255,0);
				color[2][1] = new Color(0,255,0,20);
				color[3][0] = new Color(255,177,18);
				color[3][1] = new Color(255,177,18,20);
				color[4][0] = new Color(249,44,255);
				color[4][1] = new Color(249,44,255,20);
				
				for(int i=0; i<num_label.length; i++){
					demo.setSeriesPaint(s_label[i], color[i%color.length][0]);
					demo.setSeriesPaint(s_label[i+num_label.length], color[i%color.length][1]);
				}
				

				for(int i=0; i<num_label.length; i++){
					demo.setSeriesShape(s_label[i], shapeRect);
					demo.setSeriesShape(s_label[i+num_label.length], shapeCir);
				}

				dots_drawn = 0;
				
				for(int i=0; i<train.length; i++){
					demo.addData(str_labels[i]+" points", train[i][0], train[i][1]);
					dots_drawn ++;
				}
				
				double[][] dots = generateDots(200, 200);
				
				for(int i=0; i<dots.length; i++){
					double results = pp.predict(dots[i]);
					
					demo.addData(s_label[(int)results + num_label.length], dots[i][0], dots[i][1]);
					dots_drawn ++;
				}
				
				
				double[][] SVs = getSV();
				for(int i=0; i<SVs.length; i++){
					demo.addData(s_label[s_label.length-1], SVs[i][0], SVs[i][1]);
					dots_drawn ++;
				}

			    pbar.setString("Almost done...");
			    pbar.setValue(100);
			    
			    ChartProgressListener progress_listener2 = new ChartProgressListener(){

			    	private boolean first_time = true;
			    	@Override
					public void chartProgress(ChartProgressEvent arg0) {
						// TODO Auto-generated method stub

						if(first_time && arg0.getType() == ChartProgressEvent.DRAWING_FINISHED){
							first_time = false;
							timer.cancel();
							load_frame.setVisible(false);
							System.out.print("Done plotting graph");
						}
					}
			    };
			    demo.addProgressListener(progress_listener2);
			    
			    if(progress_listener != null)
			    	demo.addProgressListener(progress_listener);
			    
			    
			   	cp.setVisible(true);
			    
			}
		};
		b.start();
	}
	public void setModelFileName(String model_file_name){
		this.model_file_name = model_file_name;
	}
	
	public String getModelFileName(){
		return model_file_name;
	}
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String[] a = new String[]{"-1", "-1", "-1", "-1", "-2", "-1", "-1", "+1", "+1", "+1", "+1"};
		
		double[][] b = new double[][]{
				{2, 0},
				{2, 1},
				{2, 2},
				{2, 3},
				{1, 3},
				{2, 4},
				{2, 5},
				{4, 0},
				{4, 2},
				{4, 4},
				{4, 6}
		};
		
		STrainHelper t = new STrainHelper(a, b);
		t.setParam(_t, LINEAR);
		t.svmTrain();
		t.plot_graph("Axis 1", "Axis 2", null);
		
		SPredictHelper h = new SPredictHelper(t.getModel());
		System.out.println(h.predict(b[10]));
		
	}

}