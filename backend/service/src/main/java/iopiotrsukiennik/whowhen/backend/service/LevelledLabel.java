package iopiotrsukiennik.whowhen.backend.service;

/**
 * @author Piotr Sukiennik
 */
public class LevelledLabel {
    private String label;

    private Integer level;

    public LevelledLabel( String label, Integer level ) {
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
