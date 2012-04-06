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
import com.byktol.jcr.qb.criteria.Prop;
import com.google.common.base.Strings;

/**
 * Abstract implementation of the {@link Criteria} interface. Defines common
 * methods to be used by child classes and convenience methods for building the
 * query string.
 *
 * @since 1.0
 * @author Victor Alvarez
 */
public abstract class CriteriaImpl
  implements Criteria, Prop
{
  private final List<Criterion> criterion;
  private final List<Order> orders;
  private String nodeType;
  private String path;
  private long limit;
  private long offset;

  /**
   * The constructor is only accessible to the child classes and is meant to set
   * the specific implementation of {@link List} for both criterion and orders.
   * I'm not assuming a specific implementation here, but leave that to whoever
   * construct the child class. Both lists may or may not be empty.
   *
   * @param criterionList
   *          The {@link List} implementation for filtering the results.
   * @param orderList
   *          The {@link List} implementation for orders.
   */
  protected CriteriaImpl(
    final List<Criterion> criterionList,
    final List<Order> orderList)
  {
    this.criterion = criterionList;
    this.orders = orderList;

    this.path = this.nodeType = "";
  }

  /**
   * Adds a {@link Criterion} to the list used to refine the query. This method
   * may be called multiple times, and the restrictions would then be added to
   * the list. The child class is responsible for dealing with this.
   *
   * @param criterion
   *          The restriction being added to the list
   * @return The current {@link Criteria} to enable chaining
   */
  @Override
  public Criteria add(final Criterion criterion)
  {
    getCriterion().add(criterion);

    return this;
  }

  /**
   * Adds an {@link Order} to the list used to sort the results. This method may
   * be called multiple times, which adds complexity to the ordering clause so
   * pay careful attention to what you do.
   *
   * @param order
   *          The order being added to the list
   * @return The current {@link Criteria} to enable chaining
   */
  @Override
  public Criteria addOrder(final Order order)
  {
    getOrders().add(order);

    return this;
  }

  /**
   * Adds a path to the query which is used to narrow the search hierarchy in
   * the repository. This path should start with a slash (/) and not have a
   * trailing slash, e.g. /content/some/path
   *
   * @param path
   *          The path to start the query on
   * @return The current {@link Criteria} to enable chaining
   */
  @Override
  public Criteria setPath(final String path)
  {
    this.path = Strings.nullToEmpty(path);

    return this;
  }

  @Override
  public String getPath()
  {
    return path;
  }

  /**
   * @return the limit set for the query results
   */
  @Override
  public long getLimit()
  {
    return limit;
  }

  /**
   * @param limit
   *          the maximum number of query results
   * @return the current {@link Criteria} to enable chaining
   */
  @Override
  public Criteria setLimit(final long limit)
  {
    this.limit = limit;

    return this;
  }

  /**
   * @return the offset
   */
  @Override
  public long getOffset()
  {
    return offset;
  }

  /**
   * @param offset
   *          the number of results to skip
   * @return the current {@link Criteria} to enable chaining
   */
  @Override
  public Criteria setOffset(final long offset)
  {
    this.offset = offset;

    return this;
  }

  @Override
  public List<Criterion> getCriterion()
  {
    return criterion;
  }

  @Override
  public List<Order> getOrders()
  {
    return orders;
  }

  @Override
  public String getNodeType()
  {
    return nodeType;
  }

  /**
   * Sets the node type being queried, i.e. you may want to query only the nodes
   * whose node type is cq:PageContent or nt:base. Because the are several types
   * I haven't defined a list or hard coded one.
   *
   * @param nodeType
   *          the node type used to narrow the results of possible nodes
   * @return the current {@link Criteria} to enable chaining
   */
  @Override
  public Criteria setNodeType(final String nodeType)
  {
    this.nodeType = Strings.nullToEmpty(nodeType);

    return this;
  }

}
