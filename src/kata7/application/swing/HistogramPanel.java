package kata7.application.swing;

import java.awt.BorderLayout;
import kata7.model.Histogram;
import javax.swing.JPanel;
import kata7.view.HistogramDisplay;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class HistogramPanel extends JPanel implements HistogramDisplay {

    private Histogram<String> histogram;

    public HistogramPanel() {
        super(new BorderLayout());
    }

    @Override
    public Histogram histogram() {
        return this.histogram;
    }

    @Override
    public void show(Histogram histogram) {
        this.histogram = histogram;
        this.reload();
    }

    private void reload() {
        this.removeAll();
        this.add(new ChartPanel(createChart(createDataset(this.histogram))));
        this.revalidate();
    }

    private JFreeChart createChart(DefaultCategoryDataset dataSet) {
        JFreeChart chart = ChartFactory.createBarChart(null, "", "NÚMERO", dataSet, PlotOrientation.VERTICAL, false, false, false);
        return chart;
    }

    private DefaultCategoryDataset createDataset(Histogram histogram) {
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        for (Object key : histogram.keySet()) {
            dataSet.addValue(histogram.get(key), "", (Comparable) key);
        }
        return dataSet;

    }

}
