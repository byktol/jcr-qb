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
import com.byktol.jcr.qb.criteria.operators.LogicalOperator;

/**
 * Logically binds together different {@link Criterion} for building queries.
 *
 * @see LogicalOperator
 *
 * @since 1.0
 * @author Victor Alvarez
 */
public class LogicalExpression
  implements Criterion
{

  /** Array of logically evaluated {@link Criterion}. */
  private Criterion[] criterion;

  /** The logical operator for evaluation the array of {@link Criterion}. */
  private LogicalOperator op;

  /**
   * Constructor.
   *
   * @param criterion
   *            Array of logically evaluated {@link Criterion}
   * @param op
   *            The logical operator for the evaluation
   */
  public LogicalExpression(
    final Criterion[] criterion,
    final LogicalOperator op)
  {

    if (0 >= criterion.length)
    {
      throw new IllegalArgumentException("criterion cannot be an empty array.");
    }

    if (null == op)
    {
      throw new IllegalArgumentException("op cannot be null.");
    }

    this.criterion = criterion;
    this.op = op;
  }

  /**
   * @return The array criterion being logically evaluated.
   */
  public final Criterion[] getCriterion()
  {
    return criterion;
  }

  /**
   * @return The operator being used for binding the restrictions.
   */
  public final LogicalOperator getOp()
  {
    return op;
  }

}