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
package com.byktol.jcr.qb.criteria.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.apache.jackrabbit.util.ISO8601;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.byktol.jcr.qb.criteria.Criterion;
import com.byktol.jcr.qb.criteria.Order;
import com.byktol.jcr.qb.criteria.Restrictions;
import com.byktol.jcr.qb.criteria.impl.XPathCriteria;

/**
 * Unit tests for {@link XPathCriteria} and whatever related classes can be
 * usefully tested with it.
 *
 * @since 1.0
 * @author Victor Alvarez
 */
public class XPathCriteriaBuilderTest
{

  /** The criteria being tested. */
  private XPathCriteria criteria;

  /** The list of criterion to instantiate the criteria with. */
  private List<Criterion> restrictions;

  /** The list of orders to instantiate the criteria with. */
  private List<Order> orders;

  /**
   * Executes at the beginning of every test case. Initiates variables.
   */
  @Before
  public final void setUp()
  {
    restrictions = new LinkedList<Criterion>();
    orders = new LinkedList<Order>();
    criteria = new XPathCriteria(restrictions, orders);
  }

  /**
   * Executes at the end of every test case.
   */
  @After
  public final void tearDown()
  {
    restrictions.clear();
    orders.clear();
  }

  /**
   * Tests the creation of a query with no parameters. This query is valid
   * although is not recommended for consumption.
   */
  @Test
  public final void testDefaultQuery()
  {
    assertEquals("/jcr:root//element(*)", criteria.buildQuery());
  }

  /**
   * Tests the creation of a query where certain property exists.
   */
  @Test
  public final void testExists()
  {
    criteria.add(Restrictions.exists("title"));
    assertEquals("/jcr:root//element(*)[@title]", criteria.buildQuery());

    criteria.add(Restrictions.exists("created"));
    assertEquals("/jcr:root//element(*)[@title and @created]",
      criteria.buildQuery());
  }

  /**
   * Tests an expression that can be negated.
   */
  @Test
  public final void testNot()
  {
    criteria.add(Restrictions.not(Restrictions.exists("title")));
    assertEquals("/jcr:root//element(*)[not(@title)]", criteria.buildQuery());

    restrictions.clear(); // I hate to do this, but I have to clean it.
    criteria.add(Restrictions.not(Restrictions.and(
      Restrictions.exists("title"),
      Restrictions.eq("slideshow", "slideshow.json"))));

    assertEquals("/jcr:root//element(*)"
      + "[not((@title and @slideshow = 'slideshow.json'))]",
      criteria.buildQuery());
  }

  /**
   * Tests an And with only one expression.
   */
  @Test
  public final void testAnd()
  {
    criteria.add(Restrictions.and(Restrictions.eq("property", "value")));

    assertEquals("/jcr:root//element(*)[(@property = 'value')]",
      criteria.buildQuery());
  }

  /**
   * Tests method {@link XPathCriteria#setPath(String)}.
   */
  @Test
  public final void testSetPath()
  {
    // By default, you get an empty string
    assertNotNull(criteria.getPath());
    assertTrue(criteria.getPath().isEmpty());

    // The path set must remain unaltered.
    final String path = "/content/nymag";
    criteria.setPath(path);
    assertEquals(path, criteria.getPath());
    assertEquals(String.format("/jcr:root%s//element(*)", path),
      criteria.buildQuery());

    // Set null, don't get null
    criteria.setPath(null);
    assertNotNull(criteria.getPath());
    assertTrue(criteria.getPath().isEmpty());
  }

  /**
   * Tests method {@link XPathCriteria#setPath(String)} with a node starting
   * with numbers.
   */
  @Test
  public final void testSetPathWithNumbers()
  {
    criteria.setPath("/content/2011");
    assertNotNull(criteria.getPath());
    assertEquals("/content/_x0032_011", criteria.getPath());

    criteria.setPath("/2");
    assertEquals("/_x0032_", criteria.getPath());

    criteria.setPath("/321/123");
    assertEquals("/_x0033_21/_x0031_23", criteria.getPath());

    criteria.setPath("/node/12node/some");
    assertEquals("/node/_x0031_2node/some", criteria.getPath());
  }

  /**
   * Tests method {@link XPathCriteria#setNodeType(String)}.
   */
  @Test
  public final void testNodeType()
  {
    assertNotNull(criteria.getNodeType());
    assertTrue(criteria.getNodeType().isEmpty());

    final String nodeType = "sling:someType";
    criteria.setNodeType(nodeType);
    assertSame(nodeType, criteria.getNodeType());
    assertEquals(String.format("/jcr:root//element(*,%s)", nodeType),
      criteria.buildQuery());

    criteria.setNodeType(null);
    assertNotNull(criteria.getNodeType());
    assertTrue(criteria.getNodeType().isEmpty());
  }

