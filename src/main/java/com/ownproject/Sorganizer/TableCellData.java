package com.ownproject.Sorganizer;

public class TableCellData {
    private boolean isScheduled;
    private String textOfCell;
    private Integer colspan;

    public TableCellData(boolean isScheduled, String textOfCell, Integer colspan) {
        this.isScheduled = isScheduled;
        this.textOfCell = textOfCell;
        this.colspan = colspan;
    }

    public TableCellData() {
    }

    public boolean isScheduled() {
        return isScheduled;
    }

    public void setScheduled(boolean scheduled) {
        isScheduled = scheduled;
    }

    public String getTextOfCell() {
        return textOfCell;
    }

    public void setTextOfCell(String textOfCell) {
        this.textOfCell = textOfCell;
    }

    public Integer getColspan() {
        return colspan;
    }

    public void setColspan(Integer colspan) {
        this.colspan = colspan;
    }
}
