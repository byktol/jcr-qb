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

import java.util.List;

import com.byktol.jcr.qb.criteria.Criterion;
import com.byktol.jcr.qb.criteria.operators.LogicalOperator;

/**
 * Groups several {@link Criterion} into a single predicate.
 *
 * @since 1.0
 * @author Victor Alvarez
 */
public class Junction
  implements Criterion
{

  /** The operator used to concatenate the logical predicates. */
  private LogicalOperator operator;

  /** The list of statements/predicates being concatenated. */
  private List<Criterion> criterionList;

  /**
   * Constructor.
   *
   * @param op
   *          The operator for the logical operation.
   */
  public Junction(final LogicalOperator op)
  {
    this.operator = op;
  }

  /**
   * Adds a criterion to the list.
   *
   * @param criterion
   *            A new criterion added to the junction.
   * @return This for chaining.
   */
  public final Junction add(final Criterion criterion)
  {
    criterionList.add(criterion);

    return this;
  }

  /**
   * @return The list of criterion.
   */
  public List<Criterion> getCriterionList()
  {
    return criterionList;
  }

  /**
   * @return The operator to concatenate the junction statements.
   */
  public final LogicalOperator getOperator()
  {
    return operator;
  }

}
