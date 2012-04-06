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

/**
 * Negates another {@link Criterion}.
 *
 * @since 1.0
 * @author Victor Alvarez
 */
public class NotExpression
  implements Criterion
{

  /** The criterion being negated. */
  private final Criterion criterion;

  /**
   * Constructor.
   *
   * @param criterion
   *          The {@link Criterion} being negated in this expression.
   */
  public NotExpression(final Criterion criterion)
  {
    if (null == criterion) {
      throw new IllegalArgumentException("criterion cannot be null.");
    }

    this.criterion = criterion;
  }

  /**
   * @return The criterion being negated.
   */
  public final Criterion getCriterion()
  {
    return criterion;
  }
}
