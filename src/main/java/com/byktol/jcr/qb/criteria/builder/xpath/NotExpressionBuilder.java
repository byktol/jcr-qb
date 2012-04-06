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
package com.byktol.jcr.qb.criteria.builder.xpath;

import com.byktol.jcr.qb.criteria.Criterion;
import com.byktol.jcr.qb.criteria.builder.Context;
import com.byktol.jcr.qb.criteria.builder.XPathCriterionBuilder;
import com.byktol.jcr.qb.criteria.expressions.NotExpression;

/**
 * Builds a single {@link NotExpression} based on the specific query type.
 *
 * @since 1.0
 * @author Victor Alvarez
 */
public class NotExpressionBuilder
  implements XPathCriterionBuilder
{

  /*
   * (non-Javadoc)
   * @see XPathCriterionBuilder#buildXPath(Criterion, Context)
   */
  @Override
  public final String buildXPath(
    final Criterion criterion,
    final Context context)
  {
    final NotExpression ne = (NotExpression) criterion;
    return String.format(
      "not(%s)",
      context.build(ne.getCriterion())
    );
  }

}
