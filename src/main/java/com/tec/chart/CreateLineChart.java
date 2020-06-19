package com.tec.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class CreateLineChart {
    /**
     * 获得指定文件的byte数组
     */
    public static byte[] getBytes(String filePath) {
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    /**
     * 创建JFreeChart LineXY Chart（折线图）
     */
    public static void main(String[] args) {
        /**
         * 读取url数据
         */
        byte[] data = getBytes("d:\\temp\\Ci5dQV7fMRyAU9zPAACvjGJzjUU.filena");

        byte[] newData = new byte[data.length];
        List listCharts = new ArrayList<Short>();
        //根据协议解析数据
        for (int i = 0; i + 1 < data.length; i += 2) {
            short value = (short) (((data[i] & 0xff) << 8) | (data[i + 1] & 0xff));
            //01：x轴按照时间间隔为1/250秒画
            //02：y轴值按下面顺序画
            System.out.println(value);
//            System.out.println("Y value : " + value);
            listCharts.add(value);

        }
        //步骤1：创建XYDataset对象（准备数据）
        XYDataset dataset = createXYDataset(listCharts);
//        XYDataset dataset = createXYDataset();
        //步骤2：根据Dataset 生成JFreeChart对象，以及做相应的设置
        JFreeChart freeChart = createChart(dataset);
        //步骤3：将JFreeChart对象输出到文件，Servlet输出流等
        saveFile(freeChart, "D:\\temp\\t.png", 600, 400);
    }

    public static void saveFile(JFreeChart chart, String outputPath, int weight, int height) {
        FileOutputStream out = null;
        try {
            File outFile = new File(outputPath);
            if (!outFile.getParentFile().exists()) {
                outFile.getParentFile().mkdirs();
            }
            out = new FileOutputStream(outputPath);
            // 保存为PNG
            ChartUtilities.writeChartAsPNG(out, chart, weight, height);
            // 保存为JPEG
            // ChartUtilities.writeChartAsJPEG(out, chart, weight, height);
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }
    }

    // 根据XYDataset创建JFreeChart对象
    public static JFreeChart createChart(XYDataset dataset) {
        // 创建JFreeChart对象：ChartFactory.createXYLineChart
        JFreeChart jfreechart = ChartFactory.createXYLineChart("", // 标题
                "", // categoryAxisLabel （category轴，横轴，X轴标签）
                "", // valueAxisLabel（value轴，纵轴，Y轴的标签）
                dataset, // dataset
                PlotOrientation.VERTICAL,
                true, // legend
                false, // tooltips
                false); // URLs
        // 使用CategoryPlot设置各种参数。以下设置可以省略。
        XYPlot plot = (XYPlot) jfreechart.getPlot();
        // 背景色 透明度
        plot.setBackgroundAlpha(0.5f);
        // 前景色 透明度
        plot.setForegroundAlpha(0.5f);
        // 其它设置可以参考XYPlot类
        return jfreechart;
    }

    /**
     * 创建XYDataset对象
     *
     * @param listCharts
     */
    private static XYDataset createXYDataset(List<Short> listCharts) {
        XYSeries xyseries = new XYSeries("");
        for (int i = 0; i < listCharts.size(); i++) {
            xyseries.add(i, listCharts.get(i));
        }
        XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
        xySeriesCollection.addSeries(xyseries);
        return xySeriesCollection;
    }
}