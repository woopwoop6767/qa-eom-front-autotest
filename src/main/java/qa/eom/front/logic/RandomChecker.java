package qa.eom.front.logic;

import com.codeborne.selenide.ElementsCollection;

public interface RandomChecker {


    default boolean isOptionsOrderDifferent (String[] arrayOne, String[] arrayTwo) {
        for (int i = 0; i < arrayOne.length; i++) {
            if (!arrayOne[i].equals(arrayTwo[i])) {
                return true;
            }
        }
        return false;
    }
}
