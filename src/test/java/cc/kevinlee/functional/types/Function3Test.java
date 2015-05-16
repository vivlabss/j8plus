package cc.kevinlee.functional.types;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static cc.kevinlee.testosterone.Testosterone.test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

/**
 * @author Lee, Seong Hyun (Kevin)
 * @since 2015-05-16
 */
public class Function3Test {

  @Test
  public void testAndThen() throws Exception {

    final Function3<Integer, Integer, Integer, Integer> first = (i1, i2, i3) -> i1 + i2 + i3;
    final Function<Integer, String> second = i -> "Answer is " + i;

    final int input1 = 1;
    final int input2 = 2;
    final int input3 = 3;
    test("Function3.andThen(Function)", "f3.andThen(f) should apply f3.apply(p1, p2, p3) then f.apply(f3Result). \n" +
         "In other words, f3.andThen(f) == f(f3(p1, p2, p3))")
      .when(() ->
        first.andThen(second).apply(input1, input2, input3)
      )
      .then(actual ->
        assertThat(actual).isEqualTo(second.apply(first.apply(input1, input2, input3)))
      );
  }
}