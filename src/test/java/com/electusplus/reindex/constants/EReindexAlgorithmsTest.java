package com.electusplus.reindex.constants;

import org.junit.Test;

import static com.electusplus.reindex.constants.EReindexAlgorithms.getByValue;
import static org.junit.Assert.assertEquals;

public class EReindexAlgorithmsTest {

    @Test
    public void getVarFromUITest() {
        final EReindexAlgorithms ALGORITHM = EReindexAlgorithms.TIME_ORIENTED;
        final String expectedResult = "Time oriented";
        assertEquals(expectedResult, ALGORITHM.getVarForUI());
    }

    @Test
    public void getByValueTest() {
        final String ALGORITHM_NAME_FOR_UI = "Time oriented";
        final String expectedResult = "TIME_ORIENTED";
        assertEquals(expectedResult, getByValue(ALGORITHM_NAME_FOR_UI).toString());
    }
}