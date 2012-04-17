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
package com.byktol.jcr.qb;

import java.security.InvalidParameterException;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.query.InvalidQueryException;
import javax.jcr.query.Query;
import javax.jcr.query.QueryResult;

import com.byktol.jcr.qb.criteria.Criteria;

/**
 * Abstract the nasty details of having to deal with JCR.
 *
 * @since 1.0
 * @author Victor Alvarez
 */
public abstract class QueryBuilder {

  private QueryLanguage language;
  private Session session;

  protected QueryBuilder(final Session session, final QueryLanguage lang) {
    if (session == null) {
      throw new InvalidParameterException("JCR Session cannot be null");
    }

    if (lang == null) {
      throw new InvalidParameterException("Query Language cannot be null");
    }

    this.session = session;
    this.language = lang;
  }

  /**
   * Creates a {@link Criteria} for defining the search parameters (criteria).
   *
   * @return a new {@link Criteria}
   */
  public abstract Criteria createCriteria();

  /**
   * @return the query language used to fetch results from JCR
   */
  protected String getQueryLanguage() {
    return language.toString();
  }

  /**
   * Executes the given criteria against the repository. The {@link Criteria} to
   * execute was created by the specific implementation of
   * {@link #createCriteria()}, so that both go together. Trying to execute a
   * {@link Criteria} with from another factory can only end in tears.
   *
   * @param criteria the parameters and filters to narrow the results down
   * @return a JCR {@link QueryResult} containing (what else?) the results
   *
   * @throws InvalidQueryException when the repository doesn't like the query
   * @throws RepositoryException when it's not your fault
   */
  public QueryResult execute(final Criteria criteria)
    throws InvalidQueryException, RepositoryException
  {
    return execute(criteria.buildQuery(), criteria.getLimit(), criteria.getOffset());
  }

  /**
   *
   * @param queryString
   * @param limit
   * @param offset
   * @return
   * @throws InvalidQueryException
   * @throws RepositoryException
   */
  public QueryResult execute(final String queryString, final long limit, final long offset)
    throws InvalidQueryException, RepositoryException
  {
    if (!session.isLive()) {
      throw new RepositoryException("JCR Session is not alive.");
    }

    final Query query = session.getWorkspace().getQueryManager().createQuery(queryString, getQueryLanguage());

    if (limit > 0) {
      query.setLimit(limit);
      query.setOffset(offset);
    }

    return query.execute();
  }

}