  /**
   * Tests that a property with a boolean is rendered correctly.
   */
  @Test
  public final void testRestrictionWithBoolean()
  {
    final boolean b1 = true;
    final String prop1 = "prop1";

    final boolean b2 = false;
    final String prop2 = "prop2";

    // I'm just going to test both in the same query
    criteria.add(Restrictions.eq(prop1, b1));
    criteria.add(Restrictions.eq(prop2, b2));
    assertEquals(String.format("/jcr:root//element(*)[@%s = %b and @%s = %b]",
      prop1, b1, prop2, b2), criteria.buildQuery());
  }

  /**
   * Tests that a property with an integer number is rendered correctly.
   */
  @Test
  public final void testRestrictionWithInteger()
  {
    final Number number = 123;
    final String prop = "prop1";

    criteria.add(Restrictions.eq(prop, number));
    assertEquals(
      String.format("/jcr:root//element(*)[@%s = %d]", prop, number),
      criteria.buildQuery());
  }

  /**
   * Test that a property with a long integer number is rendered correctly.
   */
  @Test
  public final void testRestrictionWithLong()
  {
    final Number number = 678L;
    final String prop = "prop2";

    criteria.add(Restrictions.eq(prop, number));
    assertEquals(
      String.format("/jcr:root//element(*)[@%s = %d]", prop, number),
      criteria.buildQuery());
  }

  /**
   * Tests that a property with a float number is rendered correctly.
   */
  @Test
  public final void testRestrictionWithDecimal()
  {
    final Number number = 9.0;
    final String prop = "prop3";

    criteria.add(Restrictions.eq(prop, number));
    assertEquals(
      String.format("/jcr:root//element(*)[@%s = %.1f]", prop, number),
      criteria.buildQuery());
  }

  /**
   * Tests that a property set with a date-time is rendered in ISO8601 format
   * and with the correct JackRabbit/CRX dateTime function.
   */
  @Test
  public final void testRestrictionWithCalendar()
  {
    final String prop = "published";
    final Calendar cal = Calendar.getInstance();
    cal.set(2010, 10, 10, 10, 10);

    criteria.add(Restrictions.eq(prop, cal));
    assertEquals(String.format(
      "/jcr:root//element(*)[@%s = xs:dateTime('%s')]", prop,
      ISO8601.format(cal)), criteria.buildQuery());
  }

  /**
   * Test method for {@link XPathCriteria#add(Restrictions)}.
   */
  @Test
  public final void testAddRestrictions()
  {
    criteria.add(Restrictions.eq("property", "value"));
    assertFalse(restrictions.isEmpty());
    assertEquals(1, restrictions.size());

    // let's see the query
    assertEquals("/jcr:root//element(*)[@property = 'value']",
      criteria.buildQuery());

    // let's add another restriction and test the query
    criteria.add(Restrictions.eq("property2", ""));
    // both predicates are appended by 'and' by default
    assertEquals("/jcr:root//element(*)[@property = 'value' and "
      + "@property2 = '']", criteria.buildQuery());
  }

  /**
   * Test method for {@link XPathCriteria#add(Restrictions)} when said
   * restriction is an AND.
   */
  @Test
  public final void testAddAndRestrictions()
  {
    criteria.add(Restrictions.and(Restrictions.eq("prop", "val"),
      Restrictions.notEq("prop2", "val2")));

    assertEquals("/jcr:root//element(*)[(@prop = 'val' and @prop2 != 'val2')]",
      criteria.buildQuery());
  }

  /**
   * Test method for {@link XPathCriteria#add(Restrictions)} when said
   * restriction is an OR.
   */
  @Test
  public final void testAddOrRestrictions()
  {
    criteria.add(Restrictions.or(Restrictions.gt("prop", "val"),
      Restrictions.lt("prop2", "val2"), Restrictions.ge("prop3", "val3"),
      Restrictions.le("prop4", "val4")));

    assertEquals("/jcr:root//element(*)[(@prop > 'val' or @prop2 < 'val2' or "
      + "@prop3 >= 'val3' or @prop4 <= 'val4')]", criteria.buildQuery());
  }

  /**
   * {@link XPathCriteria#toString()} only knows the existence of specific
   * {@link Criterion} descendants. Since {@link Criterion} is not
   * supported, then the resulting XPath query string should be valid and not
   * contain any parameters.
   */
  @Test
  public final void testBuildUnsupportedCriterion()
  {
    final Criterion mockRestriction = mock(Criterion.class);
    criteria.add(mockRestriction);

    assertEquals("/jcr:root//element(*)", criteria.buildQuery());
  }

  /**
   * Test method {@link XPathCriteria#addOrder(Order)}.
   */
  @Test
  public final void testAddOrder()
  {
    criteria.addOrder(Order.asc("property"));
    assertFalse(orders.isEmpty());
    assertEquals(1, orders.size());

    // and the query is
    final String expected =
      "/jcr:root//element(*) order by @property ascending";
    assertEquals(expected, criteria.buildQuery());

    // adding another order gets us
    criteria.addOrder(Order.desc("prop2"));
    assertEquals(expected + ", @prop2 descending", criteria.buildQuery());
  }

}
