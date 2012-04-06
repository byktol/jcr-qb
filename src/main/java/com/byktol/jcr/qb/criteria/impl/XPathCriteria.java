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

import java.util.List;

import com.byktol.jcr.qb.criteria.Criteria;
import com.byktol.jcr.qb.criteria.Criterion;
import com.byktol.jcr.qb.criteria.Order;
import com.byktol.jcr.qb.criteria.builder.impl.XPathBuilder;
import com.byktol.jcr.qb.criteria.builder.utils.XPathUtils;

/**
 * A {@link Criteria} that builds XPath queries.
 *
 * @since 1.0
 * @author Victor Alvarez
 */
public class XPathCriteria
  extends CriteriaImpl
  implements Criteria
{

  /**
   * Constructor.
   *
   * @param restrictions
   *            The list of {@link Criterion} to create the criteria.
   *            An empty list is preferred.
   * @param orders
   *            The list of {@link Order} to order the results.
   *            An empty list is preferred.
   */
  public XPathCriteria(
    final List<Criterion> restrictions,
    final List<Order> orders)
  {
    super(restrictions, orders);
  }

  @Override
  public String buildQuery()
  {
    final XPathBuilder builder = new XPathBuilder(this);

    return builder.buildQuery();
  }

  /**
   * Override parent setPath() method because there's some peculiarity when
   * setting a path where nodes start with numbers.
   *
   * @see XPathUtils#encodePath(String)
   * @param path
   *          The path to start the search at.
   * @return this
   *          For chaining.
   */
  @Override
  public Criteria setPath(final String path)
  {
    super.setPath(XPathUtils.encodePath(path));

    return this;
  }

}
