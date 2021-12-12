package com.huawei.pattern.builer;
import lombok.Data;
/**
 * @Author：胡灯
 * @Date：2021-12-12 9:17
 * @Description：<描述>
 */
@Data
public class Course
{
   private String name;
   private String ppt;
   private String note;
   private String homeWork;
   private String video;
   public static class Builder
   {
      private final Course course = new Course();
      public Builder addName(String name)
      {
         course.setName(name);
         return this;
      }
      public Builder addPpt(String ppt)
      {
         course.setPpt(ppt);
         return this;
      }
      public Builder addNode(String note)
      {
         course.setNote(note);
         return this;
      }
      public Builder addHomeWord(String homeWork)
      {
         course.setHomeWork(homeWork);
         return this;
      }
      public Builder addVideo(String video)
      {
         course.setVideo(video);
         return this;
      }
      public Course build()
      {
         return course;
      }
   }

}
