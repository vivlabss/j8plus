package cc.kevinlee.functional;

import static cc.kevinlee.functional.Functions.*;
import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import org.junit.Test;

public class FunctionsTest {
  @Test
  public void testIsNull() {
    /* given */
    @SuppressWarnings("unchecked")
    final Predicate<String> expected = (Predicate<String>) Functions.IS_NULL;

    /* when */
    final Predicate<String> actual = Functions.isNull();

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testIsNull2() {
    /* given */
    final String string = null;

    /* when */
    final boolean actual = Functions.isNull()
        .test(string);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public void testIsNull3() {
    /* given */
    final String string = "Some string";

    /* when */
    final boolean actual = Functions.isNull()
        .test(string);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public void testIsNull4() {
    /* given */
    final String string = "";

    /* when */
    final boolean actual = Functions.isNull()
        .test(string);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public void testIsNotNull() {
    /* given */
    @SuppressWarnings("unchecked")
    final Predicate<String> expected = (Predicate<String>) Functions.IS_NOT_NULL;

    /* when */
    final Predicate<String> actual = Functions.isNotNull();

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void testIsNotNull2() {
    /* given */
    final String string = null;

    /* when */
    final boolean actual = Functions.isNotNull()
        .test(string);

    /* then */
    assertThat(actual).isFalse();
  }

  @Test
  public void testIsNotNull3() {
    /* given */
    final String string = "Some string";

    /* when */
    final boolean actual = Functions.isNotNull()
        .test(string);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public void testIsNotNull4() {
    /* given */
    final String string = "";

    /* when */
    final boolean actual = Functions.isNotNull()
        .test(string);

    /* then */
    assertThat(actual).isTrue();
  }

  @Test
  public final void testNot() {
    /* given */
    final List<String> words = Arrays.asList("Hello", "Kevin", "Hi", "How are you?", "Good morning", "This", "That", "do", "do ", "where",
        "which", "does", "not");
    final List<String> expected = Arrays.asList("Hi", "Good morning", "That", "do ", "where");
    final String text = "Hello Kevin. How are you? This will get all the words which this text does not contain.";

    /* when */
    final List<String> actual = words.stream()
        .filter(not(text::contains))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testNot2() {
    /* given */
    final List<Integer> numbers = Arrays.asList(-11, 4, -3, 2, 0, 3, -7, 10, -8, 32, 99, 57, 100, -88);
    final List<Integer> expected = Arrays.asList(4, 2, 0, 3, 10, 32, 99, 57, 100);

    final Predicate<Integer> negativeNumber = i -> i < 0;

    /* when */
    final List<Integer> actual = numbers.stream()
        .filter(not(negativeNumber))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  private static boolean positiveNumber(final int number) {
    return number > 0;
  }

  @Test
  public final void testNot3() {
    /* given */
    final List<Integer> numbers = Arrays.asList(-11, 4, -3, 2, 0, 3, -7, 10, -8, 32, 99, 57, 100, -88);
    final List<Integer> expected = Arrays.asList(-11, -3, 0, -7, -8, -88);

    /* when */
    final List<Integer> actual = numbers.stream()
        .filter(not(FunctionsTest::positiveNumber))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testReversed() {
    /* given */
    final List<Integer> numbers = Arrays.asList(4, 2, 0, 3, 10, 32, 99, 57, 100);
    final List<Integer> expected = Arrays.asList(100, 99, 57, 32, 10, 4, 3, 2, 0);

    /* when */
    final List<Integer> actual = numbers.stream()
        .sorted(reversed(Integer::compareTo))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public final void testReversed2() {
    /* given */
    final List<Integer> numbers = Arrays.asList(4, 2, 0, 3, 10, 32, 99, 57, 100);
    final List<Integer> expected = Arrays.asList(0, 2, 3, 4, 10, 32, 57, 99, 100);

    final Comparator<Integer> descendingOrder = (i1, i2) -> i2.compareTo(i1);

    /* when */
    final List<Integer> actual = numbers.stream()
        .sorted(reversed(descendingOrder))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

  private int ascendingOrder(final int i1, final int i2) {
    return Integer.compare(i1, i2);
  }

  @Test
  public final void testReversed3() {
    /* given */
    final List<Integer> numbers = Arrays.asList(4, 2, 0, 3, 10, 32, 99, 57, 100);
    final List<Integer> expected = Arrays.asList(100, 99, 57, 32, 10, 4, 3, 2, 0);

    /* when */
    final List<Integer> actual = numbers.stream()
        .sorted(reversed(this::ascendingOrder))
        .collect(toList());

    /* then */
    assertThat(actual).isEqualTo(expected);
  }

}