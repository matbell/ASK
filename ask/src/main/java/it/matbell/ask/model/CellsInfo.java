package it.matbell.ask.model;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CellsInfo implements Loggable {

    private List<CellInfo> cells = new ArrayList<>();

    public CellsInfo(List<CellInfo> cells){ this.cells = cells; }

    public void addCell(CellInfo cellInfo){ this.cells.add(cellInfo); }

    @Override
    public String getDataToLog() {
        List<String> data = new ArrayList<>();

        for(CellInfo cellInfo : cells) data.add(cellInfo.getDataToLog());

        return StringUtils.join(data, "\t");
    }
}
