package it.ispw.unibook.entity;

public class CourseEntity {

    private final int code;
    private final String name;

    public CourseEntity(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
