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

import java.util.HashMap;
import java.util.Map;

import com.byktol.jcr.qb.criteria.Criterion;
import com.byktol.jcr.qb.criteria.builder.Context;
import com.byktol.jcr.qb.criteria.builder.XPathCriterionBuilder;
import com.byktol.jcr.qb.criteria.expressions.ContainsExpression;
import com.byktol.jcr.qb.criteria.expressions.ExistExpression;
import com.byktol.jcr.qb.criteria.expressions.LikeExpression;
import com.byktol.jcr.qb.criteria.expressions.LogicalExpression;
import com.byktol.jcr.qb.criteria.expressions.NotExpression;
import com.byktol.jcr.qb.criteria.expressions.SimpleExpression;

/**
 * @since 1.0
 * @author Victor Alvarez
 */
public class JackrabbitContext
  implements Context
{
  private static final Map<Class<? extends Criterion>, XPathCriterionBuilder> map = new HashMap<Class<? extends Criterion>, XPathCriterionBuilder>() {
    /** Context.java */
    private static final long serialVersionUID = 1L;
    {
      put(SimpleExpression.class, new SimpleExpressionBuilder());
      put(LogicalExpression.class, new LogicalExpressionBuilder());
      put(ContainsExpression.class, new ContainsExpressionBuilder());
      put(NotExpression.class, new NotExpressionBuilder());
      put(LikeExpression.class, new LikeExpressionBuilder());
      put(ExistExpression.class, new ExistExpressionBuilder());
    }
  };

  /**
   * Filters the appropriate restriction type and invokes the builder methods.
   *
   * @param criterion
   *          An object inheriting from {@link Criterion}
   * @return The result of calling a builder method or an empty string
   */
  public final String build(final Criterion criterion) {
    final XPathCriterionBuilder builder = map.get(criterion.getClass());

    if (null == builder) {
      return "";
    }

    return builder.buildXPath(criterion, this);
  }
}
