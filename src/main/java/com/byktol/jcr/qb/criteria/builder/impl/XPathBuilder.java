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
package com.byktol.jcr.qb.criteria.builder.impl;

import java.util.Iterator;

import com.byktol.jcr.qb.criteria.Criterion;
import com.byktol.jcr.qb.criteria.Prop;
import com.byktol.jcr.qb.criteria.Restrictions;
import com.byktol.jcr.qb.criteria.builder.Context;
import com.byktol.jcr.qb.criteria.builder.xpath.JackrabbitContext;
import com.byktol.jcr.qb.criteria.builder.xpath.OrderBuilder;
import com.byktol.jcr.qb.criteria.operators.LogicalOperator;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;

/**
 * Specific builder specializing on XPath queries.
 *
 * @since 1.0
 * @author Victor Alvarez
 */
public class XPathBuilder
  extends AbstractBuilder
{

  private Prop criteria;

  public XPathBuilder(final Prop criteria) {
    this.criteria = criteria;
  }

  private Context context = new JackrabbitContext();

  /**
   * Builds an XPath query string based on the defined constraints.
   *
   * @see Restrictions
   * @return a valid XPath query string.
   */
  @Override
  public String buildQuery() {
    final StringBuilder xpath = new StringBuilder();

    xpath.append(
      String.format(
        "/jcr:root%s//element(%s)",
        criteria.getPath(),
        buildElementTest()
      )
    );

    if (criteria.getCriterion().size() > 0) {

      final StringBuilder sb = new StringBuilder();
      final Iterator<Criterion> iterator = criteria.getCriterion().iterator();
      sb.append(context.build(iterator.next()));
      while (iterator.hasNext()) {
        sb.append(' ');
        sb.append(LogicalOperator.AND);
        sb.append(' ');
        sb.append(context.build(iterator.next()));
      }

      if (sb.length() > 0) {
        xpath.append('[');
        xpath.append(sb);
        xpath.append(']');
      }

    } // end of if

    if (criteria.getOrders().size() > 0) {
      xpath.append(" order by ");
      xpath.append(
        Joiner.on(", ").skipNulls().join(
          new OrderBuilder().build(criteria.getOrders())
        )
      );
    }

    return xpath.toString();
  }

  /**
   * In XPath, the element test is defined by "element(nodeName, nodeType)".
   * This gives us up to four possible combinations based onf what is specified:
   * <ol>
   *  <li>nodeName and nodeType not defined: element(*)</li>
   *  <li>nodeName defined, and nodeType not defined: element(nodeName)</li>
   *  <li>nodeName not defined, and nodeType defined: element(*,nodeType)</li>
   *  <li>nodeName and nodeType defined: element(nodeName,nodeType)</li>
   * </ol>
   *
   * @return The XPath element test.
   */
  protected final String buildElementTest() {
    String nt = criteria.getNodeName();

    if (Strings.isNullOrEmpty(criteria.getNodeName()))
    {
      nt = "*";
    }

    if (!Strings.isNullOrEmpty(criteria.getNodeType()))
    {
      nt += "," + criteria.getNodeType();
    }

    return nt;
  }

}
