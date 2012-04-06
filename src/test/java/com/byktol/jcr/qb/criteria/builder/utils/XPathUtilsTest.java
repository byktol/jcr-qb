/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.byktol.jcr.qb.criteria.builder.utils;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.apache.jackrabbit.util.ISO8601;
import org.junit.Test;

import com.byktol.jcr.qb.criteria.builder.utils.XPathUtils;

/**
 * Unit tests for {@link XPathUtilsTest}.
 *
 * @since 1.0
 * @author Victor Alvarez
 */
public class XPathUtilsTest
{

  /**
   * Tests the method {@link XPathUtils#encodePath(String)}.
   */
  @Test
  public final void testEncodePath()
  {
    // Null gets you an empty string.
    assertEquals("", XPathUtils.encodePath(null));

    // Empty string is an empty string
    String path = "";
    assertEquals(path, XPathUtils.encodePath(path));

    path = "/";
    assertEquals(path, XPathUtils.encodePath(path));

    path = "/jcr:root/content/company/demo/some/blog/name/and/title";
    assertEquals(path, XPathUtils.encodePath(path));

    path = "/jcr:root/2010";
    assertEquals("/jcr:root/_x0032_010", XPathUtils.encodePath(path));

    path = "/123/node/node/path";
    assertEquals("/_x0031_23/node/node/path", XPathUtils.encodePath(path));

    // Because we can
    path = "/path[@123 = 'Hello']";
    assertEquals(path, XPathUtils.encodePath(path));
  }

  /**
   * Tests the method {@link XPathUtils#prependSymbol(String)} with a null.
   * <strong>The method is not able to handle nulls.</strong>
   */
  @Test(expected = NullPointerException.class)
  public final void testPrependSymbolWithNull()
  {
    assertEquals("@", XPathUtils.prependSymbol(null));
  }

  /**
   * Tests the method {@link XPathUtils#prependSymbol(String)}.
   */
  @Test
  public final void testPrependSymbol()
  {
    String propertyName = "";
    assertEquals("@", XPathUtils.prependSymbol(propertyName));

    propertyName = "@something";
    assertEquals(propertyName, XPathUtils.prependSymbol(propertyName));

    propertyName = "propName";
    assertEquals("@propName", XPathUtils.prependSymbol(propertyName));
  }

  /**
   * Tests the method {@link XPathUtils#typedValue(Object)} with a String.
   */
  @Test
  public final void testTypedValueWithString()
  {
    String value = "";
    assertEquals("''", XPathUtils.typedValue(value));

    value = "She said 'Hello'";
    assertEquals("'She said ''Hello'''", XPathUtils.typedValue(value));

    value = "She said \"Hello\"";
    assertEquals("'She said \"\"Hello\"\"'", XPathUtils.typedValue(value));
  }

  /**
   * Tests the method {@link XPathUtils#typedValue(Object)} with a Calendar.
   */
  @Test
  public final void testTypedValueWithCalendar()
  {
    final Calendar cal = Calendar.getInstance();
    cal.set(2099, Calendar.AUGUST, 29, 0, 1);

    final String result =
      String.format("xs:dateTime('%s')", ISO8601.format(cal));
    assertEquals(result, XPathUtils.typedValue(cal));
  }

  /**
   * Tests the method {@link XPathUtils#typedValue(Object)} with numbers.
   */
  @Test
  public final void testTypedValueWithNumbers()
  {
    // An int
    Number number = 1;
    assertEquals("1", XPathUtils.typedValue(number));

    // A long
    number = 2L;
    assertEquals("2", XPathUtils.typedValue(number));

    // A float
    number = 2.0f;
    assertEquals("2.0", XPathUtils.typedValue(number));

    // A double
    number = 2.0;
    assertEquals("2.0", XPathUtils.typedValue(number));
  }

  /**
   * Tests the method {@link XPathUtils#typedValue(Object)} with a null.
   * <strong>The method is not able to handle nulls.</strong>
   */
  @Test(expected = NullPointerException.class)
  public final void testTypedValueWithNull()
  {
    assertEquals("", XPathUtils.typedValue(null));
  }

}
