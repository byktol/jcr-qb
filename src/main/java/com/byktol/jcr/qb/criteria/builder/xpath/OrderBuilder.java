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

import java.util.LinkedList;
import java.util.List;

import com.byktol.jcr.qb.criteria.Order;
import com.byktol.jcr.qb.criteria.builder.utils.XPathUtils;

/**
 * @since 1.0
 * @author Victor Alvarez
 */
public class OrderBuilder
{

  /**
   * Builds a {@link List} of {@link Order} as a {@link List} of {@link String}
   * in order for them to be appended together.
   *
   * @param orders
   *          The {@link Order}s that are going to be appended to the query
   * @return A {@link List} of {@link String} to be appended to the query
   */
  public final List<String> build(final List<Order> orders)
  {
    final List<String> list = new LinkedList<String>();

    for (Order o : orders)
    {

      list.add(String.format("%s %s",
        XPathUtils.prependSymbol(o.getPropertyName()), o.getDirection())
      );

    }

    return list;
  }
}
