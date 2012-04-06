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

/**
 * Defines the parameters and filters to narrow a query down, i.e. what goes
 * into a query. Its purpose is to build the query, but not execute it.
 *
 * @see Restrictions
 * @see Order
 *
 * @since 1.0
 * @author Victor Alvarez
 */
public interface Criteria
{

  /**
   * Adds a single {@link Criterion} to the query.
   *
   * @param criterion The criterion being added to the query
   * @return The current {@link Criteria} to enable chaining
   */
  Criteria add(Criterion criterion);

  /**
   * Adds a single {@link Order} to the query.
   *
   * @param order The order being added to the query
   * @return The current {@link Criteria} to enable chaining
   */
  Criteria addOrder(Order order);

  /**
   * @param path The path used to narrow down the search in the hierarchy
   * @return The current {@link Criteria} to enable chaining
   */
  Criteria setPath(String path);

  /**
   * @param nodeType The node type use to narrow the search, e.g. cq:PageContent
   * @return The current {@link Criteria} to enable chaining
   */
  Criteria setNodeType(String nodeType);

  /**
   * Builds the query based on {@link Restrictions} and {@link Order}.
   *
   * @return The query built for the specific implementation
   */
  String buildQuery();

  /**
   * @param limit The number of results to return for the query
   * @return The current {@link Criteria} to enable chaining
   */
  Criteria setLimit(long limit);

  /**
   * @return The number of results to return
   */
  long getLimit();

  /**
   * @param offset The number indicating where to start the fetch
   * @return The current {@link Criteria} to enable chaining
   */
  Criteria setOffset(long offset);

  /**
   * @return The offset number
   */
  long getOffset();
}
