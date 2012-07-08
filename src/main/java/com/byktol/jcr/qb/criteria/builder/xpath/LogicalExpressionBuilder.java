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

import java.util.ArrayList;
import java.util.List;

import com.byktol.jcr.qb.criteria.Criterion;
import com.byktol.jcr.qb.criteria.builder.Context;
import com.byktol.jcr.qb.criteria.builder.XPathCriterionBuilder;
import com.byktol.jcr.qb.criteria.expressions.LogicalExpression;
import com.google.common.base.Joiner;

/**
 * Builds a single {@link LogicalExpression} based on the specific query type.
 *
 * @since 1.0
 * @author Victor Alvarez
 */
public class LogicalExpressionBuilder
  implements XPathCriterionBuilder
{

  @Override
  public final String buildXPath(final Criterion criterion, final Context context)
  {
    final LogicalExpression le = (LogicalExpression) criterion;

    final Criterion[] criterionArray = le.getCriterion();

    final List<String> builtrestrictions =
      new ArrayList<String>(criterionArray.length);

    final Joiner joiner = Joiner.on(
      String.format(" %s ", le.getOp().toString())
    ).skipNulls();

    for (int i = 0; i < criterionArray.length; i++)
    {

      builtrestrictions.add(context.build(criterionArray[i]));

    } // end of for

    return String.format("(%s)", joiner.join(builtrestrictions));
  }
}
