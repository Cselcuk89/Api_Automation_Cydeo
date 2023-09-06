package day5;

import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;

public class P01_HamcrestMatchersIntro {
    @Test
    public void numbers(){
        //junit 5 assertEquals() method
        Assertions.assertEquals(9,6+3);
        //assertions with hamcrest matchers class
        assertThat(6+3,is(9));
        assertThat(6+3,is(equalTo(9)));
        assertThat(6+3,equalTo(9));
        /**
          is(someValue)
          is(equalTo(someValue))
          equalTo(someValue)
         All of them are same in terms of assertions
         */
        assertThat(5+5,not(9));
        assertThat(5+5,is(not(9)));
        assertThat(5+5,is(not(equalTo(9))));

        assertThat(5+6,is(greaterThan(10)));
        assertThat(5+7,is(lessThanOrEqualTo(12)));
    }
    @Test
    public void testStrings(){
        String msg = "Api is fun";
        assertThat(msg,is(("Api is fun")));
        assertThat(msg,is(equalTo("Api is fun")));
        assertThat(msg,startsWith("Api"));
        assertThat(msg,endsWith("fun"));
        assertThat(msg,containsString("is"));

    }
    @Test
    public void testCollections(){
        List<Integer> numberList = Arrays.asList(3,5,2,77,44,76);
        //how to check the size of elements
        assertThat(numberList,hasSize(6));
        //how to check 77 is in the collection
        assertThat(numberList,hasItem(77));
        //how to check 44 and 76 is in the list
        assertThat(numberList,hasItems(44,76));

        //loop through each of the element and make sure they are matching with the matchers inside the every item
        assertThat(numberList,everyItem(greaterThan(1)));
        //check if you have values and their position is correct
        assertThat(numberList,containsInRelativeOrder(3,5,44));
        //check if you have all the values and position might be different
        assertThat(numberList,containsInAnyOrder(3,5,2,77,44,76));

    }

}
