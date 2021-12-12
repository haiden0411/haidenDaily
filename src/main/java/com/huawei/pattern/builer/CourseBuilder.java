package com.huawei.pattern.builer;
/**
 * @Author：胡灯
 * @Date：2021-12-12 9:32
 * @Description：<描述>
 */
public class CourseBuilder{

    private final Course course = new Course();

    public CourseBuilder addName(String name)
    {
        course.setName(name);
        return this;
    }
    public CourseBuilder addPpt(String ppt)
    {
        course.setPpt(ppt);
        return this;
    }
    public CourseBuilder addNode(String note)
    {
        course.setNote(note);
        return this;
    }
    public CourseBuilder addHomeWord(String homeWork)
    {
        course.setHomeWork(homeWork);
        return this;
    }
    public CourseBuilder addVideo(String video)
    {
        course.setVideo(video);
        return this;
    }
    public Course build()
    {
        return course;
    }
}