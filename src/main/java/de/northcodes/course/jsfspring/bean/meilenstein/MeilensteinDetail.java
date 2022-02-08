package de.northcodes.course.jsfspring.bean.meilenstein;

import de.northcodes.course.jsfspring.model.Meilenstein;
import de.northcodes.course.jsfspring.model.Uebung;
import de.northcodes.course.jsfspring.service.MeilensteinService;
import de.northcodes.course.jsfspring.service.UebungService;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
@ManagedBean
@ViewScoped
public class MeilensteinDetail implements Serializable {

    private long uebungId;
    private Uebung uebung;
    private List<Meilenstein> meilensteinList;
    private LineChartModel lineChartModel;

    @Autowired
    private MeilensteinService meilensteinService;

    @Autowired
    private UebungService uebungService;

    public void onLoad() {
        this.meilensteinList = this.meilensteinService.getMeilensteinListByUebungId(uebungId);
        this.uebung = this.uebungService.getUebungById(this.uebungId);

        this.createData();
    }

    public void createData() {
        this.lineChartModel = new LineChartModel();
        LineChartDataSet dataSet = new LineChartDataSet();
        ChartData data = new ChartData();

        List<Object> valueList = new ArrayList<>();
        List<String> labelList = new ArrayList<>();

        for (Meilenstein meilenstein : this.meilensteinList) {
            valueList.add(meilenstein.getGewicht());
            labelList.add(new SimpleDateFormat("dd.MM.yyyy").format(meilenstein.getDatum()));
        }

        dataSet.setData(valueList);
        dataSet.setLabel("Gewicht");

        data.addChartDataSet(dataSet);
        data.setLabels(labelList);

        lineChartModel.setData(data);
    }

    public long getUebungId() {
        return uebungId;
    }

    public void setUebungId(long uebungId) {
        this.uebungId = uebungId;
    }

    public List<Meilenstein> getMeilensteinList() {
        return meilensteinList;
    }

    public void setMeilensteinList(List<Meilenstein> meilensteinList) {
        this.meilensteinList = meilensteinList;
    }

    public LineChartModel getLineChartModel() {
        return lineChartModel;
    }

    public void setLineChartModel(LineChartModel lineChartModel) {
        this.lineChartModel = lineChartModel;
    }

    public Uebung getUebung() {
        return uebung;
    }

    public void setUebung(Uebung uebung) {
        this.uebung = uebung;
    }
}
