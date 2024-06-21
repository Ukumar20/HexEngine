package com.example.base;

import com.example.App;
import com.example.engine.enums.Response;
import com.example.model.Tuple;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BaseTest {

    private static TestBot red;

    private static TestBot blue;

    public static void setup(){
        red = new TestBot();
        blue = new TestBot();
        App.registerBot(red, "RED");
        App.registerBot(blue, "BLUE");
        App.launch();
        suspend(2000);
    }

    protected void triggerAutoPlay(String colour, boolean swapRule, Object[][] tuples) throws Exception {
        App.autoPlayAgain(colour, swapRule);
        executeTuples(tuples);
    }

    private void executeTuples(Object[][] tuples) throws Exception {
        for (Object[] tupleArr : tuples){
            Object[] params = Arrays.copyOfRange(tupleArr, 3, tupleArr.length);
            Tuple tuple = convertToTuple(tupleArr[0], tupleArr[1], tupleArr[2], params);
            boolean flag = executeAndAssertTuple(tuple);
            assertTrue(flag);
        }
    }

    private Tuple convertToTuple(Object expectedResponse, Object botName, Object methodName, Object... params) throws NoSuchMethodException {
        Response response = Response.valueOf((String) expectedResponse);
        String botNameString = (String) botName;
        String methodNameString = (String) methodName;
        TestBot testBot = null;
        Class<?>[] args = IntStream.range(0, params.length).mapToObj(i -> int.class).toArray(Class<?>[]::new);
        Method method = TestBot.class.getMethod(methodNameString, args);
        if(botNameString.equalsIgnoreCase("RED")){
            testBot = red;
        }
        else if (botNameString.equalsIgnoreCase("BLUE")){
            testBot = blue;
        }
        return new Tuple(response, testBot, method, params);
    }

    private boolean executeAndAssertTuple(Tuple tuple) throws InvocationTargetException, IllegalAccessException {
        TestBot testBot = tuple.getTestBot();
        Method method = tuple.getMethod();
        Response expectedResponse = tuple.getExpectedResponse();
        Object[] params = new Integer[tuple.getParams().length];
        for(int i=0; i<tuple.getParams().length; i++){
            params[i] = tuple.getParams()[i];
        }
        Response actualResponse = (Response) method.invoke(testBot, params);
        return actualResponse == expectedResponse;
    }

    private static void suspend(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
