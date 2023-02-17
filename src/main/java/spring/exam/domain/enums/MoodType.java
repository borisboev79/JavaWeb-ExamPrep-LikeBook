package spring.exam.domain.enums;

import lombok.Getter;

@Getter
public enum MoodType {
    HAPPY("Happy"),
    SAD("Sad"),
    INSPIRED("Inspired");

   public final String label;

   MoodType(String label){
       this.label = label;
   }

}
