package spring.exam.domain.enums;

import lombok.Getter;

@Getter
public enum MoodType {
    HAPPY("Happy"),
    INSPIRED("Inspired"),
    SAD("Sad");


   public final String label;

   MoodType(String label){
       this.label = label;
   }

}
