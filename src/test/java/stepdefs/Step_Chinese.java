package stepdefs;

import io.cucumber.java.zh_cn.*;
import org.testng.annotations.Test;

import static org.junit.Assert.*;

class 今天是星期五吗 {
    static String 是不是星期五(String today) {
        return "星期五".equals(today) ? "是的" : "不是";
    }
}

public class Step_Chinese{
    private String today;
    private String actualAnswer;

    @假如("今天是{string}")
    public void 今天是(String today) {
        this.today = today;
    }

    @当("我问今天是不是星期五")
    public void 我问今天是不是星期五() {
        actualAnswer = 今天是星期五吗.是不是星期五(today);
    }

    @那么("我应该被告知{string}")
    public void 我应该被告知(String expectedAnswer) {
        assertEquals(expectedAnswer, actualAnswer);
    }
}