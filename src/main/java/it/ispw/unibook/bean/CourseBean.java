package it.ispw.unibook.bean;

public class CourseBean {

    private final int code;
    private final String name;
    private final int startYear;
    private final int endYear;

    public CourseBean(int code, String name, int startYear, int endYear) {
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

    @Override
    public String toString() {
        return this.getName() + " - " + this.getStartYear() + "/" + this.getEndYear();
    }

}
