package qa.eom.front.logic;

import org.apache.commons.lang3.RandomStringUtils;

public interface GenerateText {


    default String generateMobilePhone() {
        return new StringBuilder().append(9).append(RandomStringUtils.randomNumeric(9)).toString();
    }

    default String generateRandomText(int count) {
        return new StringBuilder().append(RandomStringUtils.randomAlphabetic(count)).toString();
    }

    default String generateRandomNumber(int count) {
        return new StringBuilder().append(RandomStringUtils.randomNumeric(count)).toString();
    }

}
