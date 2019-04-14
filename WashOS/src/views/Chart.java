package views;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;

public class Chart extends JFrame {
	private String typeOfChart = "";
	private long maxBound = 2000;
	private long minBound = 0;

	public Chart(ArrayList<Long> times, String indicator) {
		switch (indicator) {
		case "E": {
			this.typeOfChart = "Execution Times";
			maxBound = 2040;
			minBound=1995;
			break;
		}
		case "R":{
			this.typeOfChart = "Response Times";
			minBound =0;
			maxBound = 5000;
			break;
		}
		case "T":{
			this.typeOfChart = "TurnAround Times";
			minBound = 800;
			maxBound = 7000;
			break;
		}
		}
		initUI(times);
	}

	private void initUI(ArrayList<Long> times) {

		XYDataset dataset = createDataset(times);
		JFreeChart chart = createChart(dataset);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		add(chartPanel);
		pack();
		setTitle("Bar chart");
		this.setBounds(1300, 1300, 1300, 1300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private XYDataset createDataset(ArrayList<Long> times) {

		XYSeries series1 = new XYSeries(typeOfChart);
		for (int i = 0; i < times.size(); i++) {
			series1.add(i + 1, (double) times.get(i));
		}

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series1);
		return dataset;
	}

	private JFreeChart createChart(XYDataset dataset) {

		JFreeChart chart = ChartFactory.createXYLineChart(typeOfChart + " per process", "Process Number", "Time (ms)",
				dataset, PlotOrientation.VERTICAL, true, true, false);

		XYPlot plot = chart.getXYPlot();
		final NumberAxis axis = (NumberAxis) plot.getRangeAxis();
//		System.out.println(axis.getLowerBound());
		axis.setLowerBound(minBound);
		axis.setUpperBound(maxBound);
		plot.setRangeAxis(axis);
		// plot.setAxisOffset(new RectangleInsets(100,100,100,100));
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesPaint(0, Color.RED);
		renderer.setSeriesStroke(0, new BasicStroke(2.0f));

		plot.setRenderer(renderer);
		plot.setBackgroundPaint(Color.white);

		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.BLACK);

		chart.getLegend().setFrame(BlockBorder.NONE);

		chart.setTitle(
				new TextTitle("All " + typeOfChart + "for processes", new Font("Serif", java.awt.Font.BOLD, 18)));

		return chart;

	}

	public static void main(String[] args) {

	}
}