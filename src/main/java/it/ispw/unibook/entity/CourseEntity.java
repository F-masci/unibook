package it.ispw.unibook.entity;

import org.jetbrains.annotations.NotNull;

public class CourseEntity {

    private final int code;
    private final String name;
    private final int startYear;
    private final int endYear;

    public CourseEntity(int code, @NotNull String name, int startYear, int endYear) {
        this.code = code;
        this.name = name;
        this.startYear = startYear;
        this.endYear = endYear;
    }

    public int getCode() {
        return code;
    }
    public String getName() {
        return name;
    }
    public int getStartYear() {
        return startYear;
    }
    public int getEndYear() {
        return endYear;
    }

}
