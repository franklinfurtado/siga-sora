/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ao.sigp.catequese.core.util.primefaces;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;

//import java.awt.Color;
import co.ao.sigp.catequese.core.util.GeradorCoresUtil;

//import java;

/**
 *
 * @author franklinfurtado
 */
public class BarChartUtils implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private static GeradorCoresUtil geradorCoresUtil = new GeradorCoresUtil();

    public static BarChartDataSet getDataSet(String label, String color, List<Number> values) {
        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel(label);
        barDataSet.setBackgroundColor(color);
        barDataSet.setBorderColor(color);
        barDataSet.setBorderWidth(1);
        barDataSet.setData(values);

        return barDataSet;
    }

    public static BarChartOptions getBarChartOptions() {
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);
        return options;
    }

    public static BarChartModel getChartModelTotalCatequistaCatecumenos(List<Number> valueTotalCatecumenos, List<Number> valuesTotalCatequistas, List<String> labels) {
        BarChartModel barChartModel = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet catecumenoBarDataSet, catequistasBarDataSet;

        catecumenoBarDataSet = getDataSet("Total de Catecumenos", "#67b1ff", valueTotalCatecumenos);
        catequistasBarDataSet = getDataSet("Total de Catequistas", "#f4a0b5", valuesTotalCatequistas);

        data.addChartDataSet(catecumenoBarDataSet);
        data.addChartDataSet(catequistasBarDataSet);

        data.setLabels(labels);
        barChartModel.setData(data);
        barChartModel.setOptions(getBarChartOptions());

        return barChartModel;
    }

    public static DonutChartModel getChartModelPercentualCatequistaPorPastoral(List<Number> valuesTotalCatequistas, List<String> labels) {
        DonutChartModel donutModel = new DonutChartModel();
        ChartData data = new ChartData();

        DonutChartDataSet dataSet = new DonutChartDataSet();
        dataSet.setData(valuesTotalCatequistas);

        List<Color> colors = geradorCoresUtil.gerarCores(valuesTotalCatequistas.size());
        List<String> bgColors = new ArrayList<String>();
        
        for (Color color : colors) {
            String hex = "#"+Integer.toHexString(color.getRGB()).substring(2);
            bgColors.add(hex);
        }
        
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        data.setLabels(labels);

        donutModel.setData(data);

        return donutModel;
    }
}
