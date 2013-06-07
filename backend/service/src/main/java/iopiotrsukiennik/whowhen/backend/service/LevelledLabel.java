package iopiotrsukiennik.whowhen.backend.service;

/**
 * Created with IntelliJ IDEA.
 * User: Piotr
 * Date: 09.12.12
 * Time: 02:50
 * To change this template use File | Settings | File Templates.
 */
public class LevelledLabel {
    private String label;
    private Integer level;

    public LevelledLabel(String label, Integer level) {
        this.label = label;
        this.level = level;
    }

    public String getLabel() {
        return label;
    }

    public Integer getLevel() {
        return level;
    }
}
