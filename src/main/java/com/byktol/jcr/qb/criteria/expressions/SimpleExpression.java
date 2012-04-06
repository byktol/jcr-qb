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
package com.byktol.jcr.qb.criteria.expressions;

import com.byktol.jcr.qb.criteria.Criterion;
import com.byktol.jcr.qb.criteria.operators.ComparisonOperator;
import com.google.common.base.Strings;

/**
 * A simple comparison expression for building queries.
 *
 * @see ComparisonOperator
 *
 * @since 1.0
 * @author Victor Alvarez
 */
public class SimpleExpression
  implements Criterion
{

  /** The object of the evaluation. */
  private final Object value;

  /** The name of the property being evaluated. */
  private final String propertyName;

  /** The operator for the evaluation. */
  private final ComparisonOperator op;

  /**
   * Constructor.
   *
   * @param propertyName
   *          The name of the property being evaluated
   * @param value
   *          The object of the evaluation
   * @param op
   *          The operator for the evaluation
   */
  public SimpleExpression(
    final String propertyName,
    final Object value,
    final ComparisonOperator op)
  {
    if (Strings.isNullOrEmpty(propertyName))
    {
      throw new IllegalArgumentException("propertyName cannot be empty");
    }

    if (null == op)
    {
      throw new IllegalArgumentException("op cannot be null");
    }

    this.propertyName = propertyName;
    this.value = value;
    this.op = op;
  }

  /**
   * @return the value of the property being evaluated
   */
  public final Object getValue()
  {
    return value;
  }

  /**
   * @return the property name being compared
   */
  public final String getPropertyName()
  {
    return propertyName;
  }

  /**
   * @return the operator in used
   */
  public final ComparisonOperator getOp()
  {
    return op;
  }

}

