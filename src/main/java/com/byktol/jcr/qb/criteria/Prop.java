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

import java.util.List;

/**
 * @since 1.0
 * @author Victor Alvarez
 */
public interface Prop {

  /**
   * @return The list of {@link Criterion} used in building the query
   */
  List<Criterion> getCriterion();

  /**
   * @return The list of {@link Order} used in building the query
   */
  List<Order> getOrders();

  /**
   * @return The name of the node being queried.
   */
  String getNodeName();

  /**
   * @return The node type being used in building the query or an empty string
   */
  String getNodeType();

  /**
   * @return The path used to narrow the search or an empty string
   */
  String getPath();
}
