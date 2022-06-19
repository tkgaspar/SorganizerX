package com.ownproject.Sorganizer;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class TableFormData {
    private LocalDate dateOfTable;
    private Map<String, List<TableCellData>> timeTable;



    public TableFormData(){};

    public TableFormData(LocalDate dateOfTable,  Map<String,List<TableCellData>> timeTable) {
        this.dateOfTable = dateOfTable;
        this.timeTable = timeTable;

    }

    public LocalDate getDateOfTable() {
        return dateOfTable;
    }

    public void setDateOfTable(LocalDate dateOfTable) {
        this.dateOfTable = dateOfTable;
    }

    public Map<String, List<TableCellData>> getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(Map<String, List<TableCellData>> timeTable) {
        this.timeTable = timeTable;
    }

}

