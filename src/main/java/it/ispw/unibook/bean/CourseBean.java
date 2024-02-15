package it.ispw.unibook.bean;

public class CourseBean extends Bean {

    private final int code;
    private final String name;
    private final int startYear;
    private final int endYear;

    public CourseBean(int code) {
        this(code, null, 0, 0);
    }

    public CourseBean(int code, String name, int startYear, int endYear) {
        this.endYear = endYear;
        this.startYear = startYear;
        this.name = name;
        this.code = code;
    }

    public int getEndYear() {
        return endYear;
    }

    public int getStartYear() {
        return startYear;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return this.getName() + " - " + this.getStartYear() + "/" + this.getEndYear();
    }

}
