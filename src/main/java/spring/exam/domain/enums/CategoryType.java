package spring.exam.domain.enums;

import lombok.Getter;

@Getter
public enum CategoryType {
    DRINK("Drink"),
    FOOD("Food"),
    HOUSEHOLD("Household"),
    OTHER("Other");

   public final String label;

   CategoryType(String label){
       this.label = label;
   }



   // Food, Drink, Household, Other
}
