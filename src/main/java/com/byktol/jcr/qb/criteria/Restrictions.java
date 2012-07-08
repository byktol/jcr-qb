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
package com.byktol.jcr.qb.criteria;

import com.byktol.jcr.qb.criteria.expressions.ContainsExpression;
import com.byktol.jcr.qb.criteria.expressions.ExistExpression;
import com.byktol.jcr.qb.criteria.expressions.Junction;
import com.byktol.jcr.qb.criteria.expressions.LikeExpression;
import com.byktol.jcr.qb.criteria.expressions.LogicalExpression;
import com.byktol.jcr.qb.criteria.expressions.NotExpression;
import com.byktol.jcr.qb.criteria.expressions.SimpleExpression;
import com.byktol.jcr.qb.criteria.operators.ComparisonOperator;
import com.byktol.jcr.qb.criteria.operators.LogicalOperator;

/**
 * Utility class for adding restrictions/constraints to a {@link Criteria}.
 *
 * @since 1.0
 * @author Victor Alvarez
 */
public final class Restrictions
{

  /** Utility class. Private constructor. */
  private Restrictions()
  {
    // nothing here
  }

  /**
   * Creates a criterion that verifies the existence of a property.
   *
   * @param property
   *          The name of the property to verify its existence
   * @return A new {@link Criterion} verifying a property
   */
  public static Criterion exists(final String property)
  {
    return new ExistExpression(property);
  }

  /**
   * Creates a criterion that negates another restriction.
   *
   * @param criterion
   *          The criterion to be negated
   * @return A new {@link Criterion} negating another one
   */
  public static Criterion not(final Criterion criterion)
  {
    return new NotExpression(criterion);
  }

  /**
   * Creates a criterion that binds other criteria with AND.
   *
   * @see #conjunction() For another way to accomplish the same thing.
   *
   * @param criteria
   *          The criteria being bound by the logical operator
   * @return A new {@link Criterion} with the AND expression
   */
  public static Criterion and(final Criterion... criteria)
  {
    return new LogicalExpression(criteria, LogicalOperator.AND);
  }

  /**
   * Creates a criterion that binds other criteria with OR.
   *
   * @see #disjunction() For another way to accomplish the same thing.
   *
   * @param criteria
   *          The criteria being bound by the logical operator
   * @return A new {@link Criterion} with the OR expression
   */
  public static Criterion or(final Criterion... criteria)
  {
    return new LogicalExpression(criteria, LogicalOperator.OR);
  }

  /**
   * Creates a criterion where a property should equals a value.
   * <strong>Remember:</strong> The property shouldn't have the at sign (@)
   * since it's only XPath-compatible.
   *
   * @param property
   *          The name of the property being evaluated
   * @param value
   *          The value against the property is being evaluated
   * @return A new {@link Criterion} with the EQUALS expression
   */
  public static Criterion eq(final String property, final Object value)
  {
    return new SimpleExpression(property, value, ComparisonOperator.EQUALS);
  }

  /**
   * Creates a criterion where a property should be greater than a value.
   * <strong>Remember:</strong> The property shouldn't have the at sign (@)
   * since it's only XPath-compatible.
   *
   * @param property
   *          The name of the property being evaluated
   * @param value
   *          The value against the property is being evaluated
   * @return A new {@link Criterion} with the GREATER THAN expression
   */
  public static Criterion gt(final String property, final Object value)
  {
    return new SimpleExpression(property, value,
      ComparisonOperator.GREATER_THAN);
  }

  /**
   * Creates a criterion where a property should be greater than or equals to
   * a value. <strong>Remember:</strong> The property shouldn't have the at sign
   * (@) since it's only XPath-compatible.
   *
   * @param property
   *          The name of the property being evaluated
   * @param value
   *          The value against the property is being evaluated
   * @return A new {@link Criterion} with the GREATER THAN OR EQUALS
   *         expression
   */
  public static Criterion ge(final String property, final Object value)
  {
    return new SimpleExpression(property, value,
      ComparisonOperator.GREATER_THAN_OR_EQUALS);
  }

  /**
   * Creates a criterion where a property should be less than a value.
   * <strong>Remember:</strong> The property shouldn't have the at sign (@)
   * since it's only XPath-compatible.
   *
   * @param property
   *          The name of the property being evaluated
   * @param value
   *          The value against the property is being evaluated
   * @return A new {@link Criterion} with the LESS THAN expression
   */
  public static Criterion lt(final String property, final Object value)
  {
    return new SimpleExpression(property, value, ComparisonOperator.LESS_THAN);
  }

  /**
   * Creates a criterion where a property should be less than or equals to a
   * value. <strong>Remember:</strong> The property shouldn't have the at sign
   * (@) since it's only XPath-compatible.
   *
   * @param property
   *          The name of the property being evaluated
   * @param value
   *          The value against the property is being evaluated
   * @return A new {@link Criterion} with the LESS THAN OR EQUALS expression
   */
  public static Criterion le(final String property, final Object value)
  {
    return new SimpleExpression(property, value,
      ComparisonOperator.LESS_THAN_OR_EQUALS);
  }

  /**
   * Creates a criterion where a property should not equals a value.
   * <strong>Remember:</strong> The property shouldn't have the at sign (@)
   * since it's only XPath-compatible.
   *
   * @param property
   *          The name of the property being evaluated
   * @param value
   *          The value against the property is being evaluated
   * @return A new {@link Criterion} with the NOT EQUALS expression
   */
  public static Criterion notEq(final String property, final Object value)
  {
    return new SimpleExpression(property, value, ComparisonOperator.NOT_EQUALS);
  }

  /**
   * Creates a criterion where a property should contain certain String value.
   * <strong>Remember:</strong> The property shouldn't have the at sign (@)
   * since it's only XPath-compatible.
   *
   * @param property
   *          The name of the property containing the given value
   * @param value
   *          The value the property should contain
   * @return A new {@link Criterion} with the CONTAINS expression
   */
  public static Criterion contains(final String property, final String value)
  {
    return new ContainsExpression(property, value);
  }

  /**
   * Creates a criterion where a property's value and the given parameter
   * should be alike. <strong>Remember:</strong> The property shouldn't have the
   * at sign (@) since it's only XPath-compatible.
   *
   * @param property
   *          The name of the property's whose value is alike
   * @param value
   *          The value the property should be alike to
   * @return A new {@link Criterion} with the LIKE expression
   */
  public static Criterion like(final String property, final String value)
  {
    return new LikeExpression(property, value);
  }

  /**
   * Creates a group capable of adding multiple constraints in a single
   * conjunction.
   *
   * @return A new conjunction.
   */
  public static Junction conjunction()
  {
    return new Junction(LogicalOperator.AND);
  }

  /**
   * Creates a group capable of adding multiple constraints in a single
   * disjunction.
   *
   * @return A new disjunction.
   */
  public static Junction disjunction()
  {
    return new Junction(LogicalOperator.OR);
  }
}
