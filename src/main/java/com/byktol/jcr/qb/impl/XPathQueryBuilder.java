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
package com.byktol.jcr.qb.impl;

import java.util.LinkedList;

import javax.jcr.Session;

import com.byktol.jcr.qb.QueryBuilder;
import com.byktol.jcr.qb.QueryLanguage;
import com.byktol.jcr.qb.criteria.Criteria;
import com.byktol.jcr.qb.criteria.Criterion;
import com.byktol.jcr.qb.criteria.Order;
import com.byktol.jcr.qb.criteria.impl.XPathCriteria;

/**
 * A specific implementation of {@link QueryBuilder}. The purpose is to create
 * XPath related queries.
 *
 * @since 1.0
 * @author Victor Alvarez
 */
public class XPathQueryBuilder
  extends QueryBuilder
{

  public XPathQueryBuilder(final Session session)
  {
    super(session, QueryLanguage.XPATH);
  }

  @Override
  public Criteria createCriteria()
  {
    return new XPathCriteria(new LinkedList<Criterion>(),
      new LinkedList<Order>());
  }

}
