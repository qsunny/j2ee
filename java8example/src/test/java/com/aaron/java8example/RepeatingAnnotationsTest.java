package com.aaron.java8example;

import org.junit.Test;

public class RepeatingAnnotationsTest {

    @Test
    public void testRepeatAnnotation() {
        for( RepeatingAnnotations.Filter filter: RepeatingAnnotations.Filterable.class.getAnnotationsByType( RepeatingAnnotations.Filter.class ) ) {
            System.out.println( filter.value() );
        }
    }
}
